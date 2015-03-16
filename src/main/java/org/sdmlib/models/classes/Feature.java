package org.sdmlib.models.classes;

import java.util.HashSet;

public enum Feature
{
   PropertyChangeSupport(new FeatureProperty()),
   PatternObject(new FeatureProperty()),
   Serialization(new FeatureProperty()),
   ALBERTsSets(new FeatureProperty());
   
   private FeatureProperty feature;
   
   Feature(FeatureProperty value) {
	   this.feature = value;
   }
   
   
   public static final HashSet<Feature> getNone(){
      return new HashSet<Feature>();
   }
   
   public static HashSet<Feature> getAll(){
      HashSet<Feature> result = new HashSet<Feature>();
      result.add(PropertyChangeSupport);
      result.add(PatternObject);
      result.add(Serialization);
      result.add(ALBERTsSets);
      return result;
   }
   
   public FeatureProperty getFeature() {
	   return feature;
   }


	public Feature withIncludeClazz(String... value) {
		getFeature().withInclude(value);
		return this;
	}
	public Feature withExcludeClazz(String... value) {
		getFeature().withExclude(value);
		return this;
	}
	public Feature withExcludeClazz(Clazz... value) {
		getFeature().withExclude(value);
		return this;
	}
	
	public Feature withPath(String... value) {
		getFeature().withPath(value);
		return this;
	}


	public boolean match(Clazz clazz) {
		return getFeature().match(clazz.getFullName());
	}


	public HashSet<String> getPath() {
		return getFeature().getPath();
	}
	
	public static void reset()
	{
	   for (Feature f : Feature.values())
      {
         f.feature = new FeatureProperty();
      }
	}
}


