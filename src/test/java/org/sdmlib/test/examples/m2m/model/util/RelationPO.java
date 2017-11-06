package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.m2m.model.util.GraphPO;
import org.sdmlib.test.examples.m2m.model.util.RelationPO;
import org.sdmlib.test.examples.m2m.model.util.PersonPO;

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


   public RelationPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RelationPO(Relation... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public RelationPO hasKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RelationPO hasKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RelationPO createKind(String value)
   {
      this.startCreate().hasKind(value).endCreate();
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
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public RelationPO hasGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_GRAPH);
   }

   public RelationPO createGraph(GraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
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
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_SRC, result);
      
      return result;
   }

   public PersonPO createSrc()
   {
      return this.startCreate().hasSrc().endCreate();
   }

   public RelationPO hasSrc(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_SRC);
   }

   public RelationPO createSrc(PersonPO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
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
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_TGT, result);
      
      return result;
   }

   public PersonPO createTgt()
   {
      return this.startCreate().hasTgt().endCreate();
   }

   public RelationPO hasTgt(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_TGT);
   }

   public RelationPO createTgt(PersonPO tgt)
   {
      return this.startCreate().hasTgt(tgt).endCreate();
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
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RelationPO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RelationPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
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
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }

   public GraphPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public RelationPO hasParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }

   public RelationPO createParent(GraphPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public Graph getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GraphComponent) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public RelationPO filterKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO filterKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GraphPO filterGraph()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_GRAPH, result);
      
      return result;
   }

   public RelationPO filterGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_GRAPH);
   }

   public PersonPO filterSrc()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_SRC, result);
      
      return result;
   }

   public RelationPO filterSrc(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_SRC);
   }

   public PersonPO filterTgt()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_TGT, result);
      
      return result;
   }

   public RelationPO filterTgt(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_TGT);
   }

   public RelationPO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GraphPO filterParent()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }

   public RelationPO filterParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }


   public RelationPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public RelationPO createKindCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO createKindCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO createKindAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GraphPO createGraphPO()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraphPO(String modifier)
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(modifier);
      super.hasLink(Relation.PROPERTY_GRAPH, result);
      
      return result;
   }

   public RelationPO createGraphLink(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_GRAPH);
   }

   public RelationPO createGraphLink(GraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_GRAPH, modifier);
   }

   public PersonPO createSrcPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_SRC, result);
      
      return result;
   }

   public PersonPO createSrcPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Relation.PROPERTY_SRC, result);
      
      return result;
   }

   public RelationPO createSrcLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_SRC);
   }

   public RelationPO createSrcLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_SRC, modifier);
   }

   public PersonPO createTgtPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_TGT, result);
      
      return result;
   }

   public PersonPO createTgtPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Relation.PROPERTY_TGT, result);
      
      return result;
   }

   public RelationPO createTgtLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_TGT);
   }

   public RelationPO createTgtLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_TGT, modifier);
   }

   public RelationPO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RelationPO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Relation.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GraphPO createParentPO()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Relation.PROPERTY_PARENT, result);
      
      return result;
   }

   public GraphPO createParentPO(String modifier)
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(modifier);
      super.hasLink(Relation.PROPERTY_PARENT, result);
      
      return result;
   }

   public RelationPO createParentLink(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_PARENT);
   }

   public RelationPO createParentLink(GraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Relation.PROPERTY_PARENT, modifier);
   }

}
