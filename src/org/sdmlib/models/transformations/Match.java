/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.models.transformations;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;
import java.lang.Object;
import org.sdmlib.models.transformations.creators.MatchSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Match implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         return getStartPos();
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         return getEndPos();
      }

      if (PROPERTY_FULLTEXT.equalsIgnoreCase(attrName))
      {
         return getFullText();
      }

      if (PROPERTY_MATCHTEXT.equalsIgnoreCase(attrName))
      {
         return getMatchText();
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         return getModelObject();
      }

      if (PROPERTY_TEMPLATE.equalsIgnoreCase(attrName))
      {
         return getTemplate();
      }

      if (PROPERTY_PLACEHOLDER.equalsIgnoreCase(attrName))
      {
         return getPlaceholder();
      }

      if (PROPERTY_SUBMATCHES.equalsIgnoreCase(attrName))
      {
         return getSubMatches();
      }

      if (PROPERTY_PARENTMATCH.equalsIgnoreCase(attrName))
      {
         return getParentMatch();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STARTPOS.equalsIgnoreCase(attrName))
      {
         setStartPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_ENDPOS.equalsIgnoreCase(attrName))
      {
         setEndPos(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_FULLTEXT.equalsIgnoreCase(attrName))
      {
         setFullText((String) value);
         return true;
      }

      if (PROPERTY_MATCHTEXT.equalsIgnoreCase(attrName))
      {
         setMatchText((String) value);
         return true;
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         setModelObject((Object) value);
         return true;
      }

      if (PROPERTY_TEMPLATE.equalsIgnoreCase(attrName))
      {
         setTemplate((Template) value);
         return true;
      }

      if (PROPERTY_PLACEHOLDER.equalsIgnoreCase(attrName))
      {
         setPlaceholder((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_SUBMATCHES.equalsIgnoreCase(attrName))
      {
         addToSubMatches((Match) value);
         return true;
      }
      
      if ((PROPERTY_SUBMATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromSubMatches((Match) value);
         return true;
      }

      if (PROPERTY_PARENTMATCH.equalsIgnoreCase(attrName))
      {
         setParentMatch((Match) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setTemplate(null);
      setPlaceholder(null);
      removeAllFromSubMatches();
      setParentMatch(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
   
   public Match withStartPos(int value)
   {
      setStartPos(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getStartPos());
      _.append(" ").append(this.getEndPos());
      _.append(" ").append(this.getFullText());
      _.append(" ").append(this.getMatchText());
      return _.substring(1);
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
   
   public Match withEndPos(int value)
   {
      setEndPos(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_FULLTEXT = "fullText";
   
   private String fullText;

   public String getFullText()
   {
      return this.fullText;
   }
   
   public void setFullText(String value)
   {
      if ( ! StrUtil.stringEquals(this.fullText, value))
      {
         String oldValue = this.fullText;
         this.fullText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FULLTEXT, oldValue, value);
      }
   }
   
   public Match withFullText(String value)
   {
      setFullText(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MATCHTEXT = "matchText";
   
   private String matchText;

   public String getMatchText()
   {
      if (this.matchText == null)
      {
         this.matchText = this.fullText.substring(startPos, endPos+1);
      }
      return this.matchText;
   }
   
   public void setMatchText(String value)
   {
      if ( ! StrUtil.stringEquals(this.matchText, value))
      {
         String oldValue = this.matchText;
         this.matchText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHTEXT, oldValue, value);
      }
   }
   
   public Match withMatchText(String value)
   {
      setMatchText(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MODELOBJECT = "modelObject";
   
   private Object modelObject;

   public Object getModelObject()
   {
      return this.modelObject;
   }
   
   public void setModelObject(Object value)
   {
      if (this.modelObject != value)
      {
         Object oldValue = this.modelObject;
         this.modelObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELOBJECT, oldValue, value);
      }
   }
   
   public Match withModelObject(Object value)
   {
      setModelObject(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Match ----------------------------------- Template
    *              matches                   template
    * </pre>
    */
   
   public static final String PROPERTY_TEMPLATE = "template";
   
   private Template template = null;
   
   public Template getTemplate()
   {
      return this.template;
   }
   
   public boolean setTemplate(Template value)
   {
      boolean changed = false;
      
      if (this.template != value)
      {
         Template oldValue = this.template;
         
         if (this.template != null)
         {
            this.template = null;
            oldValue.withoutMatches(this);
         }
         
         this.template = value;
         
         if (value != null)
         {
            value.withMatches(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEMPLATE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Match withTemplate(Template value)
   {
      setTemplate(value);
      return this;
   } 
   
   public Template createTemplate()
   {
      Template value = new Template();
      withTemplate(value);
      return value;
   } 

   
   public static final MatchSet EMPTY_SET = new MatchSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Match ----------------------------------- PlaceHolderDescription
    *              matches                   placeholder
    * </pre>
    */
   
   public static final String PROPERTY_PLACEHOLDER = "placeholder";
   
   private PlaceHolderDescription placeholder = null;
   
   public PlaceHolderDescription getPlaceholder()
   {
      return this.placeholder;
   }
   
   public boolean setPlaceholder(PlaceHolderDescription value)
   {
      boolean changed = false;
      
      if (this.placeholder != value)
      {
         PlaceHolderDescription oldValue = this.placeholder;
         
         if (this.placeholder != null)
         {
            this.placeholder = null;
            oldValue.withoutMatches(this);
         }
         
         this.placeholder = value;
         
         if (value != null)
         {
            value.withMatches(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLACEHOLDER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Match withPlaceholder(PlaceHolderDescription value)
   {
      setPlaceholder(value);
      return this;
   } 
   
   public PlaceHolderDescription createPlaceholder()
   {
      PlaceHolderDescription value = new PlaceHolderDescription();
      withPlaceholder(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Match ----------------------------------- Match
    *              parentMatch                   subMatches
    * </pre>
    */
   
   public static final String PROPERTY_SUBMATCHES = "subMatches";
   
   private MatchSet subMatches = null;
   
   public MatchSet getSubMatches()
   {
      if (this.subMatches == null)
      {
         return Match.EMPTY_SET;
      }
   
      return this.subMatches;
   }
   
   public boolean addToSubMatches(Match value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.subMatches == null)
         {
            this.subMatches = new MatchSet();
         }
         
         changed = this.subMatches.add (value);
         
         if (changed)
         {
            value.withParentMatch(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBMATCHES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromSubMatches(Match value)
   {
      boolean changed = false;
      
      if ((this.subMatches != null) && (value != null))
      {
         changed = this.subMatches.remove (value);
         
         if (changed)
         {
            value.setParentMatch(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBMATCHES, value, null);
         }
      }
         
      return changed;   
   }
   
   public Match withSubMatches(Match... value)
   {
      for (Match item : value)
      {
         addToSubMatches(item);
      }
      return this;
   } 
   
   public Match withoutSubMatches(Match... value)
   {
      for (Match item : value)
      {
         removeFromSubMatches(item);
      }
      return this;
   }
   
   public void removeAllFromSubMatches()
   {
      LinkedHashSet<Match> tmpSet = new LinkedHashSet<Match>(this.getSubMatches());
   
      for (Match value : tmpSet)
      {
         this.removeFromSubMatches(value);
      }
   }
   
   public Match createSubMatches()
   {
      Match value = new Match();
      withSubMatches(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Match ----------------------------------- Match
    *              subMatches                   parentMatch
    * </pre>
    */
   
   public static final String PROPERTY_PARENTMATCH = "parentMatch";
   
   private Match parentMatch = null;
   
   public Match getParentMatch()
   {
      return this.parentMatch;
   }
   
   public boolean setParentMatch(Match value)
   {
      boolean changed = false;
      
      if (this.parentMatch != value)
      {
         Match oldValue = this.parentMatch;
         
         if (this.parentMatch != null)
         {
            this.parentMatch = null;
            oldValue.withoutSubMatches(this);
         }
         
         this.parentMatch = value;
         
         if (value != null)
         {
            value.withSubMatches(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENTMATCH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Match withParentMatch(Match value)
   {
      setParentMatch(value);
      return this;
   } 
   
   public Match createParentMatch()
   {
      Match value = new Match();
      withParentMatch(value);
      return value;
   } 
}

