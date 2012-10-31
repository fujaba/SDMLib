package de.uniks.jism.gui.table;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.interfaces.PeerMessage;

import swing2swt.layout.BorderLayout;
import de.uniks.jism.gui.TableList;

public class TableComponent extends Composite implements Listener{

	private Text searchText;
	private ArrayList<TableColumnView> columns = new ArrayList<TableColumnView>();
	private TableViewer tableViewer;
	private Table table;
	private SearchResultUpdater updater;
	private Menu headerMenu;
	private Composite northComponents;
	private Cursor defaultCursor=new Cursor(Display.getDefault(),SWT.CURSOR_ARROW);
	private Cursor handCursor=new Cursor(Display.getDefault(), SWT.CURSOR_HAND);
	private TableItem activeItem;
	private TableViewer fixedElements;
	private boolean isToolTip;
	private Composite tableComposite;
	private TableSyncronizer tableSyncronizer;
	private PopupDialog window;
	private Composite firstNorth;

	public TableComponent(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		northComponents = new Composite(this, SWT.FILL);
		northComponents.setLayoutData(BorderLayout.NORTH);
		northComponents.setLayout(new GridLayout(3,false));
		
		firstNorth=new Composite(northComponents, SWT.NONE);
		firstNorth.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblSearch = new Label(northComponents, SWT.NONE);
		lblSearch.setText("Search:");
		searchText = new Text(northComponents, SWT.BORDER);
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
//			table.addListener(SWT.MouseDown, tableSyncronizer);
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

	public DataBindingContext finish(Class<?> clazz, TableViewer viewer) {
		DataBindingContext bindingContext = new DataBindingContext();

		ObservableSetContentProvider setContentProvider = new ObservableSetContentProvider();
		viewer.setContentProvider(setContentProvider);

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

		IObservableMap[] observeMaps = BeansObservables.observeMaps(
				setContentProvider.getKnownElements(), clazz,
				items.toArray(new String[items.size()]));
		viewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));

		for (TableColumnView view : columns) {
			Column columnConfig = view.getColumn();
			if(columnConfig.getRegEx()!=null||columnConfig.getAltAttribute()!=null){
				view.getTableViewerColumn().setLabelProvider(
						new TableCellLabelProvider(columnConfig));
			}
		}


		// ADD SPECIAL PROVIDER
		for (TableColumnView view : columns) {
			view.addLabelProvider();
		}



		// DataBinding
		refreshNorthLayout();
		return bindingContext;
	}
	public void refreshNorthLayout(){
		if(firstNorth!=null){
			if(firstNorth.getChildren().length<1){
				firstNorth.setVisible(false);
				firstNorth.dispose();
				firstNorth=null;
			}
		}
		northComponents.setLayout(new GridLayout(northComponents.getChildren().length, false));
	}

	public DataBindingContext finishDataBinding(TableList item, Class<?> itemClazz, String searchProperties) {
		return finishDataBinding(item, itemClazz, TableList.PROPERTY_ITEMS, new TableList(), searchProperties);
	}
	
	public DataBindingContext finishDataBinding(PeerMessage item,
			Class<?> itemClazz, String property, PeerMessage blanko, String searchProperties) {

		if(fixedElements!=null){
			finish(itemClazz, fixedElements);
		}
		DataBindingContext bindingContext = finish(itemClazz, tableViewer);
		if (updater == null) {
			updater = new SearchResultUpdater(this.searchText, item, property, blanko, searchProperties);
			updater.propertyChange(null);
		}
		IObservableSet talkListTalksObserveSet;
		
		if(fixedElements!=null){
			talkListTalksObserveSet = BeansObservables.observeSet(
					Realm.getDefault(), updater.getSearchResults(),
					updater.getProperty());
			fixedElements.setInput(talkListTalksObserveSet);
		}
		
		talkListTalksObserveSet = BeansObservables.observeSet(
				Realm.getDefault(), updater.getSearchResults(),
				updater.getProperty());
		tableViewer.setInput(talkListTalksObserveSet);

		// DataBinding
		return bindingContext;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public Text getSearchField() {
		return searchText;
	}

	public SearchResultUpdater getUpdater() {
		return updater;
	}

	public void setUpdater(SearchResultUpdater updater) {
		this.updater = updater;
		searchText.addModifyListener(updater);
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

	public Composite getNorth() {
		return northComponents;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(updater!=null){
			updater.propertyChange(evt);
		}
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

	public Composite getFirstNorth() {
		return firstNorth;
	}
	public Table getTable(){
		return table;
	}

	public void setElementValue(PeerMessage element, String editColumn,
			Object object, Object value) {
		element.set(editColumn, value);
	}
}
