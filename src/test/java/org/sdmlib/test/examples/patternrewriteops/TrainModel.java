package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TrainModel
{
	
   /**
    * 
    * @see <a href='../../../../../../../../doc/TrainModel.html'>TrainModel.html</a>
*/
   @Test 
	public void TrainModel()
	{
      StoryPage storyboard = new StoryPage();
      
      storyboard.add("Example model for testing destroy with model patterns: ");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.patternrewriteops.model");
            
      Clazz trainClass = model.createClazz("Train").with(model);
      
      Clazz stationClass = model.createClazz("Station").withBidirectional(trainClass, "trains", Cardinality.MANY, "station", Cardinality.ONE);

      stationClass.withBidirectional(stationClass, "next", Cardinality.ONE, "prev", Cardinality.ONE);
      
      Clazz personClass = model.createClazz("Person").withBidirectional(stationClass, "station", Cardinality.ONE, "people", Cardinality.MANY);
      
      trainClass.withBidirectional(personClass, "passengers", Cardinality.MANY, "train", Cardinality.ONE);
      
      model.createClazz("SignalFlag").withBidirectional(stationClass, "station", Cardinality.MANY, "flag", Cardinality.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
	}
}
