package org.sdmlib.serialization.yuml;

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
	private int typ;
	private ArrayList<Cardinality> cardinalityValues = new ArrayList<Cardinality>();

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
			String[] items = new String[2];
			for (Cardinality element : cardinalityValues) {
				if (typ == YUMLIdMap.OBJECT) {
					items[0] = element.getSourceID();
					items[1] = element.getTargetID();
				} else {
					items[0] = element.getSourceClazz();
					items[1] = element.getTargetClazz();
				}
				for (int z = 0; z < 2; z++) {
					if (links.containsKey(items[z])) {
						links.get(items[z]).add(element.reset());
					} else {
						HashSet<Cardinality> hashSet = new HashSet<Cardinality>();
						hashSet.add(element.reset());
						links.put(items[z], hashSet);
					}
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
		String key;
		if (typ == YUMLIdMap.OBJECT) {
			key = item.getId();
		} else {
			key = item.getClassName();
		}
		HashSet<Cardinality> showedLinks = links.get(key);
		if (showedLinks == null) {
			sb.append(item.toString(typ, visited.contains(item)));
			visited.add(item);
			return;
		}
		Iterator<Cardinality> iterator = showedLinks.iterator();
		if (iterator.hasNext()) {
			Cardinality entry = iterator.next();
			while (iterator.hasNext() && entry.isShowed()) {
				entry = iterator.next();
			}
			if (!entry.isShowed()) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(item.toString(typ, visited.contains(item)));
				visited.add(item);
				sb.append("-");
				YUMLEntity target = children.get(entry.getTargetID());
				sb.append(target.toString(typ, visited.contains(target)));
				visited.add(target);
				entry.withShowed(true);
				while (iterator.hasNext()) {
					sb.append(",");
					sb.append(item.toString(typ, true));
					sb.append("-");
					visited.add(item);
					sb.append(item.toString(typ, visited.contains(item)));
					entry.withShowed(true);
				}
			}
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
		return this.cardinalityValues.add(cardinality);
	}
}
