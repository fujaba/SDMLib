package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;

public class PersonPO extends PatternObject<PersonPO, Person>
{

    public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PersonPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasFirstName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasFirstName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO createFirstName(String value)
   {
      this.startCreate().hasFirstName(value).endCreate();
      return this;
   }
   
   public String getFirstName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getFirstName();
      }
      return null;
   }
   
   public PersonPO withFirstName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setFirstName(value);
      }
      return this;
   }
   
   public PersonPO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public PersonPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public PersonPO hasGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GRAPH);
   }

   public PersonPO createGraph(GraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
   }

   public Graph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public RelationPO hasOutEdges()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_OUTEDGES, result);
      
      return result;
   }

   public RelationPO createOutEdges()
   {
      return this.startCreate().hasOutEdges().endCreate();
   }

   public PersonPO hasOutEdges(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OUTEDGES);
   }

   public PersonPO createOutEdges(RelationPO tgt)
   {
      return this.startCreate().hasOutEdges(tgt).endCreate();
   }

   public RelationSet getOutEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getOutEdges();
      }
      return null;
   }

   public RelationPO hasInEdges()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_INEDGES, result);
      
      return result;
   }

   public RelationPO createInEdges()
   {
      return this.startCreate().hasInEdges().endCreate();
   }

   public PersonPO hasInEdges(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_INEDGES);
   }

   public PersonPO createInEdges(RelationPO tgt)
   {
      return this.startCreate().hasInEdges(tgt).endCreate();
   }

   public RelationSet getInEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getInEdges();
      }
      return null;
   }

   public PersonPO hasKnows()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_KNOWS, result);
      
      return result;
   }

   public PersonPO createKnows()
   {
      return this.startCreate().hasKnows().endCreate();
   }

   public PersonPO hasKnows(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_KNOWS);
   }

   public PersonPO createKnows(PersonPO tgt)
   {
      return this.startCreate().hasKnows(tgt).endCreate();
   }

   public PersonSet getKnows()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getKnows();
      }
      return null;
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

   public PersonPO hasParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }

   public PersonPO createParent(GraphPO tgt)
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

   public PersonPO filterFirstName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterFirstName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
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
      super.hasLink(Person.PROPERTY_GRAPH, result);
      
      return result;
   }

   public PersonPO filterGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GRAPH);
   }

   public RelationPO filterOutEdges()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_OUTEDGES, result);
      
      return result;
   }

   public PersonPO filterOutEdges(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OUTEDGES);
   }

   public RelationPO filterInEdges()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_INEDGES, result);
      
      return result;
   }

   public PersonPO filterInEdges(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_INEDGES);
   }

   public PersonPO filterKnows()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_KNOWS, result);
      
      return result;
   }

   public PersonPO filterKnows(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_KNOWS);
   }

   public GraphPO filterParent()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }

   public PersonPO filterParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }


   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createFirstNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createFirstNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createFirstNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_FIRSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createKnowsPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_KNOWS, result);
      
      return result;
   }

   public PersonPO createKnowsPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_KNOWS, result);
      
      return result;
   }

   public PersonPO createKnowsLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_KNOWS);
   }

   public PersonPO createKnowsLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_KNOWS, modifier);
   }

   public GraphPO createGraphPO()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraphPO(String modifier)
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_GRAPH, result);
      
      return result;
   }

   public PersonPO createGraphLink(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GRAPH);
   }

   public PersonPO createGraphLink(GraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_GRAPH, modifier);
   }

   public RelationPO createOutEdgesPO()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_OUTEDGES, result);
      
      return result;
   }

   public RelationPO createOutEdgesPO(String modifier)
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_OUTEDGES, result);
      
      return result;
   }

   public PersonPO createOutEdgesLink(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OUTEDGES);
   }

   public PersonPO createOutEdgesLink(RelationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_OUTEDGES, modifier);
   }

   public RelationPO createInEdgesPO()
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_INEDGES, result);
      
      return result;
   }

   public RelationPO createInEdgesPO(String modifier)
   {
      RelationPO result = new RelationPO(new Relation[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_INEDGES, result);
      
      return result;
   }

   public PersonPO createInEdgesLink(RelationPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_INEDGES);
   }

   public PersonPO createInEdgesLink(RelationPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_INEDGES, modifier);
   }

   public GraphPO createParentPO()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PARENT, result);
      
      return result;
   }

   public GraphPO createParentPO(String modifier)
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_PARENT, result);
      
      return result;
   }

   public PersonPO createParentLink(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARENT);
   }

   public PersonPO createParentLink(GraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARENT, modifier);
   }

}
