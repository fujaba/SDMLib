package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateSet;

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


   public NodePO(){
      newInstance(null);
   }

   public NodePO(Node... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public NodePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public NodePO createNumCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public NodePO createNumCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public NodePO createNumAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public NodePO createPrevPO()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_PREV, result);
      
      return result;
   }

   public NodePO createPrevPO(String modifier)
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(modifier);
      super.hasLink(Node.PROPERTY_PREV, result);
      
      return result;
   }

   public NodePO createPrevLink(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_PREV);
   }

   public NodePO createPrevLink(NodePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_PREV, modifier);
   }

   public NodeSet getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public NodePO createNextPO()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_NEXT, result);
      
      return result;
   }

   public NodePO createNextPO(String modifier)
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(modifier);
      super.hasLink(Node.PROPERTY_NEXT, result);
      
      return result;
   }

   public NodePO createNextLink(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_NEXT);
   }

   public NodePO createNextLink(NodePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_NEXT, modifier);
   }

   public NodeSet getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public SimpleStatePO createGraphPO()
   {
      SimpleStatePO result = new SimpleStatePO(new SimpleState[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public SimpleStatePO createGraphPO(String modifier)
   {
      SimpleStatePO result = new SimpleStatePO(new SimpleState[]{});
      
      result.setModifier(modifier);
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public NodePO createGraphLink(SimpleStatePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_GRAPH);
   }

   public NodePO createGraphLink(SimpleStatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_GRAPH, modifier);
   }

   public SimpleStateSet getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

}
