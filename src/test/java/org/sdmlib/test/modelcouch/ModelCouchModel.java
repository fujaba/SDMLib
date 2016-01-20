package org.sdmlib.test.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelCouchModel
{

	@Test
	public void genModelCouchModel()
	{
		ClassModel mCModel = new ClassModel("org.sdmlib.modelcouch");
		
		Clazz modelCouch = mCModel.createClazz("ModelCouch")
			.with(new Attribute("hostName", DataType.STRING).withValue("localhost"))
			.with(new Attribute("port", DataType.INT).withValue("5984"));
		
		Clazz modelDBListener = mCModel.createClazz("ModelDBListener");
		
		modelCouch.withBidirectional(modelDBListener, "modelDBListener", Cardinality.ONE, "couch", Cardinality.ONE);
		
		mCModel.generate("src/main/replication");
	}
}
