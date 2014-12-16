package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
	private JPanel panel,contentPane,textPanel;
	private JScrollPane scrollPane;
	private JButton btnSearch,btnShow,btnGenerate;
	private MyTextField txtSearchBusinessRule;
	private ArrayList<JCheckBox> checkboxes;
	
	public MainFrame(BusinessRuleControl brc) {
		super("Business Rule generator tool");
		control = brc;
		createGUI();
	}
	
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
		
		
		btnSearch = new JButton("Search");
		textPanel.add(btnSearch, "cell 0 0,grow");
		btnSearch.addActionListener(searchButton);
		
		cb1 = new JComboBox<String>(control.getAllBusinessRulesTypeString());
		textPanel.add(cb1, "cell 0 1,grow");
		
		btnShow = new JButton("Show");
		textPanel.add(btnShow, "cell 0 1,grow");
		btnShow.addActionListener(showButton);
		
		scrollPane = new JScrollPane(contentPane);
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Business Rules Generator", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
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
	}
	
	public BusinessRuleControl getBusinessRuleControl() {
		return control;
	}
	
	public void setControl(BusinessRuleControl brc) {
		control = brc;
	}
	
	ActionListener searchButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {			
			for(JCheckBox c : checkboxes) {
				contentPane.remove(c);
			}
			String term = txtSearchBusinessRule.getText();
			checkboxes.clear();
			String labels[] = control.getAllBusinessRulesBySearch(term);
			for (int i = 0; i < labels.length; i++) {
	    		if(labels[i] == null) {
	    			break;
	    		}
	    		JCheckBox checkbox = new JCheckBox(labels[i]);
	    		contentPane.add(checkbox);
	    		checkboxes.add(checkbox);
	    	}
	    	pack();
		}
	};
	
	ActionListener showButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {			
			for(JCheckBox c : checkboxes) {
				contentPane.remove(c);
			}
			
			String type = cb1.getSelectedItem().toString();
			checkboxes.clear();
			String labels[] = control.getAllBusinessRulesByType(type);
			for (int i = 0; i < labels.length; i++) {
	    		if(labels[i] == null) {
	    			break;
	    		}
	    		JCheckBox checkbox = new JCheckBox(labels[i]);
	    		contentPane.add(checkbox);
	    		checkboxes.add(checkbox);
	    	}
	    	pack();
		}
	};
	
	ActionListener generateButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {			
			ArrayList<String> list = new ArrayList<String>();
			for(JCheckBox m : checkboxes) {
				if(m.isSelected()) {
					list.add(m.getText());
				}
			}
			
			if(list.size()>0) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Choose a save location");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					 File file = fc.getSelectedFile();
					 control.generate(list,file);
					 JOptionPane.showMessageDialog(null, list.size() + " Business Rules generated");
				}				
			}
			else {
				JOptionPane.showMessageDialog(null, "No Business Rules selected");
			}			
		}
	};

}
