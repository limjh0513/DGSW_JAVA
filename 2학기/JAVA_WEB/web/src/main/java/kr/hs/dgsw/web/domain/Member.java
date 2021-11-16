package kr.hs.dgsw.web.domain;

import java.util.Calendar;

public class Member {
	private int memberIdx;
	private String email;
	private String password;
	private String name;
	private String contact;
	private String birthday;
	private String registerTime;

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public int getAge() {
		if (birthday == null) {
			return -1;
		}
		String sYear = birthday.substring(0, 4);
		int year = Integer.parseInt(sYear);

		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);

		return thisYear - year + 1;
	}
}
