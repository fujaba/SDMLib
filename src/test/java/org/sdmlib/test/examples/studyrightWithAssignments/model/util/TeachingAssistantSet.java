package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import de.uniks.networkparser.list.SimpleSet;
import java.util.Collection;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;

public class TeachingAssistantSet extends SimpleSet<TeachingAssistant>
{

   public Class<?> getTypClass()
   {
      return TeachingAssistant.class;
   }

   public TeachingAssistantSet()
   {
      // empty
   }

   public TeachingAssistantSet(TeachingAssistant... objects)
   {
      for (TeachingAssistant obj : objects)
      {
         this.add(obj);
      }
   }

   public TeachingAssistantSet(Collection<TeachingAssistant> objects)
   {
      this.addAll(objects);
   }
		public static final TeachingAssistantSet EMPTY_SET = new TeachingAssistantSet().withFlag(TeachingAssistantSet.READONLY);

   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant";
   }
   @Override   public TeachingAssistantSet getNewList(boolean keyValue)
   {
      return new TeachingAssistantSet();
   }

   @SuppressWarnings("unchecked")
   public TeachingAssistantSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TeachingAssistant>)value);
      }
      else if (value != null)
      {
         this.add((TeachingAssistant) value);
      }
      return this;
   }

   public BooleanList isCertified()
   {
      BooleanList result = new BooleanList();
      for (TeachingAssistant obj : this)
      {
         result.add(obj.isCertified());
      }
      return result;
   }

   public TeachingAssistantSet filterCertified(boolean value)
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      for(TeachingAssistant obj : this)
      {
         if ( value == obj.isCertified())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public TeachingAssistantSet withCertified(boolean value) {
      for (TeachingAssistant obj : this)
      {
         obj.setCertified(value);
      }
      return this;
   }
   public TeachingAssistantSet getRoom()
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      for (TeachingAssistant obj : this)
      {
         result.with(obj.getRoom());
      }
      return result;
   }

   public TeachingAssistantSet filterRoom(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      TeachingAssistantSet answer = new TeachingAssistantSet();
      for (TeachingAssistant obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public TeachingAssistantSet withRoom(Room value)
   {
      for (TeachingAssistant obj : this)
      {
         obj.withRoom(value);
      }
      return this;
   }
}