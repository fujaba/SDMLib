package org.sdmlib.examples.m2m.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.m2m.Graph;
import org.sdmlib.examples.m2m.creators.GraphSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.m2m.creators.PersonPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.m2m.creators.GraphPO;
import org.sdmlib.examples.m2m.Person;
import org.sdmlib.examples.m2m.creators.PersonSet;
import org.sdmlib.examples.m2m.creators.RelationPO;
import org.sdmlib.examples.m2m.Relation;
import org.sdmlib.examples.m2m.creators.RelationSet;
import org.sdmlib.examples.m2m.creators.GraphComponentPO;
import org.sdmlib.examples.m2m.GraphComponent;
import org.sdmlib.examples.m2m.creators.GraphComponentSet;

public class GraphPO extends PatternObject<GraphPO, Graph>
{
   public GraphSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GraphSet matches = new GraphSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Graph) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PersonPO hasPersons()
   {
      PersonPO result = new PersonPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Graph.PROPERTY_PERSONS, result);
      
      return result;
   }

   public GraphPO hasPersons(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Graph.PROPERTY_PERSONS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public RelationPO hasRelations()
   {
      RelationPO result = new RelationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Graph.PROPERTY_RELATIONS, result);
      
      return result;
   }

   public GraphPO hasRelations(RelationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Graph.PROPERTY_RELATIONS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public RelationSet getRelations()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getRelations();
      }
      return null;
   }

   public GraphComponentPO hasGcs()
   {
      GraphComponentPO result = new GraphComponentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Graph.PROPERTY_GCS, result);
      
      return result;
   }

   public GraphPO hasGcs(GraphComponentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Graph.PROPERTY_GCS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public GraphComponentSet getGcs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getGcs();
      }
      return null;
   }

}


