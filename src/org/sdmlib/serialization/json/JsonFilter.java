package org.sdmlib.serialization.json;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.util.LinkedHashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;
/**
// * The Class JsonFilter.
 * This Class is for filter the elements of a modell. The Standard is filtering the deep of
 * serialization  
 */

public class JsonFilter extends IdMapFilter {
	public static final String PROPERTY_ITEMS = "items";

	/** The Constant REFERENCE. */
	public static final String REF_SUFFIX = "_ref";

	/** The Constant REFERENCE. */
	public static final String REFERENCE = "#";
	/** The Constant Cut the Association. */
	public static final String CUTREFERENCE = "-";

	/** The hyperref ref. */
   public static final String HYPERREFERENCE = "@";

	/** Check if property starts with Model-Prefix */
	private boolean checkModelValue = true;

	/** The objects and exclusive properties. */
	private LinkedHashSet<String> items;

	/**
	 * Instantiates a new json filter.
	 */
	public JsonFilter() {

	}

	/**
	 * Instantiates a new json filter.
	 */
	public JsonFilter(boolean checkModelValue) {
		this.checkModelValue = checkModelValue;
	}

	/**
	 * Instantiates a new json filter.
	 * 
	 * @param deep
	 *            the deep
	 */
	public JsonFilter(int deep) {
		this.deep = deep;
	}

	/**
	 * Instantiates a new json filter.
	 * 
	 * @param filter
	 *            the filter
	 */
	public JsonFilter(String... filters) {
		items = new LinkedHashSet<String>();
		for (String value : filters) {
			if (value != null && value.length() > 0) {
				if (value.startsWith(REFERENCE)
						|| value.startsWith(CUTREFERENCE)
						|| value.startsWith(HYPERREFERENCE)) {
					items.add(value);
				} else {
					items.add(REFERENCE + value);
				}
			}
		}
	}

	/**
	 * Instantiates a new json filter.
	 * 
	 * @param deep
	 *            the deep
	 * @param filter
	 *            the filter
	 */
	public JsonFilter(int deep, String... filters) {
		this(filters);
		this.deep = deep;
	}

	/**
	 * Adds the object or Attribute Starts with # {@link #REFERENCE} or -
	 * {@link #CUTREFERENCE}
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public boolean add(String typ, String... value) {
		if (value != null
				&& (REFERENCE.equals(typ) || CUTREFERENCE.equals(typ))) {
			if (items == null) {
				items = new LinkedHashSet<String>();
			}
			for (String item : value) {
				if (item != null) {
					items.add(typ + item);
				}
			}
			return true;
		}
		return false;
	}

	public boolean add(String value) {
		if (value != null) {
			String typ = value.substring(0, 1);
			return add(typ, value.substring(1));
		}
		return false;
	}

	public JsonFilter with(String typ, String... value) {
		add(typ, value);
		return this;
	}

	public void setEnableModelCheck(boolean value) {
		this.checkModelValue = value;
	}

	public boolean checkProperty(IdMap map, String typ, String property,
			Object value) {
		if (items != null) {
			if (items.contains(typ + property)) {
				return false;
			} else {
				String sessionIdMap = map.getPrefixSession();
				if (isId
						&& (!checkModelValue || property
								.startsWith(sessionIdMap))) {
					String key = map.getKey(value);
					if (items.contains(typ + key)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uni.kassel.peermessage.IdMapFilter#isConvertable(de.uni.kassel.peermessage
	 * .IdMap, java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean isConvertable(IdMap map, Object entity, String property,
			Object value, boolean isMany) {
		if (!super.isConvertable(map, entity, property, value, isMany)) {
			return false;
		}
		if (property.endsWith(REF_SUFFIX)) {
			return false;
		}

		if (!checkProperty(map, CUTREFERENCE, property, entity)) {
			return false;
		}

		if (!checkProperty(map, REFERENCE, property, entity)) {
			return false;
		}

		if (!isManySerialization() && isMany) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isRegard(IdMap map, Object entity, String property,
			Object value, boolean isMany) {
		if (!checkProperty(map, CUTREFERENCE, property, value)) {
			return false;
		}

		return super.isRegard(map, entity, property, value, isMany);
	}

	public Object get(String attrName) {
		int pos = attrName.indexOf(".");
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}
		if (PROPERTY_ITEMS.equalsIgnoreCase(attribute)) {
			return getItems();
		}
		return super.get(attrName);
	}

	public String[] getItems() {
		if (items == null) {
			return null;
		}
		return this.items.toArray(new String[this.items.size()]);
	}

	public boolean set(String attribute, Object value) {
		if (PROPERTY_ITEMS.equalsIgnoreCase(attribute)) {
			if (value instanceof String) {
				return add("" + value);
			} else if (value instanceof String[]) {
				for (String item : (String[]) value) {
					add(item);
				}
				return true;
			}
		}
		return super.set(attribute, value);
	}
}
