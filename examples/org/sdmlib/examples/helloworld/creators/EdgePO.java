package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class EdgePO extends PatternObject<EdgePO, Edge>
{
   public EdgeSet allMatches()
   {
      setDoAllMatches(true);
      
      EdgeSet matches = new EdgeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Edge) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Edge.PROPERTY_GRAPH, result);
      
      return result;
   }
   
   public EdgePO hasGraph(GraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Edge.PROPERTY_GRAPH)
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
         return ((Edge) this.getCurrentMatch()).getGraph();
      }
      return null;
   }
   
   public NodePO hasSrc()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Edge.PROPERTY_SRC, result);
      
      return result;
   }
   
   public EdgePO hasSrc(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Edge.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Node getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) this.getCurrentMatch()).getSrc();
      }
      return null;
   }
   
   public NodePO hasTgt()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Edge.PROPERTY_TGT, result);
      
      return result;
   }
   
   public EdgePO hasTgt(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Edge.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Node getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) this.getCurrentMatch()).getTgt();
      }
      return null;
   }
   
   public EdgePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
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
         return ((Edge) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public EdgePO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
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
         return ((Edge) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public EdgePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Edge) getCurrentMatch()).withText(value);
      }
      return this;
   }
   
}



