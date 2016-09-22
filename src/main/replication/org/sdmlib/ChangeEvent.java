package org.sdmlib;

public class ChangeEvent
{

   public String getTimestamp()
   {
      return timestamp;
   }

   public void setTimestamp(String timestamp)
   {
      this.timestamp = timestamp;
   }

   public String getNewValue()
   {
      return newValue;
   }

   public String getValueType()
   {
      return valueType;
   }

   public String getObjectId()
   {
      return objectId;
   }

   public String getObjectType()
   {
      return objectType;
   }

   public String getProperty()
   {
      return property;
   }

   public String getSessionId()
   {
      return sessionId;
   }

   public String getPropertyKind()
   {
      return propertyKind;
   }

   public String getChangeNo()
   {
      return changeNo;
   }

   public String getOldValue()
   {
      return oldValue;
   }

   private String newValue;
   private String valueType = null;
   private String objectId;
   private String objectType;
   private String property;
   private String sessionId;
   private String propertyKind;
   private String changeNo;
   private String oldValue;
   private String timestamp;

   public ChangeEvent()
   {
      timestamp = String.valueOf(System.currentTimeMillis());
   }

   public void setNewValue(String newValue)
   {
      this.newValue = newValue;
   }

   public void setValueType(String valueType)
   {
      this.valueType = valueType;

   }

   public void setObjectId(String objectId)
   {
      this.objectId = objectId;

   }

   public void setObjectType(String objectType)
   {
      this.objectType = objectType;

   }

   public void setProperty(String property)
   {
      this.property = property;

   }

   public void setSessionId(String sessionId)
   {
      this.sessionId = sessionId;

   }

   public void setPropertyKind(String propertyKind)
   {
      this.propertyKind = propertyKind;

   }

   public void setChangeNo(String changeNo)
   {
      this.changeNo = changeNo;

   }

   public void setOldValue(String oldValue)
   {
      this.oldValue = oldValue;

   }

}
