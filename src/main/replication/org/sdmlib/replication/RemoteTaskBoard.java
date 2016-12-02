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

package org.sdmlib.replication;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.replication.util.LaneSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
/**
 * 
 * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
 * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
 * @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
public class RemoteTaskBoard implements PropertyChangeInterface, SendableEntity
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_LANES.equalsIgnoreCase(attrName))
      {
         return getLanes();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_LANES.equalsIgnoreCase(attrName))
      {
         addToLanes((Lane) value);
         return true;
      }

      if ((PROPERTY_LANES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLanes((Lane) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

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
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   // ==========================================================================

   public void removeYou()
   {
      removeAllFromLanes();
      withoutLanes(this.getLanes().toArray(new Lane[this.getLanes().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * TaskFlowBoard ----------------------------------- Lane
    *              board                   lanes
    * </pre>
    */

   public static final String PROPERTY_LANES = "lanes";

   private LaneSet lanes = null;

   public LaneSet getLanes()
   {
      if (this.lanes == null)
      {
         return Lane.EMPTY_SET;
      }

      return this.lanes;
   }

   public boolean addToLanes(Lane value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.lanes == null)
         {
            this.lanes = new LaneSet();
         }

         changed = this.lanes.add(value);

         if (changed)
         {
            value.withBoard(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LANES, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromLanes(Lane value)
   {
      boolean changed = false;

      if ((this.lanes != null) && (value != null))
      {
         changed = this.lanes.remove(value);

         if (changed)
         {
            value.setBoard(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LANES,
               value, null);
         }
      }

      return changed;
   }

   public RemoteTaskBoard withLanes(Lane value)
   {
      addToLanes(value);
      return this;
   }

   public RemoteTaskBoard withoutLanes(Lane value)
   {
      removeFromLanes(value);
      return this;
   }

   public void removeAllFromLanes()
   {
      LinkedHashSet<Lane> tmpSet = new LinkedHashSet<Lane>(this.getLanes());

      for (Lane value : tmpSet)
      {
         this.removeFromLanes(value);
      }
   }

     /**
      * Create a new Lane
    * @return The created Lane 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
*/
   public Lane createLanes()
   {
      Lane value = new Lane();
      withLanes(value);
      return value;
   }

   public Lane getLanes(String name)
   {
      // TODO Auto-generated method stub
      for (Lane l : getLanes())
      {
         if (name.equals(l.getName()))
         {
            return l;
         }
      }

      return null;
   }

   public BoardTask createTask(String laneName, String taskName)
   {
      Lane lane = this.getLanes(laneName);

      return lane.createTask(taskName);
   }

   public void startTask(String laneName, String taskName)
   {
      Lane lane = this.getLanes(laneName);

      lane.startTask(taskName);
   }

     /**
      * Create a new Lane
      * @param name The name of new Lane
    * @return The created Lane 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
*/
   public Lane createLanes(String name)
   {
      Lane lane = new Lane().withName(name);
      this.addToLanes(lane);
      return lane;
   }

   public RemoteTaskBoard withLanes(Lane... value)
   {
      for (Lane item : value)
      {
         addToLanes(item);
      }
      return this;
   }

   public RemoteTaskBoard withoutLanes(Lane... value)
   {
      for (Lane item : value)
      {
         removeFromLanes(item);
      }
      return this;
   }

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }

