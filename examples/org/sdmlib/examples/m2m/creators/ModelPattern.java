package org.sdmlib.examples.m2m.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.m2m.creators.GraphPO;
import org.sdmlib.examples.m2m.Graph;
import org.sdmlib.examples.m2m.creators.PersonPO;
import org.sdmlib.examples.m2m.Person;
import org.sdmlib.examples.m2m.creators.RelationPO;
import org.sdmlib.examples.m2m.Relation;
import org.sdmlib.examples.m2m.creators.GraphComponentPO;
import org.sdmlib.examples.m2m.GraphComponent;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super();
   }
   
   @Override
   public JsonIdMap getJsonIdMap()
   {
      JsonIdMap jsonIdMap = super.getJsonIdMap();
      
      if (jsonIdMap == null)
      {
         jsonIdMap = CreatorCreator.createIdMap("hg");
         
         this.setJsonIdMap(jsonIdMap);
      }
      
      return jsonIdMap;
   }


   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public GraphPO hasElementGraphPO()
   {
      GraphPO value = new GraphPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GraphPO hasElementGraphPO(Graph hostGraphObject)
   {
      GraphPO value = new GraphPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PersonPO hasElementPersonPO()
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PersonPO hasElementPersonPO(Person hostGraphObject)
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public RelationPO hasElementRelationPO()
   {
      RelationPO value = new RelationPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public RelationPO hasElementRelationPO(Relation hostGraphObject)
   {
      RelationPO value = new RelationPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GraphComponentPO hasElementGraphComponentPO()
   {
      GraphComponentPO value = new GraphComponentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GraphComponentPO hasElementGraphComponentPO(GraphComponent hostGraphObject)
   {
      GraphComponentPO value = new GraphComponentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



