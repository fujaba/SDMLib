package org.sdmlib.models.classes;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.SDMSet;

public enum Feature {
	PROPERTYCHANGESUPPORT, PATTERNOBJECT, SERIALIZATION, SETCLASS, REMOVEYOUMETHOD, STANDALONE,EMFSTYLE;
	public static final HashSet<FeatureProperty> getNone(){
	      return new HashSet<FeatureProperty>();
	   }
	   
	   public static HashSet<FeatureProperty> getAll(){
	      HashSet<FeatureProperty> result = new HashSet<FeatureProperty>();
	      result.add(PROPERTYCHANGESUPPORT.create());
	      result.add(PATTERNOBJECT.create());
	      result.add(SERIALIZATION.create());
	      result.add(SETCLASS.create().withClazzValue(SDMSet.class));
	      result.add(REMOVEYOUMETHOD.create());
	      return result;
	   }
	   
	   public static HashSet<FeatureProperty> getStandAlone(){
	      HashSet<FeatureProperty> result = new HashSet<FeatureProperty>();
	      result.add(PROPERTYCHANGESUPPORT.create());
//	      result.add(SERIALIZATION.create());
	      result.add(SETCLASS.create().withClazzValue(LinkedHashSet.class));
	      result.add(REMOVEYOUMETHOD.create());
	      result.add(STANDALONE.create());
	      return result;
	   }
	   
	public final FeatureProperty create() {
		return new FeatureProperty(this); 
	}
}
