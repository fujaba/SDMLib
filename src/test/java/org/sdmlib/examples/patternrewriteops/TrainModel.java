package org.sdmlib.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.storyboards.Storyboard;

public class TrainModel
{
	
	@Test 
	public void makeModel()
	{
      Storyboard storyboard = new Storyboard("examples", "TrainModel");
      
      storyboard.add("Example model for testing destroy with model patterns: ",
      		Storyboard.DONE, "zuendorf", "07.02.2013 16:20:42", 3, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.patternrewriteops.model");
            
      Clazz trainClass = model.createClazz("Train").with(model);
      
      Clazz stationClass = model.createClazz("Station").withAssoc(trainClass, "trains", Card.MANY, "station", Card.ONE);

      stationClass.withAssoc(stationClass, "next", Card.ONE, "prev", Card.ONE);
      
      Clazz personClass = model.createClazz("Person").withAssoc(stationClass, "station", Card.ONE, "people", Card.MANY);
      
      trainClass.withAssoc(personClass, "passengers", Card.MANY, "train", Card.ONE);
      
      model.createClazz("SignalFlag").withAssoc(stationClass, "station", Card.MANY, "flag", Card.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("examples");
      
      storyboard.dumpHTML();
	}
}
