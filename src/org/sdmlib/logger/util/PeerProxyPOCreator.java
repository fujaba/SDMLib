package org.sdmlib.logger.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class PeerProxyPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PeerProxyPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerProxyPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PeerProxyPO) target).set(attrName, value);
   }
}

