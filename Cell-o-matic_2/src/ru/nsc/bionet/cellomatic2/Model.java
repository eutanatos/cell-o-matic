package ru.nsc.bionet.cellomatic2;

import ru.nsc.bionet.cellomatic2.agents.*;

public class Model {
	private int length; 												//высота поля
	private int heigth; 												//ширина поля
	private int iterations; 											//количество итераций
	private Agent agentsField[][]; 										//объявление и инициализация поля агентов
	private int agentsOpinions[][][]; 									//поле для хранения вычисленных мнений агентов по итерациям
		
	public Model() { 													//конструктор модели по умолчанию (10х10)
		this.length = 10;
		this.heigth = 10;
		this.iterations = 10;
		this.agentsField = new Agent[length][heigth];
		this.agentsOpinions = new int[length][heigth][iterations+1]; 	// iterations+1 потому что в [0] слое мы храним стартовые значения
		this.startFillAgents();
	}
	
	public Model(int length, int heigth, int iterations) { 				//конструктор модели заданного размера
		this.length = length;
		this.heigth = heigth;
		this.iterations = iterations;
		this.agentsField = new Agent[this.length][this.heigth];
		this.agentsOpinions = new int[this.length][this.heigth][this.iterations+1];
		this.startFillAgents();
	}
	
	public void startFillAgents() { 									//метод для стартового заполнения массива агентами случайным образом
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				int r = (int) (Math.random() * 6); 						//случайный выбор одного из классов
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
					System.out.println("какая-то проблема при заполнении поля агентами");
					break;
				}
			}
		}
	}
	
	public void printAgents() { 										//печать мнений стартового поля агентов
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j].getOpinion() + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
		
		System.out.println("Полные данные по стартовому полю агентов:");
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j]);
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}
	
	public void printOpinions(int iteration) { 							//печать поля мнений заданной итерации
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsOpinions[i][j][iteration] + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}

	public int[] getNeighborsOpinions(int i, int j) { 					//собираем мнения соседей вокруг координаты (i,j)
	int[] neighborsOpinion = new int[9]; 								//линейный массив под мнения соседей  
	
	for (int k = 0; k < 3; k++) {			
		for ( int m = 0; m < 3; m++) {
			neighborsOpinion[k + 3 * m] = this.agentsOpinions[(this.length + i - 1 + k) % this.length]
					[(this.heigth + j - 1 + m) % this.heigth][0];
			}
		}
	return(neighborsOpinion);
	}	
	
	public static void main(String[] args) {
		Model m = new Model();											//создаем модель
		System.out.println("Начальные данные: \n");
		System.out.print("Размер поля агентов " + m.length + " на " + m.heigth + "\n");
		System.out.print("Количество итераций: " + m.iterations + "\n");
		System.out.println("Начальное состояние агентов:\n");
		m.printAgents();												//в дальнейшем пользоваться .printOpinions(0)

		if (m.iterations >= 1) {										//проверка на количество итераций
			for (int i = 0; i < m.length; i++) {						//нулевая итерация - заполняем её начальными мнениями
				for (int j = 0; j < m.heigth; j++) {
					m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
				}
			}
			
			System.out.println("Итерация 0");							//выводим на экран, для сверки с начальным состоянием
			m.printOpinions(0);
			
/*	
 * Выполняем итерации, начиная c 1, т.к. 0 занято исходными мнениями
 */
			for (int iteration = 1; iteration <= m.iterations; iteration++) { 	// от первой до заданной, включительно
																				//обходим и сохраняем промежуточный результат в матрицу ответов, 
																				//затем переписываем в соответствующий слой ответов
				for (int i = 0; i < m.length; i++) { 					//обходим агентов
					for (int j = 0; j < m.heigth; j++) {
																		//сохраняем вычисленный промежуточный вариант мнения
						m.agentsOpinions[i][j][iteration] = m.agentsField[i][j].formOpinion(m.getNeighborsOpinions(i, j));
						}
					}
				for (int i = 0; i < m.length; i++) { 					//обходим матрицу ответов и меняем мнение агентов, потом возвращаем итог в ответы
					for (int j = 0; j < m.heigth; j++) {
						m.agentsField[i][j].setOpinion(m.agentsOpinions[i][j][iteration]);
						m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
					}
				}	
				System.out.println("Итерация " + iteration);			//выводим итерацию на экран
				m.printOpinions(iteration);	
				}
			} else { System.out.println("Количество итераций должно быть больше 1!"); } //жалуемся, если задано мало итераций
	}
}