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

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.util.RoleSet;

public class Role extends SDMLibClass
{
   public static final String VANILLA = "vanilla";
   public static final String AGGREGATION = "aggregation";

   public static final String PROPERTY_CARD = "card";
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final String PROPERTY_KIND = "kind";
  
   private String kind= VANILLA;
   private Clazz clazz= null;
   private String card= Card.MANY.toString();

   protected Role(){
      
   }
   
   public Role(Clazz clazz, String roleName, Card card){
      setName(roleName);
      setClazz(clazz);
      setCard(card.toString());
   }
   
   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      result.append(" ").append(this.getCard());
      result.append(" ").append(this.getKind());
      result.append(" ").append(this.getName());
      return result.substring(1);
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
            oldValue.without(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.with(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Role with(Clazz value)
   {
      setClazz(value);
      return this;
   } 
   
   public String getCard()
   {
      return this.card;
   }
   
   public boolean setCard(String value)
   {
      if ( ! StrUtil.stringEquals(this.card, value))
      {
         String oldValue = this.card;
         this.card = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CARD, oldValue, value);
         return true;
      }
      return false;
   }
   
   public Role withCard(String value)
   {
      setCard(value);
      return this;
   } 
  
   @Override
   public Role withName(String value)
   {
      setName(value);
      return this;
   } 

   public String getKind()
   {
      return this.kind;
   }
   
   public boolean setKind(String value)
   {
      if ( ! StrUtil.stringEquals(this.kind, value))
      {
         String oldValue = this.kind;
         this.kind = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KIND, oldValue, value);
         return true;
      }
      return false;
   }
   
   public Role withKind(String value)
   {
      setKind(value);
      return this;
   }
   
   public static final RoleSet EMPTY_SET = new RoleSet().withReadOnly(true);

   
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
   
   public Role with(Association value)
   {
      setAssoc(value);
      return this;
   } 
   
   @Override
   public void removeYou()
   {
      super.removeYou();
      setAssoc(null);
      setClazz(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public Role getPartnerRole()
   {
      if (this.getAssoc() == null)
      {
         return null;
      }
      
      if (this == this.getAssoc().getSource())
      {
         return this.getAssoc().getTarget();
      }
      else 
      {
         return this.getAssoc().getSource();
      }
   }

   protected Clazz createClazz()
   {
      Clazz value = new Clazz(null);
      with(value);
      return value;
   } 

   protected Association createAssoc()
   {
      Association value = new Association();
      with(value);
      return value;
   } 

   Role withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   Role withAssoc(Association value)
   {
      setAssoc(value);
      return this;
   } 
}
