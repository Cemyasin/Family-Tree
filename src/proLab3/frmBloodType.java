package proLab3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import java.awt.SystemColor;

public class frmBloodType extends JFrame {
	public static DefaultListModel<String> blood= new DefaultListModel<>();
	private JPanel contentPane;
	static String bloods="";
	public static void main(ReadInventoryExcel r,String blooD) {
//		ReadInventoryExcel r=new ReadInventoryExcel() ;
		bloods=blooD;
		for (int i = 0; i < r.families.length; i++) {
			for (int j = 0; j < r.families[i].size(); j++) {
				if (r.families[i].get(j).getBloodType().equals(blooD.concat("(+)"))
						|| r.families[i].get(j).getBloodType().equals(blooD.concat("(-)"))) {
					blood.addElement(r.families[i].get(j).getBloodType()+"   "+r.families[i].get(j).getFirstName() + " " + r.families[i].get(j).getLastName());
				}
			}
		}
		
		for (int i = 1; i < blood.size(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (blood.get(i).equalsIgnoreCase(blood.get(j))) {
					blood.remove(j);
					i--;
				}
				System.out.println(blood.get(i)+"----"+blood.get(j));
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmBloodType frame = new frmBloodType();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmBloodType() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("                       --Kan Grubu "+bloods  +" Olan Kişiler--");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JList list = new JList(blood);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setAutoscrolls(false);
		
		contentPane = new JPanel();
		contentPane.setLocation(new Point(100, 100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(list, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(SystemColor.control);
		getContentPane().add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(SystemColor.control);
		getContentPane().add(lblNewLabel_2, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(SystemColor.control);
		getContentPane().add(lblNewLabel_3, BorderLayout.EAST);
		
		
		
	}

}
