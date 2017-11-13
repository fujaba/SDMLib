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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;
import de.uniks.networkparser.list.ObjectSet;

import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.ReachabilityGraph;

/**
 * 
 * @see <a href= '../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 */
public class ReachableState implements PropertyChangeInterface, SendableEntity
{
   private class JsonIdCompare implements Comparator<Object>
   {
      @Override
      public int compare(Object o1, Object o2)
      {
         JsonObject jo1 = (JsonObject) o1;
         JsonObject jo2 = (JsonObject) o2;
         return jo1.getString(IdMap.ID).compareTo(jo2.getString(IdMap.ID));
      }
   }

   public long noOfRuleMatchesDone = 0;

   private String certificate = null;

   private TreeMap<String, Integer> lazyAllCertificate2Number;


   public String getCertificate()
   {
      return certificate;
   }


   public void setCertificate(String certificate)
   {
      this.certificate = certificate;
   }


   public String lazyComputeCertificate()
   {
      Objects.requireNonNull(getParent());
      
      LazyCloneOp lazyCloneOp = getParent().getLazyCloneOp();
      
      Objects.requireNonNull(lazyCloneOp);
      
      this.certificate = null;

      long category = 1;

      HashMap<Object, Integer> oldNode2CertNo = new HashMap<Object, Integer>();
      long oldNumOfCertificates = 0;

      lazyNode2CertNo = new HashMap<Object, Integer>();

      lazyGraph = new ObjectSet();
      lazyCloneOp.aggregate(lazyGraph, this.getGraphRoot());
      
      lazyAllCertificate2Number = new TreeMap<String, Integer>();
      
      TreeMap<String, Integer> allCertificate2Number = new TreeMap<String, Integer>();
      
      // collect new certificates
      for (Object o : lazyGraph)
      {
         String simpleName = o.getClass().getSimpleName()+'\n';

         allCertificate2Number.put(simpleName, 0);
      }
      
      // number new certificates
      int numOfCerts = 0;
      for (String cert : allCertificate2Number.keySet())
      {
         numOfCerts++;
         allCertificate2Number.put(cert, numOfCerts);
      }

      // assign cert numbers to nodes
      for (Object o : lazyGraph)
      {
         String simpleName = o.getClass().getSimpleName()+'\n';
         Integer certNo = allCertificate2Number.get(simpleName);
         oldNode2CertNo.put(o, certNo);
      }
      
      TreeMap<String, ArrayList> cert2Nodes = new TreeMap<String, ArrayList>();
      boolean stopNextRound = false;
      while (true)
      {
         // collect new certificates
         for (Object o : lazyGraph)
         {
            SendableEntityCreator creator = lazyCloneOp.getMap().getCreatorClass(o);
            
            Integer certNo = oldNode2CertNo.get(o);
            Objects.requireNonNull(certNo);
            StringBuilder newCertificate = new StringBuilder().append(certNo).append(": ");
            
            // loop through props
            for (String prop : creator.getProperties())
            {
               Object value = creator.getValue(o, prop);
               
               if (value != null)
               {
                  if (value instanceof Collection)
                  {
                     Collection valueCollection = (Collection) value;

                     ArrayList<Integer> valueCertNumbers = new ArrayList<Integer>(valueCollection.size());

                     for (Object valueElem : (Collection) value)
                     {
                        if (lazyGraph.contains(valueElem))
                        {
                           Integer valueCertNo = oldNode2CertNo.get(valueElem);
                           Objects.requireNonNull(valueCertNo);
                           valueCertNumbers.add(valueCertNo);
                        }
                     }

                     if ( ! valueCertNumbers.isEmpty())
                     {
                        valueCertNumbers.sort(null);

                        newCertificate.append("   ").append(prop).append(": ");
                        for (Integer no : valueCertNumbers)
                        {
                           newCertificate.append(no).append(' ');
                        }
                        newCertificate.append('\n');
                     }
                  }
                  else if (lazyGraph.contains(value))
                  {
                     Integer valueCertNo = oldNode2CertNo.get(value);
                     Objects.requireNonNull(valueCertNo);
                     String line = String.format("   %s: %s\n", prop, valueCertNo);
                     newCertificate.append(line);
                     
                  }
                  else // plain value
                  {
                     String line = String.format("   %s: %s\n", prop, value.toString());
                     newCertificate.append(line);
                  }
               }
            } // for (String prop : creator.getProperties())

            ArrayList nodeList = cert2Nodes.get(newCertificate.toString());
            if (nodeList == null)
            {
               nodeList = new ArrayList();
               cert2Nodes.put(newCertificate.toString(), nodeList);
            }
            nodeList.add(o);
            
            // lazyNode2CertNo.put(o, certNo);
         } // for (Object o : graph)

         // number certificates and assign numbers to nodes and count nodes per certificate
         for (String newCert : cert2Nodes.keySet())
         {
            Integer certNo = allCertificate2Number.get(newCert);
            if (certNo == null)
            {
               certNo = allCertificate2Number.size() + 1;
               allCertificate2Number.put(newCert, certNo);
            }
            
            for (Object elem : cert2Nodes.get(newCert))
            {
               lazyNode2CertNo.put(elem, certNo);
            }
         }

         if (cert2Nodes.size() <= oldNumOfCertificates || cert2Nodes.size() == lazyGraph.size())
         {
            // write state certificate
            StringBuilder buf = new StringBuilder();
            for (String cert : cert2Nodes.keySet())
            {
               Integer noOfNodes = cert2Nodes.get(cert).size();
               Integer certNo = allCertificate2Number.get(cert);

               buf.append(certNo).append('*').append(noOfNodes)
               .append('\n');
            }

            // append certNo certText list
            for ( Entry<String, Integer> entry : allCertificate2Number.entrySet())
            {
               buf.append(entry.getValue()).append(": ").append(entry.getKey());
            }

            this.certificate = buf.toString();

            break;
         }

         // do another round
         oldNumOfCertificates = cert2Nodes.size();
         cert2Nodes.clear();
         oldNode2CertNo = lazyNode2CertNo;

         lazyNode2CertNo = new HashMap<Object, Integer>();
      } // while

      return this.certificate;
   }

