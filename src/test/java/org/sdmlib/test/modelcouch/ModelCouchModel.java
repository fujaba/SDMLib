package org.sdmlib.test.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelCouchModel
{

	@Test
	public void genModelCouchModel()
	{
		ClassModel mCModel = new ClassModel("org.sdmlib.modelcouch");
		
		Clazz modelCouch = mCModel.createClazz("ModelCouch");
		modelCouch.createAttribute("hostName", DataType.STRING).withValue("localhost");
		modelCouch.createAttribute("port", DataType.INT).withValue("5984");
		
		Clazz modelDBListener = mCModel.createClazz("ModelDBListener");
		
		modelCouch.withBidirectional(modelDBListener, "modelDBListener", Association.ONE, "couch", Association.ONE);
		
		mCModel.generate("src/main/replication");
	}
}
