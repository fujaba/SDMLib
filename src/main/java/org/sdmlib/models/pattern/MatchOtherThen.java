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
   
package org.sdmlib.models.pattern;

import org.sdmlib.models.pattern.util.MatchOtherThenSet;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;
import java.lang.Object;
import org.sdmlib.models.pattern.PatternObject;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
*/
   public class MatchOtherThen extends PatternElement<MatchOtherThen> implements PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         // does not make sense
         throw new RuntimeException("cannot create a cardinality constraint");
      }

      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         // check that the size of the targeted assoc is in between min and max card
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());

         if (hostGraphSrcObject != null)
         {
            Object forbidden = this.getForbidden().getCurrentMatch();

            long valueSize = -1;

            if (hostGraphSrcObject != forbidden)
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {  
                  String msg = "// node x differs from node y";
                  msg = msg.replaceFirst("y", "" + getTopPattern().getIdMap().getId(forbidden) + " " + forbidden);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
                  getTopPattern().addLogMsg(msg);
               }

               return true;
            }
            else 
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {  
                  String msg = "// node x equals forbidden node y, backtrack!";
                  msg = msg.replaceFirst("y", "" + getTopPattern().getIdMap().getId(forbidden) + " " + forbidden);
                  msg = msg.replaceFirst("x", "" + getTopPattern().getIdMap().getId(hostGraphSrcObject) + " " + hostGraphSrcObject.toString());
                  getTopPattern().addLogMsg(msg);
               }

               this.setHostGraphSrcObject(null);

               return false;
            }
         }
         else 
         {
            return false;
         }
      }
      else
      {
         this.setHostGraphSrcObject(null); 

         return false;
      }
   }


   @Override
   public void resetSearch()
   {
      this.setHasMatch(false);
      this.setHostGraphSrcObject(null);
   }
   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setPattern(null);
      setSrc(null);
      setForbidden(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTGRAPHSRCOBJECT = "hostGraphSrcObject";
   
   private Object hostGraphSrcObject;

   public Object getHostGraphSrcObject()
   {
      return this.hostGraphSrcObject;
   }
   
   public void setHostGraphSrcObject(Object value)
   {
      if (this.hostGraphSrcObject != value)
      {
         Object oldValue = this.hostGraphSrcObject;
         this.hostGraphSrcObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTGRAPHSRCOBJECT, oldValue, value);
      }
   }
   
   public MatchOtherThen withHostGraphSrcObject(Object value)
   {
      setHostGraphSrcObject(value);
      return this;
   } 

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * MatchOtherThen ----------------------------------- PatternObject
    *              matchOtherThen                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private PatternObject src = null;
   
   public PatternObject getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(PatternObject value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         PatternObject oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutMatchOtherThen(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withMatchOtherThen(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public MatchOtherThen withSrc(PatternObject value)
   {
      setSrc(value);
      return this;
   } 
   
   public PatternObject createSrc()
   {
      PatternObject value = new PatternObject();
      withSrc(value);
      return value;
   } 

   
   public static final MatchOtherThenSet EMPTY_SET = new MatchOtherThenSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * MatchOtherThen ----------------------------------- PatternObject
    *              excluders                   forbidden
    * </pre>
    */
   
   public static final String PROPERTY_FORBIDDEN = "forbidden";
   
   private PatternObject forbidden = null;
   
   public PatternObject getForbidden()
   {
      return this.forbidden;
   }
   
   public boolean setForbidden(PatternObject value)
   {
      boolean changed = false;
      
      if (this.forbidden != value)
      {
         PatternObject oldValue = this.forbidden;
         
         if (this.forbidden != null)
         {
            this.forbidden = null;
            oldValue.withoutExcluders(this);
         }
         
         this.forbidden = value;
         
         if (value != null)
         {
            value.withExcluders(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FORBIDDEN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public MatchOtherThen withForbidden(PatternObject value)
   {
      setForbidden(value);
      return this;
   } 
   
   public PatternObject createForbidden()
   {
      PatternObject value = new PatternObject();
      withForbidden(value);
      return value;
   } 

}

