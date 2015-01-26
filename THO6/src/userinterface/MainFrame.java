package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import application_logic.BusinessRuleControl;
import application_logic.LoginControl;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private BusinessRuleControl control;
	private JComboBox<String> cb1;
	private JPanel bottomPanel, contentPanel, searchPanel, logoutPanel, topPanel;
	private JScrollPane scrollPane;
	private JButton btnGenerate, btnSelectAll,btnRefresh, btnLogout;
	private JLabel labUsername;
	private MyTextField txtSearchBusinessRule;
	private ArrayList<JCheckBox> checkboxes;

	public MainFrame(BusinessRuleControl brc) {
		super("Business Rule generator tool");
		control = brc;
		if(control.getAllBusinessRules()!=null) {
			createGUI();
		}
		else {
			errorMessage();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createGUI() {
		checkboxes = new ArrayList<JCheckBox>();
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		logoutPanel = new JPanel();
		logoutPanel.setBackground(Color.decode("#b20b00"));
		topPanel.add(logoutPanel, BorderLayout.NORTH);
		logoutPanel.setLayout(new MigLayout("", "[600px][80px][30px]", "[][2px]"));
		
		labUsername = new JLabel("User: "+control.getUsername());
		labUsername.setForeground(Color.decode("#ffffff"));
		logoutPanel.add(labUsername, "cell 1 0, grow");
		
		btnLogout = new JButton("Logout");
		logoutPanel.add(btnLogout, "cell 2 0,grow");
		btnLogout.addActionListener(logoutButton);

		searchPanel = new JPanel();
		searchPanel.setBackground(Color.decode("#b20b00"));
		topPanel.add(searchPanel, BorderLayout.SOUTH);
		searchPanel.setLayout(new MigLayout("", "[300px][][300px]", "[][2px]"));

		txtSearchBusinessRule = new MyTextField();
		txtSearchBusinessRule.setBorder(BorderFactory.createCompoundBorder(
				txtSearchBusinessRule.getBorder(), 
		        BorderFactory.createEmptyBorder(2, 2, 2, 0)));
		txtSearchBusinessRule.setBackground(Color.decode("#efefef"));
		txtSearchBusinessRule.setPlaceholder("Search rule");
		searchPanel.add(txtSearchBusinessRule, "cell 1 0,grow");
		txtSearchBusinessRule.setColumns(13);
		txtSearchBusinessRule.addKeyListener(searchOnKey);

		cb1 = new JComboBox(control.getAllBusinessRulesTypeString().toArray());
		cb1.addActionListener(comboBoxChanged);
		searchPanel.add(cb1, "cell 1 1,grow");	
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.decode("#efefef"));
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));		

		scrollPane = new JScrollPane(contentPanel);
		scrollPane.setPreferredSize(new Dimension(540,300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#b0b0b0"));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new MigLayout("", "[241px][][241px]", "[25px]"));

		btnGenerate = new JButton("Generate");
		btnGenerate.setBackground(Color.decode("#e3e3e3"));
		bottomPanel.add(btnGenerate, "cell 1 0,alignx center,aligny center");
		btnGenerate.addActionListener(generateButton);
		
		btnSelectAll = new JButton("Select all");
		btnSelectAll.setBackground(Color.decode("#e3e3e3"));
		bottomPanel.add(btnSelectAll, "cell 1 0,alignx center,aligny center");
		btnSelectAll.addActionListener(selectAllButton);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(Color.decode("#e3e3e3"));
		bottomPanel.add(btnRefresh, "cell 1 0,alignx center,aligny center");
		btnRefresh.addActionListener(refreshButton);

		setSize(540, 300);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		fillBusinessRules("typeSearch");
	}
	
	public void errorMessage() {
		JOptionPane.showMessageDialog(null,	"No Business Rules available. Please check your connectivity!");
	}

	public void fillBusinessRules(String method) {
		if (method.equals("stringSearch")) {
			this.searchByString(txtSearchBusinessRule.getText());
		} else if (method.equals("typeSearch")) {
			this.searchByType(cb1.getSelectedItem().toString());
		}
		btnSelectAll.setText("Select All");
		pack();
	}

	public void refreshBusinessRules(ArrayList<String> al) {
		ArrayList<JCheckBox> unselectedRules = new ArrayList<JCheckBox>();
		for (JCheckBox c : checkboxes) {
			contentPanel.remove(c);
			unselectedRules.add(c);
		}
		
		for(JCheckBox c : unselectedRules) {
			checkboxes.remove(c);
		}
		
		for (String s : al) {
			JCheckBox checkbox = new JCheckBox(s);
			checkbox.setBorder(new EmptyBorder(10, 10, 10, 10));
			checkbox.addItemListener(chechkboxChanged);
			contentPanel.add(checkbox);
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

	KeyAdapter searchOnKey = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent keyEvent) {
			fillBusinessRules("stringSearch");
		}

	};
	
	ItemListener chechkboxChanged = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox c = (JCheckBox) e.getItem();
			if(!c.isSelected()) {
				btnSelectAll.setText("Select All");
			}
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
				btnSelectAll.setText("Deselect all");
			} else {
				for (JCheckBox c : checkboxes) {
					c.setSelected(false);
				}
				btnSelectAll.setText("Select all");
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
				int returnVal = fc.showOpenDialog(contentPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						control.generate(list, file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, list.size()
							+ " Business Rules generated");					
					dispose();
					control.fillDomainFromDatabase();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"No Business Rules selected");
			}
		}
	};
	
	ActionListener refreshButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			control.fillDomainFromDatabase();
		}
	};
	
	ActionListener logoutButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new LoginControl();
		}
	};

}
