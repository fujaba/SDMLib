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
import org.sdmlib.models.transformations.creators.TemplatePO;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPO;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.ObjectPO;
import java.lang.Object;

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

   public TemplatePO hasElementTemplatePO()
   {
      TemplatePO value = new TemplatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TemplatePO hasElementTemplatePO(Template hostGraphObject)
   {
      TemplatePO value = new TemplatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PlaceHolderDescriptionPO hasElementPlaceHolderDescriptionPO()
   {
      PlaceHolderDescriptionPO value = new PlaceHolderDescriptionPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PlaceHolderDescriptionPO hasElementPlaceHolderDescriptionPO(PlaceHolderDescription hostGraphObject)
   {
      PlaceHolderDescriptionPO value = new PlaceHolderDescriptionPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ObjectPO hasElementObjectPO()
   {
      ObjectPO value = new ObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ObjectPO hasElementObjectPO(Object hostGraphObject)
   {
      ObjectPO value = new ObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



