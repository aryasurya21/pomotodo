package memento;

import java.util.ArrayList;

import composite.Task;
import setting.PointSetting;

public final class Memento {
  private ArrayList<Task> saved = new ArrayList<Task>();
  private PointSetting point;
  
  public Memento(PointSetting p, ArrayList<Task> taskList) {
	  this.point = new PointSetting(p.getTotal());
	
	  for (Task Task : taskList) {
		  this.saved.add(new Task(Task)); 
	  }
  }
  
  public final ArrayList<Task> returnList() {
	  ArrayList<Task> list = new ArrayList<Task>();
	  
	  for (Task Task : this.saved) {
		  list.add(new Task(Task)); 
	  }
	  
    return list;
  }
  
  public final PointSetting returnPoint() {
	  return new PointSetting(this.point.getTotal());
  }
}