   // ==========================================================================

   private long countBlanks(String str)
   {
      long num = 0;

      for (int i = 0; i < str.length(); i++)
      {
         if (str.charAt(i) == ' ')
         {
            num++;
         }
      }

      return num;
   }

   // ==========================================================================

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


   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }


   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }


   public boolean removePropertyChangeListener(String property,
         PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(property, listener);
      }
      return true;
   }
   // ==========================================================================


   public void removeYou()
   {
      setParent(null);
      removeAllFromRuleapplications();
      removeAllFromResultOf();
      withoutRuleapplications(this.getRuleapplications().toArray(new RuleApplication[this.getRuleapplications().size()]));
      withoutResultOf(this.getResultOf().toArray(new RuleApplication[this.getResultOf().size()]));
      setGraphRoot(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   public static final ReachableStateSet EMPTY_SET = new ReachableStateSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * ReachableState ----------------------------------- ReachabilityGraph
    *              states                   parent
    * </pre>
    */

   public static final String PROPERTY_PARENT = "parent";

   private ReachabilityGraph parent = null;


   public ReachabilityGraph getParent()
   {
      return this.parent;
   }


   public boolean setParent(ReachabilityGraph value)
   {
      boolean changed = false;

      if (this.parent != value)
      {
         ReachabilityGraph oldValue = this.parent;

         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutStates(this);
         }

         this.parent = value;

         if (value != null)
         {
            value.withStates(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }

      return changed;
   }


   public ReachableState withParent(ReachabilityGraph value)
   {
      setParent(value);
      return this;
   }


   public ReachabilityGraph createParent()
   {
      ReachabilityGraph value = new ReachabilityGraph();
      withParent(value);
      return value;
   }

   private TreeMap<String, String> certificates2nodes;

   private HashMap<String, String> node2certificates;
   private HashMap<Object, Integer> lazyNode2CertNo;
   
   public HashMap<Object, Integer> getLazyNode2CertNo()
   {
      return lazyNode2CertNo;
   }

   private IdMap certificateIdMap = null;
   
   public IdMap getCertificateIdMap()
   {
      return certificateIdMap;
   }

   public HashMap<String, String> getNode2certificates()
   {
      return node2certificates;
   }

   // ==========================================================================
   public static final String PROPERTY_GRAPHROOT = "graphRoot";

   private Object graphRoot;


   public Object getGraphRoot()
   {
      return this.graphRoot;
   }


   public void setGraphRoot(Object value)
   {
      if (this.graphRoot != value)
      {
         Object oldValue = this.graphRoot;
         this.graphRoot = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPHROOT, oldValue, value);
      }
   }


   public ReachableState withGraphRoot(Object value)
   {
      setGraphRoot(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_NUMBER = "number";

   private long number;


   public long getNumber()
   {
      return this.number;
   }


   public void setNumber(long value)
   {
      if (this.number != value)
      {
         long oldValue = this.number;
         this.number = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NUMBER, oldValue, value);
      }
   }


   public ReachableState withNumber(long value)
   {
      setNumber(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getNumber());
      result.append(" ").append(this.getMetricValue());
      
      if (graphRoot != null)
      {
         result.append("\n").append(graphRoot.toString());
      }
      
      return result.substring(1);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * ReachableState ----------------------------------- RuleApplication
    *              src                   ruleapplications
    * </pre>
    */

   public static final String PROPERTY_RULEAPPLICATIONS = "ruleapplications";

   private RuleApplicationSet ruleapplications = null;


   public RuleApplicationSet getRuleapplications()
   {
      if (this.ruleapplications == null)
      {
         return RuleApplicationSet.EMPTY_SET;
      }

      return this.ruleapplications;
   }


   public boolean addToRuleapplications(RuleApplication value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.ruleapplications == null)
         {
            this.ruleapplications = new RuleApplicationSet();
         }

         changed = this.ruleapplications.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULEAPPLICATIONS, null, value);
         }
      }

      return changed;
   }


   public boolean removeFromRuleapplications(RuleApplication value)
   {
      boolean changed = false;

      if ((this.ruleapplications != null) && (value != null))
      {
         changed = this.ruleapplications.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULEAPPLICATIONS, value, null);
         }
      }

      return changed;
   }


   public ReachableState withRuleapplications(RuleApplication value)
   {
      addToRuleapplications(value);
      return this;
   }


   public ReachableState withoutRuleapplications(RuleApplication value)
   {
      removeFromRuleapplications(value);
      return this;
   }


   public void removeAllFromRuleapplications()
   {
      LinkedHashSet<RuleApplication> tmpSet = new LinkedHashSet<RuleApplication>(this.getRuleapplications());

      for (RuleApplication value : tmpSet)
      {
         this.removeFromRuleapplications(value);
      }
   }


   public RuleApplication createRuleapplications()
   {
      RuleApplication value = new RuleApplication();
      withRuleapplications(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * ReachableState ----------------------------------- RuleApplication
    *              tgt                   resultOf
    * </pre>
    */

   public static final String PROPERTY_RESULTOF = "resultOf";

   private RuleApplicationSet resultOf = null;


   public RuleApplicationSet getResultOf()
   {
      if (this.resultOf == null)
      {
         return RuleApplicationSet.EMPTY_SET;
      }

      return this.resultOf;
   }


   public boolean addToResultOf(RuleApplication value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.resultOf == null)
         {
            this.resultOf = new RuleApplicationSet();
         }

         changed = this.resultOf.add(value);

         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RESULTOF, null, value);
         }
      }

      return changed;
   }


   public boolean removeFromResultOf(RuleApplication value)
   {
      boolean changed = false;

      if ((this.resultOf != null) && (value != null))
      {
         changed = this.resultOf.remove(value);

         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RESULTOF, value, null);
         }
      }

      return changed;
   }


   public ReachableState withResultOf(RuleApplication value)
   {
      addToResultOf(value);
      return this;
   }


   public ReachableState withoutResultOf(RuleApplication value)
   {
      removeFromResultOf(value);
      return this;
   }


   public void removeAllFromResultOf()
   {
      LinkedHashSet<RuleApplication> tmpSet = new LinkedHashSet<RuleApplication>(this.getResultOf());

      for (RuleApplication value : tmpSet)
      {
         this.removeFromResultOf(value);
      }
   }


   public RuleApplication createResultOf()
   {
      RuleApplication value = new RuleApplication();
      withResultOf(value);
      return value;
   }


   public ReachableState withRuleapplications(RuleApplication... value)
   {
      if (value == null)
      {
         return this;
      }
      for (RuleApplication item : value)
      {
         addToRuleapplications(item);
      }
      return this;
   }


   public ReachableState withoutRuleapplications(RuleApplication... value)
   {
      for (RuleApplication item : value)
      {
         removeFromRuleapplications(item);
      }
      return this;
   }


   public ReachableState withResultOf(RuleApplication... value)
   {
      if (value == null)
      {
         return this;
      }
      for (RuleApplication item : value)
      {
         addToResultOf(item);
      }
      return this;
   }


   public ReachableState withoutResultOf(RuleApplication... value)
   {
      for (RuleApplication item : value)
      {
         removeFromResultOf(item);
      }
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_METRICVALUE = "metricValue";

   private double metricValue;


   public double getMetricValue()
   {
      return this.metricValue;
   }


   public void setMetricValue(double value)
   {
      if (this.metricValue != value)
      {

         double oldValue = this.metricValue;
         this.metricValue = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_METRICVALUE, oldValue, value);
      }
   }


   public ReachableState withMetricValue(double value)
   {
      setMetricValue(value);
      return this;
   }

   public static String PROPERTY_FAILURE_STATE = "failureState";

   private boolean failureState;

   private boolean finalState;

   private boolean startState;

   private ObjectSet lazyGraph;
   
   public ObjectSet getLazyGraph()
   {
      return lazyGraph;
   }


   public void setFailureState(boolean b)
   {
      if (this.failureState != b)
      {

         boolean oldValue = this.failureState;
         this.failureState = b;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_METRICVALUE, oldValue, b);
      }
      this.failureState = b;

   }


   public boolean isFailureState()
   {
      return failureState;
   }
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
	   if (listeners != null) {
		   listeners.firePropertyChange(propertyName, oldValue, newValue);
		   return true;
	   }
	   return false;
   }


   public void setFinalState(boolean b)
   {
      finalState = b;
      
   }


   public void setStartState(boolean b)
   {
      startState = b;
      
   }

   public Object createGraphRoot()
   {
      Object value = new Object();
      withGraphRoot(value);
      return value;
   } 
}
