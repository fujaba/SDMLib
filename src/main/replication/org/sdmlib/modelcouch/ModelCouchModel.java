package org.sdmlib.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.DataType;

public class ModelCouchModel
{

	@Test
	public void genModelCouchModel()
	{
		ClassModel mCModel = new ClassModel("org.sdmlib.modelcouch");
		
		mCModel.createClazz("ModelCouch")
			.withAttribute("hostName", DataType.STRING, "localhost")
			.withAttribute("port", DataType.INT, "5984");
		
		mCModel.generate("src/main/replication");
	}
}
