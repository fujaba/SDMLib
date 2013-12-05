package org.sdmlib.examples.m2m.creators;

import org.sdmlib.models.pattern.creators.PatternCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ModelPatternCreator extends PatternCreator
{

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new ModelPattern();
   }

}
