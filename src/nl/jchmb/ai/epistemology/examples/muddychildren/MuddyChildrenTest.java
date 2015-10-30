package nl.jchmb.ai.epistemology.examples.muddychildren;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import nl.jchmb.ai.epistemology.Model;
import nl.jchmb.ai.epistemology.assertion.KnowledgeAssertion;

public class MuddyChildrenTest {
	public static void main(String[] args) {
		int n = 4;
		int k = 2;
		
		Set<Integer> agents = new HashSet<Integer>();
		for (int i = 0; i < n; i++) {
			agents.add(i);
		}
		ArrayList<Boolean[]> worlds = new ArrayList<Boolean[]>();
		int combinations = (int) Math.pow(2, n);
		for (int i = 0; i < combinations; i++) {
			int count = 0;
			Boolean[] states = new Boolean[n];
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 0x1) == 0x1) {
					count++;
					states[j] = true;
				} else {
					states[j] = false;
				}
			}
			if (count >= 1) {
				worlds.add(states);
			}
			
		}
		Boolean[] actualWorld = worlds.get(2);
		Model<Integer, Boolean[]> model = new Model<Integer, Boolean[]>(
				agents,
				worlds,
				actualWorld,
				new MuddyChildrenAccessibility()
		);
		
		/* Does every agent i know what agent j is (where i =/= j)? */
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				MuddyAssertion assertion = new MuddyAssertion(j);
				KnowledgeAssertion<Integer, Boolean[]> knowledgeAssertion = new KnowledgeAssertion<Integer, Boolean[]>(i, assertion);
				boolean outcome = model.models(model.getActualWorld(), knowledgeAssertion);
				for (int m = 0; m < actualWorld.length; m++) {
					if (m > 0) {
						System.out.print(" ^ ");
					}
					if (!actualWorld[m]) {
						System.out.print("!");
					}
					System.out.print("m_" + m);
				}
				System.out.println(" |= K_" + i + " " + "m_" + j + " ==> " + outcome);
			}
		}
	}
}
