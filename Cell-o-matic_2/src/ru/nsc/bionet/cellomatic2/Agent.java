package ru.nsc.bionet.cellomatic2;
public abstract class Agent {
	protected int opinion;							//������, 0 ��� 1
	protected boolean zealot;						//�������� - 0 ��� �������� ������ ������, 1 - ��� ���������� ��������
	protected String[] PollTypes = {"cross", "wheel", "random"}; //�������� ������ �������
	protected boolean conformism;					//���������� - ���������� ��������� ������ �����������, �������� ����������
	protected int NeighborsPollType;				//������ �������� ������ �������

/*
 *	������������ ������
 */	
	public Agent() {								//����������� �� ���������, ������ ��������� �������� ������ � ������������
		this.opinion = (int) (Math.random() * 2); 	//�������������� ������ 0 ��� 1
		this.zealot = (Math.random() < 0.5 ); 		//����� ������� ��������������� ���������
	}
	
	public Agent(int opinion, boolean zealot) { 	//����������� � ��������� ������� � �������������
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
/*
 * ������ ������	
 */
	protected int getOpinion() { 					//������� ������
		return this.opinion;
	}
	
	protected void setOpinion(int newOpinion) { 	//���������� ����� ������, ��� ��������� �� ��������
		if (this.zealot) {							//���� �������, �� ������ �� ������
		} else {
		this.opinion = newOpinion;					//����� - �������������
		}
	}

	abstract void changeOpinion(int newOpinion);// { // ����� ��� ��������� ������ �� ��������� ���������, ����������� �������

}
/*
����������� ����������� ����� +Agent (� �������, ��������������, ����� +��� ����, +��� ������������, +��� ������ set, +��� ������ get, 
+����������� ������� ������� ��������� ������).
����������� 6 ������� �������-�����������, ���������� �� ������������� �� ������ ���������� ��������� ������ 
( 3 ��������� � �����, ������ � ������������ * 2 ������ � ����������� � �����������). � ���� ������� ������ ���� ����������� 
����������� ������� �� ������-������.
*/