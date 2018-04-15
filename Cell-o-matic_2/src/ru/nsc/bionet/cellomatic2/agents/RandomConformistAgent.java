package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class RandomConformistAgent extends Agent{
	
public RandomConformistAgent() { 							//Случайный Агент
		super();
		conformism = true; 									// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.RANDOM;
	}

public RandomConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = true; 									// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.RANDOM;
	}

public int formOpinion(int[] neighborsOpinion) {
	return(neighborsOpinion[(int) (Math.random() * (neighborsOpinion.length))]); // берем мнение случайного соседа	
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
