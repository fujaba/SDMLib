package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.creators.ReplicationNodeSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.SharedSpacePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.ReplicationNodePO;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.creators.SharedSpaceSet;

public class ReplicationNodePO extends PatternObject<ReplicationNodePO, ReplicationNode>
{
   public ReplicationNodeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReplicationNodeSet matches = new ReplicationNodeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReplicationNode) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public SharedSpacePO hasSharedSpaces()
   {
      SharedSpacePO result = new SharedSpacePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReplicationNode.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public ReplicationNodePO hasSharedSpaces(SharedSpacePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReplicationNode.PROPERTY_SHAREDSPACES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public SharedSpaceSet getSharedSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) this.getCurrentMatch()).getSharedSpaces();
      }
      return null;
   }

}

