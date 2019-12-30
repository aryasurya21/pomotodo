package setting;

import java.io.Serializable;
import composite.Task;
import serialize.PointSerializer;
import state.State;


public final class PointSetting implements State, Serializable{
	public int total;

	public PointSetting() {
		this.total = 0;
	}

	public PointSetting(int i) {
		this.total = i;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return total;
	}

	public final void addPoint(int i) {
		this.total += i;
		facade.PomotodoFacade.getInstance().setTitle();
		PointSerializer.getInstance().pointWriteFile(this);
	}

	@Override
	public void idle() {
		
	}

	@Override
	public void working(Task t) {
		
	}
	
	@Override
	public void goWork(Task t) {
		this.addPoint(10);
	}

	@Override
	public void goRest(Task t) {
		
	}
}
