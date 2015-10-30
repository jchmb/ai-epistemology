package nl.jchmb.ai.epistemology;

public interface Accessibility<A, W> {
	/**
	 * Iterate over the worlds accessible to the given agent from the given world.
	 * 
	 * @param model
	 * @param agent
	 * @param world
	 * @return
	 */
	public Iterable<W> iterate(Model<A, W> model, A agent, W world);
}
