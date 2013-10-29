package org.sdmlib.serialization.logic;

import org.sdmlib.serialization.IdMap;

public class Between implements Condition{
	private double fromValue;
	private double toValue;
	public Between withRange(double from, double to){
		this.fromValue = from;
		this.toValue = to;
		return this;
	}

	@Override
	public boolean matches(IdMap map, Object entity, String property,
			Object value, boolean isMany, int deep) {
		if(value instanceof Number){
			return (((Double)value)>=fromValue && ((Double)value)<=toValue);
		}
		return false;
	}

}
