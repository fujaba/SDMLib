package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.helloworld.creators.GraphPO;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.creators.EdgePO;
import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.creators.NodePO;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.creators.GreetingMessagePO;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.creators.PersonPO;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.examples.helloworld.creators.GraphComponentPO;
import org.sdmlib.examples.helloworld.GraphComponent;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
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

   public EdgePO hasElementEdgePO()
   {
      EdgePO value = new EdgePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public EdgePO hasElementEdgePO(Edge hostGraphObject)
   {
      EdgePO value = new EdgePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public NodePO hasElementNodePO()
   {
      NodePO value = new NodePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public NodePO hasElementNodePO(Node hostGraphObject)
   {
      NodePO value = new NodePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GreetingPO hasElementGreetingPO()
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GreetingPO hasElementGreetingPO(Greeting hostGraphObject)
   {
      GreetingPO value = new GreetingPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GreetingMessagePO hasElementGreetingMessagePO()
   {
      GreetingMessagePO value = new GreetingMessagePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GreetingMessagePO hasElementGreetingMessagePO(GreetingMessage hostGraphObject)
   {
      GreetingMessagePO value = new GreetingMessagePO();
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





