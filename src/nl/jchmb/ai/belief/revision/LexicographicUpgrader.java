package nl.jchmb.ai.belief.revision;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import nl.jchmb.ai.epistemology.Model;
import nl.jchmb.ai.epistemology.assertion.Assertion;

/**
 * Lexicographic upgrade operator. Promotes all worlds w1 where p to be preferred over all worlds w2 where not-p.
 * Within those two separate sets, the old ordering remains.
 * 
 * @param <A>
 * @param <W>
 */
public class LexicographicUpgrader<A, W> implements Revisor<A, W> {
	private Assertion<A, W> assertion;
	
	public LexicographicUpgrader(Assertion<A, W> assertion) {
		this.assertion = assertion;
	}
	
	@Override
	public Comparator<W> revise(Model<A, W> model, A agent, Comparator<W> preference) {
		Set<W> preferred = new HashSet<W>();
		for (W world : model.getAccessibility().iterate(model, agent, model.getActualWorld())) {
			if (model.models(world, assertion)) {
				preferred.add(world);
			}
		}
		return new LexicographicUpgradedComparator<W>(preferred, preference);
	}
	
	private class LexicographicUpgradedComparator<W> implements Comparator<W> {
		private Set<W> preferred;
		private Comparator<W> baseComparator;
		
		public LexicographicUpgradedComparator(Set<W> preferred, Comparator<W> baseComparator) {
			this.preferred = preferred;
			this.baseComparator = baseComparator;
		}
		
		@Override
		public int compare(W w1, W w2) {
			boolean c1 = preferred.contains(w1);
			boolean c2 = preferred.contains(w2);
			
			/* If w1 is preferred and w2 is not, then w1 is better than w2. */
			if (c1 && !c2) {
				return 1;
				
			/* If w2 is preferred and w1 is not, then w2 is better than w1. */
			} else if (!c1 && c2) {
				return -1;
				
			/* Otherwise compare with the old ordering. */
			} else {
				return baseComparator.compare(w1, w2);
			}
		}
		
	}
}
