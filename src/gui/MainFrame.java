package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private JButton btn;
	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;

	public MainFrame() {
		super("Hello CRA");

		setLayout(new BorderLayout());
		btn = new JButton("Hello");
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		controller = new Controller();
		tablePanel = new TablePanel();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		setJMenuBar(createMenuBar());

		toolBar.setStringListener(new StringListener() {
			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});

		formPanel.setFormListener(new FormListener() {
			@Override
			public void formEventOccurred(FormEvent e) {
				// TODO Auto-generated method stub
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmpCat();
				textPanel.appendText(name + " :" + occupation + ":" + ageCat + ":" + empCat + " " + " \n");
				System.out.println(e.getGender());

				controller.addPerson(e);
			}
		});
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPanel.appendText("Hello Abdullah How are you\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(btn, BorderLayout.SOUTH);
		add(tablePanel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);

		setMinimumSize(new Dimension(500, 400));
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");

		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.add(exitItem);

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());

			}

		});
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());

				}
			}

		});
		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());

				}
			}

		});

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = JOptionPane.showInputDialog(MainFrame.this, "Enter your user name", "Enter user name",
						JOptionPane.OK_OPTION | JOptionPane.WARNING_MESSAGE);
				System.out.println(text);
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you Really want to exit the application",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}

		});
		return menuBar;

	}

}
