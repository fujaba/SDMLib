package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SharedModelRoot;
import org.sdmlib.replication.creators.SharedModelRootSet;
import java.lang.Object;
import org.sdmlib.models.pattern.AttributeConstraint;

public class SharedModelRootPO extends PatternObject<SharedModelRootPO, SharedModelRoot>
{
   public SharedModelRootSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SharedModelRootSet matches = new SharedModelRootSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SharedModelRoot) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public SharedModelRootPO hasContent(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SharedModelRoot.PROPERTY_CONTENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Object getContent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedModelRoot) getCurrentMatch()).getContent();
      }
      return null;
   }
   
   public SharedModelRootPO withContent(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedModelRoot) getCurrentMatch()).setContent(value);
      }
      return this;
   }
   
}

