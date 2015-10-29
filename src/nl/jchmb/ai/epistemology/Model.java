package nl.jchmb.ai.epistemology;

import java.util.Set;

import nl.jchmb.ai.epistemology.logic.Assertion;

public class Model<A, W> {
	private Set<A> agents;
	private Set<W> universe;
	private Accessibility<A, W> accessibility;
	
	public Model(Set<A> agents, Set<W> universe, Accessibility<A, W> accessibility) {
		this.agents = agents;
		this.universe = universe;
		this.accessibility = accessibility;
	}
	
	public Set<A> getAgents() {
		return agents;
	}
	
	public Set<W> getUniverse() {
		return universe;
	}
	
	public Accessibility<A, W> getAccessibility() {
		return accessibility;
	}
	
	public boolean models(W world, Assertion<A, W> formula) {
		return formula.resolve(this, world);
	}
	
	
}
