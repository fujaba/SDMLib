package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;

public class ReplicationServerPO extends PatternObject<ReplicationServerPO, ReplicationServer>
{

    public ReplicationServerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReplicationServerSet matches = new ReplicationServerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReplicationServer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ReplicationServerPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ReplicationServerPO(ReplicationServer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ReplicationServerPO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationServer) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public ReplicationServerPO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationServer) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public ReplicationServerPO hasHistory(ChangeHistory value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO createHistory(ChangeHistory value)
   {
      this.startCreate().hasHistory(value).endCreate();
      return this;
   }
   
   public ChangeHistory getHistory()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationServer) getCurrentMatch()).getHistory();
      }
      return null;
   }
   
   public ReplicationServerPO withHistory(ChangeHistory value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationServer) getCurrentMatch()).setHistory(value);
      }
      return this;
   }
   
   public ReplicationServerPO hasLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO hasLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO createLastChangeId(long value)
   {
      this.startCreate().hasLastChangeId(value).endCreate();
      return this;
   }
   
   public long getLastChangeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationServer) getCurrentMatch()).getLastChangeId();
      }
      return 0;
   }
   
   public ReplicationServerPO withLastChangeId(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationServer) getCurrentMatch()).setLastChangeId(value);
      }
      return this;
   }
   
   public ReplicationServerPO hasNodeId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO hasNodeId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_NODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO createNodeId(String value)
   {
      this.startCreate().hasNodeId(value).endCreate();
      return this;
   }
   
   public String getNodeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationServer) getCurrentMatch()).getNodeId();
      }
      return null;
   }
   
   public ReplicationServerPO withNodeId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationServer) getCurrentMatch()).setNodeId(value);
      }
      return this;
   }
   
   public ReplicationServerPO hasJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationServer.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationServerPO createJavaFXApplication(boolean value)
   {
      this.startCreate().hasJavaFXApplication(value).endCreate();
      return this;
   }
   
   public boolean getJavaFXApplication()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationServer) getCurrentMatch()).isJavaFXApplication();
      }
      return false;
   }
   
   public ReplicationServerPO withJavaFXApplication(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationServer) getCurrentMatch()).setJavaFXApplication(value);
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

   public ReplicationServerPO hasSharedSpaces(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES);
   }

   public ReplicationServerPO createSharedSpaces(SharedSpacePO tgt)
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

   public ReplicationServerPO filterSharedSpaces(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationNode.PROPERTY_SHAREDSPACES);
   }


   public ReplicationServerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SharedSpacePO createSharedSpacesPO()
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationServer.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public SharedSpacePO createSharedSpacesPO(String modifier)
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReplicationServer.PROPERTY_SHAREDSPACES, result);
      
      return result;
   }

   public ReplicationServerPO createSharedSpacesLink(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationServer.PROPERTY_SHAREDSPACES);
   }

   public ReplicationServerPO createSharedSpacesLink(SharedSpacePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReplicationServer.PROPERTY_SHAREDSPACES, modifier);
   }

}
