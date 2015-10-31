package nl.jchmb.ai.epistemology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import nl.jchmb.ai.epistemology.assertion.Assertion;

public class Model<A, W> {
	private Collection<A> agents;
	private Collection<W> universe;
	private W actualWorld;
	private Accessibility<A, W> accessibility;
	
	public Model(Collection<A> agents, Collection<W> universe, W actualWorld, Accessibility<A, W> accessibility) {
		this.agents = agents;
		this.universe = universe;
		this.actualWorld = actualWorld;
		this.accessibility = accessibility;
	}
	
	public W getActualWorld() {
		return actualWorld;
	}
	
	public Collection<A> getAgents() {
		return agents;
	}
	
	public Collection<W> getUniverse() {
		return universe;
	}
	
	public Accessibility<A, W> getAccessibility() {
		return accessibility;
	}
	
	public boolean models(W world, Assertion<A, W> formula) {
		return formula.resolve(this, world);
	}
	
	public void announce(Assertion<A, W> assertion) {
		Collection<W> newUniverse = new ArrayList<W>();
		for (W world : universe) {
			if (models(world, assertion)) {
				newUniverse.add(world);
			}
		}
		universe = newUniverse;
	}
}
