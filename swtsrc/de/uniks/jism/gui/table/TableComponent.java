package de.uniks.jism.gui.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.utils.PropertyChangeClient;

import swing2swt.layout.BorderLayout;
import de.uniks.jism.gui.TableList;

public class TableComponent extends Composite implements Listener, PropertyChangeListener{

	private Text searchText;
	private ArrayList<TableColumnView> columns = new ArrayList<TableColumnView>();
	private TableViewer tableViewer;
	private Table table;
	private Menu headerMenu;
	private Cursor defaultCursor=new Cursor(Display.getDefault(),SWT.CURSOR_ARROW);
	private Cursor handCursor=new Cursor(Display.getDefault(), SWT.CURSOR_HAND);
	private TableItem activeItem;
	private TableViewer fixedElements;
	private boolean isToolTip;
	private Composite tableComposite;
	private TableSyncronizer tableSyncronizer;
	private PopupDialog window;
	private PeerMessage list;
	private String property;

	public TableComponent(Composite parent, int style) {
		super(parent, style);
		
		createContent();
	}
	
	public void createContent(){
		tableComposite = new Composite(this, SWT.NONE | SWT.FILL);
		tableComposite.setLayoutData(BorderLayout.CENTER);
		tableComposite.setLayout(new BorderLayout(0, 0));
		
		tableViewer = new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.FILL);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		headerMenu = new Menu(getShell(), SWT.POP_UP);
		table.setMenu(headerMenu);
		
		
		table.addListener(SWT.MouseMove, this);
		table.addListener(SWT.MouseUp, this);
		table.addListener(SWT.MouseExit, this);
		
