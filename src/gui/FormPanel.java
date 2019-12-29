package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okbtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField textField;
	private JLabel textLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name  :");
		occupationLabel = new JLabel("Occcupation :");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		okbtn = new JButton("OK");
		ageList = new JList();
		empCombo = new JComboBox();
		citizenCheck = new JCheckBox();
		textField = new JTextField(10);
		textLabel = new JLabel("Tax ID :");
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		genderGroup = new ButtonGroup();

		okbtn.setMnemonic(KeyEvent.VK_O);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);

		maleRadio.setSelected(true);
		// Set up Gender
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		// Set up tax ID
		textField.setEnabled(false);
		textLabel.setEnabled(false);

		citizenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean isTicked = citizenCheck.isSelected();
				textField.setEnabled(isTicked);
				textLabel.setEnabled(isTicked);
			}

		});

		// Set up List Box//
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "8 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or Over  "));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		// Set up Combo Box//
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("Employed");
		empModel.addElement("Self Employed");
		empModel.addElement("Unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);

		okbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empCat = (String) empCombo.getSelectedItem();
				String taxID = textField.getText();
				boolean usCitizen = citizenCheck.isSelected();

				String gender = genderGroup.getSelection().getActionCommand();

				System.out.println(empCat);

				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, taxID, usCitizen, gender);
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}

		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		/////////// First Row////////////////////////////////
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;

		gc.insets = new Insets(0, 0, 0, 5);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;

		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);

		/////////// Second Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .01;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);

		gc.gridx = 1;
		// gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);

		/////////// Third Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age :"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);

/////////// Fourth Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment :"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo, gc);

/////////// Fifth Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen :"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);

/////////// 6th Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(textLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(textField, gc);

/////////// 7th Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Gender :"), gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);

/////////// 8th Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);

/////////// 9th Row////////////////////////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;
		gc.gridx = 1;

		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okbtn, gc);

	}

	public void setFormListener(FormListener listener) {
		// TODO Auto-generated method stub
		this.formListener = listener;

	}

	class AgeCategory {
		private int id;
		private String text;

		public AgeCategory(int id, String text) {
			this.id = id;
			this.text = text;
		}

		public int getId() {
			// TODO Auto-generated method stub
			return id;
		}

		public String toString() {
			return text;
		}
	}
}
