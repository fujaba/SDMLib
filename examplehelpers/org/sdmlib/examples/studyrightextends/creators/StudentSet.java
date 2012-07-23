package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyrightextends.Person;
import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyrightextends.Lecture;

import org.sdmlib.examples.studyrightextends.creators.PersonSet;

public class StudentSet extends LinkedHashSet<Student>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public intList getMatrNo()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMatrNo());
      }
      
      return result;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }
   
   //==========================================================================
   
   public StudentSet findMyPosition()
   {
      for (Person obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0, int p1)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet setName(String p0)
   {
      for (Student obj : this)
      {
         obj.setName( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public PersonSet withName(String p0)
   {
      PersonSet result = new PersonSet();
      for (Student obj : this)
      {
         result.add(obj.withName( p0));
      }
      return result;
   }

   public StudentSet withMatrNo(int value)
   {
      for (Student obj : this)
      {
         obj.withMatrNo(value);
      }
      
      return this;
   }

   public StudentSet withLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public StudentSet withoutLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

}


