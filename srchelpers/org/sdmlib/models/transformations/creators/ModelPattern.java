package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.transformations.creators.TransformOpPO;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.models.transformations.creators.OperationObjectPO;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.creators.AttributeOpPO;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.transformations.creators.LinkOpPO;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.creators.StatementPO;
import org.sdmlib.models.transformations.Statement;

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

   public TransformOpPO hasElementTransformOpPO()
   {
      TransformOpPO value = new TransformOpPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TransformOpPO hasElementTransformOpPO(TransformOp hostGraphObject)
   {
      TransformOpPO value = new TransformOpPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public OperationObjectPO hasElementOperationObjectPO()
   {
      OperationObjectPO value = new OperationObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public OperationObjectPO hasElementOperationObjectPO(OperationObject hostGraphObject)
   {
      OperationObjectPO value = new OperationObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AttributeOpPO hasElementAttributeOpPO()
   {
      AttributeOpPO value = new AttributeOpPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AttributeOpPO hasElementAttributeOpPO(AttributeOp hostGraphObject)
   {
      AttributeOpPO value = new AttributeOpPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LinkOpPO hasElementLinkOpPO()
   {
      LinkOpPO value = new LinkOpPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LinkOpPO hasElementLinkOpPO(LinkOp hostGraphObject)
   {
      LinkOpPO value = new LinkOpPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StatementPO hasElementStatementPO()
   {
      StatementPO value = new StatementPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StatementPO hasElementStatementPO(Statement hostGraphObject)
   {
      StatementPO value = new StatementPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


