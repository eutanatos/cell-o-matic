package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.agents.*;

public class Model {
	private int length; 												//������ ����
	private int heigth; 												//������ ����
	private int iterations; 											//���������� ��������
	private Agent agentsField[][]; 										//���������� � ������������� ���� �������
	private int agentsOpinions[][][]; 									//���� ��� �������� ����������� ������ ������� �� ���������
		
	public Model() { 													//����������� ������ �� ��������� (10�10)
		this.length = 10;
		this.heigth = 10;
		this.iterations = 10;
		this.agentsField = new Agent[length][heigth];
		this.agentsOpinions = new int[length][heigth][iterations+1]; 	// iterations+1 ������ ��� � [0] ���� �� ������ ��������� ��������
		this.startFillAgents();
	}
	
	public Model(int length, int heigth, int iterations) { 				//����������� ������ ��������� �������
		this.length = length;
		this.heigth = heigth;
		this.iterations = iterations;
		this.agentsField = new Agent[this.length][this.heigth];
		this.agentsOpinions = new int[this.length][this.heigth][this.iterations+1];
		this.startFillAgents();
	}
	
	public void startFillAgents() { 									//����� ��� ���������� ���������� ������� �������� ��������� �������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				int r = (int) (Math.random() * 6); 						//��������� ����� ������ �� �������
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
	
	public void printAgents() { 										//������ ������ ���������� ���� �������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j].getOpinion() + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
		
		System.out.println("������ ������ �� ���������� ���� �������:");
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j]);
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}
	
	public void printOpinions(int iteration) { 							//������ ���� ������ �������� ��������
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsOpinions[i][j][iteration] + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}

	public int[] getNeighborsOpinions(int i, int j) { 					//�������� ������ ������� ������ ���������� (i,j)
	int[] neighborsOpinion = new int[9]; 								//�������� ������ ��� ������ �������  
	
	for (int k = 0; k < 3; k++) {			
		for ( int m = 0; m < 3; m++) {
			neighborsOpinion[k + 3 * m] = this.agentsOpinions[(this.length + i - 1 + k) % this.length]
					[(this.heigth + j - 1 + m) % this.heigth][0];
			}
		}
	return(neighborsOpinion);
	}	
	
	public static void main(String[] args) {
		Model m = new Model();											//������� ������
		System.out.println("��������� ������: \n");
		System.out.print("������ ���� ������� " + m.length + " �� " + m.heigth + "\n");
		System.out.print("���������� ��������: " + m.iterations + "\n");
		System.out.println("��������� ��������� �������:\n");
		m.printAgents();												//� ���������� ������������ .printOpinions(0)

		if (m.iterations >= 1) {										//�������� �� ���������� ��������
			for (int i = 0; i < m.length; i++) {						//������� �������� - ��������� � ���������� ��������
				for (int j = 0; j < m.heigth; j++) {
					m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
				}
			}
			
			System.out.println("�������� 0");							//������� �� �����, ��� ������ � ��������� ����������
			m.printOpinions(0);
			
/*	
 * ��������� ��������, ������� c 1, �.�. 0 ������ ��������� ��������
 */
			for (int iteration = 1; iteration <= m.iterations; iteration++) { 	// �� ������ �� ��������, ������������
																				//������� � ��������� ������������� ��������� � ������� �������, 
																				//����� ������������ � ��������������� ���� �������
				for (int i = 0; i < m.length; i++) { 					//������� �������
					for (int j = 0; j < m.heigth; j++) {
																		//��������� ����������� ������������� ������� ������
						m.agentsOpinions[i][j][iteration] = m.agentsField[i][j].formOpinion(m.getNeighborsOpinions(i, j));
						}
					}
				for (int i = 0; i < m.length; i++) { 					//������� ������� ������� � ������ ������ �������, ����� ���������� ���� � ������
					for (int j = 0; j < m.heigth; j++) {
						m.agentsField[i][j].setOpinion(m.agentsOpinions[i][j][iteration]);
						m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
					}
				}	
				System.out.println("�������� " + iteration);			//������� �������� �� �����
				m.printOpinions(iteration);	
				}
			} else { System.out.println("���������� �������� ������ ���� ������ 1!"); } //��������, ���� ������ ���� ��������
	}
}