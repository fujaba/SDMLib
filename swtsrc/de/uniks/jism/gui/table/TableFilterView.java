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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

import de.uniks.jism.gui.TableList;

public class TableFilterView extends ViewerFilter implements ModifyListener{
	private Text searchText;
	private String lastSearchCriteria = "####";
	private TableComponent component;
	private ArrayList<String> searchProperties = new ArrayList<String>();
	private IdMap map;
	private String[] lastSearchCriteriaItems;
	private Column updateField;
	
	public TableFilterView(TableComponent component, IdMap map){
		this.component=component;
		this.map=map;
	}
	
	public void setSearchProperties(String... searchProperties){
		if(searchProperties!=null){
			this.searchProperties.clear();
			for (String item : searchProperties) {
				this.searchProperties.add(item);
			}
		}
	}
	
	@Override
	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
		TableList list = component.getList();
		ArrayList<Object> items=new ArrayList<Object>();
		for(Iterator<Object> i=list.iterator();i.hasNext();){
			Object item = i.next();
			if(matchesSearchCriteria(item)){
				items.add(item);
			}
		}
		return items.toArray(new Object[items.size()]);
//		return super.filter(viewer, parent, elements);
	}
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		return matchesSearchCriteria(element);
	}
	
	public boolean matchesSearchCriteria(Object item) {
		if(lastSearchCriteriaItems==null){
			return false;
		}
		String fullText = "";
		SendableEntityCreator creatorClass = map.getCreatorClass(item);
		// SEARCH FOR #ID:3
		if(creatorClass!=null){
			for (String property : searchProperties) {
				Object value = creatorClass.getValue(item, property);
				if(value!=null){
					fullText += " " + value.toString().toLowerCase();
				}
			}
			fullText = fullText.trim();
		}

		Boolean matches = true;
		for (String word : lastSearchCriteriaItems) {
			if (!"".equals(word)) {
				if (word.startsWith("#") && ":".indexOf(word) > 1) {
					int pos = ":".indexOf(word);
					String propString = word.substring(1, pos);

					if (searchProperties.contains(propString)) {
						String value = word.substring(pos + 1);
						String itemValue = creatorClass.getValue(item, word.substring(1))
								.toString().toLowerCase();
						// Search for simple Property
						if (itemValue.indexOf(value) < 0) {
							return false;
						}
					}
				} else if (word.startsWith("-") && word.length() > 1) {
					if (fullText.indexOf(word.substring(1)) >= 0) {
						return false;
					}
				} else{
					if (fullText.indexOf(word) < 0) {
						// no this search word is not found in full text
						return false;
					}
				}
			}
		}

		return matches;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		refresh();
	}

	public void refresh() {
		if(searchText!=null){
			refresh(searchText.getText());
		}else{
			refresh("");
		}
	}
	public void refresh(String searchCriteria) {
		// if search did not change do nothing
		if (searchCriteria == null && lastSearchCriteria == null)
			return; // <========= sudden death

		lastSearchCriteria=searchCriteria;
		lastSearchCriteriaItems = new String[] {};
		if (searchCriteria != null) {
			lastSearchCriteriaItems = searchCriteria.toLowerCase().split(" ");
		}
		component.refreshViewer();
		refreshCounter();
	}
	public void refreshCounter(){
		if(updateField!=null){
			TableColumnView column = component.getColumn(updateField);
			if(column!=null){
				column.getTableColumn().setText(updateField.getLabel() + " (" + component.getTable().getItemCount() + ")");
			}
		}
	}
	public void setSearchText(Text searchText) {
		this.searchText = searchText;
	}

	public boolean setCounterField(Column column) {
		this.updateField=column;
		refreshCounter();
		return true;
	}
}
