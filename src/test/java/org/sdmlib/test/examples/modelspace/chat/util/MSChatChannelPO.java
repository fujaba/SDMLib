package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMsgPO;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelPO;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMsgSet;

public class MSChatChannelPO extends PatternObject<MSChatChannelPO, MSChatChannel>
{

    public MSChatChannelSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatChannelSet matches = new MSChatChannelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatChannel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatChannelPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatChannelPO(MSChatChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatMsgPO hasMsgs()
   {
      MSChatMsgPO result = new MSChatMsgPO(new MSChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public MSChatMsgPO createMsgs()
   {
      return this.startCreate().hasMsgs().endCreate();
   }

   public MSChatChannelPO hasMsgs(MSChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannel.PROPERTY_MSGS);
   }

   public MSChatChannelPO createMsgs(MSChatMsgPO tgt)
   {
      return this.startCreate().hasMsgs(tgt).endCreate();
   }

   public MSChatMsgSet getMsgs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannel) this.getCurrentMatch()).getMsgs();
      }
      return null;
   }

}
