package org.sdmlib.test.examples.simplelist;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.test.examples.helloworld.Person;
import org.sdmlib.test.examples.simpleModel.model.util.ObjectSet;

public class SimpleListTest {

	@Test
	public void testList() {
		ObjectSet list = new ObjectSet();
		int len = 1000000;
		for(int i=1;i<=len;i++) {
			list.add(new Person().withName("P_"+i));
		}
		for(Iterator<Object> i =list.iterator();i.hasNext();) {
			i.next();
		}
		for(int i=0;i<len;i++) {
			list.get(i);
		}
		
		Assert.assertEquals(len, list.size() );
		System.out.println("FINISH");
	}
}
