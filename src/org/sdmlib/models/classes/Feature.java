package org.sdmlib.models.classes;

import java.util.HashSet;

public enum Feature
{
   PropertyChangeSupport, PatternObject, Serialization, ALBERTsSets;
   


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
}
