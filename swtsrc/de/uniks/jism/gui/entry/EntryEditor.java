package de.uniks.jism.gui.entry;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.interfaces.JSIMEntity;

import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.layout.BorderLayout;
import de.uniks.jism.gui.text.TextComponente;

public class EntryEditor extends Composite{

	private TextComponente textComponente;
	private Tree tree;
	private EntryTreeItem root;

	public EntryEditor(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		
		SashForm form=new SashForm(this, SWT.NONE);
		
		tree = new Tree(form, SWT.BORDER | SWT.RESIZE);
		tree.setLayoutData(GUIPosition.WEST);
		tree.addListener(SWT.Expand, new Listener() {
			@Override
			public void handleEvent(Event event) {
				EntryTreeItem item=(EntryTreeItem) event.item;
				item.getEntity().setVisible(true);
				textComponente.setText(root.getEntity().toString(2));
			}
		});
		tree.addListener(SWT.Collapse, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				EntryTreeItem item=(EntryTreeItem) event.item;
				item.getEntity().setVisible(false);
				textComponente.setText(root.getEntity().toString(2));
			}
		});
		textComponente = new TextComponente(form, SWT.BORDER);
		form.setWeights(new int[] {65, 382});
	}
	

	public void setEntry(JSIMEntity entry){
		setEntry(entry, null);
	}
	public void setEntry(JSIMEntity entry, String idField){
		if(entry instanceof Entity){
			setEntity(null, (Entity)entry, null);
			textComponente.setText(entry.toString(2));
		}else if(entry instanceof EntityList){
			setEntity(null, (EntityList)entry, null);
			textComponente.setText(entry.toString(2));
		}
	}
	private void setEntity(TreeItem parent, EntityList item, String idField){
		EntryTreeItem ti;
		if(parent==null){
			ti=new EntryTreeItem(tree, idField);
			root=ti;
		}else{
			ti=new EntryTreeItem(parent, idField);
		}
		ti.setText("list");
		ti.setEntity(item);
		for (Iterator<Object> i=item.iterator();i.hasNext();) {
			Object value=i.next();
			if(value instanceof Entity) {
				setEntity(ti, (Entity)value, idField);
			}
			if(value instanceof EntityList) {
				setEntity(ti, (EntityList)value, idField);
			}
		}
	}
	private void setEntity(TreeItem parent, Entity item, String idField)
	{
		EntryTreeItem ti;
		if(parent==null){
			ti=new EntryTreeItem(tree, idField);
			root=ti;
		}else{
			ti=new EntryTreeItem(parent, idField);
		}
		String caption=null;
		if(idField!=null){
			caption=(String) item.get(idField);
		}
		if(caption==null){
			if(item.has("name")){
				caption=item.getString("name");
			}else if(item.has("id")){
				caption=item.getString("id");
			}else {
				caption="item";
			}
		}
		ti.setText(caption);
		ti.setEntity(item);
		
		for(Iterator<String> i=item.keys();i.hasNext();){
			String key=i.next();
			Object value=item.get(key);
			if(value instanceof Entity) {
				setEntity(ti, (Entity)value, idField);
			}
			if(value instanceof EntityList) {
				setEntity(ti, (EntityList)value, idField);
			}
		}
		ti.setExpanded(true);
	}
}
