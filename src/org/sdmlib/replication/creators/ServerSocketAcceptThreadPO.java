package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.creators.ServerSocketAcceptThreadSet;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
   public ServerSocketAcceptThreadPO hasPort(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ServerSocketAcceptThread.PROPERTY_REPLICATIONNODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
}


