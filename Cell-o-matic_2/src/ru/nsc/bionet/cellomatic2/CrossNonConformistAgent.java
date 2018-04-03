package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class CrossNonConformistAgent extends Agent{
	
public CrossNonConformistAgent() { //Случайный Агент
		super();
		conformism = false; 	// проверка на конформизм в суждениях
		NeighborsPollType = 0;
	}

public CrossNonConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = false; 	// проверка на конформизм в суждениях
		NeighborsPollType = 0;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
