package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class CrossConformistAgent extends Agent{
	
	public CrossConformistAgent() { 							//��������� �����
			super();
			conformism = true; 									//���������� � ���������, ����� �������
			NeighborsPollType = PollTypes.CROSS;				//��� ������ cross, ����� �������
		}

	public CrossConformistAgent(int opinion, boolean zealot) { 	//������ ��������� �����������
			super(opinion, zealot);
			conformism = true; 									//���������� � ���������, ����� �������
			NeighborsPollType = PollTypes.CROSS;				//��� ������ cross, ����� �������
		}

	public int formOpinion(int[] neighborsOpinion) { 			//������������ ������ ������ �� ���������� ������� ������� (3�3)
		int crossSummOpinion = 0; 								//���� ������ ������ �������
		for (int k : crossPoll) { 								//����� ������� ������ �� ���������������� ������� (�������� � ������������ ������)
			crossSummOpinion = crossSummOpinion + neighborsOpinion[k];
		}
		
		if (crossSummOpinion > 2) {								//���������� ������ �����������
			return(1);
		} else if (neighborsOpinion[0] < 2) {					//���������� ������ �����������
			return(0);
		} else {
			return(neighborsOpinion[4]); 						//���� �����, �� �������� ��� ����� ������
		}	
	}
	
	public void changeOpinion(int newOpinion) {
			this.setOpinion(newOpinion);
		}
}
