package builder;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import composite.Task;

public abstract class PanelContainer extends JPanel implements ActionListener {
	JTextField pTextField;
	Task pTask;
}
