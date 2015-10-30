package nl.jchmb.ai.epistemology.examples.muddychildren;

import nl.jchmb.ai.epistemology.Model;
import nl.jchmb.ai.epistemology.assertion.Assertion;

public class MuddyAssertion implements Assertion<Integer, Boolean[]> {
	
	private int agent;
	
	public MuddyAssertion(int agent) {
		this.agent = agent;
	}

	@Override
	public boolean resolve(Model<Integer, Boolean[]> model, Boolean[] world) {
		return world[agent];
	}

}
