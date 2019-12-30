package facade;

import java.util.ArrayList;
import java.util.Date;
import composite.Task;
import frame.TaskList;
import memento.Memento;
import memento.CareTaker;
import observer.TaskObserver;
import frame.Frame;
import serialize.PointSerializer;
import serialize.SettingSerializer;
import serialize.TaskSerializer;
import setting.PointSetting;
import setting.TimeSetting;
import setting.TimeConfig;
import state.State;

public final class PomotodoFacade {
  private static PomotodoFacade facade = null;
  
  private Frame frame;
  public TaskObserver taskObserver;
  public CareTaker careTaker;
  public TimeSetting time;
  public ArrayList<Task> readedTaskList;
  public PointSetting point;
  public TimeConfig settTimeGUI;
  
  private PomotodoFacade() {
	  this.settTimeGUI = null;
	  this.time = SettingSerializer.getInstance().settReadFile();
	  this.readedTaskList = TaskSerializer.getInstance().taskReadFile();
	  this.point = PointSerializer.getInstance().pointReadFile();
	  this.taskObserver = new TaskObserver(this.readedTaskList);
	  this.frame = new Frame();
	  this.careTaker = new CareTaker();
	  this.careTaker.addMemento(new Memento(this.point, this.readedTaskList));
	  this.taskObserver.addToObserveList(this.point);
	  this.taskObserver.addToObserveList(this.frame);
  }
  
  public static synchronized PomotodoFacade getInstance(){
	  //sekaligus singleton
	  if(facade == null){
		  facade = new PomotodoFacade();		
	  }		
	  return facade;
  }
  
  public void setTitle() {
	  this.frame.setTitle("Total Point: " +  this.point.getTotal());
  }
 
  public void runApp() {
	  this.frame.run((new TaskList()).getList(this.readedTaskList));
	  setTitle();
  }
  
  public void taskDone(Task task) {
	  this.point.addPoint(5);
	  removeAndSave(task);
  }
  
  public void addToList(State state) { 
	  this.taskObserver.addToObserveList(state); 
  }
  
  public void save(){
	  TaskSerializer.getInstance().taskWriteFile(readedTaskList);
	  PointSerializer.getInstance().pointWriteFile(point);
  }
  
  public void removeAndSave(Task t) {
	  Task temp = t;
	  TaskObserver to = this.taskObserver;
	  to.hasUnsubscribed(temp);
	  to.hasUnsubscribefromList(temp, to.taskList);
	  runApp();
	  if (this.taskObserver.observed != null) {
		  this.taskObserver.setRunning(this.taskObserver.observed); 
	  }
	  save();
	  this.careTaker.addMemento(new Memento(this.point, this.readedTaskList));
  }
 
  public Date getTime() {
	  TimeSetting time = this.time;
	  long l = (l = (l = System.currentTimeMillis()) + (time.workMinute * 60 * 1000)) + (time.workSecond * 1000);
	  return new Date(l);
  }
}
