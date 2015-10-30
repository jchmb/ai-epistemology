package nl.jchmb.ai.belief.assertion;

import java.util.Comparator;

import nl.jchmb.ai.belief.PreferenceModel;
import nl.jchmb.ai.epistemology.Model;
import nl.jchmb.ai.epistemology.assertion.Assertion;

/**
 * Belief operator. An agent i believes an assertion p iff there is no world where not-p
 * that is preferred as much or over some world where p.
 * 
 * Of course, if there no world where not-p, then the agent knows the assertion to be true.
 *
 * @param <A>
 * @param <W>
 */
public class BeliefAssertion<A, W> implements Assertion<A, W> {

	private A agent;
	private Assertion<A, W> f;
	
	public BeliefAssertion(A agent, Assertion<A, W> assertion) {
		this.agent = agent;
		this.f = assertion;
	}
	
	@Override
	public boolean resolve(Model<A, W> model, W world) {
		if (model instanceof PreferenceModel) {
			PreferenceModel<A, W> preferenceModel = (PreferenceModel<A, W>) model;
			Comparator<W> preference = preferenceModel.getPreferenceComparator(agent);
			for (W w1 : preferenceModel.getAccessibility().iterate(model, agent, world)) {
				/* We want w1 to be the worlds where the assertion is not true. */
				if (f.resolve(model, w1)) {
					continue;
				}
				for (W w2 : preferenceModel.getAccessibility().iterate(model, agent, world)) {
					/* We want w2 to be the worlds where the assertion is true. */
					if (!f.resolve(model, w2)) {
						continue;
					}
					
					/* 
					 * So, if w1 is preferred as much as or over w2, 
					 * then the agent cannot believe that the assertion is true. 
					 * */
					if (preference.compare(w1, w2) >= 0) {
						return false;
					}
				}
			}
			/* 
			 * However, if there is no w1 preferred as much as or over w2, 
			 * then the assertion is believed.
			 */
			return true;
		} else {
			return false;
		}
	}

}
