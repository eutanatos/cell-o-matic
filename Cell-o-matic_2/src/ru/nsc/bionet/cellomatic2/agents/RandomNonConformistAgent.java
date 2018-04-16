package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class RandomNonConformistAgent extends Agent{
	
public RandomNonConformistAgent() { 											//Случайный Агент
		super();
		conformism = false; 													// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.RANDOM;
	}

public RandomNonConformistAgent(int opinion, boolean zealot) { 					//заданные параметры суперкласса
		super(opinion, zealot);
		conformism = false; 													// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.RANDOM;
	}

public int formOpinion(int[] neighborsOpinion) {
	return(neighborsOpinion[(int) (Math.random() * (neighborsOpinion.length))]); // берем мнение случайного соседа
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(Math.abs(newOpinion - 1));
	}
}
