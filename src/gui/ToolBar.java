package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {

	private JButton hellobtn;
	private JButton goodbyebtn;
	private StringListener textListener;

	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		hellobtn = new JButton("Hello");
		goodbyebtn = new JButton("Good Bye");

		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(hellobtn);
		add(goodbyebtn);
		hellobtn.addActionListener(this);
		goodbyebtn.addActionListener(this);
	}

	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton) e.getSource();
		if (clicked == hellobtn) {
			if (textListener != null) {
				textListener.textEmitted("Hello\n");
			}
		} else if (clicked == goodbyebtn) {
			if (textListener != null) {
				textListener.textEmitted("Good Bye \n");
			}

		}
	}

}
