package org.sdmlib.models.classes;

import java.util.HashMap;
import java.util.HashSet;

public enum Feature
{
   PropertyChangeSupport,
   PatternObject,
   Serialization,
   ALBERTsSets,
   WithExistingCreators,
   WithoutCreators;
   

   public static final HashSet<Feature> getNone(){
      return new HashSet<Feature>();
   }
   
   public static final HashSet<Feature> getAll(){
      HashSet<Feature> result = new HashSet<Feature>();
      result.add(PropertyChangeSupport);
      result.add(PatternObject);
      result.add(Serialization);
      result.add(ALBERTsSets);
      return result;
   }
   
   static HashMap<Feature, String[]> featureSets = new HashMap<Feature, String[]>();
   
   public static final void with(Feature feature, String... values) {
	   featureSets.put(feature, values);
   }
   
   public static final String[] getFeatureSet(Feature feature) {
	   return featureSets.get(feature);
   }   
   
   
   public static final void withOut(Feature feature) {
	   featureSets.remove(feature);
   }
}


