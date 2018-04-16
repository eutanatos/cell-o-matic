package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class CrossNonConformistAgent extends Agent{
	
public CrossNonConformistAgent() { 								//Случайный Агент
		super();
		conformism = false; 									// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.CROSS;
	}

public CrossNonConformistAgent(int opinion, boolean zealot) { 	//заданные параметры суперкласса
		super(opinion, zealot);
		conformism = false; 									// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.CROSS;
	}

public int formOpinion(int[] neighborsOpinion) {
	int crossSummOpinion = 0;
	for (int k : crossPoll) {
		crossSummOpinion = crossSummOpinion + neighborsOpinion[k];
	}
	
	if (crossSummOpinion > 2) {									//возвращаем мнение большинства
		return(1);
	} else if (neighborsOpinion[0] < 2) {						//возвращаем мнение меньшинства
		return(0);
	} else {
		return(neighborsOpinion[4]); 							//если ничья, то остаемся при своем мнении
	}	
}

public void changeOpinion(int newOpinion) {
	this.setOpinion(Math.abs(newOpinion - 1));
	}
}
