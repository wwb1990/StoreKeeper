package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class confirmDialog extends JDialog{
	MainManageWindow win;
	JPanel panel;
	JButton yes;
	JButton no;
	JLabel label;
	
	public confirmDialog(MainManageWindow win,Object obj){
		this.win = win;
		createFormPanel(obj);
        addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              dispose();
           }
        });
	}
	
	public void createFormPanel(Object obj){
		panel = new JPanel();
		label = new JLabel("Are you sure you want to remove this?");
		label.setBounds(24, 26, 225, 14);
		yes = new JButton("Yes");
		yes.setBounds(60, 51, 60, 23);
		no = new JButton("No");
		no.setBounds(130, 51, 60, 23);
		panel.setLayout(null);
		panel.add(label);
		panel.add(yes);
        panel.add(no);
        getContentPane().add(panel);
        setTitle("Confirm Remove");
        setSize(300,150);
        setVisible(true);
        
		yes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					performOkAction(obj);
			}
			});
		no.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			});
	}
	
	public void performOkAction(Object obj){
		if(obj instanceof MemberPanel){
			win.removeSelectedMember();
			dispose();
		}
		if(obj instanceof ProductPanel){
			win.removeSelectedProduct();
			dispose();
		}
		if(obj instanceof CategoryPanel){
			win.removeSelectedCategory();
			dispose();
		}
		if(obj instanceof VendorPanel){
			win.removeSelectedVendorFromCategory();
			dispose();
		}
		if(obj instanceof DiscountPanel){
			win.removeSelectedDiscount();
			dispose();
		}

	}
}
