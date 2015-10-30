package nl.jchmb.ai.belief;

import java.util.Comparator;
import java.util.List;

public class Preference<A, W> {
	private List<W> worlds;
	private Comparator<W> preferenceComparator;
	
	public Preference(List<W> worlds, Comparator<W> comparator) {
		this.worlds = worlds;
		this.preferenceComparator = comparator;
	}
	
	
}
