import java.util.BitSet;

import org.junit.Test;

public class TestAlo {

	
	@Test
	public void testBitSet() {
		BitSet b1 = new BitSet(91);
		int[] nums = new int[] {1,2,90,76,78,67,56};
		for(int i = 0;i<nums.length;i++) {
			b1.set(nums[i]);
		}
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE);
		
		for(int j = 0;j<91;j++) {
			if(b1.get(j)) {
				System.out.println(j);
			}
		}
	
	}
	
	
	@Test
	public void  testBit01() {
		BitSet bm=new BitSet();
		System.out.println(bm.isEmpty()+"--"+bm.size());
		bm.set(0);
		System.out.println(bm.isEmpty()+"--"+bm.size());
		bm.set(1);
		System.out.println(bm.isEmpty()+"--"+bm.size());
		System.out.println(bm.get(65));
		System.out.println(bm.isEmpty()+"--"+bm.size());
		bm.set(65);
		System.out.println(bm.isEmpty()+"--"+bm.size());
	}
	
	
	@Test
	public void  testBit02() {
		BitSet bm=new BitSet();
		
	}
}
