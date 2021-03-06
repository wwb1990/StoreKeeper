package window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objects.Vendor;

import java.awt.event.ActionListener;
import java.awt.List;
import java.awt.event.ActionEvent;

public class AddVendorDialog extends JDialog{

	MainManageWindow win;
	JPanel new_panel;
	JButton ok;
	JButton cancel;
	JLabel catCodeL;
	List vList = new List(10);
	ArrayList<JTextField> listOfVendorNames = new ArrayList<JTextField>();
	ArrayList<JTextField> listOfVendorDesc = new ArrayList<JTextField>();

	public AddVendorDialog(MainManageWindow win,String catCode) {
		this.win =win;
		createFormPanel(catCode);
        addWindowListener(new WindowAdapter() {
           @Override
		public void windowClosing(WindowEvent windowEvent){
              dispose();
           }
        });
	}
	
	public void createFormPanel(String catCode){

		getExistingVendors(catCode);
		
		setTitle("Add Vendors To Category");
		getContentPane().setLayout(null);
		
		new_panel = new JPanel();
		new_panel.setBounds(10, 192, 450, 210);
		new_panel.setLayout(null);
		getContentPane().add(new_panel);
		
		JLabel l1 = new JLabel("Category Code:");
		l1.setBounds(10, 10, 90, 30);
		getContentPane().add(l1);
		catCodeL = new JLabel(catCode);
		catCodeL.setBounds(131, 15, 86, 20);
		getContentPane().add(catCodeL);
		
		JLabel vLabel = new JLabel("Existing Vendors");
		vLabel.setBounds(10,46,120,20);
		getContentPane().add(vLabel);
		vList.setBounds(10,67,200,80);
		getContentPane().add(vList);
		
		JButton addVendor = new JButton("Add More Vendors");
		addVendor.setBounds(10, 161, 190, 20);
		getContentPane().add(addVendor);
		addVendor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addVendor.setEnabled(false);
				JLabel l_name = new JLabel("Vendor Name");
				l_name.setBounds(15, 11, 80, 14);
				new_panel.add(l_name);
				JLabel l_desc = new JLabel("Description");
				l_desc.setBounds(161, 11, 90, 14);
				new_panel.add(l_desc);
				
				JTextField tf_name = new JTextField(10);
				tf_name.setBounds(9, 32, 86, 20);
				new_panel.add(tf_name);
				listOfVendorNames.add(tf_name);
				JTextField tf_desc = new JTextField(10);
				tf_desc.setBounds(152, 32, 175, 20);
				new_panel.add(tf_desc);
				listOfVendorDesc.add(tf_desc);
				
				JButton btnNewButton = new JButton("+");
				btnNewButton.setBounds(347, 32, 45, 23);
				new_panel.add(btnNewButton);
				btnNewButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						addMoreVendors(57);
						btnNewButton.setEnabled(false);
					}
				});
				getContentPane().validate();
				getContentPane().repaint();
			}
			});
		
		ok = new JButton("Ok");
		ok.setBounds(132, 420, 68, 23);
		getContentPane().add(ok);
		cancel = new JButton("Cancel");
		cancel.setBounds(240, 420, 75, 23);
		
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					performOkAction();
			}
			});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			});
		
		getContentPane().add(cancel);
				
        setSize(500,500);
        setVisible(true);
}
	public void getExistingVendors(String catCode){
		ArrayList<Vendor> venList = win.getVendorsOfCategory(catCode);
		if(venList != null){
			for(Vendor v : venList){
				vList.add(v.getVendorName());
			}
		}
		else{
			vList.add("--No Vendors Present--");
		}
	}
	
	public void addMoreVendors(int y){
		JTextField tf_name = new JTextField(10);
		tf_name.setBounds(9, y, 86, 20);
		new_panel.add(tf_name);
		listOfVendorNames.add(tf_name);
		JTextField tf_desc = new JTextField(10);
		tf_desc.setBounds(152, y, 175, 20);
		new_panel.add(tf_desc);
		listOfVendorDesc.add(tf_desc);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBounds(347, y, 45, 23);
		new_panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addMoreVendors(y+25);
				btnNewButton.setEnabled(false);
			}
		});
		
		getContentPane().validate();
		getContentPane().repaint();
				
	}
	public void performOkAction(){
		System.out.println("Adding more vendors");
		for(int i=0;i<listOfVendorNames.size();i++){
			String vName = listOfVendorNames.get(i).getText();
			String vDesc = listOfVendorDesc.get(i).getText();
			if(!vName.isEmpty() && !vDesc.isEmpty()){
				win.addVendorsToCategory(catCodeL.getText(), vName, vDesc);
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(this,"Please Enter All Details.");
			}
			
		}
		}
}
