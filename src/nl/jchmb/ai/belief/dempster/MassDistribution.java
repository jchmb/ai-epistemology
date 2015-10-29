package nl.jchmb.ai.belief.dempster;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * MassDistribution class. Represents the mass function m, with the following properties:
 * 1. m({}) = 0 				(i.e., the empty set can have no mass)
 * 2. sum_{U in W} = 1			(i.e., the total mass of all allocations must be equal to 1)
 *
 * 
 * @param <W>
 */
public class MassDistribution<W> {
	private Map<Set<W>, Double> allocations;
	
	public MassDistribution(Map<Set<W>, Double> allocations) {
		this.allocations = allocations;
	}
	
	/**
	 * Get the total mass of this mass function. Must be 1, or normalization becomes necessary.
	 * @return
	 */
	public double getTotalMass() {
		double total = 0.0d;
		for (Map.Entry<Set<W>, Double> allocation : allocations.entrySet()) {
			total += allocation.getValue();
		}
		return total;
	}
	
	/**
	 * Normalize this mass function to ensure that the sum of all masses is equal to 1.
	 */
	public void normalize() {
		double totalMass = getTotalMass();
		if (totalMass == 0.0d) {
			return;
		}
		double modifier = 1 / totalMass;
		Set<Set<W>> keySet = allocations.keySet();
		for (Set<W> worlds : keySet) {
			allocations.put(worlds, allocations.get(worlds) * modifier);
		}
	}
	
	/**
	 * Get the belief in the set of possible worlds.
	 * 
	 * @param worlds
	 * @return
	 */
	public double getBelief(Set<W> worlds) {
		double belief = 0.0d;
		for (Map.Entry<Set<W>, Double> allocation : allocations.entrySet()) {
			if (worlds.contains(allocation.getKey())) {
				belief += allocation.getValue();
			}
		}
		return belief;
	}
	
	/**
	 * Get the plausibility of the set of possible worlds.
	 * 
	 * @param worlds
	 * @return
	 */
	public double getPlausibility(Set<W> worlds) {
		double plausibility = 0.0d;
		for (Map.Entry<Set<W>, Double> allocation : allocations.entrySet()) {
			Set<W> intersection = new HashSet<W>(allocation.getKey());
			intersection.retainAll(worlds);
			if (!intersection.isEmpty()) {
				plausibility += allocation.getValue();
			}
		}
		return plausibility;
	}
	
	/**
	 * Compute the confidence in the set of possible worlds with the given self-confidence parameter.
	 * The self-confidence parameter must be between 0 and 1.
	 * 
	 * @param worlds
	 * @param selfConfidence
	 * @return
	 */
	public double getConfidence(Set<W> worlds, double selfConfidence) {
		double belief = getBelief(worlds);
		double plausibility = getPlausibility(worlds);
		return (1 - selfConfidence) * belief + selfConfidence * plausibility;
	}
	
	/**
	 * Combine this mass distribution m_1 with another mass distribution m_2.
	 * This yields a new mass distribution m_3 = m_1 (+) m_2.
	 * 
	 * @param distribution
	 * @return
	 */
	public MassDistribution<W> combine(MassDistribution<W> distribution) {
		return null; // TODO
	}
}
