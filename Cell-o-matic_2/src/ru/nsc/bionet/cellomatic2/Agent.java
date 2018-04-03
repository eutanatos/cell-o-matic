package ru.nsc.bionet.cellomatic2;
public abstract class Agent {
	protected int opinion;			//������
	protected boolean zealot;		//�������� - 0 ��� �������� ������ ������, 1 - ��� ���������� ��������
	protected String[] PollTypes = {"cross", "wheel", "random"}; //�������� ������ �������
	protected boolean conformism;
	protected int NeighborsPollType;

/*������������ ������*/	
	public Agent() {			//����������� �� ���������, ������ ��������� �������� ������ � ������������
		this.opinion = (int) (Math.random() * 2); //�������������� ������ 0 ��� 1
	/*	if ((Math.random() * 2) >= 1 ) { //������ ������� ��������������� ���������
			this.zealot = true;
		} else {
			this.zealot = false;
		}	*/
		this.zealot = (Math.random() < 0.5 ); // ����� ������� ��������������� ���������
	}
	
	public Agent(int opinion, boolean zealot) { //����������� � ��������� ������� � �������������
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
	protected int getOpinion() { //����� ���������� ������� ������??? ������ ������ �������� ������� ��������?
		return this.opinion;
	}
	
	protected void setOpinion(int newOpinion) { //���������� ����� ������
		if (this.zealot) {
			//do nothing
		} else {
		this.opinion = newOpinion;
		}
	}

	abstract void changeOpinion(int newOpinion);// { // ����� ��� ��������� ������ �� ��������� ���������, ����������� �������
	

	
	
	
	public int getNeighborsOpinion(Agent agentsField[][], int i, int j) { //����� ������ �������
		//��� �������� ������ ������� �������� �� ���� ������� - ����� ������������� ��� ������������ ������, ��������, ����� ����� ����������...
		// 1 �������
		
		// 2 �������
		System.out.println("������ ������ ������� ������ " + i + " " + j);
		Agent agentsFieldXL[][] = new Agent[agentsField.length+1][agentsField[0].length+1]; //������� � -1 �������� � ������� ����� �� ���� ������ ������ �� ���� �����-�����
		System.out.println("������� ����������� ������� +1");
		for (int k1 = 1; k1 < agentsFieldXL.length; k1++) { //���������
			for (int k2 = 1; k2 < agentsFieldXL[0].length; k2++) {
				System.out.print(agentsField[(k1-1) / agentsField.length][(k2-1) / agentsField.length].getOpinion() + " ");
				/*try {
				agentsFieldXL[k1 / agentsFieldXL.length][k2 / agentsFieldXL[0].length] = agentsField[(k1-1) / agentsField.length][(k2-1) / agentsField.length];
				System.out.print(agentsFieldXL[k1+1][k2+1].getOpinion() + " ");
				} catch(Exception e) {
					System.out.println(k1 + " " + k2 + " " + e);
				}*/
			}
			System.out.println();
		}
		//������ ���������� ��� ������� ������
		int summCrossOpinion; //����� ������ �����-�������
		int summXOpinion; //����� ������ �� ����������
		int returnOpinion; //������������ ������
		summCrossOpinion = (agentsFieldXL[i+2][j+1].getOpinion() + agentsFieldXL[i][j+1].getOpinion() + agentsFieldXL[i+1][j+2].getOpinion() + agentsFieldXL[i+1][j].getOpinion());
		summXOpinion = (agentsFieldXL[i][j].getOpinion() + agentsFieldXL[i+2][j].getOpinion() + agentsFieldXL[i][j+2].getOpinion() + agentsFieldXL[i+2][j+2].getOpinion());
//		summCrossOpinion = (agentsFieldXL[i+1][j].getOpinion() + agentsFieldXL[i-1][j].getOpinion() + agentsFieldXL[i][j+1].getOpinion() + agentsFieldXL[i][j-1].getOpinion());
//		summXOpinion = (agentsFieldXL[i-1][j-1].getOpinion() + agentsFieldXL[i+1][j-1].getOpinion() + agentsFieldXL[i-1][j+1].getOpinion() + agentsFieldXL[i+1][j+1].getOpinion());

		
		
		switch (agentsField[i][j].NeighborsPollType) { //���������� ������ ������� 
		case 0: //cross
			if (summCrossOpinion > 2) { //��������� ������� �����������
				returnOpinion = 1;
			} else if (summCrossOpinion < 2) {
				returnOpinion = 0;
			} else {
				returnOpinion = agentsField[i][j].getOpinion(); //���� �����, �� �������� ��� ����� ������
			}
			break;
			
		case 1: //wheel
			int summOpinion = summCrossOpinion + summXOpinion;
			if (summOpinion > 4) { //��������� ������� �����������
				returnOpinion = 1;
			} else if (summOpinion < 4) {
				returnOpinion = 0;
			} else {
				returnOpinion = agentsField[i][j].getOpinion(); //���� �����, �� �������� ��� ����� ������
			}
			break;
		case 2: //"random"
			int i_random = (int) (Math.random() * (agentsField.length)); //��������� ����������
			int j_random = (int) (Math.random() * (agentsField[0].length)); //��������� ����������
			returnOpinion = agentsField[i_random][j_random].getOpinion();
			break;
			
		default:
			System.out.println("�������� ��� ������ �������");
			System.out.println(agentsField[i][j].NeighborsPollType);
			returnOpinion = 0;
			break;
		}
		
		return(returnOpinion);
	}
	

}
/*
����������� ����������� ����� +Agent (� �������, ��������������, ����� +��� ����, +��� ������������, +��� ������ set, +��� ������ get, 
+����������� ������� ������� ��������� ������).
����������� 6 ������� �������-�����������, ���������� �� ������������� �� ������ ���������� ��������� ������ 
( 3 ��������� � �����, ������ � ������������ * 2 ������ � ����������� � �����������). � ���� ������� ������ ���� ����������� 
����������� ������� �� ������-������.
*/