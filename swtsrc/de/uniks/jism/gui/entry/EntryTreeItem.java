package de.uniks.jism.gui.entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.sdmlib.serialization.interfaces.JSIMEntity;

public class EntryTreeItem extends TreeItem{
	private JSIMEntity entity;
	private String idField;
	public EntryTreeItem(Tree parent, String idField) {
		super(parent, SWT.NONE);
		this.idField = idField;
	}

	public EntryTreeItem(TreeItem parent, String idField){
		super(parent, SWT.NONE);
		this.idField = idField;
	}
	
	public JSIMEntity getEntity() {
		return entity;
	}

	public void setEntity(JSIMEntity entity) {
		this.entity = entity;
	}

	@Override
	protected void checkSubclass() {
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}
}
