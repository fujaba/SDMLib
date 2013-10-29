package org.sdmlib.serialization;

/*
 NetworkParser
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

import org.sdmlib.serialization.logic.Condition;

public class Filter {
	private Condition idFilter;
	private Condition convertable;
	private Condition property;
	
	// Temporary variables
	private LinkedHashSet<Object> visitedObjects;
	private Boolean full;

	public Condition getIdFilter() {
		return idFilter;
	}
	
	public Filter withIdFilter(Condition idFilter) {
		this.idFilter = idFilter;
		return this;
	}
	
	public boolean isId(IdMap map, Object entity, String className) {
		if(idFilter!=null){
			return idFilter.matches(map, entity, className, null, false, 0);
		}
		return true;
	}
	
	
	/**
	 * Serialization the Full object inclusive null value
	 * 
	 * @return boolean for serialization the full object
	 */
	public Boolean isFullSeriation(){
		return full;
	}
	
	public Filter withFull(boolean value){
		this.full = value;
		return this;
	}
	
	public Condition getConvertable() {
		return convertable;
	}
	public Filter withConvertable(Condition convertable) {
		this.convertable = convertable;
		return this;
	}
	public Condition getPropertyRegard() {
		return property;
	}
	public Filter withPropertyRegard(Condition property) {
		this.property = property;
		return this;
	}
	public Filter withStandard(Filter referenceFilter) {
		if(idFilter== null){
			idFilter = referenceFilter.getIdFilter();
		}
		if(convertable== null){
			convertable = referenceFilter.getConvertable();
		}
		if(property== null){
			property = referenceFilter.getPropertyRegard();
		}
		visitedObjects=new LinkedHashSet<Object>();
		if(full==null){
			full = referenceFilter.isFullSeriation();
			if(full==null){
				full=false;
			}
		}
		
		return this;
	}
	
	public Filter clone(){
		return clone(new Filter());
	}
	public Filter clone(Filter newInstance){
		return newInstance.withConvertable(convertable).withIdFilter(idFilter).withPropertyRegard(property);
	}
	
	
	public boolean hasVisitedObjects(String id, Object element) {
		if(id!=null){
			return visitedObjects.contains(id);
		}
		return visitedObjects.contains(element);
	}
	
	public void addToVisitedObjects(Object visitedObjects) {
		this.visitedObjects.add(visitedObjects);
	}

	public boolean isPropertyRegard(IdMap map, Object entity,
			String property, Object value, boolean isMany, int deep) {
		if(this.property!=null){
			return this.property.matches(map, entity, property, value, isMany, deep);
		}
		return true;
	}

	public boolean isConvertable(IdMap map, Object entity,
			String property, Object value, boolean isMany, int deep) {
		if(this.convertable!=null){
			return this.convertable.matches(map, entity, property, value, isMany, deep);
		}
		return true;
	}
}
