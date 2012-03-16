package org.sdmlib.serialization.json;

import java.util.HashSet;

public class JsonFilter {
	public final static int ALLDEEP = -1;
	private int deep = ALLDEEP;
	private String[] exclusiveProperties;
	private HashSet<String> objects = new HashSet<String>();

	public JsonFilter() {

	}

	public JsonFilter(int deep) {
		this.deep = deep;
	}

	public JsonFilter(String... filter) {
		this.exclusiveProperties = filter;
	}

	public JsonFilter(int deep, String... filter) {
		this.deep = deep;
		this.exclusiveProperties = filter;
	}

	public int getDeep() {
		return deep;
	}

	public String[] getExcusiveProperties() {
		return exclusiveProperties;
	}

	public int setDeeper() {
		int oldVlaue = deep;
		if (deep != ALLDEEP) {
			deep = deep - 1;
		}

		return oldVlaue;
	}

	public void setDeep(int value) {
		this.deep = value;
	}

	public boolean existsObject(String id) {
		boolean result = objects.contains(id);
		if (!result) {
			this.objects.add(id);
		}
		return result;
	}
}
