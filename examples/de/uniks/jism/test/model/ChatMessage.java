package de.uniks.jism.test.model;

import java.util.Date;

public class ChatMessage
{
   public static final String PROPERTY_TEXT = "txt";
   public static final String PROPERTY_TIME = "time";
   public static final String PROPERTY_SENDER = "sender";
   public static final String PROPERTY_COUNT = "count";
   public static final String PROPERTY_ACTIV = "activ";
   private String text;
   private int count;
   private boolean activ;

   /**
    * <pre>
    *           1..1     1..1
    * ChatMessage ------------------------> Date
    *           &lt;       date
    * </pre>
    */
   private Date date;

   private String sender;

   public String getText()
   {
      return text;
   }

   public void setText(String text)
   {
      this.text = text;
   }

   public Date getDate()
   {
      return this.date;
   }

   public void setDate(Date value)
   {
      this.date = value;
   }

   public Object get(String attrName)
   {
      String attribute;
      int pos = attrName.indexOf(".");
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      else
      {
         attribute = attrName;
      }
      if (attribute.equalsIgnoreCase(PROPERTY_TEXT))
      {
         return getText();
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_TIME))
      {
         return getDate();
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_SENDER))
      {
         return getSender();
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_COUNT))
      {
         return getCount();
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_ACTIV))
      {
         return isActiv();
      }
      return null;
   }

   public boolean set(String attribute, Object value)
   {
      if (attribute.equalsIgnoreCase(PROPERTY_TEXT))
      {
         setText((String) value);
         return true;
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_TIME))
      {
         setDate((Date) value);
         return true;
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_SENDER))
      {
         setSender((String) value);
         return true;
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_COUNT))
      {
         setCount((Integer) value);
         return true;
      }
      else if (attribute.equalsIgnoreCase(PROPERTY_ACTIV))
      {
         setActiv((Boolean) value);
         return true;
      }
      return false;
   }

   public String getSender()
   {
      return sender;
   }

   public void setSender(String sender)
   {
      this.sender = sender;
   }

   public int getCount()
   {
      return count;
   }

   public void setCount(int count)
   {
      this.count = count;
   }

   public boolean isActiv()
   {
      return activ;
   }

   public void setActiv(boolean activ)
   {
      this.activ = activ;
   }
}
