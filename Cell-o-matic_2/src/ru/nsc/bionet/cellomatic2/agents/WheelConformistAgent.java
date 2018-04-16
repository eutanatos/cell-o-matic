package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class WheelConformistAgent extends Agent{
	
public WheelConformistAgent() { 													//Случайный Агент
		super();
		conformism = true; 															// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.WHEEL;
	}

public WheelConformistAgent(int opinion, boolean zealot) { 							//заданные параметры суперкласса
		super(opinion, zealot);
		conformism = true; 															// проверка на конформизм в суждениях
		NeighborsPollType = PollTypes.WHEEL;
	}

public int formOpinion(int[] neighborsOpinion) {
	int wheelSummOpinion = 0;
	for (int k : wheelPoll) {
		wheelSummOpinion = wheelSummOpinion + neighborsOpinion[k];
		}
	
	if (wheelSummOpinion > 4) {														//возвращаем мнение большинства
			return(1);
		} else if (neighborsOpinion[0] < 4) {										//возвращаем мнение меньшинства
			return(0);
		} else {
			return(neighborsOpinion[4]); 											//если ничья, то остаемся при своем мнении
		}	
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
