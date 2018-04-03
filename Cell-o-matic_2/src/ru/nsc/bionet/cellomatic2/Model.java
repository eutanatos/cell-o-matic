package ru.nsc.bionet.cellomatic2;

public class Model {
	private int length; //������ ����
	private int heigth; //������ ����
	private int iterations; //���������� ��������
	private Agent agentsField[][]; //���������� � ������������� ���� �������
	private int agentsOpinions[][][]; //���� ��� �������� ����������� ������ ������� �� ���������
	
	public Model() { //����������� ������ �� ��������� (10�10)
		this.length = 10;
		this.heigth = 10;
		this.iterations = 2;
		this.agentsField = new Agent[length][heigth];
		this.agentsOpinions = new int[length][heigth][iterations+1];
		this.startFillAgents();
	}
	
	public Model(int length, int heigth, int iterations) { //����������� ������ ��������� �������
		this.length = length;
		this.heigth = heigth;
		this.iterations = iterations;
		this.agentsField = new Agent[this.length][this.heigth];
		this.agentsOpinions = new int[this.length][this.heigth][this.iterations+1];
		this.startFillAgents();
	}
	
	public void startFillAgents() { //����� ��� ���������� ���������� ������� �������� ��������� �������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				int r = (int) (Math.random() * 6); //����� ���������� ������
				switch (r) {
				case 0:
					this.agentsField[i][j] = new CrossConformistAgent();
					break;
				case 1:
					this.agentsField[i][j] = new CrossNonConformistAgent();
					break;
				case 2:
					this.agentsField[i][j] = new WheelConformistAgent();
					break;
				case 3:
					this.agentsField[i][j] = new WheelNonConformistAgent();
					break;
				case 4:
					this.agentsField[i][j] = new RandomConformistAgent();
					break;
				case 5:
					this.agentsField[i][j] = new RandomNonConformistAgent();
					break;
				default:
					System.out.println("�����-�� �������� ��� ���������� ���� ��������");
					break;
				}
			}
		}
	}
	
	public void printAgents() { //������ ������ ���� �������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j].getOpinion() + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n \n");
	}
	
	public void printOpinions(int iteration) { //������ ���� ������ �������� ��������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsOpinions[i][j][iteration] + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n \n");
	}
	
	public int[] askNeighbors(int i, int j) {
		int SumCrossOpinions = 0;
		SumCrossOpinions = SumCrossOpinions 
			+ this.agentsOpinions[(this.length + i) % this.length][(this.heigth + j - 1) % this.heigth][0] //top
			+ this.agentsOpinions[(this.length + i) % this.length][(this.heigth + j + 1) % this.heigth][0] //base
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j) % this.heigth][0] //left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j) % this.heigth][0]; //right
		/*	System.out.println("������ ��������� � ������� " + SumCrossOpinions);
			System.out.println("top: " + (i) + "," + ((m.length + j - 1) % m.length));
			System.out.println("base: " + (i) + "," + ((m.length + j + 1) % m.length));
			System.out.println("left: " + ((m.length + i - 1) % m.length) + "," + (j));
			System.out.println("right: " + ((m.length + i + 1) % m.length) + "," + (j));
		*/
		int SumXOpinions = 0;
		SumXOpinions = SumXOpinions
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j - 1) % this.heigth][0] //top left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j - 1) % this.heigth][0] //top right
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j + 1) % this.heigth][0] //base left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j + 1) % this.heigth][0]; //base right
		/*	System.out.println("������ X " + SumXOpinions);
			System.out.println("top left: " + ((m.length + i - 1) % m.length) + "," + ((m.length + j - 1) % m.length));
			System.out.println("top right: " + ((m.length + i + 1) % m.length) + "," + ((m.length + j - 1) % m.length));
			System.out.println("base left: " + ((m.length + i - 1) % m.length) + "," + ((m.length + j + 1) % m.length));
			System.out.println("base right: " + ((m.length + i + 1) % m.length) + "," + ((m.length + j + 1) % m.length));
		*/	
		return new int[]{SumCrossOpinions,SumXOpinions,0};
	}
	
	public void iterateOpinions() { //1 �������� ����� ������
		//������� ������ ��� ���������� ������������ ���������� ��������
		int newOpinions[][];
		newOpinions = new int[this.length][this.heigth];
		//�������� �� ���� ������� � ��������� ������ ��������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				newOpinions[i][j] = this.agentsField[i][j].getNeighborsOpinion(this.agentsField, i, j);
			}
		}
		//���������� � ������ ����� ������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				this.agentsField[i][j].setOpinion(newOpinions[i][j]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Model m = new Model();
		System.out.println("��������� ������: \n");
		System.out.println("������ ���� ������� " + m.length + " �� " + m.heigth + "\n");
		System.out.println("���������� ��������: " + m.iterations + "\n");
		System.out.println("��������� ��������� �������:\n");
		m.printAgents();
		/*
		System.out.println();
		System.out.println("������ ������� �� ������ \n");
		*/
		//�������� �� ���������� ��������
		if (m.iterations >= 1) {
		//������� �������� - ��������� ���������� ��������
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.heigth; j++) {
					m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
				}
			}
			System.out.println("�������� 0");
			m.printOpinions(0); // ������� �� �����, ��� ��������
			
			//��������� ��������
			for (int iteration = 1; iteration <= m.iterations; iteration++) { // �� ������ �� ��������, ������������
				//������� �������, ���������� ������ �������, ��������� ����� ������, ��������� � ������� �������
				System.out.println("�������� " + iteration);
				
				for (int i = 0; i < m.length; i++) { //�����
					for (int j = 0; j < m.heigth; j++) {
						
						int[] neighborsOpinion = m.askNeighbors(i, j); //���������� ������ ������� 
						
						switch (m.agentsField[i][j].NeighborsPollType) { //
						case 0: //cross
							if (neighborsOpinion[0] > 2) { //��������� ������� �����������
								neighborsOpinion[2] = 1;
							} else if (neighborsOpinion[0] < 2) {
								neighborsOpinion[2] = 0;
							} else {
								neighborsOpinion[2] = m.agentsField[i][j].getOpinion(); //���� �����, �� �������� ��� ����� ������
							}
							break;
							
						case 1: //wheel
							int summOpinion = neighborsOpinion[0] + neighborsOpinion[1];
							if (summOpinion > 4) { //��������� ������� �����������
								neighborsOpinion[2] = 1;
							} else if (summOpinion < 4) {
								neighborsOpinion[2] = 0;
							} else {
								neighborsOpinion[2] = m.agentsField[i][j].getOpinion(); //���� �����, �� �������� ��� ����� ������
							}
							break;
						case 2: //"random"
							int i_random = (int) (Math.random() * (m.agentsField.length)); //��������� ���������� (���������� �� ���������� ������)
							int j_random = (int) (Math.random() * (m.agentsField[0].length)); //��������� ����������
							neighborsOpinion[2] = m.agentsField[i_random][j_random].getOpinion();
							break;
							
						default:
							System.out.print("�������� ��� ������ �������: ");
							System.out.println(m.agentsField[i][j].NeighborsPollType);
							neighborsOpinion[2] = 0;
							break;
						}
						//System.out.print(neighborsOpinion[2] + " ");
						m.agentsOpinions[i][j][iteration] = neighborsOpinion[2];
						}
					
					}
				for (int i = 0; i < m.length; i++) {
					for (int j = 0; j < m.heigth; j++) {
						m.agentsField[i][j].setOpinion(m.agentsOpinions[i][j][iteration]);
					}
				}		
				}
			m.printOpinions(1);	
			
			
			
			} else {
			System.out.println("���������� �������� ������ ���� ������ 1!");
		}
	}
}