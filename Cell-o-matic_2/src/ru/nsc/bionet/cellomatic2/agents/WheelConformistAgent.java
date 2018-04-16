package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class WheelConformistAgent extends Agent{
	
public WheelConformistAgent() { 													//��������� �����
		super();
		conformism = true; 															// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.WHEEL;
	}

public WheelConformistAgent(int opinion, boolean zealot) { 							//�������� ��������� �����������
		super(opinion, zealot);
		conformism = true; 															// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.WHEEL;
	}

public int formOpinion(int[] neighborsOpinion) {
	int wheelSummOpinion = 0;
	for (int k : wheelPoll) {
		wheelSummOpinion = wheelSummOpinion + neighborsOpinion[k];
		}
	
	if (wheelSummOpinion > 4) {														//���������� ������ �����������
			return(1);
		} else if (neighborsOpinion[0] < 4) {										//���������� ������ �����������
			return(0);
		} else {
			return(neighborsOpinion[4]); 											//���� �����, �� �������� ��� ����� ������
		}	
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
