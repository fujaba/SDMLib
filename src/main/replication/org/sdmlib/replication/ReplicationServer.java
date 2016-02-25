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

import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.replication.SharedSpace;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
* @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class ReplicationServer extends ReplicationNode implements
      PropertyChangeInterface
{
   public static final int REPLICATION_SERVER_PORT = 11142;

   /**
    * @param args The Startparameter
    */
   public static void main(String[] args)
   {
      // as a server, I provide a serverSocket.accept thread
      ReplicationServer replicationServer = new ReplicationServer();

      new ServerSocketAcceptThread(replicationServer, REPLICATION_SERVER_PORT)
         .start();
   }

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_SHAREDSPACES.equalsIgnoreCase(attrName))
      {
         return getSharedSpaces();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SHAREDSPACES.equalsIgnoreCase(attrName))
      {
         addToSharedSpaces((SharedSpace) value);
         return true;
      }

      if ((PROPERTY_SHAREDSPACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromSharedSpaces((SharedSpace) value);
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

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromSharedSpaces();
      withoutSharedSpaces(this.getSharedSpaces().toArray(new SharedSpace[this.getSharedSpaces().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getSpaceId());
      s.append(" ").append(this.getNodeId());
      return s.substring(1);
   }

}
