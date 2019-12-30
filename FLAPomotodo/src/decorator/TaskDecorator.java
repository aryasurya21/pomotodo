package decorator;

import javax.swing.JLabel;
import javax.swing.Timer;
import composite.Task;
import timer.TimerImplementor;

public final class TaskDecorator extends JLabel implements IDecorator {
	public Task task;
	private Timer timer;

	public TaskDecorator(Task task) {
		this.task = task;
		this.timer = null;
	}

	@Override
	public void start() {
		this.timer = new Timer(100, new TimerImplementor(this));
		this.timer.start();
	}

	@Override
	public void stop() {
		if (this.timer != null) {
			this.timer.stop();
		}
		this.setText("");
	}

	@Override
	public IDecorator returnSelf() {
		return this;
	}
}