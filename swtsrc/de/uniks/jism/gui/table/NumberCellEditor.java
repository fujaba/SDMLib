package de.uniks.jism.gui.table;

import java.text.ParseException;

import org.eclipse.jface.viewers.TextCellEditor;
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

public class NumberCellEditor extends TextCellEditor {
	public static final int FORMAT_INTEGER=1;
	protected String numberFormat;
	protected Spinner spinner;
	protected int format;
	
	public NumberCellEditor(String numberFormat, int format) {
		super();
		this.numberFormat = numberFormat;
		this.format = format;
	}

	public NumberCellEditor(Composite parent, String numberFormat, int format) {
		super(parent);
		this.numberFormat = numberFormat;
		this.format = format;
	}

	public NumberCellEditor(Composite parent, int style, String numberFormat, int format) {
		super(parent, style);
		this.numberFormat = numberFormat;
		this.format = format;
	}

	protected Object convertFromString(String value) throws ParseException{
		if((value==null)||(value.trim().equals(""))) return null;
		if(format==FORMAT_INTEGER){
			return Integer.valueOf(value.toString());
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		return spinner.getText();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		spinner.setSelection(Integer.valueOf(""+value));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#isCorrect(java.lang.Object)
	 */
	@Override
	protected boolean isCorrect(Object value) {
		try{
			if(value instanceof Number) return super.isCorrect((Number)value);
			Object o = convertFromString((String)value);
			return super.isCorrect(o);
		}catch(Exception e) {
			setErrorMessage(e.getLocalizedMessage());
			return false;
		}
	}
	
	protected Control createControl(Composite parent) {
        spinner = new Spinner(parent, SWT.BORDER);
        spinner.addSelectionListener(new SelectionAdapter() {
            public void widgetDefaultSelected(SelectionEvent e) {
                handleDefaultSelection(e);
            }
        });
        spinner.addKeyListener(new KeyAdapter() {
            // hook key pressed - see PR 14201  
            public void keyPressed(KeyEvent e) {
                keyReleaseOccured(e);

                // as a result of processing the above call, clients may have
                // disposed this cell editor
                if ((getControl() == null) || getControl().isDisposed()) {
					return;
				}
            }
        });
        spinner.addTraverseListener(new TraverseListener() {
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE
                        || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                    NumberCellEditor.this.focusLost();
                }
            }
        });
 
        // We really want a selection listener but it is not supported so we
        // use a key listener and a mouse listener to know when selection changes
        // may have occurred
       
        spinner.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
            	NumberCellEditor.this.focusLost();
            }
        });
        spinner.setFont(parent.getFont());
        spinner.setBackground(parent.getBackground());
        return text;
    }
	
	@Override
	public Control getControl() {
		return spinner;
	}
	public boolean isActivated() {
		// Use the state of the visible style bit (getVisible()) rather than the
		// window's actual visibility (isVisible()) to get correct handling when
		// an ancestor control goes invisible, see bug 85331.
		return spinner != null && spinner.getVisible();
	}
	  /* (non-Javadoc)
     * Method declared on CellEditor.
     */
	@Override
    protected void doSetFocus() {
        if (spinner != null) {
        	spinner.setFocus();
        }
    }
    /**
	 * Hides this cell editor's control. Does nothing if this cell editor is not
	 * visible.
	 */
	@Override
	public void deactivate() {
		if (spinner != null && !spinner.isDisposed()) {
			spinner.setVisible(false);
		}
	}

	/**
	 * Disposes of this cell editor and frees any associated SWT resources.
	 */
	@Override
	public void dispose() {
		if (spinner != null && !spinner.isDisposed()) {
			spinner.dispose();
		}
		spinner = null;
	}

}
