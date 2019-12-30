package memento;

import java.util.ArrayList;

public final class CareTaker {
	public ArrayList<Memento> mementoList = new ArrayList<Memento>();
	public int step = -1;

	public void addMemento(Memento m) {
		for (int i = this.step + 1; i < this.mementoList.size(); ++i) {
			this.mementoList.remove(i);
		}

		this.mementoList.add(m);
		this.step = this.mementoList.size()-1;
	}
}
