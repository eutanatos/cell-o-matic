package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.Agent;

public class CrossConformistAgent extends Agent{
	
public CrossConformistAgent() { 							//��������� �����
		super();
		conformism = true; 									//�������� �� ���������� � ���������, �������?
		NeighborsPollType = 0;								//��� ������ cross
	}

public CrossConformistAgent(int opinion, boolean zealot) { 	//������ ��������� �����������
		super(opinion, zealot);
		conformism = true; 									//�������� �� ���������� � ���������, �������?
		NeighborsPollType = 0;								//��� ������ cross
	}

public void changeOpinion(int newOpinion) {
	this.setOpinion(newOpinion);
	}
}
