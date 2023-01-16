package proLab3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmNodeDepth extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	static int max=0;
	static ArrayList<Integer> maxDepth= new ArrayList<>(); 
	public static ArrayList<DataStructures> trees = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<DataStructures> trees2) {
		trees=trees2;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNodeDepth frame = new frmNodeDepth();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public frmNodeDepth() {
		setTitle("Verilen kişiye ait nesil bulma");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("İsim ve soy isim giriniz...");
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(74, 52, 262, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPrintDepth = new JLabel("Derinlik:");
		lblPrintDepth.setBounds(51, 170, 316, 47);
		contentPane.add(lblPrintDepth);
		
		JButton btnSearch = new JButton("Aramayı Başlat");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < trees.size(); i++) {
					search(trees.get(i).root, textField.getText());
					if(maxDepth.size()>0) {
						Collections.sort(maxDepth);
						lblPrintDepth.setText(textField.getText() +" kişisinden sonra "+(maxDepth.get(maxDepth.size()-1)-1)+" nesil gelmiştir.");
					}
					
				}
				if(maxDepth.size()==0)
					lblPrintDepth.setText("Aranan kişi soy ağaçlarında bulunamadı!!!");
			}
		});
		btnSearch.setBounds(142, 105, 136, 42);
		contentPane.add(btnSearch);
		
		
	}
	static void search(Node node,String name) {
		if (node == null)
	        return ;
		if(node.partner!=null) {
			if(name.equalsIgnoreCase(node.partner.person.getFirstName().concat(" ").concat(node.partner.person.getLastName()))
					||name.equalsIgnoreCase(node.partner.person.getFirstName().concat(" ").concat(node.partner.person.getMaidenName())) ) {
			    System.out.println(node.partner.person.getFirstName() + " " + node.partner.person.getLastName());
			    maxDepth.clear();
			    depthOfTree(node.partner);
			    return;
			    }
		}
		
		    
		if(name.equalsIgnoreCase(node.person.getFirstName().concat(" ").concat(node.person.getLastName()))) {
		    System.out.println(node.person.getFirstName() + " " + node.person.getLastName());
		    maxDepth.clear();
		    depthOfTree(node);
		    return;
		}
	    search(node.child,name);
		
		search(node.sibling,name);
	}
	
	
	static void depthOfTree(Node node){
	
	    if (node == null)
	        return ;
	    System.out.println(node.person.getFirstName() + " " + node.person.getLastName());
	    max++;
	    depthOfTree(node.child);
		maxDepth.add(max);

		
		max--;
		depthOfTree(node.sibling);
		System.out.println(max);
	}
}
