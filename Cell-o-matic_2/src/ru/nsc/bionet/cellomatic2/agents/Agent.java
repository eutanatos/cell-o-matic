package ru.nsc.bionet.cellomatic2.agents;
public abstract class Agent {
	protected int opinion;										//Мнение, 0 или 1
	protected boolean zealot;									//Фанатизм - 0 для принятия нового мнения, 1 - для сохранения прежнего
	public enum PollTypes { CROSS, WHEEL, RANDOM};				//варианты опроса соседей
	protected boolean conformism;								//Конформизм - склонность принимать мнение большинства, задается подклассом		
	public PollTypes NeighborsPollType;							//Индекс варианта опроса соседей
	protected int[] crossPoll = {0,2,6,8};						//шаблон опроса по типу "крест"
	protected int[] wheelPoll = {1,3,5,7};						//шаблон опроса по типу "колесо"

/*
 *	конструкторы класса
 */	
	public Agent() {											//конструктор по умолчанию, задает случайные значения мнения и фанатичности
		this.opinion = (int) (Math.random() * 2); 				//равновероятное мнение {0, 1}
		this.zealot = (Math.random() < 0.5 ); 					//равновероятный фанатизм {true, false}
	}
	
	public Agent(int opinion, boolean zealot) { 				//конструктор с заданными мнением и фанатичностью
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
/*
 * методы класса	
 */
	public int getOpinion() { 									//текущее мнение
		return this.opinion;
	}
	
	public void setOpinion(int newOpinion) { 					//установить новое мнение, тут проверяем на фанатизм
		if (this.zealot) {										//если фанатик, то мнение не меняем
		} else {
		this.opinion = newOpinion;								//иначе - устанавливаем
		}
	}

	abstract void changeOpinion(int newOpinion);				// метод для изменения мнения на основании окружения, виртуальная функция

	public abstract int formOpinion(int[] neighborsOpinion); 	//спросить мнение соседей своим способом, виртуальная функция
	
	@Override
    public String toString() {									//перегруженный вывод информации по классу
		//return "{Op_" + this.opinion + ", Ze_" + this.zealot + ", Conf_" + this.conformism + ", Poll_" + this.NeighborsPollType + "}";
		return "{" + this.opinion + "," + this.zealot + "," + this.conformism + "," + this.NeighborsPollType + "}";
	}
}
