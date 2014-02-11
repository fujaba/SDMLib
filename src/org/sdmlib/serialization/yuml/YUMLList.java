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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;

public class YUMLList implements BaseEntityList {
	private LinkedHashMap<String, YUMLEntity> children = new LinkedHashMap<String, YUMLEntity>();
	private ArrayList<Cardinality> cardinalities = new ArrayList<Cardinality>();
	private int typ;

	@Override
	public BaseEntityList initWithMap(Collection<?> value) {
		for (Iterator<?> i = value.iterator(); i.hasNext();) {
			Object item = i.next();
			if (item instanceof YUMLEntity) {
				YUMLEntity entity = (YUMLEntity) item;
				children.put(entity.getId(), entity);
			}
		}
		return this;
	}

	@Override
	public BaseEntityList put(Object value) {
		if (value instanceof YUMLEntity) {
			YUMLEntity entity = (YUMLEntity) value;
			children.put(entity.getId(), entity);
		}
		return this;
	}

	@Override
	public int size() {
		return children.size();
	}

	@Override
	public boolean add(Object value) {
		if (value instanceof YUMLEntity) {
			YUMLEntity entity = (YUMLEntity) value;
			children.put(entity.getId(), entity);
			return true;
		}
		return false;
	}

	@Override
	public Object get(int z) {
		Iterator<Entry<String, YUMLEntity>> iterator = children.entrySet()
				.iterator();
		while (z > 0 && iterator.hasNext()) {
			iterator.next();
		}
		if (z == 0) {
			return iterator.next().getValue();
		}
		return null;
	}

	public YUMLEntity getById(String id) {
		return children.get(id);
	}

	@Override
	public BaseEntityList getNewArray() {
		return new YUMLList();
	}

	@Override
	public BaseEntity getNewObject() {
		return new YUMLEntity();
	}

	@Override
	public String toString() {
		return toString(0, 0);
	}

	@Override
	public String toString(int indentFactor) {
		return toString(0, 0);
	}

	@Override
	public String toString(int indentFactor, int intent) {
		if (children.size() > 0) {
			StringBuilder sb = new StringBuilder();
			Iterator<YUMLEntity> i = children.values().iterator();

			HashMap<String, HashSet<Cardinality>> links = new HashMap<String, HashSet<Cardinality>>();
			for (Cardinality element : cardinalities) {
				String key = element.getSource().getTyp(typ);
				if (links.containsKey(key)) {
					links.get(key).add(element);
				} else {
					HashSet<Cardinality> hashSet = new HashSet<Cardinality>();
					hashSet.add(element);
					links.put(key, hashSet);
				}
			}

			HashSet<YUMLEntity> visitedObj = new HashSet<YUMLEntity>();

			parse(i.next(), sb, visitedObj, links);
			while (i.hasNext()) {
				parse(i.next(), sb, visitedObj, links);
			}
			return sb.toString();
		}
		return null;
	}

	public void parse(YUMLEntity item, StringBuilder sb,
			HashSet<YUMLEntity> visited,
			HashMap<String, HashSet<Cardinality>> links) {
		String key = item.getTyp(typ);
		HashSet<Cardinality> showedLinks = links.get(key);
		if (showedLinks == null) {
			sb.append(item.toString(typ, visited.contains(item)));
			visited.add(item);
			return;
		}
		Iterator<Cardinality> iterator = showedLinks.iterator();
		while (iterator.hasNext() ) {
			Cardinality entry = iterator.next();
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(item.toString(typ, visited.contains(item)));
			visited.add(item);
			sb.append("-");
			YUMLEntity target;
			target = children.get(entry.getTarget().getTyp(typ));

			sb.append(target.toString(typ, visited.contains(target)));
			visited.add(target);
		}
	}

	@Override
	public BaseEntity withVisible(boolean value) {
		return this;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public int getTyp() {
		return typ;
	}

	public YUMLList withTyp(int typ) {
		this.typ = typ;
		return this;
	}

	public boolean addCardinality(Cardinality cardinality) {
		return this.cardinalities.add(cardinality);
	}
	public YUMLList withCardinality(String sourceID, String targetID) {
		YUMLEntity source = children.get(sourceID);
		if(source==null){
			source = new YUMLEntity().withClassName(sourceID);
			children.put(sourceID, source);
		}
		YUMLEntity target = children.get(targetID);
		if(target==null){
			target = new YUMLEntity().withClassName(targetID);
			children.put(targetID, target);
		}
		
		for(Cardinality item : this.cardinalities){
			if(item.getSource().getTyp(typ).equals(source.getTyp(typ)) && item.getTarget().getTyp(typ).equals(target.getTyp(typ))){
				return this;
			}else if(item.getSource().getTyp(typ).equals(target.getTyp(typ)) && item.getTarget().getTyp(typ).equals(source.getTyp(typ))){
				return this;
			}
		}
		Cardinality cardinality = new Cardinality().withSource(source).withTarget(target);
		this.cardinalities.add(cardinality);

		return this;
	}

}
