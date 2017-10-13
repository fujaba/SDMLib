/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.replication.chat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.replication.chat.util.ChatUserSet;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.replication.chat.ChatUser;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/replication/chat/ReplicationChatModel.java'>ReplicationChatModel.java</a>
*/
   public class ChatRoot implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutUsers(this.getUsers().toArray(new ChatUser[this.getUsers().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ChatRoot ----------------------------------- ChatUser
    *              chatRoot                   users
    * </pre>
    */
   
   public static final String PROPERTY_USERS = "users";

   private ChatUserSet users = null;
   
   public ChatUserSet getUsers()
   {
      if (this.users == null)
      {
         return ChatUserSet.EMPTY_SET;
      }
   
      return this.users;
   }

   public ChatRoot withUsers(ChatUser... value)
   {
      if(value==null){
         return this;
      }
      for (ChatUser item : value)
      {
         if (item != null)
         {
            if (this.users == null)
            {
               this.users = new ChatUserSet();
            }
            
            boolean changed = this.users.add (item);

            if (changed)
            {
               item.withChatRoot(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, null, item);
            }
         }
      }
      return this;
   } 

   public ChatRoot withoutUsers(ChatUser... value)
   {
      for (ChatUser item : value)
      {
         if ((this.users != null) && (item != null))
         {
            if (this.users.remove(item))
            {
               item.setChatRoot(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_USERS, item, null);
            }
         }
      }
      return this;
   }

   public ChatUser createUsers()
   {
      ChatUser value = new ChatUser();
      withUsers(value);
      return value;
   }

//   public ChatUser getOrCreateUsers(String name)
//   {
//      ChatUser user = this.getUsers().hasUserName(name).first();
//      
//      if (user == null)
//      {
//         user = new ChatUser().withUserName(name);
//         this.withUsers(user);
//      }
//      
//      return user;
//   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
