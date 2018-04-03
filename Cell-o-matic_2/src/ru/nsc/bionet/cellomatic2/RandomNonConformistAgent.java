package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class RandomNonConformistAgent extends Agent{
	
public RandomNonConformistAgent() { //��������� �����
		super();
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 2;
	}

public RandomNonConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 2;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
