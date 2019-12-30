package serialize;

import java.util.ArrayList;

import composite.Task;
import template.FileCheck;


public class TaskSerializer extends FileCheck {

	 private static TaskSerializer taskFile;
	    
	    static {
	        TaskSerializer.taskFile = null;
	    }
	    
	    private TaskSerializer() {
	    }
	    
	    public static TaskSerializer getInstance() {
	        if ( TaskSerializer.taskFile == null) {
	            synchronized (TaskSerializer.class) {
	                if ( TaskSerializer.taskFile == null) {
	                	 TaskSerializer.taskFile = new TaskSerializer();
	                }
	            }
	            
	        }
	        return  TaskSerializer.taskFile;
	    }
	    
	    public final ArrayList<Task> taskReadFile() {
	        ArrayList<Task>  list;
	        if ((list = (ArrayList)fileRead("tasks.ser")) == null) {
	            list = new ArrayList<>();
	            ArrayList<Task> listTemp = list;
	            taskWriteFile(listTemp);
	        }
	        
	        return list;
	    }
	    
	    public final void taskWriteFile(final ArrayList<Task> list) {
	       fileWrite("tasks.ser", list);
	    }
}
