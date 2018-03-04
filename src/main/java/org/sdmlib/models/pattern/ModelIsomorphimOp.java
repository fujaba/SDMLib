package org.sdmlib.models.pattern;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.CreatorMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ModelIsomorphimOp
{
   private static CreatorMap creatorMap;

   public static Map<Object, Object> match(Object srcRoot, Object tgtRoot)
   {
      Map<Object, Object> fwdmapping = new LinkedHashMap<Object, Object>();
      Map<Object, Object> bwdmapping = new LinkedHashMap<Object, Object>();

      fwdmapping.put(srcRoot, tgtRoot);
      bwdmapping.put(tgtRoot, srcRoot);

      String packageName = srcRoot.getClass().getPackage().getName();

      creatorMap = new CreatorMap(packageName);

      boolean match = match(srcRoot, tgtRoot, fwdmapping, bwdmapping);

      if (match)
      {
         return fwdmapping;
      }
      else
      {
         return null;
      }
   }

   private static boolean match(Object src, Object tgt, Map<Object, Object> fwdmapping, Map<Object, Object> bwdmapping)
   {
      if (src.getClass() != tgt.getClass()) return false;

      // validate match from src to tgt
      SendableEntityCreator creator = creatorMap.getCreator(src);

      for (String prop : creator.getProperties())
      {
         Object value1 = creator.getValue(src, prop);
         Object value2 = creator.getValue(tgt, prop);

         if (value1 == null)
         {
            if (value2 != null)
            {
               return false;
            }
            else
            {
               // else go on with next property
               continue;
            }
         }

         if (value1 instanceof Collection)
         {
            // both collections should have same size
            Collection collect1 = (Collection) value1;
            Collection collect2 = (Collection) value2;

            if (collect1.size() != collect2.size()) return false;

            obj1RefLoop: for (Object object1 : (Collection) value1)
            {
               // might already have been matched?
               Object mapObject2 = fwdmapping.get(object1);

               if (mapObject2 != null)
               {
                  // consistent?
                  if (((Collection) value2).contains(mapObject2))
                  {
                     // yes
                     continue;
                  }
                  else
                  {
                     return false;
                  }
               }


               // not yet mapped, search for mapping
               for (Object object2 : (Collection) value2)
               {
                  if (object1 == object2)
                  {
                     // the object is actually shared by both states.
                     // thus it definitely matches to itself
                     fwdmapping.put(object1, object2);
                     bwdmapping.put(object2, object1);

                     continue obj1RefLoop;
                  }

                  if (bwdmapping.get(object2) != null)
                  {
                     // has already been mapped to someone else.
                     continue;
                  }

                  // might be a candidate, match it
                  fwdmapping.put(object1, object2);
                  bwdmapping.put(object2, object1);

                  boolean match = match(object1, object2, fwdmapping, bwdmapping);

                  if ( ! match)
                  {
                     // did not work
                     fwdmapping.remove(object1);
                     bwdmapping.remove(object2);

                     continue;
                  }
                  else
                  {
                     continue obj1RefLoop;
                  }
               }

               // did not find a match for object1, thus cn2 does not match cn1
               return false;
            }
         }
         else
         {
            // neighbor object?
            // only care for refs
            Class<?> valueClass = value1.getClass();

            if (  valueClass.getName().startsWith("java.lang.") || valueClass == String.class)
            {
               // better check
               if (value1.equals(value2))
               {
                  continue;
               }
               else
               {
                  return false;
               }
            }

            // might already have been matched?
            Object mapValue2 = fwdmapping.get(value1);

            if (mapValue2 != null)
            {
               // consistent?
               if (mapValue2 == value2)
               {
                  // yes
                  continue;
               }
               else
               {
                  return false;
               }
            }

            if (value1 == value2)
            {
               // definitely isomorphic
               continue;
            }

            // not yet mapped, thus add mapping and check it.
            fwdmapping.put(value1, value2);
            bwdmapping.put(value2, value1);

            boolean match = match(value1, value2, fwdmapping, bwdmapping);

            if (!match)
            {
               // did not work
               fwdmapping.remove(value1);
               bwdmapping.remove(value2);

               return false;
            }
            else
            {
               continue;
            }
         }
      }

      return true;
   }
}
