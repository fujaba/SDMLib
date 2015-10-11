package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.storyboards.Storyboard;

public class TrainModel
{
	
   /**
    * 
    * @see <a href='../../../../../../../../doc/TrainModel.html'>TrainModel.html</a>
*/
   @Test 
	public void TrainModel()
	{
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Example model for testing destroy with model patterns: ");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.patternrewriteops.model");
            
      Clazz trainClass = model.createClazz("Train").with(model);
      
      Clazz stationClass = model.createClazz("Station").withAssoc(trainClass, "trains", Card.MANY, "station", Card.ONE);

      stationClass.withAssoc(stationClass, "next", Card.ONE, "prev", Card.ONE);
      
      Clazz personClass = model.createClazz("Person").withAssoc(stationClass, "station", Card.ONE, "people", Card.MANY);
      
      trainClass.withAssoc(personClass, "passengers", Card.MANY, "train", Card.ONE);
      
      model.createClazz("SignalFlag").withAssoc(stationClass, "station", Card.MANY, "flag", Card.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
	}
}
