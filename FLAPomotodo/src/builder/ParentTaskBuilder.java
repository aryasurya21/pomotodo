package builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import composite.Task;
import decorator.RedDecorator;
import decorator.IDecorator;
import facade.PomotodoFacade;
import observer.TaskObserver;
import decorator.TaskDecorator;
import decorator.GreenDecorator;
import state.State;
import timer.BlankTimer;
import timer.RestTimer;
import timer.WorkTimer;

public final class ParentTaskBuilder extends PanelContainer implements State{
	private JButton btnStart; 
	private JButton btnDone; 
	private JButton btnRemove;
	private IDecorator decorator;

	public ParentTaskBuilder(Task pTask) {
		this.pTask = pTask;
		
		ParentTaskBuilder temp = this;
		temp.setPreferredSize(new Dimension(750, 30));
		
		Task tempTask = temp.pTask;
		
		int level = (tempTask.getLevel() - 1) * 25;

		temp.setBorder(new EmptyBorder(0,level,0,0));
		temp.setLayout(new BorderLayout());
		
		String taskName = tempTask.getTaskName();
		ParentTaskBuilder temp2 = temp;

		temp2.pTextField = new JTextField(20);
		temp2.pTextField.setText(taskName);
		temp2.pTextField.setEditable(false);
		temp2.add(temp2.pTextField, "West");	
		
		temp2.btnStart = new JButton("Start");
		temp2.btnDone = new JButton("Done");
		temp2.btnRemove = new JButton("Remove");
		
		temp2.btnStart.addActionListener(temp2);
		temp2.btnDone.addActionListener(temp2);
		temp2.btnRemove.addActionListener(temp2);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout());
		tempPanel.add(temp.btnStart);
		tempPanel.add(temp2.btnDone);
		tempPanel.add(temp2.btnRemove);
		temp2.add(tempPanel, "East");
		
		this.decorator = new TaskDecorator(this.pTask);
		this.add((Component) this.decorator);

		if ((this.pTask.timer).getClass() != BlankTimer.class) {
			if (this.pTask.timer.getClass() == WorkTimer.class) {
				this.decorator = new RedDecorator((TaskDecorator) this.decorator.returnSelf());
			}
			if (this.pTask.timer.getClass() == RestTimer.class) {
				this.decorator = new GreenDecorator((TaskDecorator) this.decorator.returnSelf());
			}
			this.decorator.start();
			this.btnStart.setText("Stop");
		}
		
	}
	
	@Override
	public final void actionPerformed(final ActionEvent actionEvent) {
		if (actionEvent.getSource() == this.btnStart) {
			if (this.btnStart.getText().equals("Start")) {	
				PomotodoFacade facade = PomotodoFacade.getInstance();
				Task tempTask = this.pTask;		
				TaskObserver to;
				boolean obs;
				
				if ((to = facade.taskObserver).observed != null) {
					obs = false;
				} 
				else {
					tempTask.setWorkTimer();
					to.observed = tempTask;
					to.setRunning(tempTask);
					obs = true;
				}

				if (!obs) {
					return;
				}

				this.btnStart.setText("Stop");
				this.decorator.start();
				return;
			}
			else if (this.btnStart.getText().equals("Stop")) {
				PomotodoFacade facade = PomotodoFacade.getInstance();
				Task Task = this.pTask;
		   
		        if (!facade.taskObserver.hasUnsubscribed(Task)){
		        	 return; 
		        }
		         
		        this.btnStart.setText("Start");
		        this.decorator.stop();
		        return;
			}			
		}
		else if (actionEvent.getSource() == this.btnDone) {
			  PomotodoFacade.getInstance().taskDone(this.pTask);
		}
		//remove
		else{
			 PomotodoFacade.getInstance().removeAndSave(this.pTask);
		}
	}
	
	@Override
	public void idle() {
		((JLabel) this.decorator).setText("");
		this.btnStart.setEnabled(true);
		this.btnDone.setEnabled(true);
		this.btnRemove.setEnabled(true);
	}

	@Override
	public void working(Task t) {
		if (t == this.pTask) {
			if (t.timer.getClass() == WorkTimer.class) {
				this.decorator = new RedDecorator((TaskDecorator) this.decorator.returnSelf());
				
			}
			if (t.timer.getClass() == RestTimer.class) {
				this.decorator = new GreenDecorator((TaskDecorator) this.decorator.returnSelf());
			}
			return;
		}
		this.btnStart.setEnabled(false);
		this.btnDone.setEnabled(false);
		this.btnRemove.setEnabled(false);
	}
	
	@Override
	public void goRest(Task t) {
		if (t != this.pTask) {
			return;
		}
		this.decorator = new GreenDecorator((TaskDecorator) this.decorator.returnSelf());	
	}
	
	@Override
	public void goWork(Task t) {
		if (t != this.pTask) {
			return;
		}
		this.decorator = new RedDecorator((TaskDecorator) this.decorator.returnSelf());
	}

}