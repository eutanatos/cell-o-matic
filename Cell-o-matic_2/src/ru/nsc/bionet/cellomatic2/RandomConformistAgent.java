package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class RandomConformistAgent extends Agent{
	
public RandomConformistAgent() { //Случайный Агент
		super();
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 2;
	}

public RandomConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 2;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
