package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class WheelNonConformistAgent extends Agent{
	protected boolean conformism = true; 	// �������� �� ���������� � ���������
	protected int NeighborsPollType = 0;
	
public WheelNonConformistAgent() { //��������� �����
		super();
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 1;
	}

public WheelNonConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = false; 	// �������� �� ���������� � ���������
		NeighborsPollType = 1;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
