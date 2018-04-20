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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.sdmlib.StrUtil;
import org.sdmlib.modelcouch.connection.ContentType;
import org.sdmlib.modelcouch.connection.HTTPConnectionHandler;
import org.sdmlib.modelcouch.connection.RequestObject;
import org.sdmlib.modelcouch.connection.RequestType;
import org.sdmlib.modelcouch.connection.ReturnObject;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.SimpleEvent;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import javafx.concurrent.Task;
import org.sdmlib.modelcouch.ModelDBListener;

public class ModelCouch implements SendableEntity, PropertyChangeInterface, ObjectCondition {
	public enum ApplicationType {
		StandAlone, JavaFX
	};

	private ApplicationType applicationType;

	private final int RESPONSE_CODE_DB_MISSING = 404;

	private IdMap idMap;
	private String databaseName;
	private String userName = "couchdb";

	private ExecutorService executor; // = Executors.newFixedThreadPool(2);
	private long lastChangeId = 0;

	private ConcurrentLinkedQueue<Task<Boolean>> queue = new ConcurrentLinkedQueue<>();

	private CouchDBAdapter couchDBAdapter;
	
	public CouchDBAdapter getCouchDBAdapter() {
		return couchDBAdapter;
	}
	
	public void setCouchDBAdapter(CouchDBAdapter couchAdapter) {
		this.couchDBAdapter = couchAdapter;
	}

	public ModelCouch(CouchDBAdapter couchAdapter) {
		this.couchDBAdapter = couchAdapter;
		this.hostName = couchAdapter.getHostName();
		this.port = couchAdapter.getPort();
	}

	public ModelCouch registerAtIdMap() {
		idMap.with(this);
		return this;
	}

