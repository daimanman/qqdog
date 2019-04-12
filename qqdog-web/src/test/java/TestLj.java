import org.junit.Test;

public class TestLj {

	@Test
	public void testLast() {
		String m = "com.man.qqdog.biz.mapper.LjAreaInfoPoCustomMapper";
		
		int index = m.indexOf("Mapper");
		
		System.out.println(index);
		System.out.println(m.substring(0,index));
	}
}
