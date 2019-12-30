package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import composite.Task;
import decorator.TaskDecorator;
import facade.PomotodoFacade;
import observer.TaskObserver;
import setting.TimeSetting;

public final class TimerImplementor implements ActionListener {
	private TaskDecorator decorator;

	public TimerImplementor(TaskDecorator decorator) {
		this.decorator = decorator;
	}

	public final void actionPerformed(ActionEvent e) {
		TaskDecorator td = this.decorator;
		
		Date tempDate = this.decorator.task.timer.date;
		if (this.decorator.task.timer.date == null) {
			td.setText("");
		} else {
			Date d = new Date();
			long time;
			if ((time = tempDate.getTime() - d.getTime()) < 0L) {
				PomotodoFacade tempFacade = PomotodoFacade.getInstance();
				Task t = td.task;
				TaskObserver to = tempFacade.taskObserver;
				
				if (t.timer.getClass() == WorkTimer.class) {
					TimeSetting timer = tempFacade.time;
					long restTimer = System.currentTimeMillis() + (long) (timer.restMinute * 60 * 1000) + (long) (timer.restSecond * 1000);
					t.timer = new RestTimer(new Date(restTimer));
				} else if (t.timer.getClass() == RestTimer.class) {
					t.setWorkTimer();
				}

				if (t.timer.getClass() == WorkTimer.class) {
					to.goWork(t);
				
				}
				else{
					to.goRest(t);
					return;
				}

				
			} else {
				long second;
				long minute = (second = time / 1000L) / 60L;
				second %= 60L;
				String str = String.format("%02d:%02d", minute, second);
				td.setText(str);
			}

		}
	}
}
