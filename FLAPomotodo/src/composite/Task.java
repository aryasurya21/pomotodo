package composite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import builder.Director;
import builder.TaskBuilder;
import frame.Frame;

public class Task extends Element implements ActionListener {
	private Vector<Element> elements;

	public void addTask(Element e) {
		elements.add(e);
	}

	@Override
	public void addComponent(Element e) {
		// TODO Auto-generated method stub
		elements.add(e);
	}

	@Override
	public void removeComponent(Element e) {
		// TODO Auto-generated method stub
		elements.removeElement(e);
	}

	@Override
	public void setState(Element e) {
		// TODO Auto-generated method stub
		//manggil cara ngubah statenya nnti
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("(to be implemented) adding new task..");
	}
}
