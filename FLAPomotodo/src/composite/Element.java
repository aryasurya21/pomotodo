package composite;

import javax.swing.JPanel;

public abstract class Element {
	public int elementState;
	public abstract void addComponent(Element e);
	public abstract void removeComponent(Element e);
	public abstract void setState(Element e);
}
