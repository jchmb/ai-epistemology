package nl.jchmb.ai.belief.revision;

import java.util.Comparator;

import nl.jchmb.ai.epistemology.Model;

public class NullRevisor<A, W> implements Revisor<A, W> {

	@Override
	public Comparator<W> revise(Model<A, W> model, A agent,
			Comparator<W> preference) {
		return preference;
	}

}
