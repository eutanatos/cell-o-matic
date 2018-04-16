package ru.nsc.bionet.cellomatic2.agents;
public abstract class Agent {
	protected int opinion;										//������, 0 ��� 1
	protected boolean zealot;									//�������� - 0 ��� �������� ������ ������, 1 - ��� ���������� ��������
	public enum PollTypes { CROSS, WHEEL, RANDOM};				//�������� ������ �������
	protected boolean conformism;								//���������� - ���������� ��������� ������ �����������, �������� ����������		
	public PollTypes NeighborsPollType;							//������ �������� ������ �������
	protected int[] crossPoll = {0,2,6,8};						//������ ������ �� ���� "�����"
	protected int[] wheelPoll = {1,3,5,7};						//������ ������ �� ���� "������"

/*
 *	������������ ������
 */	
	public Agent() {											//����������� �� ���������, ������ ��������� �������� ������ � ������������
		this.opinion = (int) (Math.random() * 2); 				//�������������� ������ {0, 1}
		this.zealot = (Math.random() < 0.5 ); 					//�������������� �������� {true, false}
	}
	
	public Agent(int opinion, boolean zealot) { 				//����������� � ��������� ������� � �������������
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
/*
 * ������ ������	
 */
	public int getOpinion() { 									//������� ������
		return this.opinion;
	}
	
	public void setOpinion(int newOpinion) { 					//���������� ����� ������, ��� ��������� �� ��������
		if (this.zealot) {										//���� �������, �� ������ �� ������
		} else {
		this.opinion = newOpinion;								//����� - �������������
		}
	}

	abstract void changeOpinion(int newOpinion);				// ����� ��� ��������� ������ �� ��������� ���������, ����������� �������

	public abstract int formOpinion(int[] neighborsOpinion); 	//�������� ������ ������� ����� ��������, ����������� �������
	
	@Override
    public String toString() {									//������������� ����� ���������� �� ������
		//return "{Op_" + this.opinion + ", Ze_" + this.zealot + ", Conf_" + this.conformism + ", Poll_" + this.NeighborsPollType + "}";
		return "{" + this.opinion + "," + this.zealot + "," + this.conformism + "," + this.NeighborsPollType + "}";
	}
}
