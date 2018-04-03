package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class CrossConformistAgent extends Agent{
	
public CrossConformistAgent() { //Случайный Агент
		super();
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 0;
	}

public CrossConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 0;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
