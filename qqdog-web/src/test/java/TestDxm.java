import java.util.LinkedList;

import org.junit.Test;


public class TestDxm {

	@Test
	public void test01(){
		LinkedList<String> link = new LinkedList<String>();
		link.add("a");
		link.add("b");
		link.add("c");
		link.add("d");
		
		System.out.println(link);
		
		String uid = link.pop();
		System.out.println(uid);
		link.add(uid);
		System.out.println(link);
	}
}
