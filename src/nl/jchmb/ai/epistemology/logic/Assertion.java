package nl.jchmb.ai.epistemology.logic;

import nl.jchmb.ai.epistemology.Model;

/**
 * The Assertion class represents an assertion about a world given a model.
 * 
 * @param <A>
 * @param <W>
 */
public interface Assertion<A, W> {
	public boolean resolve(Model<A, W> model, W world);
}
