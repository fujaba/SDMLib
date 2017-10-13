package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePO;

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
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public NodePO(Node... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public NodePO hasNum(int value)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO hasNum(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NUM)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO createNum(int value)
   {
      this.startCreate().hasNum(value).endCreate();
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
      SimpleStatePO result = new SimpleStatePO(new SimpleState[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public SimpleStatePO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public NodePO hasGraph(SimpleStatePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_GRAPH);
   }

   public NodePO createGraph(SimpleStatePO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
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
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_NEXT, result);
      
      return result;
   }

   public NodePO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public NodePO hasNext(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_NEXT);
   }

   public NodePO createNext(NodePO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
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
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_PREV, result);
      
      return result;
   }

   public NodePO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public NodePO hasPrev(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_PREV);
   }

   public NodePO createPrev(NodePO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public NodeSet getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public NodePO filterNum(int value)
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
   
   public NodePO filterNum(int lower, int upper)
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
   
   public SimpleStatePO filterGraph()
   {
      SimpleStatePO result = new SimpleStatePO(new SimpleState[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public NodePO filterGraph(SimpleStatePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_GRAPH);
   }

   public NodePO filterPrev()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_PREV, result);
      
      return result;
   }

   public NodePO filterPrev(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_PREV);
   }

   public NodePO filterNext()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_NEXT, result);
      
      return result;
   }

   public NodePO filterNext(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_NEXT);
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

}
