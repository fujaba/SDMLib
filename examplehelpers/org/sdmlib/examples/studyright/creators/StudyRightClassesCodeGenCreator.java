package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyrightextends.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.StudyRightClassesCodeGen;

public class StudyRightClassesCodeGenCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new StudyRightClassesCodeGen();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StudyRightClassesCodeGen) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((StudyRightClassesCodeGen) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StudyRightClassesCodeGen) entity).removeYou();
   }
}


