package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.util.SharedSpacePO;
import org.sdmlib.replication.util.ReplicationNodePO;

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


   public ReplicationNodePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ReplicationNodePO(ReplicationNode... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ReplicationNodePO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public ReplicationNodePO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationNode) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public ReplicationNodePO hasHistory(ChangeHistory value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO createHistory(ChangeHistory value)
   {
      this.startCreate().hasHistory(value).endCreate();
      return this;
   }
   
   public ChangeHistory getHistory()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) getCurrentMatch()).getHistory();
      }
      return null;
   }
   
   public ReplicationNodePO withHistory(ChangeHistory value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationNode) getCurrentMatch()).setHistory(value);
      }
      return this;
   }
   
   public ReplicationNodePO hasLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO hasLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO createLastChangeId(long value)
   {
      this.startCreate().hasLastChangeId(value).endCreate();
      return this;
   }
   
   public long getLastChangeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) getCurrentMatch()).getLastChangeId();
      }
      return 0;
   }
   
   public ReplicationNodePO withLastChangeId(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationNode) getCurrentMatch()).setLastChangeId(value);
      }
      return this;
   }
   
   public ReplicationNodePO hasNodeId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO hasNodeId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_NODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO createNodeId(String value)
   {
      this.startCreate().hasNodeId(value).endCreate();
      return this;
   }
   
   public String getNodeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) getCurrentMatch()).getNodeId();
      }
      return null;
   }
   
   public ReplicationNodePO withNodeId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationNode) getCurrentMatch()).setNodeId(value);
      }
      return this;
   }
   
   public ReplicationNodePO hasJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationNode.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationNodePO createJavaFXApplication(boolean value)
   {
      this.startCreate().hasJavaFXApplication(value).endCreate();
      return this;
   }
   
   public boolean getJavaFXApplication()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) getCurrentMatch()).isJavaFXApplication();
      }
      return false;
   }
   
   public ReplicationNodePO withJavaFXApplication(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationNode) getCurrentMatch()).setJavaFXApplication(value);
      }
      return this;
   }
   
   public SharedSpacePO hasSharedSpaces()
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationNode.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public SharedSpacePO createSharedSpaces()
   {
      return this.startCreate().hasSharedSpaces().endCreate();
   }

   public ReplicationNodePO hasSharedSpaces(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES);
   }

   public ReplicationNodePO createSharedSpaces(SharedSpacePO tgt)
   {
      return this.startCreate().hasSharedSpaces(tgt).endCreate();
   }

   public SharedSpaceSet getSharedSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationNode) this.getCurrentMatch()).getSharedSpaces();
      }
      return null;
   }

   public SharedSpacePO filterSharedSpaces()
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationNode.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public ReplicationNodePO filterSharedSpaces(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES);
   }


   public ReplicationNodePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SharedSpacePO createSharedSpacesPO()
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationNode.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public SharedSpacePO createSharedSpacesPO(String modifier)
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReplicationNode.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public ReplicationNodePO createSharedSpacesLink(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES);
   }

   public ReplicationNodePO createSharedSpacesLink(SharedSpacePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES, modifier);
   }

}
