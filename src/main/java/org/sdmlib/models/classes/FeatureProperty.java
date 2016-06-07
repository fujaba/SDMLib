package org.sdmlib.models.classes;

import java.util.HashSet;

import de.uniks.networkparser.graph.Clazz;

public class FeatureProperty implements Comparable<FeatureProperty> {
	public static final Clazz ALL = new Clazz().with("*");

	private HashSet<Clazz> includeClazz = new HashSet<Clazz>();
	private HashSet<Clazz> excludeClazz = new HashSet<Clazz>();
	private HashSet<String> path = new HashSet<String>();
	private Class<?> classValue;
	private Feature name;

	FeatureProperty(Feature name) {
		this.name = name;
		includeClazz.add(ALL);
	}

	public FeatureProperty withIncludeClazz(String... value) {
		if (value == null) {
			return this;
		}
		for (String item : value) {
			if (item != null) {
				excludeClazz.add(new Clazz().with(item));
			}
		}

		return this;
	}

	public FeatureProperty withExcludeClazz(String... value) {
		if (value == null) {
			return this;
		}
		if (value.length > 0) {
			// remove ALL
			includeClazz.remove(ALL);
		}
		for (String item : value) {
			if (item != null) {
				includeClazz.add(new Clazz().with(item));
			}
		}
		return this;
	}

	public FeatureProperty withExcludeClazz(Clazz... value) {
		if (value == null) {
			return this;
		}
		if (value.length > 0) {
			// remove ALL
			includeClazz.remove(ALL);
		}
		for (Clazz item : value) {
			if (item != null) {
				includeClazz.add(item);
			}
		}
		return this;
	}

	public boolean match(Clazz clazz) {
		return match(clazz.getName(false));
	}

	public boolean match(String clazzName) {
		// if Clazz is positive
		boolean result = false;
		for (Clazz item : includeClazz) {
			if (item == null) {
				continue;
			}
			if (ALL.getName(false).equals(item.getName(false))) {
				result = true;
				break;
			} else if (item.getName(false).equals(clazzName)) {
				result = true;
				break;
			}
		}

		for (Clazz item : excludeClazz) {
			if (item == null) {
				continue;
			}
			if (ALL.getName(false).equals(item.getName(false))) {
				result = false;
				break;
			} else if (item.getName(false).equals(clazzName)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public Feature getName() {
		return name;
	}

	public FeatureProperty withClazzValue(Class<?> value) {
		this.classValue = value;
		return this;
	}

	public FeatureProperty withPath(String... value) {
		if (value == null) {
			return this;
		}
		for (String item : value) {
			if (item != null) {
				path.add(item);
			}
		}
		return this;
	}

	public HashSet<String> getPath() {
		return path;
	}

	public Class<?> getClassValue() {
		return classValue;
	}

	@Override
	public int compareTo(FeatureProperty o) {
		if (this.name == null) {
			return 1;
		}
		return this.name.compareTo(o.getName());
	}

	@Override
	public int hashCode() {
		if (name != null) {
			return name.hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Feature) {
			return obj.equals(this.name);
		}
		if (obj instanceof FeatureProperty == false) {
			return false;
		}
		return compareTo((FeatureProperty) obj) == 0;
	}

	@Override
	public String toString() {
		if (name != null) {
			return name.toString();
		}
		return super.toString();
	}

}
