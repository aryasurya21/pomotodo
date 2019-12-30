package observer;

import java.util.ArrayList;
import java.util.Iterator;

import composite.Task;
import timer.BlankTimer;

public class TaskObserver extends Observer {
	public ArrayList<Task> taskList;
	public Task observed;

	public TaskObserver(ArrayList<Task> list) {
		this.taskList = list;
		Iterator<Task> iterator = list.iterator();

		while (iterator.hasNext()) {
			Task task;
			if ((task = (Task) iterator.next()).timer.getClass() != BlankTimer.class) {
				this.observed = task;
			}
		}

	}

	public final boolean hasUnsubscribed(Task t) {
		if (this.observed == null) {
			return false;
		} else {
			t.setNoWorkTimer();
			this.observed = null;
			this.goIdle();
			return true;
		}
	}

	public boolean hasUnsubscribefromList(Task t, ArrayList<Task> list) {
		if (list.remove(t)) {
			return true;
		} else {
			Iterator<Task> iterator = list.iterator();

			while (iterator.hasNext()) {
				Task temp = (Task) iterator.next();
				if (this.hasUnsubscribefromList(t, temp.childTasks)) {
					return true;
				}
			}
			return false;
		}
	}
}