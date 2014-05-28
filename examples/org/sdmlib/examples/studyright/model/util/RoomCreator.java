package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Lecture;
import org.sdmlib.examples.studyright.model.University;
import org.sdmlib.examples.studyright.model.Student;
import org.sdmlib.examples.studyright.model.Assignment;

public class RoomCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Room.PROPERTY_ROOMNO,
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_LECTURE,
      Room.PROPERTY_UNI,
      Room.PROPERTY_NEIGHBORS,
      Room.PROPERTY_STUDENTS,
      Room.PROPERTY_ROOM,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Room();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Room.PROPERTY_ROOMNO.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getRoomNo();
      }

      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getCredits();
      }

      if (Room.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getLecture();
      }

      if (Room.PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getUni();
      }

      if (Room.PROPERTY_NEIGHBORS.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getNeighbors();
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getStudents();
      }

      if (Room.PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         return ((Room) target).getRoom();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Room.PROPERTY_ROOMNO.equalsIgnoreCase(attrName))
      {
         ((Room) target).setRoomNo((String) value);
         return true;
      }

      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         ((Room) target).setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (Room.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         ((Room) target).addToLecture((Lecture) value);
         return true;
      }
      
      if ((Room.PROPERTY_LECTURE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).removeFromLecture((Lecture) value);
         return true;
      }

      if (Room.PROPERTY_UNI.equalsIgnoreCase(attrName))
      {
         ((Room) target).setUni((University) value);
         return true;
      }

      if (Room.PROPERTY_NEIGHBORS.equalsIgnoreCase(attrName))
      {
         ((Room) target).addToNeighbors((Room) value);
         return true;
      }
      
      if ((Room.PROPERTY_NEIGHBORS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).removeFromNeighbors((Room) value);
         return true;
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((Room) target).addToStudents((Student) value);
         return true;
      }
      
      if ((Room.PROPERTY_STUDENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).removeFromStudents((Student) value);
         return true;
      }

      if (Room.PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         ((Room) target).setRoom((Assignment) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Room) entity).removeYou();
   }
}


