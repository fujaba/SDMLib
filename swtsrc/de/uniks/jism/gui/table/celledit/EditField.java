package de.uniks.jism.gui.table.celledit;

import java.text.ParseException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class EditField {
	public static final int FORMAT_INTEGER=1;
	public static final int FORMAT_DOUBLE = 2;
	public static final int FORMAT_COMBOX=3;

	protected Spinner spinner;
	protected Text text;
	protected Combo combo;
	private int format;
	private CellEditorElement owner;
	private Composite parent;
	private String numberFormat;
	private ArrayList<Object> list;
	private IdMap map;
	
	public EditField(CellEditorElement owner, Composite parent){
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
		
		if(combo!=null&& !combo.isDisposed()) {
			combo.dispose();
		}
		combo = null;
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
		if(combo!=null&& !combo.isDisposed()) {
			return combo;
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
		if(combo!=null&& !combo.isDisposed()) {
			SendableEntityCreator creatorClass = this.map.getCreatorClass(value);
			if(creatorClass!=null){
				this.list = this.map.getTypList(creatorClass);
				for(Object item : list){
					combo.add(item.toString());
				}
				combo.setText(""+value);
			}
		}
	}
	public void createControl(String numberFormat, int format) {
		this.numberFormat=numberFormat;
		createControl(format); 
	}
	public void createControl(int format) {
		this.format=format;
		Control control=null;
		if(format==FORMAT_INTEGER){
			spinner = new Spinner(parent, SWT.BORDER);
			spinner.setMaximum(Integer.MAX_VALUE);
			spinner.setMinimum(Integer.MIN_VALUE);
			control=spinner;
			
			spinner.addSelectionListener(new SelectionAdapter() {
	            public void widgetDefaultSelected(SelectionEvent e) {
	            	defaultSelection(e);
	            }
	        });
			
		}else if(format==FORMAT_DOUBLE){
			text=new Text(parent, SWT.BORDER);
			control=text;
		}else if(format==FORMAT_COMBOX){
			combo=new Combo(parent, SWT.BORDER);
			control=combo;
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
		if(format==FORMAT_DOUBLE){
			return text.getText().replaceAll(",",".");
		}
		if(format==FORMAT_INTEGER){
			return spinner.getText();
		}
		if(format==FORMAT_COMBOX){
			return combo.getText();
		}
		return "";
	}
	public Object convertFromString(String value) throws ParseException{
		if((value==null)||(value.trim().equals(""))) return null;
		if(format==FORMAT_INTEGER){
			return Integer.valueOf(value.toString());
		}else if(format==FORMAT_DOUBLE){
			return Double.valueOf(value.toString());
		}else if(format==FORMAT_COMBOX){
			return value.toString();
		}
		return value;
	}
	public String getNumberFormat() {
		return numberFormat;
	}
	public void createControl(int format, IdMap map) {
		this.map=map;
		createControl(format);
	}
	public ArrayList<Object> getList() {
		return list;
	}
}
