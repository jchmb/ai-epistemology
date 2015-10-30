package nl.jchmb.ai.epistemology.examples.muddychildren;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.jchmb.ai.epistemology.Accessibility;
import nl.jchmb.ai.epistemology.Model;

public class MuddyChildrenAccessibility implements Accessibility<Integer, Boolean[]> {

	@Override
	public Iterable<Boolean[]> iterate(Model<Integer, Boolean[]> model, Integer agent, Boolean[] world) {
		List<Boolean[]> worlds = new ArrayList<Boolean[]>();
		for (Boolean[] w : model.getUniverse()) {
			boolean keep = true;
			for (int i = 0; i < w.length; i++) {
				if (agent != i && w[i].booleanValue() != world[i].booleanValue()) {
					keep = false;
				}
			}
			
			if (keep) {
				worlds.add(w);
			}
		}
		return worlds;
	}
	
}
