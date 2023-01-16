package proLab3;

import java.awt.Color;
import java.util.ArrayList;

class Node {

	Person person;
	Node child;
	Node sibling;
	Node partner;
	Node father;
	Node mother;

	public Node(Person p) {
		this.person = p;
		mother = father = child = sibling = partner = null;
	}

	public void insert(Person per) {

		if (per.getPartner().equalsIgnoreCase(person.getFirstName().concat(" ").concat(person.getLastName()))
				|| per.getPartner() == person.getFirstName() || (per.getPartner()
						.equalsIgnoreCase(person.getFirstName().concat(" ").concat(person.getMaidenName())))) {
			partner = new Node(per);
			this.partner.partner = this;
			if (child != null) {
				if (partner.person.getGender().equals("Kadın")) {
					child.mother = this.partner;
					child.father = this;
				} else {
					child.father = this.partner;
					child.mother = this;
				}
				if (child.sibling != null) {
					if (partner.person.getGender().equals("Kadın")) {
						child.sibling.mother = this.partner;
						child.sibling.father = this;
					} else {
						child.sibling.father = this.partner;
						child.sibling.mother = this;
					}
				}
			}
			return;
		}

		if (per.getDadName() == person.getFirstName() || per.getMomName() == person.getFirstName()) {
			if (per.getGender().equals("Kadın")) {
				if (per.getMaidenName() == person.getLastName() || per.getLastName() == person.getLastName()) {
					if (child == null) {
						child = new Node(per);

						if (partner != null) {
							partner.child = this.child;
							if (partner.person.getGender().equals("Kadın")) {
								child.mother = this.partner;
								child.father = this;
							} else {
								child.father = this.partner;
								child.mother = this;
							}
						} else {
							if (this.person.getGender().equals("Kadın")) {
								child.mother = this;
							} else {
								child.father = this.partner;
							}
						}

						return;
					} else {
						if (child.sibling == null) {
							child.sibling = new Node(per);
							child.sibling.father = child.father;
							child.sibling.mother = child.mother;
							if (partner != null) {
								partner.child = this.child;
								partner.child.sibling = this.child.sibling;
							}

							return;
						} else {
							child.sibling.insert(per);
						}
					}
				}
			} else if (per.getGender().equals("Erkek")) {
				if (per.getLastName() == person.getLastName()) {
					if (child == null) {
						child = new Node(per);
						if (person.getGender().equals("Kadın")) {
							child.mother = this;
						} else
							child.father = this;
						if (partner != null)
							partner.child = this.child;
					} else {
						if (child.sibling == null) {
							child.sibling = new Node(per);
							child.sibling.father = child.father;
							child.sibling.mother = child.mother;
							if (partner != null) {
								partner.child = this.child;
								if (partner.person.getGender().equals("Kadın")) {
									child.sibling.mother = this.partner;
									child.sibling.father = this;
								} else {
									child.sibling.father = this.partner;
									child.sibling.mother = this;
								}

							} else {
								if (this.person.getGender().equals("Kadın")) {
									child.mother = this;
								} else {
									child.father = this.partner;
								}
							}

							return;
						} else {
							child.sibling.insert(per);
						}
					}

				}
			}

		} else if (per.getDadName() == person.getDadName() && per.getMomName() == person.getMomName()) {
			if (per.getLastName() == person.getMaidenName() || per.getLastName() == person.getLastName()
					|| per.getMaidenName() == person.getMaidenName() || per.getMaidenName() == person.getLastName()) {
				if (sibling == null) {
					sibling = new Node(per);
					sibling.father = this.father;
					sibling.mother = this.mother;
					return;
				} else {
					sibling.insert(per);
				}
			}

		}else if (child != null) {
			child.insert(per);
			if (sibling != null) {
				sibling.insert(per);
			}
		} else if (child == null) {
			if (sibling != null) {
				sibling.insert(per);
			}
		}
	}
}

class DataStructures {

	Node root;
	static int i = 0;
	static ArrayList<Person> nonChild = new ArrayList<>();

	public DataStructures(Person per) {
		root = new Node(per);
	}

	DataStructures() {
		root = null;

	}

	void dephtFirstSearch(Node node) {
		if (node == null)
			return;
		
		if (node.child == null) {
			nonChild.add(node.person);
		}
		dephtFirstSearch(node.child);

		System.out.println(node.person.getFirstName() + " " + node.person.getLastName());

		dephtFirstSearch(node.sibling);
	}

	void dephtFirstSearch() {
		dephtFirstSearch(root);
	}

	void breathFirstSearch(Node node) {

		if (node == null)
			return;

		if (node.partner != null)
			if (node.person.getGender().equalsIgnoreCase("Erkek")) {
				if (node.child.person.getMomName() != node.partner.person.getFirstName()) {
					System.out.println(node.child.person.getFirstName() + " " + node.child.person.getFirstName()
							+ "Üvey evlat ALERT!!!!");
				}
			} else {
				if (node.child.person.getDadName() != node.partner.person.getFirstName()) {
					System.out.println(node.child.person.getFirstName() + " " + node.child.person.getFirstName()
							+ "Üvey evlat ALERT!!!!");
				}
			}
		System.out.println(
				node.person.getFirstName() + " " + node.person.getLastName() + " " + node.person.getBirthDay());
		breathFirstSearch(node.sibling);
		breathFirstSearch(node.child);

	}

	void breathFirstSearch() {
		breathFirstSearch(root);
	}

	static int max = 0;
	ArrayList<Integer> maxDepth = new ArrayList<>();

	void depthOfTree(Node node) {

		if (node == null)
			return;
		System.out.println(node.person.getFirstName() + " " + node.person.getLastName());
		max++;
		depthOfTree(node.child);
		maxDepth.add(max);

		max--;
		depthOfTree(node.sibling);
		System.out.println(max);
	}
}
