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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeListener;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
*/
   public class ReachabilityGraph implements PropertyChangeInterface, SendableEntity
{
   //==========================================================================
   private final class OmitRootCondition implements UpdateListener
   {
      private Object root;

      public OmitRootCondition(Object root)
      {
         this.root = root;
      }

	      @Override
	    public boolean update(Object event) {
          	PropertyChangeEvent evt = (PropertyChangeEvent) event;
	    	return evt.getNewValue() != root;
	    }
   }

   public String dumpDiagram(String name)
   {
      OmitRootCondition conditionMap = new OmitRootCondition(this);

      Filter filter = new Filter().withFull(true).withPropertyRegard(conditionMap);
      
      JsonArray jsonArray = masterMap.toJsonArray(this, filter);
      
      String imgLink = getAdapter().toImg(name, jsonArray);
      
      // also add images for all graph roots
      for (Object graphRoot : getStates().getGraphRoot())
      {
         JsonArray graphRootArray = masterMap.toJsonArray(graphRoot);
         
         String rootId = masterMap.getId(graphRoot);
         
         String imgName = name + "/" + rootId;
         
         String subLink = getAdapter().toImg(imgName, graphRootArray);
      }
              
      return imgLink;
   }
   private GuiAdapter adapter;
   
   public GuiAdapter getAdapter(){
	   if(adapter==null){
		   adapter = GraphFactory.getAdapter();
	   }
	   return adapter;
   }

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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   //==========================================================================

