package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.roomInterface;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class roomInterfacePO extends PatternObject<roomInterfacePO, roomInterface>
{

    public roomInterfaceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      roomInterfaceSet matches = new roomInterfaceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((roomInterface) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public roomInterfacePO(){
      newInstance(null);
   }

   public roomInterfacePO(roomInterface... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public roomInterfacePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public roomInterfacePO createNumberCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(roomInterface.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public roomInterfacePO createNumberCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(roomInterface.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public roomInterfacePO createNumberAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(roomInterface.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((roomInterface) getCurrentMatch()).getNumber();
      }
      return 0;
   }
   
   public roomInterfacePO withNumber(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((roomInterface) getCurrentMatch()).setNumber(value);
      }
      return this;
   }
   
}
