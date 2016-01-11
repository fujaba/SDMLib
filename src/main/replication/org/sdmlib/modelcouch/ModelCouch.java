/*
   Copyright (c) 2016 deeptought

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

package org.sdmlib.modelcouch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.BaseItem;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;
/**
 * 
 * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelcouch/ModelCouchModel.java'>ModelCouchModel.java</a>
 */
public  class ModelCouch implements SendableEntity, PropertyChangeInterface, UpdateListener
{
	public enum ApplicationType {StandAlone, JavaFX};
	
	private final int RESPONSE_CODE_OK = 200;
	private final int RESPONSE_CODE_DB_MISSING = 404;
	private final int RESPONSE_CODE_DB_CREATED = 201;

	private int lastPersisted = 0;
	private JsonIdMap idMap;
	private String database;
	private String userName = "couchdb";
	
	private ExecutorService executor;

	public ModelCouch registerAtIdMap()
	{
		idMap.withUpdateListenerSend(this);
		return this;
	}

	//try to open connection to an existing database
	//create new if database was not existing
	public ModelCouch open(String database)
	{
		this.database = database;
		
		String url = "http://" + hostName + ":" + port +"/" + database + "/";
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//is database existing?
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");

			int responseCode = con.getResponseCode();
			if(responseCode == RESPONSE_CODE_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String changeLine;

				while ((changeLine = in.readLine()) != null)
				{
					//handle changes
					if(changeLine.contains("committed_update_seq"))
					{
						JsonObject dbInfoObject = new JsonObject().withValue(changeLine);
						lastPersisted = (int) dbInfoObject.getValue("committed_update_seq");
					}
				}
				in.close();
				con.disconnect();
			}
			else if(responseCode == RESPONSE_CODE_DB_MISSING)
			{
				con.disconnect();
				//create
				con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("PUT");
				con.setDoInput(true);
				con.setRequestProperty("Content-Type", "application/json");

				responseCode = con.getResponseCode();
				con.disconnect();
				
				/*if(responseCode == RESPONSE_CODE_DB_CREATED)
				{
					Object root = idMap.getObject("root");
					JsonObject idObj = idMap.toJsonObject(root, new Filter().withFull(true));
					String urlParameters = idObj.toString();
					
					con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");	
					con.setDoOutput(true);
					con.setDoInput(true);
					con.setRequestProperty("Content-Type", "application/json");
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(urlParameters);
					wr.flush();
					wr.close();
					
					responseCode = con.getResponseCode();
					con.disconnect();
				}*/
			}
			
			//db is existing start model db listener
			ModelDBListener mdbListener = createModelDBListener()
					.withLastPersisted(lastPersisted)
					.withDatabaseName(database);
			executor = Executors.newFixedThreadPool(1);
		    executor.execute(mdbListener);
		    executor.shutdown();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return this;
	}

	public int send(ChangeEvent change)
	{
		int responsecode = -1;
		
		JsonObject jsonObject = change.toJson();
		
		String url = "http://" + hostName + ":" + port +"/" + database + "/";
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			String urlParameters = jsonObject.toString();

			con.setRequestMethod("POST");	
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responsecode = con.getResponseCode();
			con.disconnect();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return responsecode;
	}
	
	public void close()
	{
		executor.shutdownNow();
	}

