package org.sdmlib.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

public class ModelCouchModel
{

	@Test
	public void genModelCouchModel()
	{
		ClassModel mCModel = new ClassModel("org.sdmlib.modelcouch");
		
		Clazz modelCouch = mCModel.createClazz("ModelCouch")
			.withAttribute("hostName", DataType.STRING, "localhost")
			.withAttribute("port", DataType.INT, "5984");
		
		Clazz modelDBListener = mCModel.createClazz("ModelDBListener");
		
		modelCouch.withAssoc(modelDBListener, "modelDBListener", Card.ONE, "couch", Card.ONE);
		
		mCModel.generate("src/main/replication");
	}
}
