/*
   Copyright (c) 2013 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.studyrightextends.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.models.modelsets.StringList;

public class LectureSet extends LinkedHashSet<Lecture> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Lecture elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.studyrightextends.Lecture";
   }


   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (Lecture obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }

   public LectureSet withTitle(String value)
   {
      for (Lecture obj : this)
      {
         obj.setTitle(value);
      }
      
      return this;
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

   public LectureSet withIn(Room value)
   {
      for (Lecture obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
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

   public LectureSet withHas(Professor value)
   {
      for (Lecture obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
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

   public LectureSet withListen(Student value)
   {
      for (Lecture obj : this)
      {
         obj.withListen(value);
      }
      
      return this;
   }



   public LecturePO startModelPattern()
   {
      org.sdmlib.examples.studyrightextends.creators.ModelPattern pattern = new org.sdmlib.examples.studyrightextends.creators.ModelPattern();
      
      LecturePO patternObject = pattern.hasElementLecturePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LectureSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Lecture>)value);
      }
      else if (value != null)
      {
         this.add((Lecture) value);
      }
      
      return this;
   }
   
   public LectureSet without(Lecture value)
   {
      this.remove(value);
      return this;
   }



   public LecturePO hasLecturePO()
   {
      org.sdmlib.examples.studyrightextends.creators.ModelPattern pattern = new org.sdmlib.examples.studyrightextends.creators.ModelPattern();
      
      LecturePO patternObject = pattern.hasElementLecturePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}



