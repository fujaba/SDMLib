package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;
import org.sdmlib.test.examples.m2m.model.util.GraphComponentPO;
import org.sdmlib.test.examples.m2m.model.util.GraphPO;
import org.sdmlib.test.examples.m2m.model.util.PersonPO;
import org.sdmlib.test.examples.m2m.model.util.RelationPO;

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


   public GraphPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GraphPO(Graph... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public GraphComponentPO hasGcs()
   {
      GraphComponentPO result = new GraphComponentPO(new GraphComponent[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_GCS, result);
      
      return result;
   }

   public GraphComponentPO createGcs()
   {
      return this.startCreate().hasGcs().endCreate();
   }

   public GraphPO hasGcs(GraphComponentPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_GCS);
   }

   public GraphPO createGcs(GraphComponentPO tgt)
   {
      return this.startCreate().hasGcs(tgt).endCreate();
   }

   public GraphComponentSet getGcs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getGcs();
      }
      return null;
   }

   public PersonPO hasPersons()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().hasPersons().endCreate();
   }

   public GraphPO hasPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_PERSONS);
   }

   public GraphPO createPersons(PersonPO tgt)
   {
      return this.startCreate().hasPersons(tgt).endCreate();
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
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_RELATIONS, result);
      
      return result;
   }

   public RelationPO createRelations()
   {
      return this.startCreate().hasRelations().endCreate();
   }

   public GraphPO hasRelations(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_RELATIONS);
   }

   public GraphPO createRelations(RelationPO tgt)
   {
      return this.startCreate().hasRelations(tgt).endCreate();
   }

   public RelationSet getRelations()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getRelations();
      }
      return null;
   }

   public GraphComponentPO filterGcs()
   {
      GraphComponentPO result = new GraphComponentPO(new GraphComponent[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_GCS, result);
      
      return result;
   }

   public GraphPO filterGcs(GraphComponentPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_GCS);
   }

   public PersonPO filterPersons()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_PERSONS, result);
      
      return result;
   }

   public GraphPO filterPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_PERSONS);
   }

   public RelationPO filterRelations()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_RELATIONS, result);
      
      return result;
   }

   public GraphPO filterRelations(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_RELATIONS);
   }

}
