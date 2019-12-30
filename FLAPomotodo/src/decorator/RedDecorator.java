package decorator;

import java.awt.Color;
import javax.swing.JLabel;

public final class RedDecorator extends JLabel implements IDecorator {
	private TaskDecorator taskDecorator;

	public RedDecorator(TaskDecorator taskDecorator) {
		this.taskDecorator = (TaskDecorator) taskDecorator;
		taskDecorator.setForeground(Color.RED);
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