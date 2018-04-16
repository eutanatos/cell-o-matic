package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class CrossNonConformistAgent extends Agent{
	
public CrossNonConformistAgent() { 								//��������� �����
		super();
		conformism = false; 									// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.CROSS;
	}

public CrossNonConformistAgent(int opinion, boolean zealot) { 	//�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 									// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.CROSS;
	}

public int formOpinion(int[] neighborsOpinion) {
	int crossSummOpinion = 0;
	for (int k : crossPoll) {
		crossSummOpinion = crossSummOpinion + neighborsOpinion[k];
	}
	
	if (crossSummOpinion > 2) {									//���������� ������ �����������
		return(1);
	} else if (neighborsOpinion[0] < 2) {						//���������� ������ �����������
		return(0);
	} else {
		return(neighborsOpinion[4]); 							//���� �����, �� �������� ��� ����� ������
	}	
}

public void changeOpinion(int newOpinion) {
	this.setOpinion(Math.abs(newOpinion - 1));
	}
}
