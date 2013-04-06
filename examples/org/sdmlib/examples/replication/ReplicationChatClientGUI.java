package org.sdmlib.examples.replication;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;

public class ReplicationChatClientGUI
{
   private Text chatContent;
   private Text newMsg;
   private Label lblYourName;
   private Label lblServerip;
   private ChatRoot chatRoot;

   /**
    * Launch the application.
    * @param args
    */
   public static void main(String[] args)
   {
      try
      {
         Display display = Display.getDefault();
         ReplicationChatClientGUI window = new ReplicationChatClientGUI();
         display.asyncExec(new ClientInitTask(window, args));
         window.open();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Open the window.
    */
   public void open()
   {
      Display display = Display.getDefault();
      Shell shell = new Shell();
      shell.setSize(450, 483);
      shell.setText("SWT Application");
      shell.setLayout(new GridLayout(1, false));
      
      Composite labelLine = new Composite(shell, SWT.NONE);
      labelLine.setLayout(new GridLayout(3, false));
      
      Label lblWelcomeToThe = new Label(labelLine, SWT.NONE);
      lblWelcomeToThe.setBounds(0, 0, 55, 15);
      lblWelcomeToThe.setText("Welcome to the replication chat!");
      
      lblYourName = new Label(labelLine, SWT.NONE);
      lblYourName.setText("Your Name");
      
      lblServerip = new Label(labelLine, SWT.NONE);
      lblServerip.setText("serverIp");
      
      chatContent = new Text(shell, SWT.BORDER | SWT.MULTI);
      chatContent.setText("<chat>");
      chatContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
      
      Composite typingLine = new Composite(shell, SWT.NONE);
      typingLine.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
      typingLine.setLayout(new GridLayout(2, false));
      
      newMsg = new Text(typingLine, SWT.BORDER);
      newMsg.addKeyListener(new NewMsgAction(this));
      newMsg.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
      
      Button btnSend = new Button(typingLine, SWT.NONE);
      btnSend.addSelectionListener(new NewMsgAction(this));
      btnSend.setText("Send");

      shell.open();
      shell.layout();
      while (!shell.isDisposed())
      {
         if (!display.readAndDispatch())
         {
            display.sleep();
         }
      }
   }
   public Label getLblYourName() {
      return lblYourName;
   }
   public Label getLblServerip() {
      return lblServerip;
   }
   public Text getChatContent() {
      return chatContent;
   }
   public Text getNewMsg() {
      return newMsg;
   }

   public void setRoot(ChatRoot chatRoot)
   {
      this.chatRoot = chatRoot;
   }

   public ChatRoot getRoot()
   {
      return this.chatRoot;
   }

}
