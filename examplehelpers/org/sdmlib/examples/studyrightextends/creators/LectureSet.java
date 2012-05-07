package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.examples.studyrightextends.Student;

public class LectureSet extends LinkedHashSet<Lecture>
{
   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (Lecture obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }

   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (Lecture obj : this)
      {
         result.add(obj.getIn());
      }
      
      return result;
   }
   public ProfessorSet getHas()
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Lecture obj : this)
      {
         result.add(obj.getHas());
      }
      
      return result;
   }
   public StudentSet getListen()
   {
      StudentSet result = new StudentSet();
      
      for (Lecture obj : this)
      {
         result.add(obj.getListen());
      }
      
      return result;
   }
}

