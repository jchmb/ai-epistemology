package nl.jchmb.ai.belief;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import nl.jchmb.ai.belief.revision.Revisor;
import nl.jchmb.ai.epistemology.Accessibility;
import nl.jchmb.ai.epistemology.Model;

public class PreferenceModel<A, W> extends Model<A, W> {
	private Map<A, Comparator<W>> preferences;
	
	public PreferenceModel(Collection<A> agents, Collection<W> universe,
			Accessibility<A, W> accessibility, W actualWorld, Map<A, Comparator<W>> preferences) {
		super(agents, universe, actualWorld, accessibility);
		this.preferences = preferences;
	}
	
	public Map<A, Comparator<W>> getPreferences() {
		return preferences;
	}
	
	public Comparator<W> getPreferenceComparator(A agent) {
		return preferences.get(agent);
	}

	public void revise(A agent, Revisor<A, W> revisor) {
		preferences.put(agent, revisor.revise(this, agent, preferences.get(agent)));
	}
}
