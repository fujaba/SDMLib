package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.objects.creators.GenericGraphPO;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.creators.GenericObjectPO;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.creators.GenericAttributePO;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.creators.GenericLinkPO;
import org.sdmlib.models.objects.GenericLink;

public class ModelPattern extends Pattern
{
   public static ModelPattern lastPattern = null; 
   
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
      
      lastPattern = this;
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public GenericGraphPO hasElementGenericGraphPO()
   {
      GenericGraphPO value = new GenericGraphPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GenericGraphPO hasElementGenericGraphPO(GenericGraph hostGraphObject)
   {
      GenericGraphPO value = new GenericGraphPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GenericObjectPO hasElementGenericObjectPO()
   {
      GenericObjectPO value = new GenericObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GenericObjectPO hasElementGenericObjectPO(GenericObject hostGraphObject)
   {
      GenericObjectPO value = new GenericObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GenericAttributePO hasElementGenericAttributePO()
   {
      GenericAttributePO value = new GenericAttributePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GenericAttributePO hasElementGenericAttributePO(GenericAttribute hostGraphObject)
   {
      GenericAttributePO value = new GenericAttributePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GenericLinkPO hasElementGenericLinkPO()
   {
      GenericLinkPO value = new GenericLinkPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GenericLinkPO hasElementGenericLinkPO(GenericLink hostGraphObject)
   {
      GenericLinkPO value = new GenericLinkPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


