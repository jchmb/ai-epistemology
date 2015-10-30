package nl.jchmb.ai.epistemology.assertion;

import nl.jchmb.ai.epistemology.Model;

public class Negation<A, W> implements Assertion<A, W> {

	private Assertion<A, W> f;
	
	public Negation(Assertion<A, W> f) {
		this.f = f;
	}
	
	@Override
	public boolean resolve(Model<A, W> model, W world) {
		return !f.resolve(model, world);
	}
	
}
