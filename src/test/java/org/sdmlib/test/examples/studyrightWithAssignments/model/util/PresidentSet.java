package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class PresidentSet extends SimpleSet<President>
{
	public static final PresidentSet EMPTY_SET = new PresidentSet().withFlag(PresidentSet.READONLY);

	public Class<?> getTypClass() {
		return President.class;
	}

	@Override
	public PresidentSet getNewList(boolean keyValue) {
		return new PresidentSet();
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
      ObjectSet neighbors = new ObjectSet().init(value);
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