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
   
package org.sdmlib.codegen;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.codegen.util.StatementEntrySet;
import org.sdmlib.serialization.PropertyChangeInterface;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeListener;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
   public class StatementEntry implements PropertyChangeInterface, SendableEntity
{

   @Override
   public String toString()
   {
      StringBuilder text = new StringBuilder();
      for (String word : getTokenList())
      {
         text.append(" ").append(word);
      }
      
      /*
      s.append(" ").append(this.getKind());
      s.append(" ").append(this.getAssignTargetVarName());
      s.append(" ").append(this.getStartPos());
      s.append(" ").append(this.getEndPos());
      
      */
      
      return text.toString();
   }
      
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      withoutBodyStats(this.getBodyStats().toArray(new StatementEntry[this.getBodyStats().size()]));
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_KIND = "kind";
   
   private String kind;
   
   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      if ( ! StrUtil.stringEquals(this.kind, value))
      {
         String oldValue = this.kind;
         this.kind = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KIND, oldValue, value);
      }
   }
   
   public StatementEntry withKind(String value)
   {
      setKind(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TOKENLIST = "tokenList";
   
   private ArrayList<String> tokenList = new ArrayList<String>();
   
   public ArrayList<String> getTokenList()
   {
      return this.tokenList;
   }
   
   public void setTokenList(ArrayList<String> value)
   {
      if (this.tokenList != value)
      {
         ArrayList<String> oldValue = this.tokenList;
         this.tokenList = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TOKENLIST, oldValue, value);
      }
   }
   
   public StatementEntry withTokenList(ArrayList<String> value)
   {
      setTokenList(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ASSIGNTARGETVARNAME = "assignTargetVarName";
   
   private String assignTargetVarName;
   
   public String getAssignTargetVarName()
   {
      return this.assignTargetVarName;
   }
   
   public void setAssignTargetVarName(String value)
   {
      if ( ! StrUtil.stringEquals(this.assignTargetVarName, value))
      {
         String oldValue = this.assignTargetVarName;
         this.assignTargetVarName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNTARGETVARNAME, oldValue, value);
      }
   }
   
   public StatementEntry withAssignTargetVarName(String value)
   {
      setAssignTargetVarName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * StatementEntry ----------------------------------- StatementEntry
    *              parent                   bodyStats
    * </pre>
    */
   
   public static final String PROPERTY_BODYSTATS = "bodyStats";
   
   private StatementEntrySet bodyStats = null;
   
   public StatementEntrySet getBodyStats()
   {
      if (this.bodyStats == null)
      {
         return StatementEntry.EMPTY_SET;
      }
   
      return this.bodyStats;
   }
   
   public boolean addToBodyStats(StatementEntry value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.bodyStats == null)
         {
            this.bodyStats = new StatementEntrySet();
         }
         
         changed = this.bodyStats.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_BODYSTATS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromBodyStats(StatementEntry value)
   {
      boolean changed = false;
      
      if ((this.bodyStats != null) && (value != null))
      {
         changed = this.bodyStats.remove (value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_BODYSTATS, value, null);
         }
      }
         
      return changed;   
   }
   
   public StatementEntry withBodyStats(StatementEntry value)
   {
      addToBodyStats(value);
      return this;
   } 
   
   public StatementEntry withoutBodyStats(StatementEntry value)
   {
      removeFromBodyStats(value);
      return this;
   } 
   
   public void removeAllFromBodyStats()
   {
      LinkedHashSet<StatementEntry> tmpSet = new LinkedHashSet<StatementEntry>(this.getBodyStats());
   
      for (StatementEntry value : tmpSet)
      {
         this.removeFromBodyStats(value);
      }
   }

   
   public static final StatementEntrySet EMPTY_SET = new StatementEntrySet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * StatementEntry ----------------------------------- StatementEntry
    *              bodyStats                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";
   
   private StatementEntry parent = null;
   
   public StatementEntry getParent()
   {
      return this.parent;
   }
   
   public boolean setParent(StatementEntry value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         StatementEntry oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutBodyStats(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withBodyStats(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public StatementEntry withParent(StatementEntry value)
   {
      setParent(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_STARTPOS = "startPos";
   
   private int startPos;

   public int getStartPos()
   {
      return this.startPos;
   }
   
   public void setStartPos(int value)
   {
      if (this.startPos != value)
      {
         int oldValue = this.startPos;
         this.startPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STARTPOS, oldValue, value);
      }
   }
   
   public StatementEntry withStartPos(int value)
   {
      setStartPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ENDPOS = "endPos";
   
   private int endPos;

   public int getEndPos()
   {
      return this.endPos;
   }
   
   public void setEndPos(int value)
   {
      if (this.endPos != value)
      {
         int oldValue = this.endPos;
         this.endPos = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENDPOS, oldValue, value);
      }
   }
   
   public StatementEntry withEndPos(int value)
   {
      setEndPos(value);
      return this;
   }

   public StatementEntry withToken(Token token)
   {
      return withToken(token.text.toString(), token.endPos);
   } 

   public StatementEntry withToken(String qualifiedName, int endPos)
   {
      this.getTokenList().add(qualifiedName);
      this.setEndPos(endPos);
      return this;
   } 

   public StatementEntry withBodyStats(StatementEntry... value)
   {
      for (StatementEntry item : value)
      {
         addToBodyStats(item);
      }
      return this;
   } 

   public StatementEntry withoutBodyStats(StatementEntry... value)
   {
      for (StatementEntry item : value)
      {
         removeFromBodyStats(item);
      }
      return this;
   }

   public StatementEntry createBodyStats()
   {
      StatementEntry value = new StatementEntry();
      withBodyStats(value);
      return value;
   } 

   public StatementEntry createParent()
   {
      StatementEntry value = new StatementEntry();
      withParent(value);
      return value;
   } 
   public StatementEntrySet getBodyStatsTransitive()
   {
      StatementEntrySet result = new StatementEntrySet().with(this);
      return result.getBodyStatsTransitive();
   }

   public StatementEntrySet getParentTransitive()
   {
      StatementEntrySet result = new StatementEntrySet().with(this);
      return result.getParentTransitive();
   }

}

