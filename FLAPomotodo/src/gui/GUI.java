package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.GapContent;

public class GUI extends JFrame implements ActionListener{
	JLabel labelTask;
	JTextField task;
	JButton buttonAdd,buttonStart,buttonDone,buttonRemove;
	JPanel panel1,panel2;
	
	public GUI() {
		initPanel();
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.AFTER_LINE_ENDS);
		setSize(800, 600);
		setVisible(true);
		setTitle("Total Point: ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void initPanel() {
		//add content
		task = new JTextField();
		buttonAdd = new JButton("Add");

		//added content
		labelTask = new JLabel(task.getText());
		buttonStart = new JButton("Start");
		buttonDone = new JButton("Done");
		buttonRemove = new JButton("Remove");
		
		//panel add content
		panel1 = new JPanel(new GridLayout(1,2));
		//panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(task);
		panel1.add(buttonAdd);	
		//panel added content
		//panel2 = new JPanel(new GridLayout(1, 4));
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(labelTask);
		panel2.add(buttonStart);
		panel2.add(buttonDone);
		panel2.add(buttonRemove);
		
		buttonAdd.addActionListener(this);
		buttonStart.addActionListener(this);
		buttonDone.addActionListener(this);
		buttonRemove.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonAdd) {
			this.add(panel2, BorderLayout.AFTER_LINE_ENDS);
			//add(panel1, BorderLayout.NORTH);
		}else if(e.getSource()==buttonStart) {
			//add(panel2, BorderLayout.AFTER_LINE_ENDS);
		}else if(e.getSource()==buttonDone) {
			
		}else if(e.getSource()==buttonRemove) {
			
		}
	}
	public static void main(String[] args) {
		new GUI();
	}
}
