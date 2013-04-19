package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class PlayerListener implements PropertyChangeListener
{

   private Player player;
   private Label label;

   public PlayerListener(Player player, Label lblPlayername)
   {
      this.player = player;
      label = lblPlayername;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null)
      {
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
      }
      else
      {
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
      }

   }

}
