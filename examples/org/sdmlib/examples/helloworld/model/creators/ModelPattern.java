package org.sdmlib.examples.helloworld.model.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.helloworld.model.creators.NodePO;
import org.sdmlib.examples.helloworld.model.Node;

public class ModelPattern extends Pattern
{
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public NodePO hasElementNodePO()
   {
      NodePO value = new NodePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public NodePO hasElementNodePO(Node hostGraphObject)
   {
      NodePO value = new NodePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


