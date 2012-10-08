package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyrightextends.Lecture;

public class ProfessorSet extends LinkedHashSet<Professor>
{
   public intList getPersNr()
   {
      intList result = new intList();
      
      for (Professor obj : this)
      {
         result.add(obj.getPersNr());
      }
      
      return result;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Professor obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Professor obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }
   public ProfessorSet withPersNr(int value)
   {
      for (Professor obj : this)
      {
         obj.withPersNr(value);
      }
      
      return this;
   }

   public ProfessorSet withName(String value)
   {
      for (Professor obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public ProfessorSet withLecture(Lecture value)
   {
      for (Professor obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public ProfessorSet withoutLecture(Lecture value)
   {
      for (Professor obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Professor elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public ProfessorSet with(Professor value)
   {
      this.add(value);
      return this;
   }
   
   public ProfessorSet without(Professor value)
   {
      this.remove(value);
      return this;
   }
}



