package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class CrossConformistAgent extends Agent{
	
	public CrossConformistAgent() { 							//Случайный Агент
			super();
			conformism = true; 									//конформизм в суждениях, задан классом
			NeighborsPollType = PollTypes.CROSS;				//тип опроса cross, задан классом
		}

	public CrossConformistAgent(int opinion, boolean zealot) { 	//заданы параметры суперкласса
			super(opinion, zealot);
			conformism = true; 									//конформизм в суждениях, задан классом
			NeighborsPollType = PollTypes.CROSS;				//тип опроса cross, задан классом
		}

	public int formOpinion(int[] neighborsOpinion) { 			//формирование нового мнения по переданным мнениям соседей (3х3)
		int crossSummOpinion = 0; 								//сюда сложим мнения соседей
		for (int k : crossPoll) { 								//обход массива мнений по соответствующему шаблону (задается в родительском классе)
			crossSummOpinion = crossSummOpinion + neighborsOpinion[k];
		}
		
		if (crossSummOpinion > 2) {								//возвращаем мнение большинства
			return(1);
		} else if (neighborsOpinion[0] < 2) {					//возвращаем мнение меньшинства
			return(0);
		} else {
			return(neighborsOpinion[4]); 						//если ничья, то остаемся при своем мнении
		}	
	}
	
	public void changeOpinion(int newOpinion) {
			this.setOpinion(newOpinion);
		}
}
