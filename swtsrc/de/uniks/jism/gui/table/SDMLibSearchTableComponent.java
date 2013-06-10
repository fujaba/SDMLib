package de.uniks.jism.gui.table;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.TableListCreator;

public class SDMLibSearchTableComponent extends SearchTableComponent
{
   public SDMLibSearchTableComponent(Composite parent, final Object root, final String property) 
   {
      this(parent, root, property, null);
   }
   
   public SDMLibSearchTableComponent(Composite parent, final Object root, final String property, JsonIdMap map) 
	 {
	      super(parent, SWT.NONE);
	      
	      this.map = map;
	      
	      if (this.map == null)
         {
            // derive map from root,
            String rootClassName = root.getClass().getName();
            String creatorCreatorClassName = CGUtil.packageName(rootClassName);
            creatorCreatorClassName = creatorCreatorClassName
               + ".creators.CreatorCreator";
            try
            {
               Class<?> creatorClass = Class.forName(creatorCreatorClassName);

               Method method = creatorClass.getDeclaredMethod("createIdMap",
                  String.class);

               Object obj = method.invoke(null, "gui");

               this.map = (IdMap) obj;
               this.map.withCreator(new TableListCreator());

               if(map!=null){
	               JsonIdMap myMap = (JsonIdMap) map;
	               myMap.toJsonObject(root);
               }

            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
	      final SendableEntityCreator rootCreatorClass = this.map.getCreatorClass(root);
	      
	      Object value = rootCreatorClass.getValue(root, property);
	      
	      String entryTypeName = value.getClass().getName();
	      
	      entryTypeName = entryTypeName.substring(0, entryTypeName.length() - 3);
	      
	      String packageName = CGUtil.packageName(entryTypeName);
	      packageName = CGUtil.packageName(packageName);

	      entryTypeName = CGUtil.shortClassName(entryTypeName);
	      
	      final SendableEntityCreator entityCreatorClass = this.map.getCreatorClasses(packageName + "." + entryTypeName);
	      
	      this.createContent(parent);
	      
	      this.createFromCreator(entityCreatorClass, true);
	      
	      Button addButton = new Button(this.getNorth(), SWT.NONE);
	      addButton.addSelectionListener(new SelectionAdapter() {
	         @Override
	         public void widgetSelected(SelectionEvent e) 
	         {
	            Object entityObj = entityCreatorClass.getSendableInstance(false);
	            
	            rootCreatorClass.setValue(root, property, entityObj, null);
	         }
	      });
	      addButton.setText("Add");
	      
	      Button delButton = new Button(this.getNorth(), SWT.NONE);
	      delButton.addSelectionListener(new SelectionAdapter() {
	         @Override
	         public void widgetSelected(SelectionEvent e) 
	         {
	            Object entityObj = SDMLibSearchTableComponent.this
	                  .getTable(GUIPosition.CENTER)
	                  .getSelection()[0]
	                        .getData();
	            
	            ((EntityFactory) entityCreatorClass).removeObject(entityObj);
	         }
	      });
	      delButton.setText("Del");
	      
	      StringList propList = new StringList();
	      propList.addAll(Arrays.asList(entityCreatorClass.getProperties()));
	      setSearchProperties(propList.concat(","));
	      this.finishDataBinding(root, property);
	   }


   	@Override
	protected UpdateSearchList getUpdateListener() {
		return new SDMLibUpdateSearchList(this);
	}
}
