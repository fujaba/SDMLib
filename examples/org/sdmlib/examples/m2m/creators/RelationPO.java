package org.sdmlib.examples.m2m.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.m2m.Relation;
import org.sdmlib.examples.m2m.creators.RelationSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.m2m.creators.GraphPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.m2m.creators.RelationPO;
import org.sdmlib.examples.m2m.Graph;
import org.sdmlib.examples.m2m.creators.PersonPO;
import org.sdmlib.examples.m2m.Person;
import org.sdmlib.examples.m2m.GraphComponent;

public class RelationPO extends PatternObject<RelationPO, Relation>
{
   public RelationSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RelationSet matches = new RelationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Relation) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public RelationPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Relation) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public RelationPO withKind(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Relation) getCurrentMatch()).setKind(value);
      }
      return this;
   }
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Relation.PROPERTY_GRAPH, result);
      
      return result;
   }

   public RelationPO hasGraph(GraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Relation.PROPERTY_GRAPH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Graph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Relation) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public PersonPO hasSrc()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Relation.PROPERTY_SRC, result);
      
      return result;
   }

   public RelationPO hasSrc(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Relation.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Person getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Relation) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public PersonPO hasTgt()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Relation.PROPERTY_TGT, result);
      
      return result;
   }

   public RelationPO hasTgt(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Relation.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Person getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Relation) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

   public RelationPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
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
         return ((Relation) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public RelationPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Relation) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public GraphPO hasParent()
   {
      GraphPO result = new GraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }

   public RelationPO hasParent(GraphPO tgt)
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

   public RelationPO hasKind(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RelationPO hasText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}