		setLayout(new BorderLayout(0, 0));
	}

	public void addFixedColumn(Column column){
		if(fixedElements==null){
			fixedElements = new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.NO_SCROLL| SWT.FILL);
			Table table_fixedElements=fixedElements.getTable();
			table_fixedElements.setLayoutData(BorderLayout.CENTER);
			table_fixedElements.setLayoutData(BorderLayout.WEST);
			table_fixedElements.setHeaderVisible(true);
			table_fixedElements.setLinesVisible(true);
			tableSyncronizer = new TableSyncronizer(this, fixedElements.getTable(), table);
			table.addMouseWheelListener(tableSyncronizer);
			table.addListener(SWT.Selection, tableSyncronizer);
			table.getVerticalBar().addListener(SWT.Selection, tableSyncronizer);
		}
		TableColumnView newColumn = new TableColumnView(this, fixedElements, column);
		this.columns.add(newColumn);
		createMenuItem(headerMenu, newColumn);
		if(column.getAltAttribute()!=null){
			if(!isToolTip){
				isToolTip=true;
				ColumnViewerToolTipSupport.enableFor(fixedElements,ToolTip.NO_RECREATE);
			}
		}
	}
	
	public void setKeyListener(KeyListener listener) {
		searchText.addKeyListener(listener);
	}

	public void addControl(Control control) {
		control.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				3, 1));
	}

	public void addColumn(Column column) {
		TableColumnView newColumn = new TableColumnView(this, tableViewer, column);
		this.columns.add(newColumn);
		createMenuItem(headerMenu, newColumn);
		if(column.getAltAttribute()!=null){
			if(!isToolTip){
				isToolTip=true;
				ColumnViewerToolTipSupport.enableFor(tableViewer,ToolTip.NO_RECREATE);
			}
		}
	}

	public boolean finish(TableViewer viewer) {
		ArrayList<String> items = new ArrayList<String>();
		for (TableColumnView view : columns) {
			if(view.getViewer()==viewer){
				if(view.getColumn().getAttrName()!=null){
					items.add(view.getColumn().getAttrName());
				}else if(view.getColumn().getItem()!=null){
					items.add("empty");
				}
			}
		}

		for (TableColumnView view : columns) {
			Column columnConfig = view.getColumn();
//			if(columnConfig.getRegEx()!=null||columnConfig.getAltAttribute()!=null){
				view.getTableViewerColumn().setLabelProvider(
						new TableCellLabelProvider(columnConfig));
//			}
		}


		// ADD SPECIAL PROVIDER
		for (TableColumnView view : columns) {
			view.addLabelProvider();
		}

		return true;
	}
	
	@Override
	public void redraw(){
		
	}
	public void refresh(){
		if(fixedElements!=null){
			fixedElements.refresh();
		}
		tableViewer.refresh();
		Object listValue = list.get(property);
		if(listValue instanceof Collection<?>){
			Collection<?> listItems=(Collection<?>) listValue;
			for(Iterator<?> i=listItems.iterator(); i.hasNext();){
				Object item = i.next();
				if(item instanceof PeerMessage){
					propertyChange(list, property, null, item);
				}
			}
		}
	}

	public boolean finishDataBinding(TableList item, String searchProperties) {
		return finishDataBinding(item, TableList.PROPERTY_ITEMS, new TableList(), searchProperties);
	}
	
	public boolean finishDataBinding(PeerMessage item,	String property, PeerMessage blanko, String searchProperties) {

		if(fixedElements!=null){
			finish(fixedElements);
		}

		finish(tableViewer);
		
		if(blanko instanceof PropertyChangeClient){
			((PropertyChangeClient) blanko).addPropertyChangeListener(this);	
		}
		this.list=blanko;
		this.property=property;
//FIXME		tableViewer.setInput(input)
//FIXME		talkListTalksObserveSet = BeansObservables.observeSet(
//				Realm.getDefault(), updater.getSearchResults(),
//				updater.getProperty());
//		tableViewer.setInput(talkListTalksObserveSet);
		return true;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public Text getSearchField() {
		return searchText;
	}

	// The createMenuItem method add per column a
	// new MenuItem to the menu
	private void createMenuItem(Menu parent, TableColumnView column) {
		final MenuItem itemName = new MenuItem(parent, SWT.CHECK);
		
		Column columnConfig = column.getColumn();
		itemName.setText(columnConfig.getLabel());
		itemName.setSelection(columnConfig.isVisible());
		
		itemName.addListener(SWT.Selection, new MenueItem(itemName, column));
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt!=null){
			propertyChange(evt.getSource(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		}
	}
	public void propertyChange(Object source, String propertyName, Object oldValue, Object newValue) {
		if(list.equals(source)){
			if(propertyName.equals(property)){
				if(oldValue==null&&newValue!=null){
					if(fixedElements!=null){
						fixedElements.add(newValue);
					}
					if(tableViewer!=null){
						tableViewer.add(newValue);
					}
				}
			}
		}
//		getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
//FIXME SEARCH		if(updater!=null){
//			updater.propertyChange(evt);
//		}
	}

	@Override
	public void handleEvent(Event event) {
	    Point pt = new Point(event.x, event.y);
		TableItem currentItem = table.getItem(pt);
		if(SWT.MouseUp==event.type){
	        ViewerCell cell = getTableViewer().getCell(pt);
	        if(cell!=null){
		        int columnIndex = cell.getColumnIndex();
		        int offset=0;
		        if(fixedElements!=null){
		        	offset=fixedElements.getTable().getColumnCount();
		        }
		        TableColumnView tableColumnView = columns.get(columnIndex+offset);
	    		if(tableColumnView!=null){
	    			Composite parent=this;
	    			int x=event.x;
	    			int y=event.y;
	    			while(parent!=null){
	    				x+=parent.getBounds().x;
	    				y+=parent.getBounds().y;
	    				parent=parent.getParent();
	    			}
	    			tableColumnView.getColumn().setSelection(this, currentItem, x, y);
	    		}
//				setSelection(currentItem, cell, columnIndex);
			}
		}
		if(currentItem==null||currentItem.isDisposed()){
			table.setCursor(defaultCursor);
		}else{
		    boolean activ = false;
		    for(int i=0;i<columns.size();i++){
		    	TableColumnView tableViewerColumn = columns.get(i);
		    	Rectangle positem = currentItem.getBounds(i);
		    	if(positem!=null){
			    	if(event.x>positem.x&&event.x<(positem.x+positem.width)){
			    		if(tableViewerColumn.getColumn().isEditingSupport()){
			    			this.activeItem=currentItem;
				    		activ=true;
				    		TableLabelProvider activeCell = tableViewerColumn.getTableProvider();
				    		Color color = activeCell.getForgroundColorActiv();
					    	if(color!=null){
					    		this.activeItem.setForeground(i, color);
					    	}
					    	color = activeCell.getBackgroundColorActiv();
					    	if(color!=null){
					    		this.activeItem.setBackground(i, color);
					    	}
					    	table.setCursor(handCursor);
			    		}
				    }
		    	}
		    }
		    if(!activ){
		    	table.setCursor(defaultCursor);
		    }
    	}
	}
	public void setPopUpWindow(PopupDialog window){
		if(this.window!=null){
			this.window.close();
			this.window=null;
		}
		this.window=window;
	}

	public Table getTable(){
		return table;
	}

	public void setElementValue(PeerMessage element, String editColumn,
			Object object, Object value) {
		element.set(editColumn, value);
	}
}
