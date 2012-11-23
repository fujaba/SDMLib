package org.sdmlib.examples.groupAccount;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.sdmlib.examples.groupAccount.creators.CreatorCreator;
import org.sdmlib.examples.groupAccount.creators.PersonCreator;

import de.uniks.jism.gui.table.SearchTableComponent;
import de.uniks.jism.gui.table.TableComponent;

public class GroupAccountGUI extends Shell
{
   private GroupAccount groupAccount = new GroupAccount().withPersons(new Person().withName("Albert"));

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
      setLayout(new GridLayout(1, false));
      
      SashForm sashForm = new SashForm(this, SWT.NONE);
      
      
      sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
      
      SearchTableComponent personTable = new SearchTableComponent(sashForm, SWT.NONE, CreatorCreator.createIdMap("gui"));
      
      personTable.createFromCreator(new PersonCreator());
      
      personTable.finishDataBinding(groupAccount, GroupAccount.PROPERTY_PERSONS, "name,balance");
      
      personTable.refresh();
      
      createContents();
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
