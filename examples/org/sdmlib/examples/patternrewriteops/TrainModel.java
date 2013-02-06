package org.sdmlib.examples.patternrewriteops;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;

public class TrainModel
{
	
	@Test 
	public void makeModel()
	{
      Scenario scenario = new Scenario("examples", "TrainModel");
      
      scenario.add("Example model for testing destroy with model patterns: ",
      		Scenario.MODELING, "zuendorf", "01.02.2013 15:10:42", 1, 4);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.patternrewriteops");
            
      Clazz trainClass = model.createClazz("Train");
      
      Clazz stationClass = trainClass.createClassAndAssoc("Station", "station", R.ONE, "trains", R.MANY);

      stationClass.withAssoc(stationClass, "next", R.ONE, "prev", R.ONE);
      
      Clazz personClass = stationClass.createClassAndAssoc("Person", "people", R.MANY, "station", R.ONE);
      
      trainClass.withAssoc(personClass, "passengers", R.MANY, "train", R.ONE);
      
      stationClass.createClassAndAssoc("SignalFlag", "flag", R.ONE, "station", R.MANY);
      
      scenario.addImage(model.dumpClassDiag("examples", "TrainModelClassDiag"));
      
      model.generate("examples");
      
      scenario.dumpHTML();
	}
}
