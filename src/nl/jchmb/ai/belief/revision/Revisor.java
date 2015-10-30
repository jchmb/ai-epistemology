package nl.jchmb.ai.belief.revision;

import java.util.Comparator;

import nl.jchmb.ai.epistemology.Model;

/**
 * Revises the preference ordering of a given agent and returns a new preference ordering.
 * 
 * @param <A>
 * @param <W>
 */
public interface Revisor<A, W> {
	public Comparator<W> revise(Model<A, W> model, A agent, Comparator<W> preference);
}
