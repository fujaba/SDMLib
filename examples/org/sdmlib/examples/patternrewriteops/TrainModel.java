package org.sdmlib.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.storyboards.Storyboard;

public class TrainModel
{
	
	@Test 
	public void makeModel()
	{
      Storyboard storyboard = new Storyboard("examples", "TrainModel");
      
      storyboard.add("Example model for testing destroy with model patterns: ",
      		Storyboard.DONE, "zuendorf", "07.02.2013 16:20:42", 3, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.patternrewriteops");
            
      Clazz trainClass = model.createClazz("Train");
      
      Clazz stationClass = trainClass.createClassAndAssoc("Station", "station", R.ONE, "trains", R.MANY);

      stationClass.withAssoc(stationClass, "next", R.ONE, "prev", R.ONE);
      
      Clazz personClass = stationClass.createClassAndAssoc("Person", "people", R.MANY, "station", R.ONE);
      
      trainClass.withAssoc(personClass, "passengers", R.MANY, "train", R.ONE);
      
      stationClass.createClassAndAssoc("SignalFlag", "flag", R.ONE, "station", R.MANY);
      
      storyboard.addSVGImage(model.dumpClassDiagram("examples", "TrainModelClassDiag"));
      
      model.generate("examples");
      
      storyboard.dumpHTML();
	}
}
