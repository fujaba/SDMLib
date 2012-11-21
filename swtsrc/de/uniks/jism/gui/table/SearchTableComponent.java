package de.uniks.jism.gui.table;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

import java.beans.PropertyChangeEvent;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;

import swing2swt.layout.BorderLayout;

public class SearchTableComponent extends TableComponent {

	private Text searchText;
	private SearchResultUpdater updater;
	private Composite northComponents;
	private Composite firstNorth;

	public SearchTableComponent(Composite parent, int style, IdMap map) {
		super(parent, style, map);
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

	public boolean finish(TableViewer viewer, int browserid) {
		boolean result=super.finish(viewer, browserid);
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
			northComponents.setLayout(new GridLayout(northComponents.getChildren().length, false));
		}
	}
	
	public boolean finishDataBinding(Object item, String property, String searchProperties) {
		boolean result=super.finishDataBinding(item, property, searchProperties);

		if (updater == null) {
			updater = new SearchResultUpdater(this.searchText, item, property, searchProperties, getIdMap(), this.list);
			updater.setOwner(this);
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
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		
		if (evt != null && source.equals(evt.getSource())) {
			if(updater!=null&&evt.getNewValue()!=null){
				updater.addNewItem(evt.getNewValue());
			}
		}
	}
}