   public void removeYou()
   {
      removeAllFromStates();
      removeAllFromTodo();
      removeAllFromRules();
      withoutStates(this.getStates().toArray(new ReachableState[this.getStates().size()]));
      withoutTodo(this.getTodo().toArray(new ReachableState[this.getTodo().size()]));
      withoutRules(this.getRules().toArray(new Pattern[this.getRules().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- ReachableState
    *              parent                   states
    * </pre>
    */

   public static final String PROPERTY_STATES = "states";

   private TreeMap<String, Object> stateMap = new TreeMap<String, Object>();

   public ReachabilityGraph withStateMap(String certificate, ReachableState newState)
   {
      Object oldEntry = stateMap.get(certificate);
      
      if (oldEntry == null)
      {
         stateMap.put(certificate, newState);
      }
      else if (oldEntry instanceof ReachableState && oldEntry != newState)
      {
         ReachableStateSet newStateSet = new ReachableStateSet()
         .with((ReachableState) oldEntry).with(newState);
         stateMap.put(certificate, newStateSet);
      }
      else
      {
         ReachableStateSet oldStateSet = (ReachableStateSet) oldEntry;
         oldStateSet.with(newState);
      }
      
      return this;
   }
   
   private static ReachableStateSet emptyStatesSet = new ReachableStateSet();
   private ReachableStateSet oneElemSet = new ReachableStateSet();
   
   public ReachableStateSet getStateMap(String certificate)
   {
      Object oldEntry = stateMap.get(certificate);
      
      if (oldEntry == null)
      {
         return emptyStatesSet;
      }
      else if (oldEntry instanceof ReachableState)
      {
         oneElemSet.clear();
         oneElemSet.add((ReachableState) oldEntry);
         return oneElemSet;
      }
      else
      {
         return (ReachableStateSet) oldEntry;
      }
   }
   
   private ReachableStateSet states = null;
   
   public ReachableStateSet getStates()
   {
      if (this.states == null)
      {
         return ReachableState.EMPTY_SET;
      }

      return this.states;
   }

   public boolean addToStates(ReachableState value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.states == null)
         {
            this.states = new ReachableStateSet();
         }

         changed = this.states.add (value);

         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromStates(ReachableState value)
   {
      boolean changed = false;
      
      if ((this.states != null) && (value != null))
      {
         changed = this.states.remove(value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATES, value, null);
         }
      }
         
      return changed;   
   }

   public ReachabilityGraph withStates(ReachableState value)
   {
      addToStates(value);
      return this;
   } 

   public ReachabilityGraph withoutStates(ReachableState value)
   {
      removeFromStates(value);
      return this;
   } 

   public void removeAllFromStates()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getStates());

      for (ReachableState value : tmpSet)
      {
         this.removeFromStates(value);
      }
   }

   public ReachableState createStates()
   {
      ReachableState value = new ReachableState();
      withStates(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- ReachableState
    *              master                   todo
    * </pre>
    */

   public static final String PROPERTY_TODO = "todo";

   private ReachableStateSet todo = null;

   public ReachableStateSet getTodo()
   {
      if (this.todo == null)
      {
         return ReachableState.EMPTY_SET;
      }

      return this.todo;
   }

   public boolean addToTodo(ReachableState value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.todo == null)
         {
            this.todo = new ReachableStateSet();
         }

         changed = this.todo.add (value);

         if (changed)
         {
            value.withMaster(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromTodo(ReachableState value)
   {
      boolean changed = false;
      
      if ((this.todo != null) && (value != null))
      {
         changed = this.todo.remove(value);
         
         if (changed)
         {
            value.setMaster(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, value, null);
         }
      }
         
      return changed;   
   }

   public ReachabilityGraph withTodo(ReachableState value)
   {
      addToTodo(value);
      return this;
   } 

   public ReachabilityGraph withoutTodo(ReachableState value)
   {
      removeFromTodo(value);
      return this;
   } 

   public void removeAllFromTodo()
   {
      LinkedHashSet<ReachableState> tmpSet = new LinkedHashSet<ReachableState>(this.getTodo());

      for (ReachableState value : tmpSet)
      {
         this.removeFromTodo(value);
      }
   }

   public ReachableState createTodo()
   {
      ReachableState value = new ReachableState();
      withTodo(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- Pattern
    *              rgraph                   rules
    * </pre>
    */

   public static final String PROPERTY_RULES = "rules";

   private PatternSet rules = null;

   public PatternSet getRules()
   {
      if (this.rules == null)
      {
         return Pattern.EMPTY_SET;
      }

      return this.rules;
   }

   public boolean addToRules(Pattern value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.rules == null)
         {
            this.rules = new PatternSet();
         }

         changed = this.rules.add (value);

         if (changed)
         {
            value.withRgraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromRules(Pattern value)
   {
      boolean changed = false;
      
      if ((this.rules != null) && (value != null))
      {
         changed = this.rules.remove(value);
         
         if (changed)
         {
            value.setRgraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RULES, value, null);
         }
      }
         
      return changed;   
   }

   public ReachabilityGraph withRules(Pattern value)
   {
      addToRules(value);
      return this;
   } 

   public ReachabilityGraph withoutRules(Pattern value)
   {
      removeFromRules(value);
      return this;
   } 

   public void removeAllFromRules()
   {
      LinkedHashSet<Pattern> tmpSet = new LinkedHashSet<Pattern>(this.getRules());

      for (Pattern value : tmpSet)
      {
         this.removeFromRules(value);
      }
   }

   public Pattern createRules()
   {
      Pattern value = new Pattern();
      withRules(value);
      return value;
   }


   public long explore()
   {
      return explore(Long.MAX_VALUE);
   }

   private long noOfNewStates;

   public long explore(long maxNoOfNewStates)
   {
      long currentStateNum = 1;
      
      // add cloneOps to rules, where missing
      
      // all states without successors become todo states
      
      // take a todo state and apply all rules at all places until maxNoOfNewStates 
      // is reached
      noOfNewStates = 0;

      while (!getTodo().isEmpty() && noOfNewStates < maxNoOfNewStates)
      {
         ReachableState first = getTodo().first();
         
         first.withNumber((int) currentStateNum);
         
         currentStateNum++;

         this.withoutTodo(first);

         for (Pattern rule : getRules())
         {
            PatternObject firstPO = (PatternObject) rule.getElements().first();

            rule.resetSearch();

            ((PatternObject) firstPO.withModifier(Pattern.BOUND)).setCurrentMatch(first.getGraphRoot());

            while (rule.findMatch())
            {
               // for each match get the new reachable state and add it to the reachability graph
               Object newGraphRoot = firstPO.getCurrentMatch();
               
               ReachableState newReachableState = new ReachableState().withGraphRoot(newGraphRoot);

               // is the new graph already known?
               IdMap newJsonIdMap = (IdMap) new IdMap().with(rule.getIdMap());
               newJsonIdMap.withSessionId("s");
               String newCertificate = newReachableState.computeCertificate(newJsonIdMap);
               
               ReachableStateSet candidateStates = this.getStateMap(newCertificate);
               
               LinkedHashMap<String, String> match = null;
               
               for( ReachableState oldState : candidateStates)
               {
                  match = match(oldState, newReachableState);
                  
                  if (match != null)
                  {
                     // newReachableState is isomorphic to oldState. Just add a link from first to oldState
                     first.createRuleapplications().withDescription("" + rule.getName()).withTgt(oldState);
                     break;
                  }
               }
               
               if (match == null)
               {
                  // no isomorphic old state, add new state
                  this.withStates(newReachableState).withTodo(newReachableState).withStateMap(newCertificate, newReachableState);
                  first.createRuleapplications().withDescription("" + rule.getName()).withTgt(newReachableState);
               }
               
            }
         }
      }
      
      return currentStateNum;
   }

   public LinkedHashMap<String, String> match(ReachableState s1, ReachableState s2)
   {
      
	   IdMap map1 = (IdMap) new IdMap().with(masterMap);
      IdMap map2 = (IdMap) new IdMap().with(masterMap);
      
      map1.withSessionId("s");
      map2.withSessionId("s");

      LinkedHashMap<String, String> fwdmapping = new LinkedHashMap<String, String>();
      LinkedHashMap<String, String> bwdmapping = new LinkedHashMap<String, String>();

      String key1 = map1.getId(s1.getGraphRoot());
      String key2 = map2.getId(s2.getGraphRoot());
      
      fwdmapping.put(key1, key2);
      bwdmapping.put(key2, key1);

      JsonArray ja1 = map1.toJsonArray(s1.getGraphRoot());
      JsonArray ja2 = map2.toJsonArray(s2.getGraphRoot());

      LinkedHashMap<String, JsonObject> joMap1 = null;
      LinkedHashMap<String, JsonObject> joMap2 = null;
      
      joMap1 = new LinkedHashMap<String, JsonObject>();

      for (Object object : ja1)
      {
         JsonObject jo = (JsonObject) object;

         String key = jo.getString(IdMap.ID);

         joMap1.put(key, jo);
      }

      joMap2 = new LinkedHashMap<String, JsonObject>();

      for (Object object : ja2)
      {
         JsonObject jo = (JsonObject) object;

         String key = jo.getString(IdMap.ID);

         joMap2.put(key, jo);
      }

      boolean match = match(s1, ja1, joMap1, s2, ja2, joMap2, key1, fwdmapping, bwdmapping);

      return match ? fwdmapping : null;
   }

   
   public boolean match(ReachableState s1, JsonArray ja1, LinkedHashMap<String, JsonObject> joMap1, 
         ReachableState s2, JsonArray ja2, LinkedHashMap<String, JsonObject> joMap2,
         String cn1, LinkedHashMap<String, String> fwdmapping, LinkedHashMap<String, String> bwdmapping)
   {
      String cn2 = fwdmapping.get(cn1);

      // a mapping for currentNode has just been added. Validate it and compute mappings for neighbors
      JsonObject currentJo1 = joMap1.get(cn1);
      JsonObject currentJo2 = joMap2.get(cn2);

      // certificates are equal, only check refs
      // go through properties
      JsonObject currentProps1 = currentJo1.getJsonObject(JsonTokener.PROPS);
      JsonObject currentProps2 = currentJo2.getJsonObject(JsonTokener.PROPS);

      for (Iterator<String> iter = currentProps1.keyIterator(); iter.hasNext();)
      {
         String key = iter.next();

         Object value = currentProps1.get(key);

         if (value instanceof JsonObject)
         {
            JsonObject ref = (JsonObject) value;
            String tgt1 = ref.getString(IdMap.ID);

            String tgt2 = currentProps2.getJsonObject(key).getString(IdMap.ID);

            String mappingOfTgt1 = fwdmapping.get(tgt1);

            if (mappingOfTgt1 != null)
            {
               // already mapped. consistent?
                     if ( ! tgt2.equals(mappingOfTgt1))
                     {
                        // inconsistent mapping 
                        return false;
                     }
            }
            else
            {
               // not yet mapped, thus add mapping and check it.
               fwdmapping.put(tgt1, tgt2);
               bwdmapping.put(tgt2, tgt1);

               boolean match = match(s1, ja1, joMap1, s2, ja2, joMap2, tgt1, fwdmapping, bwdmapping);

               if ( ! match )
               {
                  // did not work
                  fwdmapping.remove(tgt1);
                  bwdmapping.remove(tgt2);
               }
            }
         }
         else if (value instanceof JsonArray)
         {
            // many refs loop through them, match each
            obj1RefLoop: for (Object object : (JsonArray) value)
            {
               JsonObject ref = (JsonObject) object;

               String tgt1 = ref.getString(IdMap.ID);

               // might already have been matched
               String tgt1Map = fwdmapping.get(tgt1);

               if (tgt1Map != null)
               {
                  // consistent? 
                  for (Object o : currentProps2.getJsonArray(key))
                  {
                     ref = (JsonObject) o;
                     String tgt2 = ref.getString(IdMap.ID);

                     if (tgt2.equals(tgt1Map))
                     {
                        continue obj1RefLoop; // <==================== this ref has a match, handle next ref in outer loop
                     }
                  }

                  // inconsistent
                  return false; // <========================== did not work out
               }

               // loop through the  refs of the other object
               for (Object o : currentProps2.getJsonArray(key))
               {
                  ref = (JsonObject) o;
                  String tgt2 = ref.getString(IdMap.ID);

                  // already used for other match?
                  if (bwdmapping.get(tgt2) != null )
                  {
                     continue; // <=========================== this one is not a match candidate, try another
                  }

                  // does the certificate match? 
                  String cert1 = s1.getNode2certificates().get(tgt1);
                  String cert2 = s2.getNode2certificates().get(tgt2);

                  if (cert1.equals(cert2))
                  {
                     // might be a candidate, match it
                     fwdmapping.put(tgt1, tgt2);
                     bwdmapping.put(tgt2, tgt1);

                     boolean match = match(s1, ja1, joMap1, s2, ja2, joMap2, tgt1, fwdmapping, bwdmapping);

                     if ( ! match )
                     {
                        // did not work
                        fwdmapping.remove(tgt1);
                        bwdmapping.remove(tgt2);
                     }
                     else
                     {
                        continue obj1RefLoop; // <==================== this ref has a match, handle next ref in outer loop
                     }
                  }
               }

               // did not find a match for current ref
               return false;
            }
         }
      }

      return true;
   }

   private IdMap masterMap = null;
   
   public IdMap getMasterMap()
   {
      return masterMap;
   }
   
   public void setMasterMap(IdMap newMasterMap)
   {
      masterMap = newMasterMap;
   }
   
   public ReachabilityGraph withMasterMap(IdMap map)
   {
      setMasterMap(map);
      
      return this;
   } 

   public ReachabilityGraph withStates(ReachableState... value)
   {
      if(value==null){
         return this;
      }
      for (ReachableState item : value)
      {
         addToStates(item);
      }
      return this;
   }

   public ReachabilityGraph withoutStates(ReachableState... value)
   {
      for (ReachableState item : value)
      {
         removeFromStates(item);
      }
      return this;
   }

   public ReachabilityGraph withTodo(ReachableState... value)
   {
      if(value==null){
         return this;
      }
      for (ReachableState item : value)
      {
         addToTodo(item);
      }
      return this;
   }

   public ReachabilityGraph withoutTodo(ReachableState... value)
   {
      for (ReachableState item : value)
      {
         removeFromTodo(item);
      }
      return this;
   }

   public ReachabilityGraph withRules(Pattern... value)
   {
      if(value==null){
         return this;
      }
      for (Pattern item : value)
      {
         addToRules(item);
      }
      return this;
   }

   public ReachabilityGraph withoutRules(Pattern... value)
   {
      for (Pattern item : value)
      {
         removeFromRules(item);
      }
      return this;
   }

   public Pattern createRulesNegativeApplicationCondition()
   {
      Pattern value = new NegativeApplicationCondition();
      withRules(value);
      return value;
   } 

   public Pattern createRulesOptionalSubPattern()
   {
      Pattern value = new OptionalSubPattern();
      withRules(value);
      return value;
   } 

   public NegativeApplicationCondition createNegativeApplicationCondition()
   {
      NegativeApplicationCondition value = new NegativeApplicationCondition();
      withRules(value);
      return value;
   } 

   public OptionalSubPattern createOptionalSubPattern()
   {
      OptionalSubPattern value = new OptionalSubPattern();
      withRules(value);
      return value;
   } 
}

