/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.models.classes;

import org.sdmlib.models.classes.util.RoleSet;

public class Role extends SDMLibClass
{
   public static final String PROPERTY_CARD = "card";
   public static final String VANILLA = "vanilla";
   public static final String AGGREGATION = "aggregation";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_NAME = "name";
   public static final String PROPERTY_KIND = "kind";
  
   private String kind= VANILLA;
   private String name= null;
   private Clazz clazz= null;
   private String card= Card.MANY.toString();

   @Override
   public String toString()
   {
   	//   	StringBuilder _ = new StringBuilder();
   	//   	_.append(" ").append(this.getName());
   	//      _.append(" ").append(this.getCard());
   	//      _.append(" ").append(this.getKind());
      return "" + name + " " + card;
   }
 
   public String labelForRole()
   {
      String result = getName();

      if (getCard().equals(Card.MANY.toString()))
      {
         result = result + " *";
      }

      return result;
   }
      
   public Clazz getClazz()
   {
      return this.clazz;
   }
   
   public boolean setClazz(Clazz value)
   {
      boolean changed = false;
      
      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;
         
         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutRoles(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.withRoles(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Role withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 
   
   public String getCard()
   {
      return this.card;
   }
   
   public void setCard(String value)
   {
      this.card = value;
   }
   
   public Role withCard(String value)
   {
      setCard(value);
      return this;
   } 

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      this.name = value;
   }
   
   public Role withName(String value)
   {
      setName(value);
      return this;
   } 

   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      this.kind = value;
   }
   
   public Role withKind(String value)
   {
      setKind(value);
      return this;
   }





   
   public static final RoleSet EMPTY_SET = new RoleSet();

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Role ----------------------------------- Association
    *              source                   assoc
    * </pre>
    */
   
   public static final String PROPERTY_ASSOC = "assoc";
   
   private Association assoc = null;
   
   public Association getAssoc()
   {
      return this.assoc;
   }
   
   public boolean setAssoc(Association value)
   {
      boolean changed = false;
      
      if (this.assoc != value)
      {
         Association oldValue = this.assoc;
         
         this.assoc = value;
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Role withAssoc(Association value)
   {
      setAssoc(value);
      return this;
   } 

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_CARD.equalsIgnoreCase(attribute))
      {
         return getCard();
      }

      if (PROPERTY_KIND.equalsIgnoreCase(attribute))
      {
         return getKind();
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return getClazz();
      }

      if (PROPERTY_ASSOC.equalsIgnoreCase(attribute))
      {
         return getAssoc();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         setKind((String) value);
         return true;
      }

      if (PROPERTY_CARD.equalsIgnoreCase(attrName))
      {
         setCard((String) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         setClazz((Clazz) value);
         return true;
      }

      if (PROPERTY_ASSOC.equalsIgnoreCase(attrName))
      {
         setAssoc((Association) value);
         return true;
      }

      return false;
   }
   
   public void removeYou()
   {
      setClazz(null);
      setAssoc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public Role getPartnerRole()
   {
      if (this == this.getAssoc().getSource())
      {
         return this.getAssoc().getTarget();
      }
      else 
      {
         return this.getAssoc().getSource();
      }
   }

   public Clazz createClazz()
   {
      Clazz value = new Clazz();
      withClazz(value);
      return value;
   } 

   public Association createAssoc()
   {
      Association value = new Association();
      withAssoc(value);
      return value;
   } 
}
