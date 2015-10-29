package nl.jchmb.ai.epistemology.logic;

import nl.jchmb.ai.epistemology.Model;

/**
 * Knowledge operator.
 * Meaning: given (M, w), an agent i knows the truth of an assertion x iff
 * the assertion is true in every world considered possible by agent i.
 * 
 * @param <A>
 * @param <W>
 */
public class Knowledge<A, W> implements Assertion<A, W> {

	private A agent;
	private Assertion<A, W> f;
	
	public Knowledge(A agent, Assertion<A, W> f) {
		this.agent = agent;
		this.f = f;
	}
	
	@Override
	public boolean resolve(Model<A, W> model, W world) {
		for (W possibleWorld : model.getAccessibility().iterate(agent, world)) {
			if (!f.resolve(model, possibleWorld)) {
				return false;
			}
		}
		return true;
	}

}
