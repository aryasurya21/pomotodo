package state;

import composite.Task;

public interface State {
	public void working(Task t);
	public void goRest(Task t);
	public void goWork(Task t);
	public void idle();
}
