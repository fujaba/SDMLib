package org.sdmlib.examples.groupAccount;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.sdmlib.serialization.json.JsonIdMap;

import de.uniks.jism.gui.table.SDMLibSearchTableComponent;
import de.uniks.jism.gui.table.SearchTableComponent;

public class GroupAccountGUI extends Shell
{
   private GroupAccount groupAccount; // = new GroupAccount().withPersons(new Person().withName("Albert"));

   /**
    * Launch the application.
    * @param args
    */
   public static void main(String args[])
   {
      try
      {
         Display display = Display.getDefault();
         GroupAccountGUI shell = new GroupAccountGUI(display);
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
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Create the shell.
    * @param display
    */
   public GroupAccountGUI(Display display)
   {
      super(display, SWT.SHELL_TRIM);
      
      initModel();
      
      setLayout(new GridLayout(1, false));
      
      SashForm sashForm = new SashForm(this, SWT.NONE);
      
      sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
      
      SearchTableComponent personTable = new SDMLibSearchTableComponent(sashForm, groupAccount, GroupAccount.PROPERTY_PERSONS);
      
      SearchTableComponent itemsTable = new SDMLibSearchTableComponent(sashForm, groupAccount, GroupAccount.PROPERTY_ITEMS);
      
      JsonIdMap map = (JsonIdMap) itemsTable.getMap();
      
      map.toJsonObject(groupAccount);
      
      map.setUpdateMsgListener(new PropertyChangeListener()
      {
         @Override
         public void propertyChange(PropertyChangeEvent evt)
         {
            groupAccount.updateBalances();
         }
      });
      
      createContents();
   }

   private void initModel()
   {
      groupAccount = new GroupAccount();
      
      Person albert = groupAccount.createPersons().withName("Albert");
      
      groupAccount.createPersons().withName("Nina");
      
      groupAccount.createItems().withBuyer(albert).withDescription("Bier").withValue(12.0);
      
      groupAccount.updateBalances();
   }

   /**
    * Create contents of the shell.
    */
   protected void createContents()
   {
      setText("SWT Application");
      setSize(944, 549);

   }

   @Override
   protected void checkSubclass()
   {
      // Disable the check that prevents subclassing of SWT components
   }
}
