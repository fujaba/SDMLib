package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.modelling_a.StudentEnum;
import java.lang.Integer;
import org.sdmlib.models.pattern.AttributeConstraint;

public class StudentEnumPO extends PatternObject<StudentEnumPO, StudentEnum>
{

    public StudentEnumSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StudentEnumSet matches = new StudentEnumSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StudentEnum) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public StudentEnumPO(){
      newInstance(null);
   }

   public StudentEnumPO(StudentEnum... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public StudentEnumPO filterValue0(Integer value)
   {
      new AttributeConstraint()
      .withAttrName(StudentEnum.PROPERTY_VALUE0)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StudentEnumPO createValue0(Integer value)
   {
      this.startCreate().filterValue0(value).endCreate();
      return this;
   }
   
   public Integer getValue0()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StudentEnum) getCurrentMatch()).getValue0();
      }
      return null;
   }
   
   public StudentEnumPO withValue0(Integer value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StudentEnum) getCurrentMatch()).setValue0(value);
      }
      return this;
   }
   
}
