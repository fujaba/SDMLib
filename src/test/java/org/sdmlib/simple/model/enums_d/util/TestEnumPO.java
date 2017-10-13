package org.sdmlib.simple.model.enums_d.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.enums_d.TestEnum;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class TestEnumPO extends PatternObject<TestEnumPO, TestEnum>
{

    public TestEnumSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TestEnumSet matches = new TestEnumSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TestEnum) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TestEnumPO(){
      newInstance(null);
   }

   public TestEnumPO(TestEnum... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TestEnumPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TestEnumPO createValue0Condition(Integer value)
   {
      new AttributeConstraint()
      .withAttrName(TestEnum.PROPERTY_VALUE0)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TestEnumPO createValue0Assignment(Integer value)
   {
      new AttributeConstraint()
      .withAttrName(TestEnum.PROPERTY_VALUE0)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Integer getValue0()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TestEnum) getCurrentMatch()).getValue0();
      }
      return null;
   }
   
   public TestEnumPO withValue0(Integer value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TestEnum) getCurrentMatch()).setValue0(value);
      }
      return this;
   }
   
}
