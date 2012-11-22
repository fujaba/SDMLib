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
import java.util.Collection;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

import de.uniks.jism.gui.TableList;

public class SearchResultUpdater implements ModifyListener
{
	private String lastSearchCriteria = "####";
	private Display myDisplay;
	private Text searchText;
	private TableColumnView tableColumnView;
	private TableList searchResult;
	private String property;
	private Object searchIn;
	private ArrayList<String> searchProperties = new ArrayList<String>();
	private SendableEntityCreator searchInCreator;
	private IdMap map;
	private SearchTableComponent owner;

	public SearchResultUpdater(Text searchField, Object searchList,
			String property, String searchProperties, IdMap map, TableList blanko) {
		init(Display.getDefault(), searchField, searchList, property,
				searchProperties);
		this.searchResult = blanko;
		this.searchInCreator = map.getCreatorClass(searchList);
		this.map=map;
	}

	public SearchResultUpdater(Text searchField, Object searchList,
			String property, String searchProperties, IdMap map) {
		init(Display.getDefault(), searchField, searchList, property,
				searchProperties);

		this.searchInCreator = map.getCreatorClass(searchList);
		this.searchResult = new TableList();
		this.map=map;
	}

	public void init(Display display, Text searchField, Object searchList,
			String property, String searchProperties) {
		this.myDisplay = display;
		this.searchText = searchField;
		this.searchIn = searchList;

		this.property = property;
		String[] properties = searchProperties.split(",");
		for (String item : properties) {
			this.searchProperties.add(item);
		}
	}

	public void setTableViewColumn(TableColumnView tableColumnView) {
		this.tableColumnView = tableColumnView;
	}

	public void addNewItem(Object item) {
		String[] split = lastSearchCriteria.split(" ");
		
		Collection<?> resultList = (Collection<?>) searchInCreator.getValue(searchResult, property);
		if (!resultList.contains(item)) {
			if (matchesSearchCriteria(item, split)) {
				searchResult.add(item);
			}
		}
	}

	public void refreshContent() {
		String searchCriteria = searchText.getText().trim().toLowerCase();
		
		// new search
		lastSearchCriteria = searchCriteria;
		
		String[] split = new String[] {};
		if (searchCriteria != null) {
			split = searchCriteria.split(" ");
		}

		// compare root.talklist and searchresults
		Collection<?> sourceList = (Collection<?>) searchInCreator.getValue(searchIn, property);
		Object[] list = searchResult.toArray(new Object[searchResult.size()]);
		for (int i = 0; i < list.length; i++) {
			// is this still in root.talklist?

			if (!sourceList.contains(list[i])) {
				searchResult.remove(list[i]);
				continue;
			}

			// does it still match the search criteria?
			if (!matchesSearchCriteria(list[i], split)) {
				//FIXME
//				System.out.print("REMOVE child "+searchResult.size()+":"+ list[i]);
//				if(!searchResult.contains(list[i])){
//					System.out.println("ERROR");
//				}
				searchResult.remove(list[i]);
//				System.out.println(" NEW COUNT: "+searchResult.size());
			}
		}
		// and now the other way round
		for (Object child : sourceList) {
			if (!searchResult.contains(child)) {
				if (matchesSearchCriteria(child, split)) {
					searchResult.add(child);
				}
			}
		}
		if (tableColumnView != null) {
			TableColumn tableColumn = tableColumnView.getTableColumn();
			if(tableColumn!=null){
				tableColumn.setText(tableColumnView.getColumn().getLabel()+ " (" + searchResult.size() + ")");
			}
		}
		
		System.out.println("COUNT OF CONTENT: "+searchResult.size());
		owner.refresh();
	}

	private boolean matchesSearchCriteria(Object item, String[] split) {
		String fullText = "";
		SendableEntityCreator creatorClass = map.getCreatorClass(item);
		// SEARCH FOR #ID:3
		for (String property : searchProperties) {
			Object value = creatorClass.getValue(item, property);
			if(value!=null){
				fullText += " " + value.toString().toLowerCase();
			}
		}
		fullText = fullText.trim();

		Boolean matches = true;
		for (String word : split) {
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

	public TableList getSearchResults() {
		return searchResult;
	}

	public Object getSearchList() {
		return searchIn;
	}

	@Override
	public void modifyText(ModifyEvent event) {
		refresh();
	}

	public void refresh() {
		String searchCriteria = searchText.getText();
		// if search did not change do nothing
		if (searchCriteria == null && lastSearchCriteria == null)
			return; // <========= sudden death

		searchCriteria = searchCriteria.trim().toLowerCase();
		if (searchCriteria.equals(lastSearchCriteria))
			return; // <========= sudden death
		if (myDisplay == null)
			return; // <=========================sudden death

		myDisplay.syncExec(new Runnable() {
			@Override
			public void run() {
				refreshContent();
			}
		});
	}

	public String getProperty() {
		return property;
	}

	public void setOwner(SearchTableComponent searchTableComponent) {
		this.owner=searchTableComponent;
	}
}
