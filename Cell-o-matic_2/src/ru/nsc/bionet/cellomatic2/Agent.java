package ru.nsc.bionet.cellomatic2;
public abstract class Agent {
	protected int opinion;			//Мнение
	protected boolean zealot;		//Фанатизм - 0 для принятия нового мнения, 1 - для сохранения прежнего
	protected String[] PollTypes = {"cross", "wheel", "random"}; //варианты опроса соседей
	protected boolean conformism;
	protected int NeighborsPollType;

/*конструкторы класса*/	
	public Agent() {			//конструктор по умолчанию, задает случайные значения мнения и фанатичности
		this.opinion = (int) (Math.random() * 2); //равновероятное мнение 0 или 1
	/*	if ((Math.random() * 2) >= 1 ) { //старый вариант равновероятного фанатизма
			this.zealot = true;
		} else {
			this.zealot = false;
		}	*/
		this.zealot = (Math.random() < 0.5 ); // новый вариант равновероятного фанатизма
	}
	
	public Agent(int opinion, boolean zealot) { //конструктор с заданными мнением и фанатичностью
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
	protected int getOpinion() { //метод возвращает текущее мнение??? почему нельзя напрямую атрибут спросить?
		return this.opinion;
	}
	
	protected void setOpinion(int newOpinion) { //установить новое мнение
		if (this.zealot) {
			//do nothing
		} else {
		this.opinion = newOpinion;
		}
	}

	abstract void changeOpinion(int newOpinion);// { // метод для изменения мнения на основании окружения, виртуальная функция
	

	
	
	
	public int getNeighborsOpinion(Agent agentsField[][], int i, int j) { //опрос мнений соседей
		//два варианта опроса соседей находясь на краю матрицы - игнор отсутствующих или закольцевать массив, наверное, лучше через исключения...
		// 1 вариант
		
		// 2 вариант
		System.out.println("узнаем мнение соседей агента " + i + " " + j);
		Agent agentsFieldXL[][] = new Agent[agentsField.length+1][agentsField[0].length+1]; //матрица с -1 столбцом и строкой чтобы не было ошибки выхода за край влево-вверх
		System.out.println("Создали увеличенную матрицу +1");
		for (int k1 = 1; k1 < agentsFieldXL.length; k1++) { //заполняем
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
		//задаем переменные для расчета мнений
		int summCrossOpinion; //сумма мнений крест-накрест
		int summXOpinion; //сумма мнений по диагоналям
		int returnOpinion; //возвращаемое мнение
		summCrossOpinion = (agentsFieldXL[i+2][j+1].getOpinion() + agentsFieldXL[i][j+1].getOpinion() + agentsFieldXL[i+1][j+2].getOpinion() + agentsFieldXL[i+1][j].getOpinion());
		summXOpinion = (agentsFieldXL[i][j].getOpinion() + agentsFieldXL[i+2][j].getOpinion() + agentsFieldXL[i][j+2].getOpinion() + agentsFieldXL[i+2][j+2].getOpinion());
//		summCrossOpinion = (agentsFieldXL[i+1][j].getOpinion() + agentsFieldXL[i-1][j].getOpinion() + agentsFieldXL[i][j+1].getOpinion() + agentsFieldXL[i][j-1].getOpinion());
//		summXOpinion = (agentsFieldXL[i-1][j-1].getOpinion() + agentsFieldXL[i+1][j-1].getOpinion() + agentsFieldXL[i-1][j+1].getOpinion() + agentsFieldXL[i+1][j+1].getOpinion());

		
		
		switch (agentsField[i][j].NeighborsPollType) { //вычисление мнения соседей 
		case 0: //cross
			if (summCrossOpinion > 2) { //принимаем сторону большинства
				returnOpinion = 1;
			} else if (summCrossOpinion < 2) {
				returnOpinion = 0;
			} else {
				returnOpinion = agentsField[i][j].getOpinion(); //если ничья, то остаемся при своем мнении
			}
			break;
			
		case 1: //wheel
			int summOpinion = summCrossOpinion + summXOpinion;
			if (summOpinion > 4) { //принимаем сторону большинства
				returnOpinion = 1;
			} else if (summOpinion < 4) {
				returnOpinion = 0;
			} else {
				returnOpinion = agentsField[i][j].getOpinion(); //если ничья, то остаемся при своем мнении
			}
			break;
		case 2: //"random"
			int i_random = (int) (Math.random() * (agentsField.length)); //случайные координаты
			int j_random = (int) (Math.random() * (agentsField[0].length)); //случайные координаты
			returnOpinion = agentsField[i_random][j_random].getOpinion();
			break;
			
		default:
			System.out.println("неверный тип опроса соседей");
			System.out.println(agentsField[i][j].NeighborsPollType);
			returnOpinion = 0;
			break;
		}
		
		return(returnOpinion);
	}
	

}
/*
Реализовать абстрактный класс +Agent (в котором, ориентировочно, будет +два поля, +два конструктора, +два метода set, +два метода get, 
+виртуальная функция расчета изменения мнения).
Реализовать 6 классов агентов-наследников, основанных на перечисленных на лекции стратегиях изменения мнения 
( 3 топологии – крест, колесо и рандомклетка * 2 выбора – большинство и меньшинство). В этих классах должна быть реализована 
виртуальная функция из класса-предка.
*/