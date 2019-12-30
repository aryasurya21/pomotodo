package setting;

import java.io.Serializable;

public final class TimeSetting implements Serializable {
	private static final long serialVersionUID = 9176343450448696637L;
	public int workMinute;
    public int workSecond;
    public int restMinute;
    public int restSecond;
    
	public TimeSetting() {
		
		this.workMinute = 25;
		this.workSecond = 0;
		this.restMinute = 5;
		this.restSecond = 0;
	}
}