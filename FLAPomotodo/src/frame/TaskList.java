package frame;

import java.util.ArrayList;
import builder.ParentTaskBuilder;
import builder.PanelContainer;
import builder.ChildTaskBuilder;
import composite.Task;
import facade.PomotodoFacade;

public final class TaskList {
	
  public final ArrayList<PanelContainer> getList(ArrayList<Task> list) {
	  ArrayList<PanelContainer> taskLists = new ArrayList<PanelContainer>();
    
	  for (Task Task : list){
		  addedList(Task, taskLists); 
	  }
    
	  ChildTaskBuilder childTask = new ChildTaskBuilder();
	  taskLists.add(childTask);
	  PomotodoFacade.getInstance().addToList(childTask);
	  return taskLists;
  }
  
  private void addedList(Task t, ArrayList<PanelContainer> list) {
	  ParentTaskBuilder parentTask = new ParentTaskBuilder(t);
	  list.add(parentTask);
	  PomotodoFacade.getInstance().addToList(parentTask);
	  Task taskTemp = t;
   
	  for (Task mytask : taskTemp.childTasks){
		  addedList(mytask, list); 
	  }
    	
	  if (taskTemp.getLevel() < 3) {
		  ChildTaskBuilder childTask = new ChildTaskBuilder(t);
		  list.add(childTask);
		  PomotodoFacade.getInstance().addToList(childTask);
	  }
  }
}
