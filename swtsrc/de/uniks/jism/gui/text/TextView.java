package de.uniks.jism.gui.text;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TextView extends TabItem{

	private TextComponente textComponente;

	public TextView(TabFolder parent, Shell shell, String label) {
		super(parent, SWT.NONE);

		setText(label);

		textComponente=new TextComponente(parent, shell, SWT.V_SCROLL | SWT.MULTI);
		setControl(textComponente);
	}
	public void setTextComponent(String text)
	{
		if(textComponente!=null){
			textComponente.setText(text);
		}
	}
	public TextComponente getTextComponente(){
		return textComponente;
	}
	
	protected void checkSubclass() {
	}
}
