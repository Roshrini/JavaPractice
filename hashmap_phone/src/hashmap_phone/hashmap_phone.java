package hashmap_phone;
import java.util.*;

public class hashmap_phone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String,Integer> phone = new HashMap<String,Integer>();
		phone.put("ABc", 123);
		phone.put("bcd", 345);
		phone.put("cda", 523);
		phone.put("abc", 678);
		String personName = "abc";
		
		Set<String> keys = phone.keySet();
		
		for(String key : keys) {
			if(key.equalsIgnoreCase(personName)) {
				System.out.println(phone.get(key));
			}
		}
	}

}
