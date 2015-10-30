package nl.jchmb.ai.epistemology.assertion;

import nl.jchmb.ai.epistemology.Model;

public class Conjunction<A, W> implements Assertion<A, W> {

	private Assertion<A, W> f1;
	private Assertion<A, W> f2;
	
	public Conjunction(Assertion<A, W> f1, Assertion<A, W> f2) {
		this.f1 = f1;
	}
	
	@Override
	public boolean resolve(Model<A, W> model, W world) {
		return f1.resolve(model, world) && f2.resolve(model, world);
	}

}
