package builder;

import java.awt.Component;

public interface Builder {
	public abstract void createTextField();
	public abstract void createButton();
	public abstract Component getComponent();
	public abstract void resetComponent();
}
