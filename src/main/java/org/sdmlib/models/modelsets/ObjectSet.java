package org.sdmlib.models.modelsets;


public class ObjectSet extends SDMSet<Object>
{
   public ObjectSet with(Object... values)
   {
      super.with(values);
      return this;
   }

	@Override
	public String getEntryType() {
		return ObjectSet.class.getName();
	}
}
