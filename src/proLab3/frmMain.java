package proLab3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import static proLab3.DataStructures.nonChild;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.ScrollPane;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

public class frmMain extends JFrame implements Runnable {
	public static ArrayList<DataStructures> trees = new ArrayList<>();
	static JPanel panel1 = new JPanel();
	static JPanel panel2 = new JPanel();
	static JPanel panel3 = new JPanel();
	static JPanel panel4 = new JPanel();
	static JPanel[] panels = new JPanel[4];
	static JLabel[] labels = new JLabel[70];
	static int max = 0;
	static ArrayList<Integer> maxDepth = new ArrayList<>();
	static ReadInventoryExcel r;
	static int i = 0;
	static int y = 40;
	static int gen = 0;
	static int[] x = { 735, 350, 80, 50 };
	static Thread thread;
	static JButton[] childs = new JButton[25];
	static JPanel pnlIster1 = new JPanel();

	public static void main(ArrayList<DataStructures> trees2, ReadInventoryExcel r2) {
		trees = trees2;
		r = r2;
		panels = new JPanel[] { panel1, panel2, panel3, panel4 };
		int k = 0;
		for (DataStructures dataStructures : trees) {
			familyTree(dataStructures.root, k++);
			// i = 0;
			y = 40;
			gen = 0;
			x[0] = 735;
			x[1] = 500;
			x[2] = 80;
			x[3] = 50;
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
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
	public frmMain() {
		
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("SOY AĞACI");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1900, 950);
		getContentPane().setLayout(null);

		JPanel panel5 = new JPanel();

		JComboBox comboBox = new JComboBox();

		comboBox.setFont(new Font("Papyrus", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1. Ister", "2. Ister", "3. Ister", "4. Ister",
				"5. Ister", "6. Ister", "7. Ister", "8. Ister", "9. Ister" }));
		comboBox.setBounds(1711, 111, 163, 55);
		getContentPane().add(comboBox);

		JPanel pnlIster = new JPanel();
		pnlIster.setVisible(false);
		pnlIster.setBounds(1711, 236, 125, 55);
		getContentPane().add(pnlIster);

		JButton btnNewButton = new JButton("Sırala");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					pnlIster.setVisible(false);
					frmNonChild.main(null);
//						Main.search(0);
//						printNonSibling();
					pnlIster1.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pnlIster.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(40, 50, 1600, 550);
		getContentPane().add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane(panel1);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		tabbedPane.addTab("1.Aile", scrollPane);

		// tabbedPane.addTab("1.Aile", null, panel1, null);
		panel1.setLayout(null);

		JLabel lblDephtFam1 = new JLabel("Derinlik :");
		lblDephtFam1.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblDephtFam1.setBounds(1352, 449, 214, 43);
		panel1.add(lblDephtFam1);

		tabbedPane.addTab("2.Aile", null, panel2, null);
		panel2.setLayout(null);

		JLabel lblDephtFam2 = new JLabel("Derinlik :");
		lblDephtFam2.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblDephtFam2.setBounds(791, 486, 214, 43);
		panel2.add(lblDephtFam2);

		tabbedPane.addTab("3.Aile", null, panel3, null);
		panel3.setLayout(null);

		JLabel lblDephtFam3 = new JLabel("Derinlik :");
		lblDephtFam3.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblDephtFam3.setBounds(791, 486, 214, 43);
		panel3.add(lblDephtFam3);

		tabbedPane.addTab("4.Aile", null, panel4, null);
		panel4.setLayout(null);

		JLabel lblDephtFam4 = new JLabel("Derinlik :");
		lblDephtFam4.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblDephtFam4.setBounds(791, 486, 214, 43);
		panel4.add(lblDephtFam4);

		pnlIster1.setVisible(false);
		pnlIster1.setFocusCycleRoot(true);
		pnlIster1.setBounds(1681, 69, 193, 489);
		getContentPane().add(pnlIster1);
		pnlIster1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblIsterler = new JLabel("ISTERLER");
		pnlIster1.add(lblIsterler);
		lblIsterler.setEnabled(false);
		lblIsterler.setBackground(Color.LIGHT_GRAY);
		lblIsterler.setFont(new Font("Pristina", Font.PLAIN, 27));

		DefaultComboBoxModel<String> bloods = new DefaultComboBoxModel<>();
		bloods.addElement("A");
		bloods.addElement("B");
		bloods.addElement("AB");
		bloods.addElement("0");

		JComboBox comboBox_2 = new JComboBox(bloods);

		JButton btnNewButton_2 = new JButton("Geri Dön");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIsterler.setVisible(true);
				comboBox.setVisible(true);
				pnlIster.setVisible(false);
				comboBox_2.setVisible(false);

				btnNewButton.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(1711, 796, 145, 46);
		getContentPane().add(btnNewButton_2);

		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String blood = "A";

				if (comboBox_2.getSelectedIndex() == 0)
					blood = "A";
				else if (comboBox_2.getSelectedIndex() == 1)
					blood = "B";
				else if (comboBox_2.getSelectedIndex() == 2)
					blood = "AB";
				else if (comboBox_2.getSelectedIndex() == 3)
					blood = "0";
				frmBloodType.main(r, blood);
			}
		});
		comboBox_2.setBounds(1711, 332, 142, 46);
		getContentPane().add(comboBox_2);
		comboBox_2.setVisible(false);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox.getSelectedIndex() == 0) {
					start();
					lblIsterler.setVisible(false);
					comboBox.setVisible(false);
					pnlIster.setVisible(true);
				}
				if (comboBox.getSelectedIndex() == 1)
					frmStepSibling.main(null);

				if (comboBox.getSelectedIndex() == 2) {
					comboBox_2.setVisible(true);

				}

				if (comboBox.getSelectedIndex() == 3)
					frmJob.main(trees);
				if (comboBox.getSelectedIndex() == 4)
					frmSameName.main(r);
				if (comboBox.getSelectedIndex() == 7) {
					maxDepth.clear();
					depthOfTree(trees.get(0).root);
					Collections.sort(maxDepth);
					lblDephtFam1.setText("Maksimum Derinlik: " + (maxDepth.get(maxDepth.size() - 1)));
					maxDepth.clear();
					depthOfTree(trees.get(1).root);
					Collections.sort(maxDepth);
					lblDephtFam2.setText("Maksimum Derinlik: " + (maxDepth.get(maxDepth.size() - 1)));
					maxDepth.clear();
					depthOfTree(trees.get(2).root);
					Collections.sort(maxDepth);
					lblDephtFam3.setText("Maksimum Derinlik: " + (maxDepth.get(maxDepth.size() - 1)));
					maxDepth.clear();
					depthOfTree(trees.get(3).root);
					Collections.sort(maxDepth);
					lblDephtFam4.setText("Maksimum Derinlik: " + (maxDepth.get(maxDepth.size() - 1)));
					maxDepth.clear();
				}
				if (comboBox.getSelectedIndex() == 8)
					frmNodeDepth.main(trees);
			}
		});

	}

	static void familyTree(Node node, int n) {

		if (node == null)
			return;

		System.out.println(node.person.getFirstName() + " " + node.person.getLastName());
		labels[i] = new JLabel();
		labels[i].setHorizontalAlignment(SwingConstants.CENTER);
		panels[n].add(labels[i]);
		if (node.person.getGender().compareTo("Erkek") == 0) {
			labels[i].setBackground(new Color(135, 206, 235));
		} else
			labels[i].setBackground(Color.pink);
		labels[i].setText(node.person.getFirstName() + " " + node.person.getLastName());
		labels[i].setBounds(x[gen], y, 100, 30);
		System.out.println(labels[i].getText() + " ---" + i);
		labels[i].setOpaque(true);
		labels[i].setVisible(true);
		if (node.partner != null) {
			i++;
			x[gen] = x[gen] + 90;
			labels[i] = new JLabel();
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			panels[n].add(labels[i]);
			labels[i].setText(node.partner.person.getFirstName() + " " + node.partner.person.getLastName());
			labels[i].setBounds(x[gen], y, 100, 30);
			labels[i].setOpaque(true);
			labels[i].setVisible(true);
			if (node.partner.person.getGender().compareTo("Erkek") == 0) {
				labels[i].setBackground(new Color(135, 206, 235));
			} else
				labels[i].setBackground(Color.pink);
			x[gen] = x[gen] + 35;
		} else if (node.person.getMaritalStatus().equalsIgnoreCase("Evli")) {
			i++;
			x[gen] = x[gen] + 90;
			labels[i] = new JLabel();
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			panels[n].add(labels[i]);
			labels[i].setText(node.person.getPartner());
			labels[i].setBounds(x[gen], y, 100, 30);
			labels[i].setOpaque(true);
			labels[i].setVisible(true);
			if (node.person.getGender().compareTo("Kadın") == 0) {
				labels[i].setBackground(new Color(135, 206, 235));
			} else
				labels[i].setBackground(Color.pink);
			x[gen] = x[gen] + 35;
		}

		i++;

		gen++;
		y = y + 50;
		familyTree(node.child, n);

		gen--;
		x[gen] = x[gen] + 125;
		y = y - 50;
		if (node.sibling == null)
			x[gen] = x[gen] + 125;

		familyTree(node.sibling, n);

	}

	static void depthOfTree(Node node) {
		if (node == null)
			return;
		max++;
		depthOfTree(node.child);
		maxDepth.add(max);

		max--;
		depthOfTree(node.sibling);
		System.out.println(max);
	}

	static int aa = 0;

	static void searching(Node node) {
		try {
			if (node == null)
				return;

			for (int i = aa; i < labels.length; i++) {
				System.out.println("!!!!!!!!!!!!!!!!! " + labels[i].getText() + " " + node.person.getFirstName() + " "
						+ node.person.getLastName());
				if (labels[i] != null) {
					if (labels[i].getText()
							.equalsIgnoreCase(node.person.getFirstName() + " " + node.person.getLastName())) {
						labels[i].setBackground(Color.orange);
						aa = i;
						break;
					}
				} else
					break;

			}

			Thread.sleep(500);
			if (node.child == null) {
				if (aa != -1)
					labels[aa].setBackground(Color.RED);
				Thread.sleep(200);
			}
			if (node.person.getGender().compareTo("Erkek") == 0) {
				labels[aa].setBackground(new Color(135, 206, 235));
			} else
				labels[aa].setBackground(Color.pink);

			System.out.println(node.person.getFirstName() + " " + node.person.getLastName());

			searching(node.child);

			searching(node.sibling);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		searching(trees.get(0).root);
		searching(trees.get(1).root);
		searching(trees.get(2).root);
		searching(trees.get(3).root);

	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public static void printNonSibling() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Person person : nonChild) {
			System.out.println("Çocuğu olmayan kişiler: " + person.getFirstName() + " " + person.getLastName() + " "
					+ df.format(person.getBirthDay()));
		}
		ArrayList<Person> nonList = new ArrayList<>();
		ArrayList<Date> dates = new ArrayList<>();
		for (int i = 0; i < nonChild.size(); i++) {
			dates.add(nonChild.get(i).getBirthDay());
		}

		for (int i = 0; i < nonChild.size(); i++) {
			for (int j = 0; j < nonChild.size(); j++) {
				if (nonChild.get(j).getBirthDay().compareTo(dates.get(i)) == 0) {
					nonList.add(nonChild.get(j));
					break;
				}
			}
		}
		// aynı kişileri kaldırma
		for (int i = 1; i < nonList.size(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nonList.get(i) == nonList.get(j)) {
					nonList.remove(j);
					i--;
				}
			}
		}

		for (int i = 0; i < nonList.size(); i++) {
			childs[i] = new JButton();
			pnlIster1.add(childs[i]);
			childs[i].setFont(new Font("Verdana", Font.PLAIN, 15));
			childs[i].setOpaque(true);
			if (nonList.get(i).getGender().compareTo("Erkek") == 0) {
				childs[i].setBackground(SystemColor.textHighlight);
			} else
				childs[i].setBackground(Color.PINK);
			pnlIster1.add(childs[i]);
			childs[i].setText(nonList.get(i).getFirstName() + " " + nonList.get(i).getLastName() + "--"
					+ df.format(nonList.get(i).getBirthDay()));
			System.out
					.println(i + "--" + nonList.get(i).getFirstName() + "--" + df.format(nonList.get(i).getBirthDay()));
		}
		Person temp;
		int max;
		for (int i = 0; i < nonList.size(); i++) {
			max = i;
			for (int j = i + 1; j < nonList.size(); j++) {

				if (nonList.get(max).getBirthDay().compareTo(nonList.get(j).getBirthDay()) > 0) {
					max = j;
					// System.out.println( nonList.get(j).getFirstName()+" büyük");
				}
			}
			temp = nonList.get(i);
			nonList.set(i, nonList.get(max));
			nonList.set(max, temp);
			for (int k = 0; k < nonList.size(); k++) {
				if (k == max || k == i) {
					System.out.print("***");
					childs[k].setBackground(new Color(102, 102, 102));

				}
				childs[k].setFont(new Font("Verdana", Font.PLAIN, 15));
				childs[k].setText(nonList.get(k).getFirstName() + " " + nonList.get(k).getLastName() + "--"
						+ df.format(nonList.get(k).getBirthDay()));
				if (nonList.get(k).getGender().compareTo("Erkek") == 0) {
					childs[k].setBackground(SystemColor.textHighlight);
				} else
					childs[k].setBackground(Color.PINK);
				System.out.println(k + ") " + nonList.get(k).getFirstName() + nonList.get(k).getLastName() + "--"
						+ df.format(nonList.get(k).getBirthDay()));
			}
			System.out.println();
		}

	}
}
