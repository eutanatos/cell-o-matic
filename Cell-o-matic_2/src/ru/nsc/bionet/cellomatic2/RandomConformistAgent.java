package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class RandomConformistAgent extends Agent{
	
public RandomConformistAgent() { //��������� �����
		super();
		conformism = true; 	// �������� �� ���������� � ���������
		NeighborsPollType = 2;
	}

public RandomConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = true; 	// �������� �� ���������� � ���������
		NeighborsPollType = 2;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
