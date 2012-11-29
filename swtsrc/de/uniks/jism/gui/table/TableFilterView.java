package de.uniks.jism.gui.table;

import java.util.ArrayList;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TableFilterView extends ViewerFilter implements ModifyListener{
	private Text searchText;
	private String lastSearchCriteria = "####";
	private TableComponent component;
	private ArrayList<String> searchProperties = new ArrayList<String>();
	private IdMap map;
	private String[] lastSearchCriteriaItems;
	
	public TableFilterView(TableComponent component, IdMap map){
		this.component=component;
		this.map=map;
	}
	
	public void setSearchProperties(String searchProperties){
		if(searchProperties!=null){
			this.searchProperties.clear();
			String[] properties = searchProperties.split(",");
			for (String item : properties) {
				this.searchProperties.add(item);
			}
		}
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
		for (String property : searchProperties) {
			Object value = creatorClass.getValue(item, property);
			if(value!=null){
				fullText += " " + value.toString().toLowerCase();
			}
		}
		fullText = fullText.trim();

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
	}

	public void setSearchText(Text searchText) {
		this.searchText = searchText;
	}
}
