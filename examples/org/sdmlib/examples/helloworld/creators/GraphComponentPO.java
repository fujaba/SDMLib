package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.GraphComponent;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.creators.GraphComponentSet;
import org.sdmlib.examples.helloworld.creators.GraphPO;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.helloworld.creators.GraphComponentPO;

public class GraphComponentPO extends PatternObject<GraphComponentPO, GraphComponent>
{
   public GraphComponentPO startNAC()
   {
      return (GraphComponentPO) super.startNAC();
   }
   
   public GraphComponentPO endNAC()
   {
      return (GraphComponentPO) super.endNAC();
   }
   
   public GraphComponentSet allMatches()
   {
      GraphComponentSet matches = new GraphComponentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((GraphComponent) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public GraphComponentPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GraphComponent.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GraphComponent) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public GraphPO hasParent()
   {
      GraphPO result = new GraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }
   
   public GraphComponentPO hasParent(GraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GraphComponent.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Graph getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GraphComponent) this.getCurrentMatch()).getParent();
      }
      return null;
   }
   
   public GraphComponentPO hasText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GraphComponent.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GraphComponentPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public GraphPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public GraphComponentPO createParent(GraphPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

}



