package frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import builder.TaskBuilder;

public class Frame {
			
	public Frame() {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("Pomotodo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(300, 300);
		
		JMenuBar mb = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenuItem settings = new JMenuItem("Settings");
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem close = new JMenuItem("Close");
		
		file.add(settings);
		file.add(undo);
		file.add(redo);
		file.add(close);
	
		JPanel p = new JPanel();
		TaskBuilder builder = new TaskBuilder();
		builder.createTextField();
		builder.createButton();
		p.add(builder.getComponent());

		mb.add(file);
		frame.setJMenuBar(mb);
		frame.setContentPane(p);
		frame.setVisible(true);
	}
}
