package org.sdmlib.replication.util;

import java.net.Socket;

import org.sdmlib.models.pattern.PatternObject;

public class SocketPO extends PatternObject<SocketPO, Socket>
{

    public SocketSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SocketSet matches = new SocketSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Socket) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SocketPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SocketPO(Socket... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
