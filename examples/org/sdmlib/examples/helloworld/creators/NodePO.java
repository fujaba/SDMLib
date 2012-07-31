package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.helloworld.creators.GraphPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.helloworld.creators.NodePO;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.creators.EdgePO;
import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.creators.EdgeSet;

public class NodePO extends PatternObject
{
   public NodePO startNAC()
   {
      return (NodePO) super.startNAC();
   }
   
   public NodePO endNAC()
   {
      return (NodePO) super.endNAC();
   }
   
   public NodePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;   }
   
   public NodePO hasGraph(GraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_GRAPH)
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
         return ((Node) this.getCurrentMatch()).getGraph();
      }
      return null;
   }
   
   public EdgePO hasOutEdges()
   {
      EdgePO result = new EdgePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_OUTEDGES, result);
      
      return result;   }
   
   public NodePO hasOutEdges(EdgePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_OUTEDGES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgeSet getOutEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getOutEdges();
      }
      return null;
   }
   
   public EdgePO hasInEdges()
   {
      EdgePO result = new EdgePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_INEDGES, result);
      
      return result;   }
   
   public NodePO hasInEdges(EdgePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_INEDGES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgeSet getInEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getInEdges();
      }
      return null;
   }

   public NodeSet allMatches()
   {
      NodeSet matches = new NodeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Node) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

