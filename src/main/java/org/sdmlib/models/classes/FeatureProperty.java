package org.sdmlib.models.classes;

import java.util.HashSet;

import de.uniks.networkparser.graph.Clazz;

public class FeatureProperty {
	public static final Clazz ALL = new Clazz("*");
	private HashSet<Clazz> includeClazz = new HashSet<Clazz>();
	private HashSet<Clazz> excludeClazz = new HashSet<Clazz>();
	private HashSet<String> path = new HashSet<String>();
	
	public FeatureProperty(){
		includeClazz.add(ALL);
	}
	
	public boolean match(String clazzName){
		// if Clazz is positive
		boolean result=false;
		for(Clazz item : includeClazz) {
			if(item == null) {
				continue;
			}
			if(ALL.getFullName().equals(item.getFullName())) {
				result = true;
				break;
			} else if(item.getFullName().equals(clazzName)) {
				result = true;
				break;
			}
		}
		
		for(Clazz item : excludeClazz) {
			if(item == null) {
				continue;
			}
			if(ALL.getFullName().equals(item.getFullName())) {
				result = false;
				break;
			} else if(item.getFullName().equals(clazzName)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public FeatureProperty withPath(String... value) {
		if(value == null) {
			return this;
		}
		for(String item : value) {
			if(item != null) {
				path.add(item);
			}
		}
		return this;
	}

	public FeatureProperty withInclude(String... value) {
		if(value == null) {
			return this;
		}
		for(String item : value) {
			if(item != null) {
				excludeClazz.add(new Clazz(item));
			}
		}
		
		return this;
	}

	public FeatureProperty withExclude(String... value) {
		if(value == null) {
			return this;
		}
		if(value.length > 0 ){
			// remove ALL
			includeClazz.remove(ALL);
		}
		for(String item : value) {
			if(item != null) {
				includeClazz.add(new Clazz(item));
			}
		}
		
		return this;
	}
	
	public FeatureProperty withExclude(Clazz... value) {
		if(value == null) {
			return this;
		}
		if(value.length > 0 ){
			// remove ALL
			includeClazz.remove(ALL);
		}
		for(Clazz item : value) {
			if(item != null) {
				includeClazz.add(item);
			}
		}
		
		return this;
	}

	public HashSet<String> getPath() {
		return path;
	}
}
