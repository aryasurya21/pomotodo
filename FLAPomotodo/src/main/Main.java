package main;

import facade.PomotodoFacade;;

public class Main {
	
	public Main() {
		 PomotodoFacade.getInstance().runApp(); 
	}
	
	public static void main(String[] args) { 
		 new Main();
	 }
  
}
