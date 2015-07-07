package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public GraphPO(Graph... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
          return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
           this.withCandidates(hostGraphObject);
      } else {
         this.withCurrentMatch(hostGraphObject[0]);  
         this.withModifier(Pattern.BOUND);
      }
      pattern.findMatch();
  }
   public NodePO hasNodes()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_NODES, result);
      
      return result;
   }

   public NodePO createNodes()
   {
      return this.startCreate().hasNodes().endCreate();
   }

   public GraphPO hasNodes(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_NODES);
   }

   public GraphPO createNodes(NodePO tgt)
   {
      return this.startCreate().hasNodes(tgt).endCreate();
   }

   public NodeSet getNodes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getNodes();
      }
      return null;
   }

   public EdgePO hasEdges()
   {
      EdgePO result = new EdgePO(new Edge[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_EDGES, result);
      
      return result;
   }

   public EdgePO createEdges()
   {
      return this.startCreate().hasEdges().endCreate();
   }

   public GraphPO hasEdges(EdgePO tgt)
   {
      return hasLinkConstraint(tgt, Graph.PROPERTY_EDGES);
   }

   public GraphPO createEdges(EdgePO tgt)
   {
      return this.startCreate().hasEdges(tgt).endCreate();
   }

   public EdgeSet getEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Graph) this.getCurrentMatch()).getEdges();
      }
      return null;
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


   public NodePO hasGcsNode()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_GCS, result);
      
      return result;
   }


   public EdgePO hasGcsEdge()
   {
      EdgePO result = new EdgePO(new Edge[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Graph.PROPERTY_GCS, result);
      
      return result;
   }

}


