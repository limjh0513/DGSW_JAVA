package dgsw427.listStudy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapStudy {
	public static void main(String[] args) {
		Map<String, Integer> histoyMap = new HashMap<String, Integer>();
		
		histoyMap.put("갑오경장", 1894);
		histoyMap.put("해방", 1945);
		histoyMap.put("625사변", 1950);
		histoyMap.put("조선 건국", 1392);
		int year = histoyMap.get("조선 건국");
		System.out.println(year);
		
		Integer year1 = histoyMap.get("고려 건국"); //int로 선언시 nullPointException
		System.out.println(year1);
	
		
		boolean exist = histoyMap.containsKey("갑오경장");
		boolean exist2 = histoyMap.containsValue(1950);
		
		Set<String> keys = histoyMap.keySet(); //우리가 넣은 순서대로 들어가지 않음 map과 list의 차이점
		for(String key : keys) {
			System.out.println(key);
		}
	}
}
