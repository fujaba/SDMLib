package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.patterns.example.creators.SimpleStatePO;
import org.sdmlib.models.patterns.example.SimpleState;
import org.sdmlib.models.patterns.example.creators.NodePO;
import org.sdmlib.models.patterns.example.Node;

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

   public SimpleStatePO hasElementSimpleStatePO()
   {
      SimpleStatePO value = new SimpleStatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SimpleStatePO hasElementSimpleStatePO(SimpleState hostGraphObject)
   {
      SimpleStatePO value = new SimpleStatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
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


