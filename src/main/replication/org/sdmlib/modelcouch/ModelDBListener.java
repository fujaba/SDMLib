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

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import javafx.application.Platform;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

import org.sdmlib.modelcouch.ModelCouch.ApplicationType;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.replication.ChangeEventList;

import java.beans.PropertyChangeListener;
import org.sdmlib.modelcouch.ModelCouch;
/**
 * 
 * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelcouch/ModelCouchModel.java'>ModelCouchModel.java</a>
 * @see <a href='../../../../../../src/test/java/org/sdmlib/test/modelcouch/ModelCouchModel.java'>ModelCouchModel.java</a>
 */
public  class ModelDBListener implements SendableEntity, Runnable
{
	private final int RESPONSE_CODE_OK = 200;

	private long lastPersisted = 0;

	private boolean isApplyingChangeMsg;
	private ChangeEventList history = new ChangeEventList().withCollectOverwrittenChanges(true);
	private int historyPos = 0;
	private boolean continuous = true;
	
	@Override
	public void run()
	{
		//?since=lastPersisted &include_docs=true &feed=continuous &heartbeat=10000
		//for every change apply to idmap
		String url = "http://" + couch.getHostName() + ":" + couch.getPort() +"/" + couch.getDatabaseName() + "/_changes?since=" + lastPersisted + "&include_docs=true" + ((continuous)?"&feed=continuous":"") + "&heartbeat=10000";
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//default is GET
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setUseCaches(false);
			con.addRequestProperty("Connection", "Keep-Alive"); 

			couch.getCouchDBAdapter().authenticate(con);

			int responseCode = con.getResponseCode();
			if(responseCode == RESPONSE_CODE_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String changeLine;
				
				if (!continuous) {
					/**
					 * result looks like {"results":[], "last_seq":85}
					 */
					StringBuffer buffer = new StringBuffer();
					while (couch != null && (changeLine = in.readLine()) != null){
						buffer.append(changeLine);
					}
					JsonObject jsonResult = new JsonObject().withValue(buffer.toString());
					JsonArray object = (JsonArray) jsonResult.get("results");
					for (Object object2 : object) {
						handleDBChange(object2.toString());
					}
					
				} else {
					// FIXME  if not (in.ready()), then a BogusChunkSize can occur...
					while (couch != null)
					{
					   changeLine = in.readLine();
						final String localChangeLine = changeLine;
						//handle changes
						if(changeLine != null && !changeLine.equals("") && !changeLine.contains("last_seq"))
						{
							if(couch != null && couch.getApplicationType() == ApplicationType.JavaFX)
							{
								Platform.runLater(new Runnable()
								{
									@Override
									public void run()
									{
										handleDBChange(localChangeLine);
									}
								});
							}
							else
							{
								handleDBChange(localChangeLine);
							}
						}
					}
				}
				in.close();
			}
			con.disconnect();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadOldChanges()
	{
		//for every change apply to idmap
		String url = "http://" + couch.getHostName() + ":" + couch.getPort() +"/" + couch.getDatabaseName() + "/_changes?include_docs=true";
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//default is GET
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setUseCaches(false);
			con.addRequestProperty("Content-Type", "application/json"); 
			
			couch.getCouchDBAdapter().authenticate(con);

			int responseCode = con.getResponseCode();
			if(responseCode == RESPONSE_CODE_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String changeLine;

				while ((changeLine = in.readLine()) != null)
				{
					if (changeLine.startsWith("{\"seq\":"))
					{
						if (changeLine.endsWith(","))
						{
							changeLine = changeLine.substring(0, changeLine.length()-1);
						}
						handleDBChange(changeLine);
					}
				}
				in.close();
			}
			con.disconnect();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	EntityUtil entityUtil = new EntityUtil();
	
	LinkedHashMap<ChangeEvent, JsonObject> jsonMap = new LinkedHashMap<>();

	private void handleDBChange(String changeLine)
	{
		/*Example of a changeLine:
		  {"seq":1,"id":"fb1b8e0e8ee333cc9dfc8715d22ccecc",
		  "changes":[{"rev":"1-fe1f79ae390abd60e939ba6f0d78c66f"}],
		  "doc":{"_id":"fb1b8e0e8ee333cc9dfc8715d22ccecc","_rev":"1-fe1f79ae390abd60e939ba6f0d78c66f",
		  "objectId":"root",
		  "property":"members",
		  "newValue":"testBasicModelOnTheCouch1452266010608.P1",
		  "oldValue":null,
		  "valueType":"org.sdmlib.test.examples.modelcouch.Person",
		  "changeNo":null,
		  "sessionId":"testBasicModelOnTheCouch1452266010608",
		  "propertyKind":"toMany",
		  "objectType":"org.sdmlib.test.examples.modelcouch.Person"}}*/

		changeLine = entityUtil.decode(changeLine);

		JsonObject seqObject = new JsonObject().withValue(changeLine);
		
		JsonObject changeObject = seqObject.getJsonObject("doc");
		
		// deleted documents are still in db..
		Object deleted = changeObject.getValue("_deleted");
		if( deleted != null && deleted.toString().equals("true")){
			return;
		}

		lastPersisted = seqObject.getLong("seq");

		this.setApplyingChangeMsg(true);
		try 
		{
			ChangeEvent change = new ChangeEvent(changeObject);
			jsonMap.put(change, seqObject);
			historyPos = history.addChange(change);

			if (historyPos < 0 || couch == null)
			{
				// change already known, ignore
				return;
			}

			// try to apply change
			applyChange(change, couch.getIdMap());
			
			// remove changes that were overwritten
			removeOverwrittenChanges(seqObject, change, history.getOverwrittenChanges());
			history.clearOverwrittenChanges();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.setApplyingChangeMsg(false);
		}

		// ChangeEvent change = new ChangeEvent(changeObject);
		// applyChange(change, couch.getIdMap()); //apply change
	}

	private void removeOverwrittenChanges(JsonObject seqObject, ChangeEvent change, List<ChangeEvent> changes) {
		if (changes.size() == 0)
			return;
		for (ChangeEvent changeEvent : changes) {
			couch.delete(jsonMap.get(changeEvent));
		}
	}

	private void applyChange(ChangeEvent change, IdMap idMap)
	{
		Object object = idMap.getObject(change.getObjectId());

		String objectType = change.getObjectType();

		SendableEntityCreator creator = idMap.getCreator(objectType, false);

		if (object == null)
		{
			// new object, create it
			object = creator.getSendableInstance(false);
			idMap.put(change.getObjectId(), object);
		}

		if (ChangeEvent.PLAIN.equals(change.getPropertyKind()))
		{
			// simple attribute just do assignment
			creator.setValue(object, change.getProperty(), change.getNewValue(), null);
		}
		else if (ChangeEvent.TO_ONE.equals(change.getPropertyKind()))
		{
			String newValueId = change.getNewValue();

			if (newValueId == null)
			{
				// set pointer to null
				creator.setValue(object, change.getProperty(), null, null);
			}
			else
			{
				// provide target object
				Object targetObject = idMap.getObject(newValueId);

				if (targetObject == null)
				{
					// not yet known target, build it. 
					SendableEntityCreator targetCreator = idMap.getCreator(change.getValueType(), false);
					targetObject = targetCreator.getSendableInstance(false);
					idMap.put(newValueId, targetObject);
				}

				// assign value
				creator.setValue(object, change.getProperty(), targetObject, null);
			}
		}
		else // toMany
		{
			String targetId = change.getNewValue();

			if (targetId == null)
			{
				// remove the object from the to_many attribute
				targetId = change.getOldValue();

				Object targetObject = idMap.getObject(targetId);

				if (targetObject != null)
				{
					creator.setValue(object, change.getProperty(), targetObject, IdMap.REMOVE);
				}
			}
			else
			{
				// insertion
				Object targetObject = idMap.getObject(targetId);

				if (targetObject == null)
				{
					// create unknown target
					SendableEntityCreator targetCreator = idMap.getCreator(change.getValueType(), false);
					targetObject = targetCreator.getSendableInstance(false);
					idMap.put(targetId, targetObject);
				}

				// assign value
				creator.setValue(object, change.getProperty(), targetObject, null);
			}
		}
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

		setCouch(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}


	/********************************************************************
	 * <pre>
	 *              one                       one
	 * ModelDBListener ----------------------------------- ModelCouch
	 *              modelDBListner                   couch
	 * </pre>
	 */

	public static final String PROPERTY_COUCH = "couch";

	private ModelCouch couch = null;

	public ModelCouch getCouch()
	{
		return this.couch;
	}

	public boolean setCouch(ModelCouch value)
	{
		boolean changed = false;

		if (this.couch != value)
		{
			ModelCouch oldValue = this.couch;

			if (this.couch != null)
			{
				this.couch = null;
				oldValue.setModelDBListener(null);
			}

			this.couch = value;

			if (value != null)
			{
				value.withModelDBListener(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_COUCH, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ModelDBListener withCouch(ModelCouch value)
	{
		setCouch(value);
		return this;
	} 

	public ModelCouch createCouch()
	{
		ModelCouch value = new ModelCouch(new CouchDBAdapter());
		withCouch(value);
		return value;
	}

	public boolean isApplyingChangeMsg()
	{
		return isApplyingChangeMsg;
	}

	public void setApplyingChangeMsg(boolean isApplyingChangeMsg)
	{
		this.isApplyingChangeMsg = isApplyingChangeMsg;
	}
	
	public void setContinuous(boolean continuous) {
		this.continuous = continuous;
	}
	
	public boolean isContinuous() {
		return continuous;
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
