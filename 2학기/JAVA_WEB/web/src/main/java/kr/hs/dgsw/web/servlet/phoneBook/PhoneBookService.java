package kr.hs.dgsw.web.servlet.phoneBook;

import java.util.List;

public interface PhoneBookService {
	public PhoneNumber create(PhoneNumber phoneNumber);
	
	public PhoneNumber read(int idx);
	
	public void update(PhoneNumber phoneNumber);
	
	public void delete(int idx);
	
	public List<PhoneNumber> list();
}
