package ru.nsc.bionet.cellomatic2;

public class Model {
	private int length; 									//высота поля
	private int heigth; 									//ширина поля
	private int iterations; 								//количество итераций
	private Agent agentsField[][]; 							//объявление и инициализация поля агентов
	private int agentsOpinions[][][]; 						//поле для хранения вычисленных мнений агентов по итерациям
	
	public Model() { 										//конструктор модели по умолчанию (10х10)
		this.length = 10;
		this.heigth = 10;
		this.iterations = 10;
		this.agentsField = new Agent[length][heigth];
		this.agentsOpinions = new int[length][heigth][iterations+1];
		this.startFillAgents();
	}
	
	public Model(int length, int heigth, int iterations) { 	//конструктор модели заданного размера
		this.length = length;
		this.heigth = heigth;
		this.iterations = iterations;
		this.agentsField = new Agent[this.length][this.heigth];
		this.agentsOpinions = new int[this.length][this.heigth][this.iterations+1];
		this.startFillAgents();
	}
	
	public void startFillAgents() { 						//метод для стартового заполнения массива агентами случайным образом
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				int r = (int) (Math.random() * 6); 			//случайный выбор одного из классов
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
	
	public void printAgents() { 							//печать мнений стартового поля агентов
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsField[i][j].getOpinion() + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}
	
	public void printOpinions(int iteration) { 				//печать поля мнений заданной итерации
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.heigth; j++) {
				System.out.print(this.agentsOpinions[i][j][iteration] + " ");
			}
		System.out.println("");	
		}
		System.out.println("\n");
	}
	
	public int[] askNeighbors(int i, int j) { 				//универсальный опрос соседей - крест и по углам
		int SumCrossOpinions = 0;
		SumCrossOpinions = SumCrossOpinions		 			//мнения верх-низ слева-справа
			+ this.agentsOpinions[(this.length + i) % this.length][(this.heigth + j - 1) % this.heigth][0] //top
			+ this.agentsOpinions[(this.length + i) % this.length][(this.heigth + j + 1) % this.heigth][0] //base
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j) % this.heigth][0] //left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j) % this.heigth][0]; //right
		
		int SumXOpinions = 0;
		SumXOpinions = SumXOpinions							//мнения угловых соседей
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j - 1) % this.heigth][0] //top left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j - 1) % this.heigth][0] //top right
			+ this.agentsOpinions[(this.length + i - 1) % this.length][(this.heigth + j + 1) % this.heigth][0] //base left
			+ this.agentsOpinions[(this.length + i + 1) % this.length][(this.heigth + j + 1) % this.heigth][0]; //base right

		return new int[]{SumCrossOpinions,SumXOpinions,0};	//возвращаем в виде массива, потом можно будет выбрать вариант опроса
	}

	
	public static void main(String[] args) {
		Model m = new Model();								//создаем модель
		System.out.println("Начальные данные: \n");
		System.out.print("Размер поля агентов " + m.length + " на " + m.heigth + "\n");
		System.out.print("Количество итераций: " + m.iterations + "\n");
		System.out.println("Начальное состояние агентов:\n");
		m.printAgents();									//в дальнейшем пользоваться .printOpinions(0)

		if (m.iterations >= 1) {							//проверка на количество итераций
			for (int i = 0; i < m.length; i++) {			//нулевая итерация - заполняем её начальными мнениями
				for (int j = 0; j < m.heigth; j++) {
					m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
				}
			}
			
			System.out.println("Итерация 0");				//выводим на экран, для сверки с начальным состоянием
			m.printOpinions(0);
			
/*	
 * Выполняем итерации, начиная c 1, т.к. 0 занято исходными мнениями
 */
			for (int iteration = 1; iteration <= m.iterations; iteration++) { // от первой до заданной, включительно
				//обходим и сохраняем промежуточный результат в матрицу ответов, затем переписываем в соответствующий слой ответов
				
				for (int i = 0; i < m.length; i++) { 		//обходим агентов
					for (int j = 0; j < m.heigth; j++) {
						
						int[] neighborsOpinion = m.askNeighbors(i, j);	//спрашиваем мнение соседей вокруг, выдаем мнение большинства
						
						switch (m.agentsField[i][j].NeighborsPollType) {	//формируем новое мнение в зависимости от типа опроса
						case 0:								//cross
							if (neighborsOpinion[0] > 2) {	//возвращаем мнение большинства
								neighborsOpinion[2] = 1;
							} else if (neighborsOpinion[0] < 2) {
								neighborsOpinion[2] = 0;
							} else {
								neighborsOpinion[2] = m.agentsField[i][j].getOpinion(); //если ничья, то остаемся при своем мнении
							}
							break;
							
						case 1: //wheel
							int summOpinion = neighborsOpinion[0] + neighborsOpinion[1];
							if (summOpinion > 4) {			//принимаем сторону большинства
								neighborsOpinion[2] = 1;
							} else if (summOpinion < 4) {
								neighborsOpinion[2] = 0;
							} else {
								neighborsOpinion[2] = m.agentsField[i][j].getOpinion();	//если ничья, то остаемся при своем мнении
							}
							break;
							
						case 2: //"random"
							int i_random = (int) (Math.random() * (m.agentsField.length));	//случайные координаты (переделать на случайного соседа)
							int j_random = (int) (Math.random() * (m.agentsField[0].length));	//случайные координаты
							neighborsOpinion[2] = m.agentsField[i_random][j_random].getOpinion();
							break;
							
						default:
							System.out.print("неверный тип опроса соседей: "); //на случай проблем
							System.out.println(m.agentsField[i][j].NeighborsPollType);
							neighborsOpinion[2] = 0;
							break;
						}
						m.agentsOpinions[i][j][iteration] = neighborsOpinion[2]; //сохраняем промежуточный вариант
						}
					}
				for (int i = 0; i < m.length; i++) { //обходим матрицу ответов и меняем мнение агентов, потом возвращаем итог в ответы
					for (int j = 0; j < m.heigth; j++) {
						m.agentsField[i][j].setOpinion(m.agentsOpinions[i][j][iteration]);
						m.agentsOpinions[i][j][0] = m.agentsField[i][j].getOpinion();
					}
				}	
				System.out.println("Итерация " + iteration);	//выводим итерацию на экран
				m.printOpinions(iteration);	
				}
			} else { System.out.println("Количество итераций должно быть больше 1!"); } //жалуемся, если задано мало итераций
	}
}