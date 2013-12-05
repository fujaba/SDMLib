package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.creators.PatternCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ModelPatternCreator extends PatternCreator
{
   private ModelPattern prototyp;
   
   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      if (prototyp)
      {
         if (this.prototyp == null)
         {
            this.prototyp = new ModelPattern();
         }
         
         return this.prototyp;
      }
      
      return new ModelPattern();
   }
   
   
}
