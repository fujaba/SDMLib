package org.sdmlib.models.classes;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.SDMSet;

import de.uniks.networkparser.graph.Clazz;

public class Feature implements Comparable<Feature>{
	public static final Clazz ALL = new Clazz().with("*");
	public static final Feature PROPERTYCHANGESUPPORT = new Feature("PROPERTYCHANGESUPPORT");
	public static final Feature PATTERNOBJECT = new Feature("PATTERNOBJECT");
	public static final Feature SERIALIZATION = new Feature("SERIALIZATION");
	public static final Feature SETCLASS = new Feature("SETCLASS").withClazzValue(SDMSet.class);
	public static final Feature REMOVEYOUMETHOD = new Feature("REMOVEYOUMETHOD");
	public static final Feature STANDALONE = new Feature("STANDALONE");

	private HashSet<Clazz> includeClazz = new HashSet<Clazz>();
	private HashSet<Clazz> excludeClazz = new HashSet<Clazz>();
	private HashSet<String> path = new HashSet<String>();
	private Class<?> classValue;
	private String name;

   Feature(String name) {
	   this.name = name;
		includeClazz.add(ALL);
   }
   
   
   public static final HashSet<Feature> getNone(){
      return new HashSet<Feature>();
   }
   
   public static HashSet<Feature> getAll(){
      HashSet<Feature> result = new HashSet<Feature>();
      result.add(PROPERTYCHANGESUPPORT.create());
      result.add(PATTERNOBJECT.create());
      result.add(SERIALIZATION.create());
      result.add(SETCLASS.create().withClazzValue(SDMSet.class));
      result.add(REMOVEYOUMETHOD.create());
      return result;
   }
   
   public static HashSet<Feature> getStandAlone(){
      HashSet<Feature> result = new HashSet<Feature>();
      result.add(PROPERTYCHANGESUPPORT.create());
//      result.add(SERIALIZATION.create());
      result.add(SETCLASS.create().withClazzValue(LinkedHashSet.class));
      result.add(REMOVEYOUMETHOD.create());
      result.add(STANDALONE.create());
      return result;
   }
   
	public Feature withIncludeClazz(String... value) {
		if(value == null) {
			return this;
		}
		for(String item : value) {
			if(item != null) {
				excludeClazz.add(new Clazz().with(item));
			}
		}
		
		return this;
	}
	public Feature withExcludeClazz(String... value) {
		if(value == null) {
			return this;
		}
		if(value.length > 0 ){
			// remove ALL
			includeClazz.remove(ALL);
		}
		for(String item : value) {
			if(item != null) {
				includeClazz.add(new Clazz().with(item));
			}
		}
		return this;
	}
	public Feature withExcludeClazz(Clazz... value) {
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
	
	public Feature withClazzValue(Class<?> value) {
		this.classValue = value;
		return this;
	}
	
	public Feature withPath(String... value) {
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

	public HashSet<String> getPath() {
		return path;
	}

	public boolean match(Clazz clazz) {
		return match(clazz.getName(false));
	}
	
	public boolean match(String clazzName){
		// if Clazz is positive
		boolean result=false;
		for(Clazz item : includeClazz) {
			if(item == null) {
				continue;
			}
			if(ALL.getName(false).equals(item.getName(false))) {
				result = true;
				break;
			} else if(item.getName(false).equals(clazzName)) {
				result = true;
				break;
			}
		}
		
		for(Clazz item : excludeClazz) {
			if(item == null) {
				continue;
			}
			if(ALL.getName(false).equals(item.getName(false))) {
				result = false;
				break;
			} else if(item.getName(false).equals(clazzName)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public Class<?> getClassValue() {
		return classValue;
	}

	@Override
	public int compareTo(Feature o) {
		if(name == null) {
			if(o.getName() != null) {
				return 1;
			}
			return 0;
		}
		return name.compareTo(o.getName());
	}
	
	@Override
	public int hashCode() {
		if(name != null) {
			return name.hashCode();
		}
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Feature == false) {
			return false;
		}
		return compareTo((Feature) obj) == 0;
	}

	public Feature create() {
		return new Feature(this.getName());
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		if(name != null) {
			return name;
		}
		return super.toString();
	}
}
