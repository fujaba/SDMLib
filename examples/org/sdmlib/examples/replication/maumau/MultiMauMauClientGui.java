package org.sdmlib.examples.replication.maumau;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class MultiMauMauClientGui
{
   /**
    * Launch the application.
    * @param args
    */
   public static void main(String[] args)
   {
      try
      {
         MauMauClientGui gui = new MauMauClientGui();

         Display.getDefault().asyncExec(new MultiMauMauClientInitTask(gui, args));

         gui.open();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
