package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSChatMessageFlow;
import org.sdmlib.examples.chats.CSClearDrawingFlow;
import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.examples.chats.CSDrawPointFlow;
import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.examples.chats.CTClearDrawing;
import org.sdmlib.examples.chats.CTDrawPoint;
import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.examples.chats.ClearDrawingFlow;
import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.examples.chats.DrawPointFlow;
import org.sdmlib.examples.chats.P2PChatMessageFlow;
import org.sdmlib.examples.chats.P2PNetworkLoginFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.examples.chats.TestChatMessageFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxyPO;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.Pattern;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public PeerToPeerChatPO hasElementPeerToPeerChatPO()
   {
      PeerToPeerChatPO value = new PeerToPeerChatPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerToPeerChatPO hasElementPeerToPeerChatPO(PeerToPeerChat hostGraphObject)
   {
      PeerToPeerChatPO value = new PeerToPeerChatPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ChatMessageFlowPO hasElementChatMessageFlowPO()
   {
      ChatMessageFlowPO value = new ChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChatMessageFlowPO hasElementChatMessageFlowPO(ChatMessageFlow hostGraphObject)
   {
      ChatMessageFlowPO value = new ChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TaskFlowPO hasElementTaskFlowPO()
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TaskFlowPO hasElementTaskFlowPO(TaskFlow hostGraphObject)
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public DrawPointFlowPO hasElementDrawPointFlowPO()
   {
      DrawPointFlowPO value = new DrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public DrawPointFlowPO hasElementDrawPointFlowPO(DrawPointFlow hostGraphObject)
   {
      DrawPointFlowPO value = new DrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ClearDrawingFlowPO hasElementClearDrawingFlowPO()
   {
      ClearDrawingFlowPO value = new ClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ClearDrawingFlowPO hasElementClearDrawingFlowPO(ClearDrawingFlow hostGraphObject)
   {
      ClearDrawingFlowPO value = new ClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PeerToPeerChatArgsPO hasElementPeerToPeerChatArgsPO()
   {
      PeerToPeerChatArgsPO value = new PeerToPeerChatArgsPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerToPeerChatArgsPO hasElementPeerToPeerChatArgsPO(PeerToPeerChatArgs hostGraphObject)
   {
      PeerToPeerChatArgsPO value = new PeerToPeerChatArgsPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PeerProxyPO hasElementPeerProxyPO()
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerProxyPO hasElementPeerProxyPO(PeerProxy hostGraphObject)
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CSChatMessageFlowPO hasElementCSChatMessageFlowPO()
   {
      CSChatMessageFlowPO value = new CSChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CSChatMessageFlowPO hasElementCSChatMessageFlowPO(CSChatMessageFlow hostGraphObject)
   {
      CSChatMessageFlowPO value = new CSChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ChatServerPO hasElementChatServerPO()
   {
      ChatServerPO value = new ChatServerPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChatServerPO hasElementChatServerPO(ChatServer hostGraphObject)
   {
      ChatServerPO value = new ChatServerPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ClientLoginFlowPO hasElementClientLoginFlowPO()
   {
      ClientLoginFlowPO value = new ClientLoginFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ClientLoginFlowPO hasElementClientLoginFlowPO(ClientLoginFlow hostGraphObject)
   {
      ClientLoginFlowPO value = new ClientLoginFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CSDrawPointFlowPO hasElementCSDrawPointFlowPO()
   {
      CSDrawPointFlowPO value = new CSDrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CSDrawPointFlowPO hasElementCSDrawPointFlowPO(CSDrawPointFlow hostGraphObject)
   {
      CSDrawPointFlowPO value = new CSDrawPointFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CSClearDrawingFlowPO hasElementCSClearDrawingFlowPO()
   {
      CSClearDrawingFlowPO value = new CSClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CSClearDrawingFlowPO hasElementCSClearDrawingFlowPO(CSClearDrawingFlow hostGraphObject)
   {
      CSClearDrawingFlowPO value = new CSClearDrawingFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CSVisitAllClientsFlowPO hasElementCSVisitAllClientsFlowPO()
   {
      CSVisitAllClientsFlowPO value = new CSVisitAllClientsFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CSVisitAllClientsFlowPO hasElementCSVisitAllClientsFlowPO(CSVisitAllClientsFlow hostGraphObject)
   {
      CSVisitAllClientsFlowPO value = new CSVisitAllClientsFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CSClientTaskPO hasElementCSClientTaskPO()
   {
      CSClientTaskPO value = new CSClientTaskPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CSClientTaskPO hasElementCSClientTaskPO(CSClientTask hostGraphObject)
   {
      CSClientTaskPO value = new CSClientTaskPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CTDrawPointPO hasElementCTDrawPointPO()
   {
      CTDrawPointPO value = new CTDrawPointPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CTDrawPointPO hasElementCTDrawPointPO(CTDrawPoint hostGraphObject)
   {
      CTDrawPointPO value = new CTDrawPointPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CTClearDrawingPO hasElementCTClearDrawingPO()
   {
      CTClearDrawingPO value = new CTClearDrawingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CTClearDrawingPO hasElementCTClearDrawingPO(CTClearDrawing hostGraphObject)
   {
      CTClearDrawingPO value = new CTClearDrawingPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TestChatMessageFlowPO hasElementTestChatMessageFlowPO()
   {
      TestChatMessageFlowPO value = new TestChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TestChatMessageFlowPO hasElementTestChatMessageFlowPO(TestChatMessageFlow hostGraphObject)
   {
      TestChatMessageFlowPO value = new TestChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public P2PNetworkLoginFlowPO hasElementP2PNetworkLoginFlowPO()
   {
      P2PNetworkLoginFlowPO value = new P2PNetworkLoginFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public P2PNetworkLoginFlowPO hasElementP2PNetworkLoginFlowPO(P2PNetworkLoginFlow hostGraphObject)
   {
      P2PNetworkLoginFlowPO value = new P2PNetworkLoginFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public P2PChatMessageFlowPO hasElementP2PChatMessageFlowPO()
   {
      P2PChatMessageFlowPO value = new P2PChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public P2PChatMessageFlowPO hasElementP2PChatMessageFlowPO(P2PChatMessageFlow hostGraphObject)
   {
      P2PChatMessageFlowPO value = new P2PChatMessageFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}
















