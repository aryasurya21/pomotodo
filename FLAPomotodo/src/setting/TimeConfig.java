package setting;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import facade.PomotodoFacade;
import serialize.SettingSerializer;

public final class TimeConfig extends JFrame implements ActionListener {
	private JSpinner spinWorkMinute;
	private JSpinner spinWorkSecond;
	private JSpinner spinRestMinute;
	private JSpinner spinRestSecond;
    private JButton btnSave;
	
	
	public TimeConfig(final TimeSetting ts) {
		this.setTitle("Timer Setting");
        this.btnSave = new JButton("Save");
        (this.spinWorkMinute= new JSpinner(new SpinnerNumberModel(0, 0, 59, 1))).setPreferredSize(new Dimension(50, 25));
        (this.spinWorkSecond = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1))).setPreferredSize(new Dimension(50, 25));
        (this.spinRestMinute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1))).setPreferredSize(new Dimension(50, 25));
        (this.spinRestSecond = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1))).setPreferredSize(new Dimension(50, 25));
        
        readTimeSetting(ts);
        
        this.setDefaultCloseOperation(1);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(5, 1));
        final JPanel comp;
        (comp = new JPanel()).add(new JLabel("Work Minute"));
        comp.add(this.spinWorkMinute);
        final JPanel comp2;
        (comp2 = new JPanel()).add(new JLabel("Work Second"));
        comp2.add(this.spinWorkSecond);
        final JPanel comp3;
        (comp3 = new JPanel()).add(new JLabel("Rest Minute"));
        comp3.add(this.spinRestMinute);
        final JPanel comp4;
        (comp4 = new JPanel()).add(new JLabel("Rest Second"));
        comp4.add(this.spinRestSecond);
        this.add(comp);
        this.add(comp2);
        this.add(comp3);
        this.add(comp4);
        final JPanel comp5;
        (comp5 = new JPanel()).add(this.btnSave);
        this.add(comp5);
        this.setVisible(true);
        this.btnSave.addActionListener(this);
		
	}

	public void readTimeSetting(TimeSetting time){
		 this.spinWorkMinute.setValue(time.workMinute);
	     this.spinWorkSecond.setValue(time.workSecond);
	     this.spinRestMinute.setValue(time.restMinute);
	     this.spinRestSecond.setValue(time.restSecond);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() == this.btnSave) {
			 	int workMinute = (int) this.spinWorkMinute.getValue();
			 	int workSecond = (int) this.spinWorkSecond.getValue();
			 	int restMinute = (int) this.spinRestMinute.getValue();
			 	int restSecond = (int) this.spinRestSecond.getValue();
			 	
			 	PomotodoFacade facadeTemp;
			 	facadeTemp = PomotodoFacade.getInstance();
			 	
			 	facadeTemp.time.workMinute = workMinute;
			 	facadeTemp.time.workSecond = workSecond;
			 	facadeTemp.time.restMinute = restMinute;
			 	facadeTemp.time.restSecond = restSecond;
			 	
			 	SettingSerializer.getInstance().settWriteFile(facadeTemp.time);
			 	
	            this.setVisible(false);
	        }
	}
}