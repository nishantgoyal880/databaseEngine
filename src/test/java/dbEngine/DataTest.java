package dbEngine;

import static org.junit.Assert.*;
import com.query.*;

import org.junit.Test;

public class DataTest {

	QueryParameter param=new QueryParameter();
	
	@Test
	public void testSplit() {
		String s[]= {"a","b","c"};
		String t="a b c";
		assertArrayEquals(s,param.splitString(t));
	}
	
	@Test
	public void testFile() {
		String s[]= {"from","ipl.csv"};
		assertEquals("ipl.csv",param.findFileName(s));
	}


}
