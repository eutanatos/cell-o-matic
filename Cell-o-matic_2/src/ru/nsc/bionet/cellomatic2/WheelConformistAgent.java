package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class WheelConformistAgent extends Agent{
	
public WheelConformistAgent() { //��������� �����
		super();
		conformism = true; 	// �������� �� ���������� � ���������
		NeighborsPollType = 1;
	}

public WheelConformistAgent(int opinion, boolean zealot) { //�������� ��������� �����������
		super(opinion, zealot);
		conformism = true; 	// �������� �� ���������� � ���������
		NeighborsPollType = 1;
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
