package builder;

import java.awt.Component;

public class Director {
	protected Builder currBuilder;
	
	public Director(Builder builder) {
		currBuilder = builder;
	}
	
	public void work(){
		currBuilder.createTextField();
		currBuilder.createButton();
	}
	
	public Component getComponent() {
		System.out.println("get componetn");
		return currBuilder.getComponent();
	}
}
