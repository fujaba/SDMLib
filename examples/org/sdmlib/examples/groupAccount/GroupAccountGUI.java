package org.sdmlib.examples.groupAccount;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.sdmlib.examples.groupAccount.creators.CreatorCreator;
import org.sdmlib.examples.groupAccount.creators.ItemCreator;
import org.sdmlib.examples.groupAccount.creators.PersonCreator;

import de.uniks.jism.gui.table.SearchTableComponent;
import de.uniks.jism.gui.table.TableComponent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
      
      final SearchTableComponent personTable = new SearchTableComponent(sashForm, groupAccount, GroupAccount.PROPERTY_PERSONS);
      
      Button addButton = new Button(personTable.getNorth(), SWT.NONE);
      addButton.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            new Person().withParent(groupAccount);
         }
      });
      addButton.setText("Add");
      
      Button delButton = new Button(personTable.getNorth(), SWT.NONE);
      delButton.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) 
         {
            Person person = (Person) personTable
                  .getTable()
                  .getSelection()[0]
                        .getData();
            
            person.removeYou();
         }
      });
      delButton.setText("Del");
      
      personTable.finishDataBinding(groupAccount, GroupAccount.PROPERTY_PERSONS, "name,balance");
      
      personTable.refresh();
      
      
      SearchTableComponent itemsTable = new SearchTableComponent(sashForm, SWT.NONE, CreatorCreator.createIdMap("gui"));
      
      itemsTable.createFromCreator(new ItemCreator(), true);
      
      Button button2 = new Button(itemsTable.getNorth(), SWT.NONE);
      button2.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            new Item().withParent(groupAccount);
         }
      });
      button2.setText("Add");
      
      itemsTable.finishDataBinding(groupAccount, GroupAccount.PROPERTY_ITEMS, "text,amount");
      
      
      
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
