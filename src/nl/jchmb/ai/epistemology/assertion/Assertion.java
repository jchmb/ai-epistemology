package nl.jchmb.ai.epistemology.assertion;

import nl.jchmb.ai.epistemology.Model;

/**
 * The Assertion interface represents an assertion about a world given a model.
 * It is up to the implementing class to define the assertion. 
 * 
 * @param <A>
 * @param <W>
 */
public interface Assertion<A, W> {
	public boolean resolve(Model<A, W> model, W world);
}
