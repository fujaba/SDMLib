package org.sdmlib.test.examples.SDMLib;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.DataType;

public class DataTypes {

	@Test
	public void testDataTypes(){
		Assert.assertEquals(DataType.STRING, DataType.ref("String"));
	}
}
