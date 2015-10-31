package nl.jchmb.ai.epistemology.assertion;

import java.util.ArrayList;
import java.util.Collection;

import nl.jchmb.ai.epistemology.Model;

public class Disjunction<A, W> implements Assertion<A, W> {

	private Collection<Assertion<A, W>> assertions;
	
	public Disjunction(Collection<Assertion<A, W>> assertions) {
		this.assertions = assertions;
	}
	
	public Disjunction(Assertion<A, W> f1, Assertion<A, W> f2) {
		assertions = new ArrayList<Assertion<A, W>>();
		assertions.add(f1);
		assertions.add(f2);
	}
	
	@Override
	public boolean resolve(Model<A, W> model, W world) {
		for (Assertion<A, W> assertion : assertions) {
			if (model.models(world, assertion)) {
				return true;
			}
		}
		return false;
	}

}
