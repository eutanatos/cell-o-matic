package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class CrossNonConformistAgent extends Agent{
	
public CrossNonConformistAgent() { //��������� �����
		super();
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 0;
	}

public CrossNonConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 0;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
