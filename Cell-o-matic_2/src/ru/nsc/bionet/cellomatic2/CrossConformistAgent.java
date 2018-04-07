package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class CrossConformistAgent extends Agent{
	
public CrossConformistAgent() { 							//Случайный Агент
		super();
		conformism = true; 									//проверка на конформизм в суждениях, удалить?
		NeighborsPollType = 0;								//тип опроса cross
	}

public CrossConformistAgent(int opinion, boolean zealot) { 	//заданы параметры суперкласса
		super(opinion, zealot);
		conformism = true; 									//проверка на конформизм в суждениях, удалить?
		NeighborsPollType = 0;								//тип опроса cross
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