	public URL getURL() {
		String urlString = "http://" + hostName + ":" + port + "/" + databaseName + "/";
		try {
			return new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// try to open connection to an existing database
	// create new if database was not existing
	public ModelCouch open(String databaseName) {
		executor = Executors.newFixedThreadPool(2);
		this.databaseName = databaseName;

		String urlString = "http://" + hostName + ":" + port + "/" + databaseName + "/";
		try {
			URL urlObj = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

			// is database existing?
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");

			int responseCode = con.getResponseCode();

			con.disconnect();

			if (responseCode == RESPONSE_CODE_DB_MISSING) {
				con.disconnect();
				// create
				this.couchDBAdapter.createDB(databaseName);
			}

			mdbListener = createModelDBListener();

			mdbListener.loadOldChanges();

			if (isContinuous()) {
				start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	private Future<?> started = null;

	public Future<?> start() {
		if (!isContinuous() || isContinuous() && (started == null || started != null && started.isDone())) {
			mdbListener.setContinuous(isContinuous());
			started = executor.submit(mdbListener, true);
		}
		return started;
	}

	public void start(boolean wait, long timeout) {
		Future<?> start = start();
		if (start == null)
			return;
		if (wait) {
			try {
				start.get(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	EntityUtil entityUtil = new EntityUtil();

	public int send(ChangeEvent change) {
		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int responsecode = -1;

				JsonObject jsonObject = change.toJson();

				String url = "http://" + hostName + ":" + port + "/" + databaseName + "/";
				try {
					HTTPConnectionHandler connectionHandler = couchDBAdapter.getConnectionHandler();
					RequestObject send = connectionHandler.createRequestObject(url);
					
					
//					URL obj = new URL(url);
//					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					String urlParameters = jsonObject.toString();
					urlParameters = entityUtil.encode(urlParameters);
					
					send.setOutput(urlParameters, "UTF-8");
					send.setRequestType(RequestType.POST);
					send.setContentType(ContentType.APPLICATION_JSON);
					ReturnObject sendResult = send.send();
					responsecode = sendResult.getResponseCode();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return responsecode;
			}

		});
		if (isContinuous()) {
			try {
				executor.submit(task);
				return task.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			executor.submit(task);
		}
		return 0;
	}

	public int delete(JsonObject change) {
		/**
		 * url for delete should look like
		 * "http://localhost:5989/dss/7264fb7568709355423ca63ee1c97b94/001?rev=1-6c8068c8e999096447efef68d4e76c53"
		 */
		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int responsecode = -1;

				JsonObject jsonObject = change;
				String objId = jsonObject.getValue("id").toString();
				JsonArray changeRev = (JsonArray) jsonObject.getValue("changes");
				JsonObject rev = ((JsonObject) changeRev.get(0));

				String url = "http://" + hostName + ":" + port + "/" + databaseName + "/" + objId + "?"
						+ rev.getKeyByIndex(0) + "=" + rev.getValueByIndex(0);
				try {
					RequestObject delete = couchDBAdapter.getConnectionHandler().createRequestObject(url);
					delete.setRequestType(RequestType.DELETE);
					delete.send();
					
//					URL obj = new URL(url);
//					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//					con.setRequestMethod("DELETE");
//					con.addRequestProperty("Content-Type", "application/json");
//					couchDBAdapter.authenticate(con);
//
//					responsecode = con.getResponseCode();
//					con.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return responsecode;
			}
		});
		if (isContinuous()) {
			try {
				executor.submit(task);
				return task.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			executor.submit(task);
		}
		return 0;
	}

	public void close() {
		executor.shutdownNow();
		this.removeYou();
	}

	public String getDatabaseName() {
		return databaseName;
	}

	// ==============================================================================
	public long getNewHistoryIdNumber() {
		long result = System.currentTimeMillis();

		if (result <= lastChangeId) {
			result = lastChangeId + 1;
		}

		lastChangeId = result;

		return result;
	}

	@Override
	public boolean update(Object event) {
		if (mdbListener.isApplyingChangeMsg()) {
			// ignore
			return true;
		}
		SimpleEvent simpleEvent = (SimpleEvent) event;
		if(simpleEvent.isNewEvent() == false) {
			return true;
		}
		JsonObject jsonObject = (JsonObject) simpleEvent.getEntity();

		String opCode = SendableEntityCreator.UPDATE;

		Object attributes = jsonObject.get(SendableEntityCreator.UPDATE);

		if (attributes == null) {
			attributes = jsonObject.get(SendableEntityCreator.REMOVE);
			opCode = SendableEntityCreator.REMOVE;

			if (attributes == null) {
				attributes = jsonObject.get("prop");
				opCode = SendableEntityCreator.UPDATE;
			}
		}

		JsonObject valueJsonObject = null;
		JsonObject attributesJson = null;
		String prop = null;

		if (attributes != null) {
			attributesJson = (JsonObject) attributes;

			Iterator<String> iter = attributesJson.keyIterator();
			while (iter.hasNext()) {
				prop = iter.next();

				ChangeEvent change = new ChangeEvent().withSessionId(((IdMap) idMap).getSession())
						.withObjectId(jsonObject.getString(IdMap.ID)).withChangeNo("" + getNewHistoryIdNumber())
						.withObjectType(jsonObject.getString(IdMap.CLASS)).withProperty(prop);

				Object attrValue = attributesJson.get(prop);

				if (attrValue instanceof JsonObject) {
					JsonArray valueJsonArray = new JsonArray();
					valueJsonArray.add(attrValue);
					attrValue = valueJsonArray;
				}

				if (attrValue instanceof JsonArray) {
					JsonArray valueJsonArray = (JsonArray) attrValue;

					for (Object arrayElem : valueJsonArray) {
						valueJsonObject = (JsonObject) arrayElem;

						String valueObjectId = (String) valueJsonObject.get(IdMap.ID);

						String valueObjectType = (String) valueJsonObject.get(IdMap.CLASS);

						Object valueObject = idMap.getObject(valueObjectId);

						if (valueObjectType == null) {
							// get object and ask it
							valueObjectType = valueObject.getClass().getName();
						}

						change.withValueType(valueObjectType);

						// toOne or toMany
						change.withPropertyKind(ChangeEvent.TO_ONE);

						Object targetObject = idMap.getObject(change.getObjectId());

						SendableEntityCreator creator = idMap.getCreatorClass(targetObject);

						Object value = creator.getValue(targetObject, change.getProperty());

						if (value != null && value instanceof Collection) {
							change.setPropertyKind(ChangeEvent.TO_MANY);
						}

						// newValue or oldValue?
						if (opCode.equals(SendableEntityCreator.REMOVE)) {
							change.withOldValue(valueObjectId);
						} else {
							change.withNewValue(valueObjectId);
						}

						// send it
						send(change);

						// does the value have properties?
						if (valueJsonObject.get("prop") != null) {
							// call recursive
							// this.update(typ, valueJsonObject, valueObject,
							// prop, null, null);
							simpleEvent.with(valueJsonObject);
							this.update(simpleEvent);
						}
					}
				} else {
					PropertyChangeEvent evt = (PropertyChangeEvent) event;
					String oldValueString = "" + evt.getOldValue();
					if (evt.getOldValue() == null) {
						oldValueString = null;
					}

					// plain attribute
					change.withPropertyKind(ChangeEvent.PLAIN).withNewValue("" + attrValue)
							.withOldValue(oldValueString);

					// send it
					send(change);
				}
			}
		}

		return true;
	}

	// ==========================================================================

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
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

	// ==========================================================================

	public void removeYou() {
		setModelDBListener(null);
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	// ==========================================================================

	public static final String PROPERTY_HOSTNAME = "hostName";

	private String hostName = "localhost";

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String value) {
		if (!StrUtil.stringEquals(this.hostName, value)) {

			String oldValue = this.hostName;
			this.hostName = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTNAME, oldValue, value);
		}
	}

	public ModelCouch withHostName(String value) {
		setHostName(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getHostName());
		result.append(" ").append(this.getPort());
		return result.substring(1);
	}

	// ==========================================================================

	public static final String PROPERTY_PORT = "port";

	private int port = 5984;

	public int getPort() {
		return this.port;
	}

	public void setPort(int value) {
		if (this.port != value) {

			int oldValue = this.port;
			this.port = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_PORT, oldValue, value);
		}
	}

	public ModelCouch withPort(int value) {
		setPort(value);
		return this;
	}

	public IdMap getIdMap() {
		return idMap;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public ModelCouch withApplicationType(ApplicationType applicationType) {
		setApplicationType(applicationType);
		return this;
	}

	public void setIdMap(IdMap idMap) {
		this.idMap = idMap;
	}

	public ModelCouch withIdMap(IdMap idMap) {
		setIdMap(idMap);
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ModelCouch withUserName(String userName) {
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
	private ModelDBListener mdbListener;

	public ModelDBListener getModelDBListener() {
		return this.modelDBListener;
	}

	public boolean setModelDBListener(ModelDBListener value) {
		boolean changed = false;

		if (this.modelDBListener != value) {
			ModelDBListener oldValue = this.modelDBListener;

			if (this.modelDBListener != null) {
				this.modelDBListener = null;
				oldValue.setCouch(null);
			}

			this.modelDBListener = value;

			if (value != null) {
				value.withCouch(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELDBLISTENER, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public ModelCouch withModelDBListener(ModelDBListener value) {
		setModelDBListener(value);
		return this;
	}

	public ModelDBListener createModelDBListener() {
		ModelDBListener value = new ModelDBListener();
		withModelDBListener(value);
		return value;
	}

	private boolean continuous = false;

	public void setContinuous(boolean continuous) {
		if (this.continuous != continuous) {
			this.continuous = continuous;
		}
	}

	public ModelCouch withContinuous(boolean continous) {
		setContinuous(continous);
		return this;
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
