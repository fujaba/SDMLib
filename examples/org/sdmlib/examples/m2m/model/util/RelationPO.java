package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.m2m.model.Relation;
import org.sdmlib.examples.m2m.model.util.RelationSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.m2m.model.util.GraphPO;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.util.RelationPO;
import org.sdmlib.examples.m2m.model.util.PersonPO;
import org.sdmlib.examples.m2m.model.Person;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public RelationPO(Relation... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
          return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
           this.withCandidates(hostGraphObject);
      } else {
           this.withCandidates(hostGraphObject[0]);
      }
      pattern.findMatch();
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

}

