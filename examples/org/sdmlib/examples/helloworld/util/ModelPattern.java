package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.helloworld.util.GreetingPO;
import org.sdmlib.examples.helloworld.Greeting;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }

   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public GreetingPO hasElementGreetingPO()
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GreetingPO hasElementGreetingPO(Greeting hostGraphObject)
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


