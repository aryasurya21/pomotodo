package builder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import composite.Task;
import facade.PomotodoFacade;
import memento.Memento;
import observer.TaskObserver;
import serialize.TaskSerializer;
import state.State;

public final class ChildTaskBuilder extends PanelContainer implements State {
	private JButton addButton;

	public ChildTaskBuilder() {
		this.pTask = null;
		createComponent();
	}

	public ChildTaskBuilder(Task ptask) {
		this.pTask = ptask;
		createComponent();
	}

	private void createComponent() {
		this.setPreferredSize(new Dimension(700, 35));
		int level = 0;
		if (this.pTask != null) {
			Task t = this.pTask;
			level = t.getLevel() * 25;
		}

		this.setBorder(new EmptyBorder(0, level, 0, 0));
		this.setLayout(new BorderLayout());
		this.makeComponent();
	}

	private void makeComponent() {
		this.pTextField = new JTextField(30);
		this.add(this.pTextField, "West");
		
		this.addButton = new JButton("Add");
		JPanel tempPanel;
		(tempPanel = new JPanel()).setLayout(new FlowLayout());
		tempPanel.add(this.addButton);
		this.add(tempPanel, "East");
		this.addButton.addActionListener(this);
	}

	public final void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.addButton) {
			PomotodoFacade facade = PomotodoFacade.getInstance();
			String str = this.pTextField.getText();
			Task t = this.pTask;
			TaskObserver to = facade.taskObserver;
		
			Task newTask = new Task(str, t);
			if (t != null) {
				t.childTasks.add(newTask);
				
			} else {
				to.taskList.add(newTask);
			}

			TaskSerializer.getInstance().taskWriteFile(facade.readedTaskList);
			facade.careTaker.addMemento(new Memento(facade.point, facade.readedTaskList));
			facade.runApp();
		}

	}

	@Override
	public void idle() {
		this.pTextField.setEnabled(true);
		this.addButton.setEnabled(true);
	}

	@Override
	public void working(Task t) {
		this.pTextField.setEnabled(false);
		this.addButton.setEnabled(false);
	}

	@Override
	public void goWork(Task t) {
		//do nothing
	}

	@Override
	public void goRest(Task t) {
		//do nothing
	}
}
