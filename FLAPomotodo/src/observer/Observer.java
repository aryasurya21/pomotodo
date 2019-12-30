package observer;

import java.util.ArrayList;
import java.util.Iterator;
import composite.Task;
import state.State;

public abstract class Observer {
	public ArrayList<State> taskStateList = new ArrayList<State>();

	public final void addToObserveList(State s) {
		this.taskStateList.add(s);
	}

	public final void setRunning(Task t) {
		Iterator<State> tempIterator = this.taskStateList.iterator();

		while (tempIterator.hasNext()) {
			((State) tempIterator.next()).working(t);
		}

	}

	public final void goIdle() {
		Iterator<State> tempIterator = this.taskStateList.iterator();

		while (tempIterator.hasNext()) {
			((State) tempIterator.next()).idle();
		}

	}

	public final void goWork(Task t) {
		Iterator<State> iterator = this.taskStateList.iterator();

		while (iterator.hasNext()) {
			((State)iterator.next()).goWork(t);
		}
	}

	public final void goRest(Task t) {
		Iterator<State> iterator = this.taskStateList.iterator();

		while (iterator.hasNext()) {
			((State)iterator.next()).goRest(t);
		}
	}
}