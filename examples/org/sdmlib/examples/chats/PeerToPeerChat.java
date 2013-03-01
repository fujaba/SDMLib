/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.chats;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.examples.chats.creators.CreatorCreator;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class PeerToPeerChat extends Shell implements PropertyChangeInterface
{
   public static final String MY_GUI = "my.gui";
   public static final String ID_MAP = "id.map";
   private static Object chatMode;
   private static PeerToPeerChatArgs peerArgs;
   private Text allMessages;
   private Text newMessageText;
   private Label nameLabel;
   private Label ipLabel;
   private Label portLabel;
   
   enum ChatMode {
      OneToOne, 
      ClientServer, 
      PeerToPeerNetwork
   }

   private TreeSet<PeerProxy> proxies = new TreeSet<PeerProxy>();
   
   public TreeSet<PeerProxy> getProxies()
   {
      return proxies;
   }
   /**
    * Launch the application.
    * @param args
    */
   public static void main(String args[])
   {
      try
      {
         idMap = CreatorCreator.createIdMap("args");
         
         Display display = Display.getDefault();
         PeerToPeerChat gui = new PeerToPeerChat();
          
         peerArgs = null;
         
         File argsFile = new File ("peerToPeer.config");
         
         if (argsFile.exists())
         {
            // read it
            BufferedReader in = new BufferedReader(
            new FileReader(argsFile));
            
            StringBuilder text = new StringBuilder();
            String line = "";
            while (line != null)
            {
               line = in.readLine();
               text.append(line).append("\n");
            }
            JsonObject jsonObject = new JsonObject(text.toString());
            
            peerArgs = (PeerToPeerChatArgs) idMap.readJson(jsonObject);
         }
         
         if (args.length == 4)
         {
            // something like Albert 4242 localhost 5353
            peerArgs = new PeerToPeerChatArgs()
            .withUserName(args[0])
            .withLocalPort(Integer.parseInt(args[1]))
            .withPeerIp(args[2])
            .withPeerPort(Integer.parseInt(args[3]));
            chatMode = ChatMode.OneToOne;
         } 
         else if (args.length == 5 && args[3].equals("cs"))
         {
            // only server ip and port
            peerArgs.withPeerIp(args[0]);
            peerArgs.withPeerPort(Integer.parseInt(args[1]));
            peerArgs.withLocalPort(Integer.parseInt(args[2]));
            peerArgs.withUserName(args[4]);
            chatMode = ChatMode.ClientServer;
         }
         else if (args.length == 3)
         {
            peerArgs.withLocalPort(Integer.parseInt(args[0]));
            peerArgs.withUserName(args[1]);
            peerArgs.withPeerPort(0);
            chatMode = ChatMode.PeerToPeerNetwork;
         }
         else if (args.length == 2)
         {
            peerArgs.withLocalPort(Integer.parseInt(args[0]));
            peerArgs.withUserName(args[1]);
            peerArgs.withPeerPort(0);
            chatMode = ChatMode.PeerToPeerNetwork;
            
            // I am first, I open the session
            gui.getAllMessages().setText("Session started by " + peerArgs.getUserName() + "\n");
         }
         else if (args.length == 5 && args[3].equals("p2p"))
         {
            // only first peer ip and port
            peerArgs.withPeerIp(args[0]);
            peerArgs.withPeerPort(Integer.parseInt(args[1]));
            peerArgs.withLocalPort(Integer.parseInt(args[2]));
            peerArgs.withUserName(args[4]);
            chatMode = ChatMode.PeerToPeerNetwork;            
         }
         
         
         
         FileWriter out = new FileWriter(argsFile);
         out.write(idMap.toJsonObject(peerArgs).toString(3));
         out.close();

         gui.getNameLabel().setText(peerArgs.getUserName() + " ");
         gui.getIpLabel().setText(InetAddress.getLocalHost().getHostAddress() + " ");
         gui.getPortLabel().setText(" " + peerArgs.getLocalPort());

         idMap = CreatorCreator.createIdMap(peerArgs.getUserName());
         idMap.put(ID_MAP, idMap);
         idMap.put(MY_GUI, gui);
         
         PeerProxy peer = new PeerProxy()
         .withIp(peerArgs.getPeerIp())
         .withPort(peerArgs.getPeerPort())
         .withIdMap(idMap);

         gui.setPeer(peer);

         new SocketThread()
         .withPort(peerArgs.getLocalPort())
         .withIdMap(idMap)
         .withDefaultTargetThread(display)
         .start();
         
         gui.open();
         gui.layout();
         

         if (chatMode == ChatMode.ClientServer)
         {
            new ClientLoginFlow().withServer(peer).withClientIP(InetAddress.getLocalHost().getHostAddress())
            .withClientPort(peerArgs.getLocalPort())
            .withIdMap((SDMLibJsonIdMap) idMap)
            .run();
         }

         else if (chatMode == ChatMode.PeerToPeerNetwork)
         {
            String localHost = InetAddress.getLocalHost().getHostAddress();
            PeerProxy localProxy = new PeerProxy(localHost, peerArgs.getLocalPort(), idMap);
            gui.setPeer(localProxy);
            gui.getProxies().add(localProxy);

            if (peerArgs.getPeerPort() != 0)
            {
               // login to first peer
               new P2PNetworkLoginFlow()
               .withClientName(peerArgs.getUserName())
               .withFirstPeer(new PeerProxy(peerArgs.getPeerIp(), peerArgs.getPeerPort(), idMap))
               .withClientPeer(localProxy)
               .withIdMap((SDMLibJsonIdMap) idMap)
               .run();
            }
            // else nothing to be done, I am the first peer and just wait for the others to show up
         }
         
         
         while (!gui.isDisposed())
         {
            if (!display.readAndDispatch())
            {
               display.sleep();
            }
         }
         
         System.exit(0);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   private PeerProxy peer;
   private Image image;
   private Color black;
   private Color white;
   private GC gc;
   
   public void paintPoint(int x, int y, int r, int g, int b)
   {
      Color newColor = new Color(Display.getDefault(), r, g, b);
      gc.setBackground(newColor);
      gc.fillRectangle(x, y, 5, 5);
      canvas.redraw(x, y, 5, 5, false);
   }

   
   
   private class MouseDownListener extends MouseAdapter
   {
      @Override
      public void mouseDown(MouseEvent e) 
      {
         mouseDown = true;
      }

      @Override
      public void mouseUp(MouseEvent e) 
      {
         mouseDown = false;
      }
   }

   private boolean mouseDown = false;
   private Color blue;
   private Color red;
   private Color currentColor;
   
   public PeerProxy getPeer()
   {
      return peer;
   }
   
   public void setPeer(PeerProxy peer)
   {
      this.peer = peer;
   }

   /**
    * Create the shell.
    * @param display
    */
   public PeerToPeerChat()
   {
      super(Display.getDefault(), SWT.SHELL_TRIM);
      
      setLayout(new GridLayout(2, false));
      
      chatColumn = new Composite(this, SWT.NONE);
      GridData gd_chatColumn = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
      gd_chatColumn.widthHint = 308;
      chatColumn.setLayoutData(gd_chatColumn);
      chatColumn.setLayout(new GridLayout(1, false));
      
      Composite peerInfoRow = new Composite(chatColumn, SWT.NONE);
      peerInfoRow.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
      peerInfoRow.setSize(424, 25);
      peerInfoRow.setLayout(new GridLayout(3, false));
      
      nameLabel = new Label(peerInfoRow, SWT.CENTER);
      nameLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
      nameLabel.setText("Name");
      
      ipLabel = new Label(peerInfoRow, SWT.CENTER);
      GridData gd_ipLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
      gd_ipLabel.widthHint = 80;
      ipLabel.setLayoutData(gd_ipLabel);
      ipLabel.setText("IP");
      
      portLabel = new Label(peerInfoRow, SWT.CENTER);
      GridData gd_port = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
      gd_port.widthHint = 60;
      portLabel.setLayoutData(gd_port);
      portLabel.setText("port");
      
      composite = new Composite(chatColumn, SWT.NONE);
      composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
      composite.setLayout(new GridLayout(2, false));
      
      Label lblChat = new Label(composite, SWT.NONE);
      lblChat.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
      lblChat.setSize(28, 15);
      lblChat.setText("Chat:");
      
      btnClearDrawing = new Button(composite, SWT.NONE);
      btnClearDrawing.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) 
         {
            if (chatMode == ChatMode.OneToOne)
            {
               new ClearDrawingFlow().withGui(PeerToPeerChat.this).run();
            }
            else
            {
               new CTClearDrawing()
               .createParent()
               .withIdMap((SDMLibJsonIdMap) idMap)
               .run();
            }
         }
      });
      btnClearDrawing.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
      btnClearDrawing.setText("Clear Drawing");
      
      allMessages = new Text(chatColumn, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
      allMessages.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
      allMessages.setSize(424, 151);
      
      Composite newMessageRow = new Composite(chatColumn, SWT.NONE);
      newMessageRow.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
      newMessageRow.setSize(424, 35);
      newMessageRow.setLayout(new GridLayout(3, false));
      
      Label lblNewMessage = new Label(newMessageRow, SWT.NONE);
      lblNewMessage.setText("New Message: ");
      
      newMessageText = new Text(newMessageRow, SWT.BORDER);
      newMessageText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
      
      Button btnSend = new Button(newMessageRow, SWT.NONE);
      btnSend.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) 
         {
            if (chatMode == ChatMode.OneToOne)
            {
               new ChatMessageFlow()
               .withGui(PeerToPeerChat.this)
               .run();
            }
            else if (chatMode == ChatMode.PeerToPeerNetwork)
            {
               new P2PChatMessageFlow().withIdMap((SDMLibJsonIdMap) idMap).run();
            }
            else
            {
               try {
                  ((Logger) new Logger().withSubFlow(
                     new CSChatMessageFlow()
                     .withIdMap((SDMLibJsonIdMap) idMap)))
                     .withStartPeer(new PeerProxy()
                     .withIp(InetAddress.getLocalHost().getHostAddress())
                     .withPort(peerArgs.getLocalPort())
                     .withIdMap(idMap))
                     .run();
               } catch (UnknownHostException e1) {
                  e1.printStackTrace();
               }
            }
         }
      });
      btnSend.setText("Send");

      whiteboard = new Composite(this, SWT.NONE);
      whiteboard.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
      whiteboard.setLayout(new GridLayout(1, false));
      
      canvas = new Canvas(whiteboard, SWT.NONE);
      image = new Image(Display.getDefault(), 500, 500);
      black = new Color(Display.getDefault(), 0,0,0);
      white = new Color(Display.getDefault(), 255,255,255);
      blue = new Color(Display.getDefault(), 0,0,255);
      red = new Color(Display.getDefault(), 255,0,0);
      
      currentColor = black;
      
      canvas.addListener (SWT.Paint, new Listener () 
      {
         public void handleEvent (Event event) {
            event.gc.drawImage (image, 0, 0);
         }
      });
      
      canvas.addMouseListener(new MouseDownListener());
      
      canvas.addMouseMoveListener(new MouseMoveListener()
      {
         
         @Override
         public void mouseMove(MouseEvent e)
         {
            if (mouseDown)
            {
               if (chatMode == ChatMode.OneToOne)
               {
                  new DrawPointFlow()
                  .withGui(PeerToPeerChat.this)
                  .withX(e.x)
                  .withY(e.y)
                  .withR(currentColor.getRed())
                  .withG(currentColor.getGreen())
                  .withB(currentColor.getBlue())
                  .run();
               }
               else
               {
                  CTDrawPoint ctDrawPoint = (CTDrawPoint) new CTDrawPoint()
                  .withX(e.x)
                  .withY(e.y)
                  .withR(currentColor.getRed())
                  .withG(currentColor.getGreen())
                  .withB(currentColor.getBlue())
                  .withIdMap((SDMLibJsonIdMap) idMap);
                  
                  ctDrawPoint.createParent()
                  .withIdMap((SDMLibJsonIdMap) idMap)
                  .run();
                  
                  
               }
            }
            
         }
      });
      
      GridData gd_canvas = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
      
      gc = new GC(image);
      
      
      gd_canvas.widthHint = 354;
      canvas.setLayoutData(gd_canvas);
      canvas.setBounds(0, 0, 64, 64);
      
      Composite composite_1 = new Composite(whiteboard, SWT.NONE);
      composite_1.setLayout(new GridLayout(3, false));
      
      Label blueLabel = new Label(composite_1, SWT.NONE);
      blueLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseUp(MouseEvent e) {
            currentColor = blue;
         }
      });
      Image blueImage = new Image(Display.getDefault(), 30, 30);
      GC gc2 = new GC(blueImage);
      gc2.setBackground(blue);
      gc2.fillRectangle(0, 0, 30, 30);
      gc2.dispose();

      blueLabel.setImage(blueImage);
      
      Label blackLabel = new Label(composite_1, SWT.NONE);
      blackLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseUp(MouseEvent e) {
            currentColor = black;
         }
      });
      Image blackImage = new Image(Display.getDefault(), 30, 30);
      gc2 = new GC(blackImage);
      gc2.setBackground(black);
      gc2.fillRectangle(0, 0, 30, 30);
      gc2.dispose();
      blackLabel.setImage(blackImage);
      
      Label redLabel = new Label(composite_1, SWT.NONE);
      redLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseUp(MouseEvent e) {
            currentColor = red;
         }
      });
      Image redImage = new Image(Display.getDefault(), 30, 30);
      gc2 = new GC(redImage);
      gc2.setBackground(red);
      gc2.fillRectangle(0, 0, 30, 30);
      gc2.dispose();
      redLabel.setImage(redImage);
      
      createContents();
   }

   /**
    * Create contents of the shell.
    */
   protected void createContents()
   {
      setText("Peer to Peer Chat");
      setSize(600, 428);

   }

   @Override
   protected void checkSubclass()
   {
      // Disable the check that prevents subclassing of SWT components
   }
   public Label getNameLabel() {
      return nameLabel;
   }
   public Label getIpLabel() {
      return ipLabel;
   }
   public Label getPortLabel() {
      return portLabel;
   }
   public Text getAllMessages() {
      return allMessages;
   }
   public Text getNewMessageText() {
      return newMessageText;
   }

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   private Composite chatColumn;
   private Composite whiteboard;
   private Canvas canvas;
   private Composite composite;
   private Button btnClearDrawing;
   private static JsonIdMap idMap;
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   public Canvas getCanvas() {
      return canvas;
   }

   public void clearImage()
   {
      gc.setBackground(white);
      gc.fillRectangle(0, 0, 500, 500);
      canvas.redraw();
   }
}

