package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class WheelConformistAgent extends Agent{
	
public WheelConformistAgent() { //Случайный Агент
		super();
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 1;
	}

public WheelConformistAgent(int opinion, boolean zealot) { //заданные параметры суперкласса
		super(opinion, zealot);
		conformism = true; 	// проверка на конформизм в суждениях
		NeighborsPollType = 1;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
