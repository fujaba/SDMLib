package org.sdmlib.test;

public class Test {
	
	@org.junit.Test
	public void main() {
		int d=1;
		Number test;
		Class<? extends Number> integerClass=Integer.class;
//		ArrayList dd = new ArrayList<>();
//		Object hh = dd;
//		dd.add(test);
//		Collection<?> as= (Collection<?>) hh;
		test=1;
//		show(integerClass.cast(test));
		show(d);
		
	}
	
//	public void show(Object value) {
//		if(value instanceof Number) {
//			show((Integer) value);
//		if(value instanceof Integer) {
//			show((Integer) value);
//		} else if(value instanceof Float) {
//			show((Float) value);
//		}
//
//	}
	public void show(Object value) {
		System.out.println("Object: "+value.toString());
	}
	
	
	public void show(Integer value) {
		System.out.println("Integer: "+value.toString());
	}
	public void show(Float value) {
		System.out.println("Float: "+value.toString());
	}
	public void show(Number value) {
		System.out.println("Number: "+value.toString());
	}
}