	public int delete(String database)
	{
		int responsecode = -1;
		
		String url = "http://" + hostName + ":" + port +"/" + database + "/";
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//try to delete database
			con.setRequestMethod("DELETE");
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");

			responsecode = con.getResponseCode();
			con.disconnect();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return responsecode;
	}

	
	@Override
	   public boolean update(String typ, BaseItem source, Object target, String property, Object oldValue, Object newValue)
	   {
	      JsonObject jsonObject = (JsonObject) source;

	      String opCode = JsonIdMap.UPDATE;

	      Object attributes = jsonObject.get(JsonIdMap.UPDATE);

	      if (attributes == null)
	      {
	         attributes = jsonObject.get(JsonIdMap.REMOVE);
	         opCode = JsonIdMap.REMOVE;

	         if (attributes == null)
	         {
	            attributes = jsonObject.get("prop");
	            opCode = JsonIdMap.UPDATE;
	         }
	      }

	      JsonObject valueJsonObject = null;
	      JsonObject attributesJson = null;
	      String prop = null;

	      if (attributes != null)
	      {
	         attributesJson = (JsonObject) attributes;

	         Iterator<String> iter = attributesJson.keyIterator();
	         while ( iter.hasNext())
	         {
	            prop = iter.next();

	            ChangeEvent change = new ChangeEvent()
	            .withSessionId(((IdMap)idMap).getCounter().getPrefixId())
	            .withObjectId(jsonObject.getString(JsonIdMap.ID))
	            .withObjectType(jsonObject.getString(JsonIdMap.CLASS))
	            .withProperty(prop);

	            Object attrValue = attributesJson.get(prop);

	            if (attrValue instanceof JsonObject)
	            {
	               JsonArray valueJsonArray = new JsonArray();
	               valueJsonArray.add(attrValue);
	               attrValue = valueJsonArray;
	            }

	            if (attrValue instanceof JsonArray)
	            {
	               JsonArray valueJsonArray = (JsonArray) attrValue;

	               for (Object arrayElem : valueJsonArray)
	               {
	                  valueJsonObject = (JsonObject) arrayElem;

	                  String valueObjectId = (String) valueJsonObject.get(JsonIdMap.ID);

	                  String valueObjectType = (String) valueJsonObject.get(JsonIdMap.CLASS);

	                  Object valueObject = idMap.getObject(valueObjectId);

	                  if (valueObjectType == null)
	                  {
	                     // get object and ask it
	                     valueObjectType = valueObject.getClass().getName();
	                  }

	                  change.withValueType(valueObjectType);

	                  // toOne or toMany
	                  change.withPropertyKind(ChangeEvent.TO_ONE);

	                  Object targetObject = idMap.getObject(change.getObjectId());

	                  SendableEntityCreator creator = idMap.getCreatorClass(targetObject);

	                  Object value = creator.getValue(targetObject, change.getProperty());

	                  if (value != null && value instanceof Collection)
	                  {
	                     change.setPropertyKind(ChangeEvent.TO_MANY);
	                  }

	                  // newValue or oldValue?
	                  if (opCode.equals(JsonIdMap.REMOVE))
	                  {
	                     change.withOldValue(valueObjectId);
	                  }
	                  else
	                  {
	                     change.withNewValue(valueObjectId);
	                  }

	                  // send it
	                  send(change);

	                  // does the value have properties?
	                  if (valueJsonObject.get("prop") != null)
	                  {
	                     // call recursive
	                     this.update(typ, valueJsonObject, valueObject, prop, null, null);
	                  }
	               }
	            }
	            else
	            {
	               String oldValueString = "" + oldValue;
	               if (oldValue == null)
	               {
	                  oldValueString = null;
	               }

	               // plain attribute
	               change.withPropertyKind(ChangeEvent.PLAIN)
	               .withNewValue("" + attrValue)
	               .withOldValue(oldValueString);

	               //send it
	               send(change);
	            }
	         }
	      }


	      return true;
	   }

	//==========================================================================

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
		getPropertyChangeSupport().removePropertyChangeListener(listener);
		return true;
	}


	//==========================================================================


	public void removeYou()
	{
      setModelDBListener(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}


	//==========================================================================

	public static final String PROPERTY_HOSTNAME = "hostName";

	private String hostName = "localhost";

	public String getHostName()
	{
		return this.hostName;
	}

	public void setHostName(String value)
	{
		if ( ! StrUtil.stringEquals(this.hostName, value)) {

			String oldValue = this.hostName;
			this.hostName = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTNAME, oldValue, value);
		}
	}

	public ModelCouch withHostName(String value)
	{
		setHostName(value);
		return this;
	} 


	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getHostName());
		result.append(" ").append(this.getPort());
		return result.substring(1);
	}



	//==========================================================================

	public static final String PROPERTY_PORT = "port";

	private int port = 5984;

	public int getPort()
	{
		return this.port;
	}

	public void setPort(int value)
	{
		if (this.port != value) {

			int oldValue = this.port;
			this.port = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_PORT, oldValue, value);
		}
	}

	public ModelCouch withPort(int value)
	{
		setPort(value);
		return this;
	}
	public JsonIdMap getIdMap()
	{
		return idMap;
	}

	public void setIdMap(JsonIdMap idMap)
	{
		this.idMap = idMap;
	}

	public ModelCouch withIdMap(JsonIdMap idMap)
	{
		setIdMap(idMap);
		return this;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public ModelCouch withUserName(String userName)
	{
		setUserName(userName);
		return this;
	}

   
   /********************************************************************
    * <pre>
    *              one                       one
    * ModelCouch ----------------------------------- ModelDBListener
    *              couch                   modelDBListener
    * </pre>
    */
   
   public static final String PROPERTY_MODELDBLISTENER = "modelDBListener";

   private ModelDBListener modelDBListener = null;

   public ModelDBListener getModelDBListener()
   {
      return this.modelDBListener;
   }

   public boolean setModelDBListener(ModelDBListener value)
   {
      boolean changed = false;
      
      if (this.modelDBListener != value)
      {
         ModelDBListener oldValue = this.modelDBListener;
         
         if (this.modelDBListener != null)
         {
            this.modelDBListener = null;
            oldValue.setCouch(null);
         }
         
         this.modelDBListener = value;
         
         if (value != null)
         {
            value.withCouch(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELDBLISTENER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ModelCouch withModelDBListener(ModelDBListener value)
   {
      setModelDBListener(value);
      return this;
   } 

   public ModelDBListener createModelDBListener()
   {
      ModelDBListener value = new ModelDBListener();
      withModelDBListener(value);
      return value;
   } 
}
