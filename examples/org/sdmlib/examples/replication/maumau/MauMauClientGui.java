package org.sdmlib.examples.replication.maumau;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MauMauClientGui
{

   public static final int CARD_WIDTH = 50;
   public static final int STACK_X = 350 - CARD_WIDTH / 2;
   public static final int CARD_HEIGHT = 80;
   public static final int STACK_Y = 350 - CARD_HEIGHT / 2;
   
   private Shell shell;
   
   public Shell getShell()
   {
      return shell;
   }

   /**
    * Launch the application.
    * @param args
    */
   public static void main(String[] args)
   {
      try
      {
         MauMauClientGui gui = new MauMauClientGui();
         Display.getDefault().asyncExec(new MauMauClientInitTask(gui));
         gui.open();
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
      shell = new Shell();
      shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
      shell.setSize(700, 700);
      shell.setText("SWT Application");
      shell.setLayout(null);
      
     
      Label stack = new Label(shell, SWT.BORDER | SWT.SHADOW_NONE);
      
      stack.setText("      ");
      stack.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
      stack.setAlignment(SWT.CENTER);
      stack.setBounds(STACK_X, STACK_Y, CARD_WIDTH, CARD_HEIGHT);
      

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
}
