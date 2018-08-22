package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import de.uniks.networkparser.list.SimpleSet;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class PresidentSet extends SimpleSet<President>
{

   public Class<?> getTypClass()
   {
      return President.class;
   }

   public PresidentSet()
   {
      // empty
   }

   public PresidentSet(President... objects)
   {
      for (President obj : objects)
      {
         this.add(obj);
      }
   }

   public PresidentSet(Collection<President> objects)
   {
      this.addAll(objects);
   }
		public static final PresidentSet EMPTY_SET = new PresidentSet().withFlag(PresidentSet.READONLY);

   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.President";
   }
   @Override   public PresidentSet getNewList(boolean keyValue)
   {
      return new PresidentSet();
   }

   @SuppressWarnings("unchecked")
   public PresidentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<President>)value);
      }
      else if (value != null)
      {
         this.add((President) value);
      }
      return this;
   }

   public PresidentSet getUniversity()
   {
      PresidentSet result = new PresidentSet();
      for (President obj : this)
      {
         result.with(obj.getUniversity());
      }
      return result;
   }

   public PresidentSet filterUniversity(Object value)
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
      PresidentSet answer = new PresidentSet();
      for (President obj : this)
      {
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public PresidentSet withUniversity(University value)
   {
      for (President obj : this)
      {
         obj.withUniversity(value);
      }
      return this;
   }
}