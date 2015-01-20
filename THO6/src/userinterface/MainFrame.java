package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import application_logic.BusinessRuleControl;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private BusinessRuleControl control;
	private JComboBox<String> cb1;
	private JPanel panel, contentPane, textPanel;
	private JScrollPane scrollPane;
	private JButton btnGenerate, btnSelectAll;
	private MyTextField txtSearchBusinessRule;
	private ArrayList<JCheckBox> checkboxes;

	public MainFrame(BusinessRuleControl brc) {
		super("Business Rule generator tool");
		control = brc;
		createGUI();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createGUI() {
		checkboxes = new ArrayList<JCheckBox>();

		contentPane = new JPanel();
		getContentPane().add(contentPane, BorderLayout.NORTH);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		textPanel = new JPanel();
		contentPane.add(textPanel);
		textPanel.setLayout(new MigLayout("", "[482px]", "[][2px]"));

		txtSearchBusinessRule = new MyTextField();
		txtSearchBusinessRule.setPlaceholder("Search rule");
		textPanel.add(txtSearchBusinessRule, "cell 0 0,grow");
		txtSearchBusinessRule.setColumns(13);
		txtSearchBusinessRule.addKeyListener(searchOnKey);

		cb1 = new JComboBox(control.getAllBusinessRulesTypeString().toArray());
		cb1.addActionListener(comboBoxChanged);
		textPanel.add(cb1, "cell 0 1,grow");

		btnSelectAll = new JButton("Select/De-select all");
		textPanel.add(btnSelectAll, "cell 0 2,grow");
		btnSelectAll.addActionListener(selectAllButton);

		scrollPane = new JScrollPane(contentPane);
		scrollPane.setViewportBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Business Rules Generator",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[241px][][241px]", "[25px]"));

		btnGenerate = new JButton("Generate");
		panel.add(btnGenerate, "cell 1 0,alignx center,aligny center");
		btnGenerate.addActionListener(generateButton);

		setSize(540, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		fillBusinessRules("typeSearch");
	}

	public void fillBusinessRules(String method) {
		if (method.equals("stringSearch")) {
			this.searchByString(txtSearchBusinessRule.getText());
		} else if (method.equals("typeSearch")) {
			this.searchByType(cb1.getSelectedItem().toString());
		}
		pack();
	}

	public void refreshBusinessRules(ArrayList<String> al) {
		for (JCheckBox c : checkboxes) {
			contentPane.remove(c);
		}
		for (String s : al) {
			JCheckBox checkbox = new JCheckBox(s);
			contentPane.add(checkbox);
			checkboxes.add(checkbox);
		}
	}

	public void searchByString(String searchTerm) {
		if(cb1.getSelectedIndex()!=0) {
			cb1.setSelectedIndex(0);
		}
		
		this.refreshBusinessRules(control
				.getAllBusinessRulesBySearch(searchTerm));
	}

	public void searchByType(String type) {
		txtSearchBusinessRule.setText("");
		refreshBusinessRules(control.getAllBusinessRulesByType(type));
	}

	ActionListener comboBoxChanged = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getModifiers()>0) {
				fillBusinessRules("typeSearch");
			}
		}
	};

	KeyListener searchOnKey = new KeyListener() {
		@Override
		public void keyReleased(KeyEvent keyEvent) {
			fillBusinessRules("stringSearch");
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	};

	ActionListener selectAllButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			boolean allChecked = true;
			for (JCheckBox c : checkboxes) {
				if (!c.isSelected()) {
					allChecked = false;
				}
			}
			if (!allChecked) {
				for (JCheckBox c : checkboxes) {
					c.setSelected(true);
				}
			} else {
				for (JCheckBox c : checkboxes) {
					c.setSelected(false);
				}
			}
		}
	};

	ActionListener generateButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> list = new ArrayList<String>();
			for (JCheckBox m : checkboxes) {
				if (m.isSelected()) {
					list.add(m.getText());
				}
			}

			if (list.size() > 0) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Choose a save location");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						control.generate(list, file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, list.size()
							+ " Business Rules generated");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"No Business Rules selected");
			}
		}
	};

}
