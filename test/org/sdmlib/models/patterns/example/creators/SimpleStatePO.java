package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.patterns.example.SimpleState;
import org.sdmlib.models.patterns.example.creators.SimpleStateSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.patterns.example.creators.NodePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.patterns.example.creators.SimpleStatePO;
import org.sdmlib.models.patterns.example.Node;
import org.sdmlib.models.patterns.example.creators.NodeSet;

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

   public NodePO hasNodes()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(SimpleState.PROPERTY_NODES, result);

      return result;
   }

   public SimpleStatePO hasNodes(NodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(SimpleState.PROPERTY_NODES)
         .withSrc(this).withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public NodeSet getNodes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SimpleState) this.getCurrentMatch()).getNodes();
      }
      return null;
   }

   public NodePO createNodes()
   {
      return this.startCreate().hasNodes().endCreate();
   }

   public SimpleStatePO createNodes(NodePO tgt)
   {
      return this.startCreate().hasNodes(tgt).endCreate();
   }

}
