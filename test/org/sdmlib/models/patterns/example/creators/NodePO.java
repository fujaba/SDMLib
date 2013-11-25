package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.Node;
import org.sdmlib.models.patterns.example.creators.NodeSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.creators.SimpleStatePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.creators.NodePO;
import org.sdmlib.models.patterns.example.SimpleState;

public class NodePO extends PatternObject<NodePO, Node>
{
   public NodeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      NodeSet matches = new NodeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Node) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public NodePO hasNum(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getNum()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) getCurrentMatch()).getNum();
      }
      return 0;
   }
   
   public NodePO withNum(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Node) getCurrentMatch()).setNum(value);
      }
      return this;
   }
   
   public SimpleStatePO hasGraph()
   {
      SimpleStatePO result = new SimpleStatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public NodePO hasGraph(SimpleStatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_GRAPH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public SimpleState getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public NodePO hasNext()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_NEXT, result);
      
      return result;
   }

   public NodePO hasNext(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public NodeSet getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public NodePO hasPrev()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_PREV, result);
      
      return result;
   }

   public NodePO hasPrev(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Node.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public NodeSet getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

}


