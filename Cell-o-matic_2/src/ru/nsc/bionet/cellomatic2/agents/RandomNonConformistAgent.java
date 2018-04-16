package ru.nsc.bionet.cellomatic2.agents;

import ru.nsc.bionet.cellomatic2.agents.Agent;

public class RandomNonConformistAgent extends Agent{
	
public RandomNonConformistAgent() { 											//��������� �����
		super();
		conformism = false; 													// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.RANDOM;
	}

public RandomNonConformistAgent(int opinion, boolean zealot) { 					//�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 													// �������� �� ���������� � ���������
		NeighborsPollType = PollTypes.RANDOM;
	}

public int formOpinion(int[] neighborsOpinion) {
	return(neighborsOpinion[(int) (Math.random() * (neighborsOpinion.length))]); // ����� ������ ���������� ������
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(Math.abs(newOpinion - 1));
	}
}
