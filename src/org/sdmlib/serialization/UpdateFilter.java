package org.sdmlib.serialization;

import org.sdmlib.serialization.json.JsonFilter;

public class UpdateFilter extends JsonFilter
{

   @Override
   public boolean isConvertable(IdMap map, Object entity, String property,
         Object value)
   {
      return map.getKey(value) == null;
//      return super.isConvertable(map, entity, property, value);
   }

}
