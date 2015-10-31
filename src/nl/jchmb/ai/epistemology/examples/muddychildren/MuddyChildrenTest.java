package nl.jchmb.ai.epistemology.examples.muddychildren;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import nl.jchmb.ai.epistemology.Model;
import nl.jchmb.ai.epistemology.assertion.Assertion;
import nl.jchmb.ai.epistemology.assertion.Disjunction;
import nl.jchmb.ai.epistemology.assertion.KnowledgeAssertion;
import nl.jchmb.ai.epistemology.assertion.Negation;

public class MuddyChildrenTest {
	public static void main(String[] args) {
		int n = 3;
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
			if (count >= 0) {
				worlds.add(states);
			}
			
		}
		Boolean[] actualWorld = worlds.get(3);
		Model<Integer, Boolean[]> model = new Model<Integer, Boolean[]>(
				agents,
				worlds,
				actualWorld,
				new MuddyChildrenAccessibility()
		);
		
		/* Does every agent i know what agent j is (where i =/= j)? */
		printKnowledge(n, model, actualWorld);
		
		/* Announce that at least one child is muddy. */
		ArrayList<Assertion<Integer, Boolean[]>> assertions = new ArrayList<Assertion<Integer, Boolean[]>>();
		for (Integer agent : agents) {
			assertions.add(new MuddyAssertion(agent));
		}
		Disjunction<Integer, Boolean[]> disj = new Disjunction<Integer, Boolean[]>(
				assertions
		);
		model.announce(disj);
		System.out.println("----");
		for (Integer agent : agents) {
			Disjunction<Integer, Boolean[]> disjunction = new Disjunction<Integer, Boolean[]>(
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new MuddyAssertion(agent)),
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new Negation<Integer, Boolean[]>(new MuddyAssertion(agent)))
			);
			if (model.models(actualWorld, disjunction)) {
				model.announce(disjunction);
				System.out.println("AN");
			}
		}
		model.announce(disj);
		for (Integer agent : agents) {
			Disjunction<Integer, Boolean[]> disjunction = new Disjunction<Integer, Boolean[]>(
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new MuddyAssertion(agent)),
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new Negation<Integer, Boolean[]>(new MuddyAssertion(agent)))
			);
			if (model.models(actualWorld, disjunction)) {
				model.announce(disjunction);
				System.out.println("AN");
			}
		}
		for (Integer agent : agents) {
			Disjunction<Integer, Boolean[]> disjunction = new Disjunction<Integer, Boolean[]>(
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new MuddyAssertion(agent)),
					new KnowledgeAssertion<Integer, Boolean[]>(agent, new Negation<Integer, Boolean[]>(new MuddyAssertion(agent)))
			);
			if (model.models(actualWorld, disjunction)) {
				model.announce(disjunction);
				System.out.println("AN");
			}
		}
		
		printKnowledge(n, model, actualWorld);
	}
	
	private static void printKnowledge(int n, Model<Integer, Boolean[]> model, Boolean[] actualWorld) {
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
