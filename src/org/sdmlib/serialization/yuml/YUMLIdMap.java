package org.sdmlib.serialization.yuml;

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
import java.util.Collection;
import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
/**
 * The Class YUMLIdParser.
 */

public class YUMLIdMap extends IdMap {
	/** The Constant URL. */
	public static final String URL = "http://yuml.me/diagram/class/";

	/** The Constant for CLASS Diagramms. */
	public static final int CLASS = 1;

	/** The Constant for OBJECT Diagramms. */
	public static final int OBJECT = 2;

	private YUMLIdMapFilter filter = new YUMLIdMapFilter().withShowCardinality(
			true).withTyp(CLASS);

	/**
	 * Instantiates a new yUML id parser.
	 */
	public YUMLIdMap() {
		super();
	}

	/**
	 * Instantiates a new yUML id parser.
	 * 
	 * @param parent
	 *            the parent
	 */
	public YUMLIdMap(IdMap parent) {
		super(parent);
	}

	/**
	 * Parses the object.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	public String parseObject(Object object) {
		return parse(object, filter.clone(new YUMLIdMapFilter())
				.withTyp(OBJECT));
	}

	/**
	 * Parses the class.
	 * 
	 * @param object
	 *            the object
	 * @param showCardinality
	 *            the show cardinality
	 * @return the string
	 */
	public String parseClass(Object object) {
		return parse(object, filter.clone(new YUMLIdMapFilter()).withTyp(CLASS));
	}

	public String parse(Object object, YUMLIdMapFilter filter) {
		YUMLList list = new YUMLList().withTyp(filter.getTyp());
		parse(object, filter, list, 0);
		return list.toString();
	}

	/**
	 * Parses the.
	 * 
	 * @param object
	 *            the object to Serialisation
	 * @param typ
	 *            Is it a OBJECT OR A CLASS diagram
	 * @param filter
	 *            Filter for Serialisation
	 * @param showCardinality
	 *            the show cardinality
	 * @return the Object as String
	 */
	private YUMLEntity parse(Object object, YUMLIdMapFilter filter,
			YUMLList list, int deep) {
		if (object == null) {
			return null;
		}

		String mainKey = getId(object);
		YUMLEntity element = list.getById(mainKey);
		if (element != null) {
			return element;
		}

		SendableEntityCreator prototyp = getCreatorClass(object);
		String className = object.getClass().getName();
		;
		className = className.substring(className.lastIndexOf('.') + 1);

		element = new YUMLEntity();
		element.withId(mainKey);
		element.withClassName(className);
		list.add(element);
		if (prototyp != null) {
			for (String property : prototyp.getProperties()) {
				Object value = prototyp.getValue(object, property);
				if (value == null) {
					continue;
				}
				if (value instanceof Collection<?>) {
					for (Object containee : ((Collection<?>) value)) {
						parsePropertyValue(object, filter, list, deep, element,
								property, containee, "0..n");
					}
				} else {
					parsePropertyValue(object, filter, list, deep, element,
							property, value, "0..1");
				}
			}
		}
		return element;
	}

	private void parsePropertyValue(Object entity, YUMLIdMapFilter filter,
			YUMLList list, int deep, YUMLEntity element, String property,
			Object item, String cardinality) {
		if (item == null) {
			return;
		}
		if (!filter.isPropertyRegard(this, entity, property, item, true,
				deep + 1)) {
			return;
		}
		if (!filter.isConvertable(this, entity, property, item, true, deep + 1)) {
			return;
		}
		SendableEntityCreator valueCreater = getCreatorClass(item);
		if (valueCreater != null) {
			YUMLEntity subId = parse(item, filter, list, deep + 1);
			list.addCardinality(new Cardinality().withSource(element)
					.withTarget(subId).withCardinality(cardinality));
		} else {
			element.addValue(property, item.getClass().getName(), "" + item);
		}
		return;
	}

	@Override
	public BaseEntity encode(Object value) {
		YUMLList list = new YUMLList();
		parse(value, this.filter.clone(new YUMLIdMapFilter()), list, 0);
		return list;
	}

	@Override
	public BaseEntity encode(Object value, Filter filter) {
		YUMLList list = new YUMLList();
		if (filter instanceof YUMLIdMapFilter) {
			YUMLIdMapFilter yumlFilter = (YUMLIdMapFilter) filter;
			list.withTyp(yumlFilter.getTyp());
			parse(value, yumlFilter, list, 0);
		}
		return list;
	}

	@Override
	public Object decode(BaseEntity value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the class name.
	 * 
	 * @param object
	 *            the object
	 * @return the class name
	 */
	public String getClassName(Object object) {
		if (object instanceof String) {
			object = getObject((String) object);
		}
		String className = object.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}
}
