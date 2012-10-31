package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.interfaces.PeerMessage;

import swing2swt.layout.BorderLayout;

public class SearchTableComponent extends TableComponent {

	private Text searchText;
	private SearchResultUpdater updater;
	private Composite northComponents;
	private Composite firstNorth;

	public SearchTableComponent(Composite parent, int style) {
		super(parent, style);
	}
	
	public void createContent(){
		northComponents = new Composite(this, SWT.FILL);
		northComponents.setLayoutData(BorderLayout.NORTH);
		northComponents.setLayout(new GridLayout(3,false));
		
		firstNorth=new Composite(northComponents, SWT.NONE);
		firstNorth.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblSearch = new Label(northComponents, SWT.NONE);
		lblSearch.setText("Search:");
		searchText = new Text(northComponents, SWT.BORDER | SWT.ICON_SEARCH | SWT.SEARCH);
		
		searchText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		super.createContent();
	}

	public boolean finish(TableViewer viewer) {
		boolean result=super.finish(viewer);
		// DataBinding
		refreshNorthLayout();
		return result;
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
	
	public boolean finishDataBinding(PeerMessage item,	String property, PeerMessage blanko, String searchProperties) {
		boolean result=super.finishDataBinding(item, property, blanko, searchProperties);

		if (updater == null) {
			updater = new SearchResultUpdater(this.searchText, item, property, blanko, searchProperties);
			updater.refresh();
			searchText.addModifyListener(updater);
		}
		return result;
	}

	public SearchResultUpdater getUpdater() {
		return updater;
	}

	public void setUpdater(SearchResultUpdater updater) {
		this.updater = updater;
		searchText.addModifyListener(updater);
	}

	public Composite getNorth() {
		return northComponents;
	}

	public Composite getFirstNorth() {
		return firstNorth;
	}
}
