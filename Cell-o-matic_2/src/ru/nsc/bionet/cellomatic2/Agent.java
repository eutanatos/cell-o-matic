package ru.nsc.bionet.cellomatic2;
public abstract class Agent {
	protected int opinion;							//ћнение, 0 или 1
	protected boolean zealot;						//‘анатизм - 0 дл€ прин€ти€ нового мнени€, 1 - дл€ сохранени€ прежнего
	protected String[] PollTypes = {"cross", "wheel", "random"}; //варианты опроса соседей
	protected boolean conformism;					// онформизм - склонность принимать мнение большинства, задаетс€ подклассом
	protected int NeighborsPollType;				//»ндекс варианта опроса соседей

/*
 *	конструкторы класса
 */	
	public Agent() {								//конструктор по умолчанию, задает случайные значени€ мнени€ и фанатичности
		this.opinion = (int) (Math.random() * 2); 	//равноверо€тное мнение 0 или 1
		this.zealot = (Math.random() < 0.5 ); 		//новый вариант равноверо€тного фанатизма
	}
	
	public Agent(int opinion, boolean zealot) { 	//конструктор с заданными мнением и фанатичностью
		this.opinion = opinion;
		this.zealot = zealot;
	}
	
/*
 * методы класса	
 */
	protected int getOpinion() { 					//текущее мнение
		return this.opinion;
	}
	
	protected void setOpinion(int newOpinion) { 	//установить новое мнение, тут провер€ем на фанатизм
		if (this.zealot) {							//если фанатик, то мнение не мен€ем
		} else {
		this.opinion = newOpinion;					//иначе - устанавливаем
		}
	}

	abstract void changeOpinion(int newOpinion);// { // метод дл€ изменени€ мнени€ на основании окружени€, виртуальна€ функци€

}
/*
–еализовать абстрактный класс +Agent (в котором, ориентировочно, будет +два пол€, +два конструктора, +два метода set, +два метода get, 
+виртуальна€ функци€ расчета изменени€ мнени€).
–еализовать 6 классов агентов-наследников, основанных на перечисленных на лекции стратеги€х изменени€ мнени€ 
( 3 топологии Ц крест, колесо и рандомклетка * 2 выбора Ц большинство и меньшинство). ¬ этих классах должна быть реализована 
виртуальна€ функци€ из класса-предка.
*/