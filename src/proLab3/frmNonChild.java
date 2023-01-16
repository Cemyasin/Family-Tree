package proLab3;

import static proLab3.DataStructures.nonChild;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class frmNonChild extends JFrame implements Runnable {
	static Thread thread;
	static JButton[] childs = new JButton[25];
	public static JPanel contentPane;
	static JPanel panel = new JPanel();

	public static void main(String[] args) throws Exception {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main.search(1);
					frmNonChild frame = new frmNonChild();
					frame.start();
					frame.setVisible(true);
					//printNonSibling();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmNonChild() {
		setTitle("Yaprak düğümler");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 389, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setAutoscrolls(true);

		panel.setBounds(27, 42, 315, 553);
		contentPane.add(panel);

	}

	public static void printNonSibling() {

		try {
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
				panel.add(childs[i]);
				childs[i].setFont(new Font("Verdana", Font.PLAIN, 15));
				childs[i].setOpaque(true);
				if (nonList.get(i).getGender().compareTo("Erkek") == 0) {
					childs[i].setBackground(SystemColor.textHighlight);
				} else
					childs[i].setBackground(Color.PINK);
				panel.add(childs[i]);
				childs[i].setText(nonList.get(i).getFirstName() + " " + nonList.get(i).getLastName() + "--"
						+ df.format(nonList.get(i).getBirthDay()));
				System.out.println(
						i + "--" + nonList.get(i).getFirstName() + "--" + df.format(nonList.get(i).getBirthDay()));
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
					childs[k].setFont(new Font("Verdana", Font.PLAIN, 14));
					childs[k].setText(nonList.get(k).getFirstName() + " " + nonList.get(k).getLastName() + "--"
							+ df.format(nonList.get(k).getBirthDay()));
					Thread.sleep(100);
					if (nonList.get(k).getGender().compareTo("Erkek") == 0) {
						childs[k].setBackground(SystemColor.textHighlight);
					} else  
						childs[k].setBackground(Color.PINK);
					System.out.println(k + ") " + nonList.get(k).getFirstName() + nonList.get(k).getLastName() + "--"
							+ df.format(nonList.get(k).getBirthDay()));
				}
				
				System.out.println();

				

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		printNonSibling();

	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

}
