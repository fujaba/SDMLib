package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePO;

public class SimpleStatePO extends PatternObject<SimpleStatePO, SimpleState>
{

    public SimpleStateSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SimpleStateSet matches = new SimpleStateSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SimpleState) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SimpleStatePO(){
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SimpleStatePO(SimpleState... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public NodePO hasNodes()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SimpleState.PROPERTY_NODES, result);
      
      return result;
   }

   public NodePO createNodes()
   {
      return this.startCreate().hasNodes().endCreate();
   }

   public SimpleStatePO hasNodes(NodePO tgt)
   {
      return hasLinkConstraint(tgt, SimpleState.PROPERTY_NODES);
   }

   public SimpleStatePO createNodes(NodePO tgt)
   {
      return this.startCreate().hasNodes(tgt).endCreate();
   }

   public NodeSet getNodes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SimpleState) this.getCurrentMatch()).getNodes();
      }
      return null;
   }

   public NodePO filterNodes()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SimpleState.PROPERTY_NODES, result);
      
      return result;
   }

   public SimpleStatePO filterNodes(NodePO tgt)
   {
      return hasLinkConstraint(tgt, SimpleState.PROPERTY_NODES);
   }


   public SimpleStatePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public NodePO createNodesPO()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SimpleState.PROPERTY_NODES, result);
      
      return result;
   }

   public NodePO createNodesPO(String modifier)
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(modifier);
      super.hasLink(SimpleState.PROPERTY_NODES, result);
      
      return result;
   }

   public SimpleStatePO createNodesLink(NodePO tgt)
   {
      return hasLinkConstraint(tgt, SimpleState.PROPERTY_NODES);
   }

   public SimpleStatePO createNodesLink(NodePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SimpleState.PROPERTY_NODES, modifier);
   }

}
