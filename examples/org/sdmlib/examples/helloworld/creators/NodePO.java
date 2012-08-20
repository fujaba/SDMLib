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
import org.sdmlib.examples.helloworld.creators.NodeSet;

public class NodePO extends PatternObject<NodePO, Node>
{
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
      if (this.getPattern() == null)
      {
         return null;
      }
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
         return this.getCurrentMatch().getInEdges();
      }
      return null;
   }

   public NodeSet allMatches()
   {
      setDoAllMatches(true);
      
      NodeSet matches = new NodeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add(this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public NodePO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Node.PROPERTY_TEXT)
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
         return ((Node) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public NodePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Node) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public NodePO hasOrig()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_ORIG, result);
      
      return result;
   }
   
   public NodePO hasOrig(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_ORIG)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Node getOrig()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getOrig();
      }
      return null;
   }
   
   public NodePO hasCopy()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_COPY, result);
      
      return result;
   }
   
   public NodePO hasCopy(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_COPY)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Node getCopy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getCopy();
      }
      return null;
   }
   
   public NodePO hasLinksTo()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_LINKSTO, result);
      
      return result;
   }
   
   public NodePO hasLinksTo(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_LINKSTO)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodeSet getLinksTo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getLinksTo();
      }
      return null;
   }
   
   public NodePO hasLinksFrom()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_LINKSFROM, result);
      
      return result;
   }
   
   public NodePO hasLinksFrom(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_LINKSFROM)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodeSet getLinksFrom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getLinksFrom();
      }
      return null;
   }
   
}





