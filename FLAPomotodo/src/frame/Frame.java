package frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import builder.PanelContainer;
import composite.Task;
import facade.PomotodoFacade;
import memento.Memento;
import memento.CareTaker;
import setting.TimeConfig;
import state.State;

public final class Frame extends JFrame implements ActionListener, State {
	private JMenu menu;
	private JMenuBar mb;
	private JMenuItem setting;
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem close;

	public final void run(ArrayList<PanelContainer> taskList) {
		getContentPane().removeAll();			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,500);
		
		mb = new JMenuBar();
		menu = new JMenu("File");	
		setting = new JMenuItem("Setting");
		setting.setAccelerator(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke('Z',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke('Y',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		close = new JMenuItem("Close");
		close.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		setting.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		close.addActionListener(this);
		
		menu.add(setting);
		menu.add(undo);
		menu.add(redo);
		menu.add(close);
		mb.add(menu);
		setJMenuBar(mb);
		
		readTask(taskList);
		
		setVisible(true);
	}

	private void readTask(ArrayList<PanelContainer> list) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700, list.size() * 60));
		panel.setLayout(new FlowLayout());
		
		if (list != null) {
			Iterator<PanelContainer> iteratorPanel = list.iterator();

			while (iteratorPanel.hasNext()) {
				Component tempComponent = (Component) iteratorPanel.next();
				panel.add(tempComponent);
			}
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(750, 600));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.getContentPane().add(scrollPane);
	}

	public final void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.setting) {
			PomotodoFacade facade = PomotodoFacade.getInstance();
			if (facade.settTimeGUI == null) {
				facade.settTimeGUI = new TimeConfig(facade.time);
			} else {
				facade.settTimeGUI.readTimeSetting(facade.time);
				facade.settTimeGUI.setVisible(true);
			}
		} 
		
		else if (e.getSource() == this.close) {
			this.dispose();
		}
		
		else {
			PomotodoFacade facade = PomotodoFacade.getInstance();
			Memento memento;
			CareTaker ct = facade.careTaker;
			ArrayList<Task> tl;
			
			if (e.getSource() == this.undo) {
				memento = ct.step <= 0 ? null : (Memento) ct.mementoList.get(--ct.step);
			
				if (memento != null) {
					facade.readedTaskList = memento.returnList();
					tl = facade.readedTaskList;
					facade.taskObserver.taskList = tl;
					facade.point = memento.returnPoint();
					facade.runApp();
					facade.save();
				}

			} else if (e.getSource() == this.redo) {
				memento = ct.step >= ct.mementoList.size() - 1 ? null : (Memento) ct.mementoList.get(++ct.step);
			
				if (memento != null) {
					facade.readedTaskList = memento.returnList();
					tl = facade.readedTaskList;
					facade.taskObserver.taskList = tl;
					facade.point = memento.returnPoint();
					facade.runApp();
					facade.save();
				}
			}
		} 
		
	}

	@Override
	public void idle() {
		this.setting.setEnabled(true);
		this.undo.setEnabled(true);
		this.redo.setEnabled(true);
	}

	@Override
	public void working(Task t) {
		this.setting.setEnabled(true);
		this.undo.setEnabled(true);
		this.redo.setEnabled(true);
	}

	@Override
	public void goWork(Task t) {
		
	}

	@Override
	public void goRest(Task t) {
		
	}
	
}