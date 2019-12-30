package composite;

import java.io.Serializable;
import java.util.ArrayList;
import facade.PomotodoFacade;
import timer.Timer;
import timer.BlankTimer;
import timer.WorkTimer;

public final class Task implements Serializable {
	public String taskName;
	public int level;
	private Task parentTask;
	public ArrayList<Task> childTasks;
	public Timer timer;

	public Task(Task task) {
		this.taskName = task.taskName;
		this.level = task.level;
		this.parentTask = task.parentTask;
		this.timer = task.timer;
		this.childTasks = new ArrayList <Task>();
		for (Task t : task.childTasks) {
			this.childTasks.add(new Task(t));
		}
	}

	public Task(String taskname, Task t) {
		this.childTasks = new ArrayList<Task>();
		this.taskName = taskname;
		this.timer = new BlankTimer();
		
		if (t != null) {
			this.level = t.level + 1;
			this.parentTask = t;
		} else {
			this.parentTask = null;
			this.level = 1;
		}
	}

	public void setWorkTimer() {
		this.timer = new WorkTimer(PomotodoFacade.getInstance().getTime());
	}

	public void setNoWorkTimer() {
		this.timer = new BlankTimer();
	}
	
	public int getLevel() {
		return level;
	}

	public String getTaskName() {
		return taskName;
	}
}