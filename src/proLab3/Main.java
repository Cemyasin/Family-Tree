package proLab3;

import static proLab3.DataStructures.nonChild;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {
	static Thread thread;
	private static ArrayList<DataStructures> trees = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		//JFrame f=new JFrame();
		String file=" ";//= JOptionPane.showInputDialog(f,"Dosya İsmi Girniz");
		System.out.println(file);
		ReadInventoryExcel r = new ReadInventoryExcel();
		r.read(file);
		int flag = 0;

		DataStructures tree1 = new DataStructures(r.families[0].get(0));
		DataStructures tree2 = new DataStructures(r.families[1].get(0));
		DataStructures tree3 = new DataStructures(r.families[2].get(0));
		DataStructures tree4 = new DataStructures(r.families[3].get(0));

		for (int i = 1; i < r.families[0].size(); i++) {
			System.out.println(r.families[0].get(i).getFirstName());
			tree1.root.insert(r.families[0].get(i));
		}
		for (int i = 1; i < r.families[1].size(); i++) {
			tree2.root.insert(r.families[1].get(i));
		}
		for (int i = 1; i < r.families[2].size(); i++) {
			tree3.root.insert(r.families[2].get(i));

		}
		for (int i = 1; i < r.families[3].size(); i++) {
			tree4.root.insert(r.families[3].get(i));
		}

		// aileleri birleştirme
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (a == b)
					continue;
				
				for (int i = 0; i < r.families[b].size(); i++) {
					flag=0;
					for (int j = 0; j < r.families[a].size(); j++) {
						if (r.families[b].get(i).getId() == r.families[a].get(j).getId()) {
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
						if (a == 0) {
							tree1.root.insert(r.families[b].get(i));
							System.out.println(r.families[b].get(i).getFirstName() + " partner");
							flag = 0;
						} else if (a == 1) {
							tree2.root.insert(r.families[b].get(i));
							System.out.println(r.families[b].get(i).getFirstName() + " partner");
							flag = 0;
						} else if (a == 2) {
							tree3.root.insert(r.families[b].get(i));
							System.out.println(r.families[b].get(i).getFirstName() + " partner");
							flag = 0;
						} else if (a == 3) {
							tree4.root.insert(r.families[b].get(i));
							System.out.println(r.families[b].get(i).getFirstName() + " partner");
							flag = 0;
						}

					}
				}

			}

		}
		trees.add(tree1);
		trees.add(tree2);
		trees.add(tree3);
		trees.add(tree4);

		System.out.println(tree1.root.child.sibling.child.father.father.person.getFirstName()+" "+tree1.root.child.sibling.child.father.person.getLastName());
		System.out.println("*-*-*-*-*-*-*-*");
		
		tree1.depthOfTree(tree1.root);
		System.out.println(tree1.maxDepth);

		Collections.sort(tree1.maxDepth);
		System.out.println(tree1.maxDepth.get(tree1.maxDepth.size()-1));
		
		 frmMain.main(trees,r);
	

	}

	static void search(int i) {
		trees.get(0).dephtFirstSearch();
		trees.get(1).dephtFirstSearch();
		trees.get(2).dephtFirstSearch();
		trees.get(3).dephtFirstSearch();
	}

}
