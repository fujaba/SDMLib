/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

import de.uniks.networkparser.json.JsonIdMap;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public class RiverCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      River.PROPERTY_BOAT,
      River.PROPERTY_BANKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new River();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (River.PROPERTY_BOAT.equalsIgnoreCase(attribute))
      {
         return ((River) target).getBoat();
      }

      if (River.PROPERTY_BANKS.equalsIgnoreCase(attribute))
      {
         return ((River) target).getBanks();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (River.PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         ((River) target).setBoat((Boat) value);
         return true;
      }

      if (River.PROPERTY_BANKS.equalsIgnoreCase(attrName))
      {
         ((River) target).withBanks((Bank) value);
         return true;
      }
      
      if ((River.PROPERTY_BANKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((River) target).withoutBanks((Bank) value);
         return true;
      }
      
      return false;
   }
     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
* @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((River) entity).removeYou();
   }
}
