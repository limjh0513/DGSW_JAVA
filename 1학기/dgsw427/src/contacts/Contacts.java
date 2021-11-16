package contacts;

import java.util.LinkedList;
import java.util.List;

public class Contacts {

	List<NameCard> contact = new LinkedList<NameCard>();
	int totalCnt = 0;

	public NameCard read(String name) {
		NameCard step;
		for (int i = 0; i < totalCnt; i++) {
			step = contact.get(i);
			if (step.getName() == name) {
				return step;
			}
		}
		return null;
	}

	public void save(String name, String phone) {
		NameCard makeCard = new NameCard();
		makeCard.setName(name);
		makeCard.setPhone(phone);

		contact.add(makeCard);
		totalCnt++;
	}

	public NameCard readByPhone(String phone) {
		NameCard step;
		for (int i = 0; i < totalCnt; i++) {
			step = contact.get(i);
			if (step.getPhone() == phone) {
				return step;
			}
		}
		return null;
	}

	public int getSize() {
		return totalCnt;
	}

	public void printAll() {
		NameCard stepCard;
		for (int i = 0; i < totalCnt; i++) {
			stepCard = contact.get(i);

			System.out.println(i + "번째 : " + stepCard.getName() + " - " + stepCard.getPhone());
		}

	}

	public static void main(String[] args) {
		Contacts con = new Contacts();
	}
}
