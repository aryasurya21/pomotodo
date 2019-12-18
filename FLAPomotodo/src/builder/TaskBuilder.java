package builder;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import composite.Task;

public class TaskBuilder implements Builder {

	private JPanel panel = new JPanel();
	private JTextField textField;
	private JButton btnSubmit;
	
	public TaskBuilder() {
	}
	
	@Override
	public void createTextField() {
		// TODO Auto-generated method stub
		textField = new JTextField(26);
		panel.add(textField);
	}

	@Override
	public void createButton() {
		// TODO Auto-generated method stub
		btnSubmit = new JButton("Add");
		btnSubmit.addActionListener(new Task());
		panel.add(btnSubmit);
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		JPanel tempPanel = new JPanel();
		tempPanel = this.panel;
		resetComponent();
		
		return tempPanel;
	}
	
	public JButton getButton() {
		return this.btnSubmit;
	}

	@Override
	public void resetComponent() {
		// TODO Auto-generated method stub
		this.panel = new JPanel();
	}
}
