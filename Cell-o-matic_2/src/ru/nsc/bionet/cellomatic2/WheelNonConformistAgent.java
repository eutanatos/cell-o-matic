package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class WheelNonConformistAgent extends Agent{
	protected boolean conformism = true; 	// проверка на конформизм в суждениях
	protected int NeighborsPollType = 0;
	
public WheelNonConformistAgent() { //Случайный Агент
		super();
		conformism = false; 	// проверка на конформизм в суждениях
		NeighborsPollType = 1;
	}

public WheelNonConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = false; 	// проверка на конформизм в суждениях
		NeighborsPollType = 1;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
