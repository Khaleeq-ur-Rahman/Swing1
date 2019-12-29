package gui;

import java.awt.BorderLayout;

import javax.swing.JTable;

public class TablePanel extends javax.swing.JPanel {
	private JTable table;
	private PersonTableModel tableModel;

	public TablePanel() {

		table = new JTable(tableModel);
		tableModel = new PersonTableModel();

		setLayout(new BorderLayout());
		add(table, BorderLayout.CENTER);
	}
}
