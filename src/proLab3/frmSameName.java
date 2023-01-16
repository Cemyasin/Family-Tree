package proLab3;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class frmSameName extends JFrame {
	static  ReadInventoryExcel r ;
	private JPanel contentPane;
	 ArrayList<String> names = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(ReadInventoryExcel r2) {
		r=r2;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSameName frame = new frmSameName();
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
	public frmSameName() {
		setTitle("Aynı isime sahip kişiler");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 789, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(136, 208, 491, 172);
		contentPane.add(scrollPane);


		JPanel panel = new JPanel();
		panel.setBounds(126, 31, 498, 80);
		contentPane.add(panel);

		JButton btnFam1 = new JButton("1.Aile");
		btnFam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSameNames(r.families[0]);
			}
		});
		btnFam1.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(btnFam1);

		JButton btnFam2 = new JButton("2.Aile");
		btnFam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSameNames(r.families[1]);
			}
		});
		btnFam2.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(btnFam2);

		JButton btnFam3 = new JButton("3.Aile");
		btnFam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSameNames(r.families[2]);
			}
		});
		btnFam3.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(btnFam3);

		JButton btnFam4 = new JButton("4.Aile");
		btnFam4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSameNames(r.families[3]);
			}
		});
		
		btnFam4.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(btnFam4);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		DefaultListModel<String> l = new DefaultListModel<>();	
		
		JButton btnSearch = new JButton("Aramayı Başlat");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String string : names) {
					l.addElement(string);
				}
				list.setModel(l);
				
			}
		});
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 20));
		btnSearch.setBounds(270, 122, 237, 50);
		contentPane.add(btnSearch);

		
		
	}

	void findSameNames(ArrayList<Person> family) {
		names.clear();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		for (int i = 0; i < family.size(); i++) {
			for (int j = i + 1; j < family.size(); j++) {
				if (family.get(i).getFirstName() == family.get(j).getFirstName()) {
					names.add(family.get(i).getLastName()+" Ailesi : " + family.get(i).getFirstName() + " " + family.get(i).getLastName() + "--"
							+ df.format(family.get(i).getBirthDay()) + "<---->" + family.get(j).getFirstName() + " "
							+ family.get(j).getLastName() + "--" + df.format(family.get(j).getBirthDay()));
				}
			}
		}
	}

}
