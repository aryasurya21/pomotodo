package decorator;

import java.awt.Color;
import javax.swing.JLabel;

public final class GreenDecorator extends JLabel implements IDecorator {
	private TaskDecorator taskDecorator;

	public GreenDecorator(TaskDecorator taskDecorator) {
		this.taskDecorator = (TaskDecorator) taskDecorator;
		taskDecorator.setForeground(Color.GREEN);
	}

	@Override
	public void start() {
		this.taskDecorator.start();
	}

	@Override
	public void stop() {
		this.taskDecorator.stop();
	}

	@Override
	public IDecorator returnSelf() {
		return this.taskDecorator;
	}
}
