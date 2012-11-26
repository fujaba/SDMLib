package de.uniks.jism.gui.table;

import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class EditField {
	protected Spinner spinner;
	protected Text text;
	private int format;
	private NumberCellEditor owner;
	private Composite parent;
	private String numberFormat;
	
	public EditField(NumberCellEditor owner, Composite parent){
		this.owner=owner;
		this.parent=parent;
		
	}
	public void dispose(){
		if (spinner != null && !spinner.isDisposed()) {
			spinner.dispose();
		}
		spinner = null;
		
		if (text != null && !text.isDisposed()) {
			text.dispose();
		}
		text = null;
	}
	public int getFormat() {
		return format;
	}
	
	public Control getControl() {
		if (spinner != null && !spinner.isDisposed()) {
			return spinner;
		}
		if (text != null && !text.isDisposed()) {
			return text;
		}
		return null;
	}
	
	
	public void setVisible(boolean value){
		 Control control=getControl();
		 if(control!=null){
			 control.setVisible(value);
		 }
	}
	
	public boolean isVisible(){
		 Control control=getControl();
		 if(control!=null){
			 return control.getVisible();
		 }
		return false;
	}
	
	public void setFocus(){
		 Control control=getControl();
		 if(control!=null){
			 control.setFocus();
		 }
	}
	
	
	public void doSetValue(Object value) {
		if (spinner != null && !spinner.isDisposed()) {
			if(value instanceof Integer || value instanceof String){
				spinner.setSelection(Integer.valueOf(""+value));
			}
		}
		if (text != null && !text.isDisposed()) {
			text.setText(""+value);
//			text.setSelection(""+value);
		}
	}
	public void createControl(String numberFormat, int format) {
		this.numberFormat=numberFormat;
		this.format=format;
		Control control=null;
		if(format==NumberCellEditor.FORMAT_INTEGER){
			spinner = new Spinner(parent, SWT.BORDER);
			control=spinner;
			
			spinner.addSelectionListener(new SelectionAdapter() {
	            public void widgetDefaultSelected(SelectionEvent e) {
	            	defaultSelection(e);
	            }
	        });
			
		}else if(format==NumberCellEditor.FORMAT_DOUBLE){
			text=new Text(parent, SWT.BORDER);
			control=text;
		}
		if(control!=null){
			control.addKeyListener(new KeyAdapter() {
	            // hook key pressed - see PR 14201  
	            public void keyPressed(KeyEvent e) {
	            	keyRelease(e);
	                // as a result of processing the above call, clients may have
	                // disposed this cell editor
	                if ((getControl() == null) || getControl().isDisposed()) {
						return;
					}
	            }
	        });
			control.addTraverseListener(new TraverseListener() {
	            public void keyTraversed(TraverseEvent e) {
	                if (e.detail == SWT.TRAVERSE_ESCAPE
	                        || e.detail == SWT.TRAVERSE_RETURN) {
	                    e.doit = false;
	                    onFocusLost();
	                }
	            }
	        });
	 
	        // We really want a selection listener but it is not supported so we
	        // use a key listener and a mouse listener to know when selection changes
	        // may have occurred
	       
			control.addFocusListener(new FocusAdapter() {
	            public void focusLost(FocusEvent e) {
	            	onFocusLost();
	            }
	        });
			control.setFont(parent.getFont());
			control.setBackground(parent.getBackground());		
		}
	}
	public void defaultSelection(SelectionEvent event){
		owner.defaultSelection(event);
	}
	public void keyRelease(KeyEvent event){
		owner.keyRelease(event);
	}
	public void onFocusLost(){
		owner.onFocusLost();
	}
	public Object getText() {
		if(format==NumberCellEditor.FORMAT_DOUBLE){
			return text.getText().replaceAll(",",".");
		}
		return spinner.getText();
	}
	public Object convertFromString(String value) throws ParseException{
		if((value==null)||(value.trim().equals(""))) return null;
		if(format==NumberCellEditor.FORMAT_INTEGER){
			return Integer.valueOf(value.toString());
		}else if(format==NumberCellEditor.FORMAT_DOUBLE){
			return Double.valueOf(value.toString());
		}
		return value;
	}
	public String getNumberFormat() {
		return numberFormat;
	}
}
