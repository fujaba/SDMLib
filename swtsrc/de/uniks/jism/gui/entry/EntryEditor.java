package de.uniks.jism.gui.entry;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

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
import de.uniks.jism.gui.text.TextComponent;

public class EntryEditor extends Composite{
	protected TextComponent textComponente;
	protected Tree tree;
	protected EntryTreeItem root;

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
		textComponente = new TextComponent(form, SWT.BORDER);
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
	
	public void setSimpleText(String value){
		textComponente.setSimpleText(value);
		tree.setVisible(false);
		
	}
}
