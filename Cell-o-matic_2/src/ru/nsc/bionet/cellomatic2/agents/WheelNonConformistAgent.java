package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class WheelNonConformistAgent extends Agent{

public WheelNonConformistAgent() { 																//��������� �����
		super();
		conformism = false; 																	// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.WHEEL;
	}

public WheelNonConformistAgent(int opinion, boolean zealot) { 									//�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 																	// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.WHEEL;
	}

public int formOpinion(int[] neighborsOpinion) {
	int wheelSummOpinion = 0;
	for (int k : wheelPoll) {
		wheelSummOpinion = wheelSummOpinion + neighborsOpinion[k];
		}
	
	if (wheelSummOpinion > 4) {																	//���������� ������ �����������
			return(1);
		} else if (neighborsOpinion[0] < 4) {													//���������� ������ �����������
			return(0);
		} else {
			return(neighborsOpinion[4]); 														//���� �����, �� �������� ��� ����� ������
		}	
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(Math.abs(newOpinion - 1));
	}
}
