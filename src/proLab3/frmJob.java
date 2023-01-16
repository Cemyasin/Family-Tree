package proLab3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JList;

public class frmJob extends JFrame {

	private JPanel contentPane;
	static ArrayList<String> jobs = new ArrayList<>();
	static ArrayList<DataStructures> trees = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(ArrayList<DataStructures> trees2) {
		trees = trees2;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmJob frame = new frmJob();
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

	public frmJob() {
		setTitle("Ata mesleğini devam ettirenler");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(31, 171, 612, 206);
		contentPane.add(scrollPane);
		JList<String> list = new JList<>();
		scrollPane.setViewportView(list);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 646, 94);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnFamily1 = new JButton("1.Aile");
		btnFamily1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sameJob(trees.get(0).root);
			}
		});
		btnFamily1.setBounds(65, 5, 83, 33);
		btnFamily1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnFamily1);

		JButton btnFamily2 = new JButton("2.Aile");
		btnFamily2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sameJob(trees.get(1).root);

			}
		});
		btnFamily2.setBounds(194, 5, 83, 33);
		btnFamily2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnFamily2);

		JButton btnFamily3 = new JButton("3.Aile");
		btnFamily3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sameJob(trees.get(2).root);
			}
		});
		btnFamily3.setBounds(325, 5, 83, 33);
		btnFamily3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnFamily3);

		JButton btnFamily4 = new JButton("4.Aile");
		btnFamily4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sameJob(trees.get(3).root);
			}
		});
		btnFamily4.setBounds(486, 5, 83, 33);
		btnFamily4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnFamily4);

		JButton btnNewButton = new JButton("Taramayı Başlat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> l=new DefaultListModel<>();
			//	textArea.setText(jobs.get(0));
				for (String string : jobs) {
					l.addElement(string);
				}
				list.setModel(l);
			}
		});
		btnNewButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		btnNewButton.setBounds(250, 60, 147, 23);
		panel.add(btnNewButton);
		
		
	}

	void sameJob(Node node) {

		if (node == null)
			return;

		sameJob(node.child);
		if (node.father != null)
			if (node.person.getJob() == node.father.person.getJob()) {
				if (node.father.father != null)
					if (node.person.getJob() == node.father.father.person.getJob()) {
						System.out.println(node.person.getJob() + "--" + node.person.getFirstName() + " "
								+ node.person.getLastName() + "<-->" + node.father.person.getFirstName() + " "
								+ node.father.person.getLastName() + "<-->" + node.father.father.person.getFirstName()
								+ " " + node.father.father.person.getLastName());
						jobs.add(node.person.getLastName() + " Ailesinde " + node.person.getJob()
								+ " mesleğini 3 nesildir yapan kişiler " + node.person.getFirstName() + " "
								+ node.person.getLastName()+"(kişi)" + "<-->" + node.father.person.getFirstName() + " "
								+ node.father.person.getLastName()+"(babası)" + "<-->" + node.father.father.person.getFirstName()
								+ " " + node.father.father.person.getLastName()+"(babasının babası)");
						return;
					}
				System.out.println(
						node.person.getJob() + "--" + node.person.getFirstName() + " " + node.person.getLastName()
								+ "<-->" + node.father.person.getFirstName() + " " + node.father.person.getLastName());
				jobs.add(node.person.getLastName()+" Ailesinde "+ node.person.getJob()
				+ " mesleğini 2 nesildir yapan kişiler " + node.person.getFirstName() + " "
				+ node.person.getLastName()+"(kişi)" + "<-->" + node.father.person.getFirstName()+ " "
				+ node.father.person.getLastName()+"(babası)");
			} else if (node.father.father != null) {
				if (node.person.getJob() == node.father.father.person.getJob()) {
					System.out.println(node.person.getJob() + "--" + node.person.getFirstName() + " "
							+ node.person.getLastName() + "<-->" + node.father.father.person.getFirstName() + " "
							+ node.father.father.person.getLastName());
					jobs.add(node.father.father.person.getLastName()+" Ailesinde "+ node.person.getJob()
					+ " mesleğini 2 nesildir yapan kişiler " + node.person.getFirstName() + " "
					+ node.person.getLastName()+"(kişi)" + "<-->" + node.father.father.person.getFirstName()
					+ " " + node.father.father.person.getLastName()+"(babasının babası)");
					return;
				}
			}
		if (node.mother != null)
			if (node.mother.father != null) {
				if (node.person.getJob() == node.mother.father.person.getJob()) {
					System.out.println(node.person.getJob() + "--" + node.person.getFirstName() + " "
							+ node.person.getLastName() + "<-->" + node.mother.father.person.getFirstName() + " "
							+ node.mother.father.person.getLastName());
					jobs.add(node.mother.father.person.getLastName()+" Ailesinde "+ node.person.getJob()
					+ " mesleğini 2 nesildir yapan kişiler " + node.person.getFirstName() + " "
					+ node.person.getLastName()+"(kişi)" + "<-->" + node.mother.father.person.getFirstName()+ " "
					+ node.mother.father.person.getLastName()+"(annesinin babası)");
					return;
				}
			}
		sameJob(node.sibling);

	}
}
