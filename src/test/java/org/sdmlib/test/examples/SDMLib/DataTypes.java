package org.sdmlib.test.examples.SDMLib;

import org.junit.Assert;
import org.junit.Test;

import de.uniks.networkparser.graph.DataType;

public class DataTypes {

	@Test
	public void testDataTypes(){
		Assert.assertEquals(DataType.STRING, DataType.create("String"));
	}
}
