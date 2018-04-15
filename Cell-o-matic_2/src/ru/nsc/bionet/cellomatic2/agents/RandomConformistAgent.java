package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class RandomConformistAgent extends Agent{
	
public RandomConformistAgent() { 							//��������� �����
		super();
		conformism = true; 									// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.RANDOM;
	}

public RandomConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = true; 									// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.RANDOM;
	}

public int formOpinion(int[] neighborsOpinion) {
	return(neighborsOpinion[(int) (Math.random() * (neighborsOpinion.length))]); // ����� ������ ���������� ������	
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
