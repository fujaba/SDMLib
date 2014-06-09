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
   
package org.sdmlib.models.pattern;

import java.util.Collection;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class LinkConstraint extends PatternLink implements PropertyChangeInterface
{
   //==========================================================================
   
   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            this.setHasMatch(false);
            return false;
         }
         else
         {
            Object srcObj = this.getSrc().getCurrentMatch();
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(srcObj);
            creatorClass.setValue(srcObj, this.getTgtRoleName(), this.getTgt().getCurrentMatch(), "");
            this.setHasMatch(true);
            
            if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
            {
               getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                  + ".set" + StrUtil.upFirstChar(getTgtRoleName()) + "(" + this.getTgt().getPatternObjectName() + ")");
            }

            return true;
         }
      }
      else if (Pattern.DESTROY.equals(getModifier()))
      {
         if ( ! this.getPattern().getHasMatch())
         {
            return false;
         }
         
         if (this.getHasMatch())
         {
            this.setHasMatch(false);
            return false;
         }
         else
         {
            Object srcObj = this.getSrc().getCurrentMatch();
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(srcObj);
            Object value = creatorClass.getValue(srcObj, this.getTgtRoleName());
            if (value == null)
            {
            	// do nothing
            }
            else if (value instanceof Collection)
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                     + ".removeFrom" + StrUtil.upFirstChar(getTgtRoleName()) + "(" + this.getTgt().getPatternObjectName() + ")");
               }
               
            	creatorClass.setValue(srcObj, this.getTgtRoleName()  + JsonIdMap.REMOVE, this.getTgt().getCurrentMatch(), "");
            }
            else
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  getTopPattern().addLogMsg(this.getSrc().getPatternObjectName()
                     + ".set" + StrUtil.upFirstChar(getTgtRoleName()) + "(null); // remove" + this.getTgt().dumpHostGraphObject(this.getTgt().getCurrentMatch()));
               }
               
            	creatorClass.setValue(srcObj, this.getTgtRoleName(), null, "");
            }
            this.setHasMatch(true);
            return true;
         }
      }
      	
      
      // real search
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (getHostGraphSrcObject() != null)
         {
            SendableEntityCreator creatorClass = this.getPattern().getJsonIdMap().getCreatorClass(getHostGraphSrcObject());
            Object value = creatorClass.getValue(getHostGraphSrcObject(), getTgtRoleName());
            Object hostGraphTgtObject = this.getTgt().getCurrentMatch();
            
            if (hostGraphTgtObject != null && 
                  (hostGraphTgtObject == value || (value instanceof Collection && ((Collection) value).contains(hostGraphTgtObject))))
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  String msg = "// cnet link from x to y exists";
                  msg = msg.replaceFirst("y", getTopPattern().getJsonIdMap().getId(value) + " " + value.toString());
                  msg = msg.replaceFirst("x", getTopPattern().getJsonIdMap().getId(getHostGraphSrcObject()) + " " + getHostGraphSrcObject().toString());
                  msg = msg.replaceFirst("cnet", getTgtRoleName());
                  getTopPattern().addLogMsg(msg);
               }

               return true;
            }
            else
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  String msg = "// cnet link from x to ? does not exists, backtrack";
                  if (value != null)
                  {
                     msg = msg.replaceFirst("\\?", getTopPattern().getJsonIdMap().getId(value) + " " + value.toString());
                  }
                  msg = msg.replaceFirst("x", getTopPattern().getJsonIdMap().getId(getHostGraphSrcObject()) + " " + getHostGraphSrcObject().toString());
                  msg = msg.replaceFirst("cnet", getTgtRoleName());
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
      super.resetSearch();
   }


   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setPattern(null);
      setTgt(null);
      setSrc(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTgtRoleName());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      return _.substring(1);
   }

}

