package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;

public class ServerSocketAcceptThreadPO extends PatternObject<ServerSocketAcceptThreadPO, ServerSocketAcceptThread>
{

    public ServerSocketAcceptThreadSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ServerSocketAcceptThreadSet matches = new ServerSocketAcceptThreadSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ServerSocketAcceptThread) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ServerSocketAcceptThreadPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ServerSocketAcceptThreadPO(ServerSocketAcceptThread... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ServerSocketAcceptThreadPO hasPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ServerSocketAcceptThreadPO hasPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ServerSocketAcceptThreadPO createPort(int value)
   {
      this.startCreate().hasPort(value).endCreate();
      return this;
   }
   
   public int getPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerSocketAcceptThread) getCurrentMatch()).getPort();
      }
      return 0;
   }
   
   public ServerSocketAcceptThreadPO withPort(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerSocketAcceptThread) getCurrentMatch()).setPort(value);
      }
      return this;
   }
   
   public ServerSocketAcceptThreadPO hasReplicationNode(ReplicationNode value)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_REPLICATIONNODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ServerSocketAcceptThreadPO createReplicationNode(ReplicationNode value)
   {
      this.startCreate().hasReplicationNode(value).endCreate();
      return this;
   }
   
   public ReplicationNode getReplicationNode()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ServerSocketAcceptThread) getCurrentMatch()).getReplicationNode();
      }
      return null;
   }
   
   public ServerSocketAcceptThreadPO withReplicationNode(ReplicationNode value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ServerSocketAcceptThread) getCurrentMatch()).setReplicationNode(value);
      }
      return this;
   }
   
   public ServerSocketAcceptThreadPO filterPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ServerSocketAcceptThreadPO filterPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ServerSocketAcceptThreadPO filterReplicationNode(ReplicationNode value)
   {
      new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_REPLICATIONNODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
