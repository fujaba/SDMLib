package org.sdmlib.serialization.sort;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or – as soon they
 will be approved by the European Commission - subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.util.Comparator;
import org.sdmlib.serialization.EntityValueFactory;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.gui.table.TableList;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class EntityComparator implements Comparator<Object> {
	public static final String IDMAP = "%idmap%";
	public static final String HASHCODE = "%hashcode%";
	public static final String LIST = "%list%";

	private SortingDirection direction = SortingDirection.ASC;
	private String column = IDMAP;
	private IdMap map;
	private EntityValueFactory cellCreator = new EntityValueFactory();
	private TableList owner;
	protected SendableEntityCreator creator;
	
	public EntityComparator withTableList(TableList owner){
		this.owner = owner;
		this.column = LIST;
		return this;
	}

	@Override
	public int compare(Object o1, Object o2) {
		return direction.getDirection() * compareValue(o2, o1);
	}

	public int compareValue(Object o1, Object o2) {
		if (map != null) {
			creator = map.getCreatorClass(o1);
			SendableEntityCreator c2 = map.getCreatorClass(o2);
			if (creator != c2) {
				creator = null;
			}
		}
		if (creator == null) {
			return checkIntern(o1, o2);
		}

		Object v1 = cellCreator.getCellValue(o1, creator, column);
		Object v2 = cellCreator.getCellValue(o2, creator, column);
		if (v1 == null ){
			if(v2 == null) {
				return checkIntern(o1, o2);
			}
			return checkValues(v1, v2);
		}
		return checkValues(v2, v1)*-1;
	}
	
	private int checkValues(Object v1, Object v2){
		if (v1 instanceof String) {
			String valueA = (String) v1;
			if (v2 != null) {
				String valueB = (String) v2;
				int value = valueB.compareTo(valueA);
				if (value < 1) {
					return -1;
				}
			}
		} else if (v1 instanceof Integer) {
			Integer valueA = (Integer) v1;
			if(v2 != null) {
				Integer valueB = (Integer) v2;
				int value = valueB.compareTo(valueA);

				if (value < 1) {
					return -1;
				}
			}
			return 1;
		} else if (v1 instanceof Long) {
			Long valueA = (Long) v1;
			if(v2 != null) {
				Long valueB = (Long) v2;
				int value = valueB.compareTo(valueA);

				if (value < 1) {
					return -1;
				}
			}
		} else if (v1 instanceof Boolean) {
			Boolean valueA = (Boolean) v1;
			Boolean valueB = (Boolean) v2;
			if (valueB != null) {
				int value = valueB.compareTo(valueA);
				if (value < 1) {
					return -1;
				}
			}
		}
		return 1;
	}

	private int checkIntern(Object o1, Object o2) {
		// SAME OBJECT MUST BE 0
		if (o2 == null) {
			if (o1 == null) {
				return 0;
			}
			return -1;
		} else if (o1 == null) {
			return -1;
		}

		if (o1.equals(o2)) {
			return 0;
		}
		
		if(LIST.equalsIgnoreCase(column) && owner != null) {
			return owner.indexOf(o1)-owner.indexOf(o2);
		}

		// KEY IN IDMAP
		if (IDMAP.equalsIgnoreCase(column) && map != null) {
			String v1 = map.getId(o1);
			String v2 = map.getId(o2);
			return v1.compareTo(v2);
		}
		// HASHCODE
		if (o1.hashCode() < o2.hashCode()) {
			return 1;
		}
		return -1;
	}

	public SortingDirection getDirection() {
		return direction;
	}

	public EntityComparator withDirection(SortingDirection direction) {
		this.direction = direction;
		return this;
	}

	public String getColumn() {
		return column;
	}

	public EntityComparator withColumn(String column) {
		this.column = column;
		return this;
	}

	public EntityComparator withMap(IdMap value) {
		this.map = value;
		return this;
	}

	public IdMap getMap() {
		return map;
	}

	public EntityValueFactory getCellCreator() {
		return cellCreator;
	}

	public EntityComparator withCellCreator(EntityValueFactory cellCreator) {
		this.cellCreator = cellCreator;
		return this;
	}

	public SortingDirection changeDirection() {
		this.direction = direction.changeDirection();
		return direction;
	}
}
