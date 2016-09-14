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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachableState;

/**
 * 
 * @see <a href= '../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'> PatternModelCodeGen.java</a>
 */
public class ReachabilityGraph implements PropertyChangeInterface, SendableEntity
{
   // ==========================================================================
   private final class OmitRootCondition implements UpdateListener
   {
      private Object root;


      public OmitRootCondition(Object root)
      {
         this.root = root;
      }


      @Override
      public boolean update(Object event)
      {
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

   private Metric metric = null;


   public void setMetric(Metric metric)
   {
      this.metric = metric;
   }

   private GuiAdapter adapter;


   public GuiAdapter getAdapter()
   {
      if (adapter == null)
      {
         adapter = GraphFactory.getAdapter();
      }
      return adapter;
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


   public boolean removePropertyChangeListener(String property, PropertyChangeListener listener)
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
      removeAllFromStates();
      removeAllFromTodo();
      removeAllFromRules();
      withoutStates(this.getStates().toArray(new ReachableState[this.getStates().size()]));
      withoutTodo(this.getTodo().toArray(new ReachableState[this.getTodo().size()]));
      withoutRules(this.getRules().toArray(new Pattern[this.getRules().size()]));
      withoutFinalStates(this.getFinalStates().toArray(new ReachableState[this.getFinalStates().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * ReachabilityGraph ----------------------------------- ReachableState
    *              reachabilitygraph                   finalStates
    * </pre>
    */

   public static final String PROPERTY_FINALSTATES = "finalStates";

   private ReachableStateSet finalStates = null;


   public ReachableStateSet getFinalStates()
   {
      if (this.finalStates == null)
      {
         return ReachableStateSet.EMPTY_SET;
      }

      return this.finalStates;
   }


   public ReachabilityGraph withFinalStates(ReachableState... value)
   {
      if (value == null)
      {
         return this;
      }
      for (ReachableState item : value)
      {
         if (item != null)
         {
            if (this.finalStates == null)
            {
               this.finalStates = new ReachableStateSet();
            }

            boolean changed = this.finalStates.add(item);

            if (changed)
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FINALSTATES, null, item);
            }
         }
      }
      return this;
   }


   public ReachabilityGraph withoutFinalStates(ReachableState... value)
   {
      for (ReachableState item : value)
      {
         if ((this.finalStates != null) && (item != null))
         {
            if (this.finalStates.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FINALSTATES, item, null);
            }
         }
      }
      return this;
   }


   public ReachableState createFinalStates()
   {
      ReachableState value = new ReachableState();
      withFinalStates(value);
      return value;
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
         ReachableStateSet newStateSet = new ReachableStateSet()
            .with((ReachableState) oldEntry).with(newState);
         stateMap.put(certificate, newStateSet);
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

         changed = this.states.add(value);

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

   private BlockingQueue<ReachableState> todo = null;


   public BlockingQueue<ReachableState> getTodo()
   {
      if (this.todo == null)
      {
         if (metric != null)
         {
            this.todo = new PriorityBlockingQueue<ReachableState>(11, (s1, s2) -> Double.compare(s2.getMetricValue(), s1.getMetricValue()));
         }
         else
         {
            this.todo = new LinkedBlockingQueue<>();
         }
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
            if (metric != null)
            {
               this.todo = new PriorityBlockingQueue<ReachableState>(11, (s1, s2) -> Double.compare(s2.getMetricValue(), s1.getMetricValue()));
            }
            else
            {
               this.todo = new LinkedBlockingQueue<>();
            }
         }

         changed = this.todo.offer(value);

         getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, null, value);
      }

      return changed;
   }


   public boolean removeFromTodo(ReachableState value)
   {
      boolean changed = false;

      if ((this.todo != null) && (value != null))
      {
         changed = this.todo.remove(value);

         getPropertyChangeSupport().firePropertyChange(PROPERTY_TODO, value, null);
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

   private long maxNoOfNewStates;


   public PatternSet getRules()
   {
      if (this.rules == null)
      {
         return Pattern.EMPTY_SET;
      }

      return this.rules;
   }


   public boolean addToRules(PatternObject po)
   {
      return addToRules(po.getPattern());
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

         changed = this.rules.add(value);

         if (changed)
         {
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
      return explore(Long.MAX_VALUE, Searchmode.DEPTH);
   }

   public enum Searchmode
   {
      DEFAULT, DEPTH, IGNORE, DEPTHIGNORE
   }


   public long explore(long maxNoOfNewStates, Searchmode mode)
   {
      long currentStateNum = 1;
      double bestMetricYet = Double.NEGATIVE_INFINITY;
      double reallyBestMetricYet = Double.NEGATIVE_INFINITY;
      int ignoredStates = 0;
      String ignoredString = "";
      boolean changedIgnoreString = false;

      long lastMessageTime = System.currentTimeMillis();

      IdMap newJsonIdMap = (IdMap) new SDMLibIdMap("s");

      // inital states get certificates
      for (ReachableState s : this.getStates())
      {
         String newCertificate = s.computeCertificate(newJsonIdMap);
         this.withStateMap(newCertificate, s);

         if (metric != null)
         {
            double smetric = metric.compute(s.getGraphRoot());
            s.setMetricValue(smetric);
            bestMetricYet = Math.max(bestMetricYet, smetric);
            reallyBestMetricYet = bestMetricYet;
         }
      }

      // take a todo state and apply all rules at all places until
      // maxNoOfNewStates
      // is reached
      doToDo: while (!getTodo().isEmpty() && this.getStates().size() <= maxNoOfNewStates)
      {
         if (metric != null)
         {
            // sort todo list
            // Collections.sort(getTodo(), (s1, s2) ->
            // Double.compare(s2.getMetricValue(), s1.getMetricValue()));
         }

         ReachableState current = null;
         try
         {
            current = getTodo().take();
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }

         if (current.getNumber() == 0)
         {
            current.withNumber((int) currentStateNum);
         }

         currentStateNum++;

         long alreadyKnownMatches = current.noOfRuleMatchesDone;
         current.noOfRuleMatchesDone = 0;

         this.withoutTodo(current);

         for (Pattern rule : getRules())
         {
            PatternObject firstPO = (PatternObject) rule.getElements().first();

            rule.resetSearch();

            ((PatternObject) firstPO.withModifier(Pattern.BOUND)).setCurrentMatch(current.getGraphRoot());

            while (rule.findMatch())
            {
               // count matches found on this graph
               current.noOfRuleMatchesDone++;

               if (current.noOfRuleMatchesDone <= alreadyKnownMatches)
               {
                  // has been considered in previous expansions of this state.
                  // Those previous expansions have been aborted in order to
                  // expand more promising state.
                  // Now it is reconsidered. But we do not need to go through
                  // the already done expansions.
                  bestMetricYet = current.getMetricValue();

                  continue;
               }

               // for each match get the new reachable state and add it to the
               // reachability graph
               Object newGraphRoot = firstPO.getCurrentMatch();

               ReachableState newReachableState = new ReachableState().withGraphRoot(newGraphRoot);

               if (metric != null)
               {
                  // computing the metric is cheap and might allow to avoid
                  // further computation
                  double newMetricValue = metric.compute(newReachableState.getGraphRoot());
                  newReachableState.setMetricValue(newMetricValue);

                  if ((mode == Searchmode.IGNORE || mode == Searchmode.DEPTHIGNORE)
                     && newMetricValue < bestMetricYet)
                  {
                     // ignore rules with a bad metric
                     if (++ignoredStates % (maxNoOfNewStates / 30) == 0)
                     {
                        ignoredString = " Ignored " + ignoredStates + " graphs.";
                        changedIgnoreString = true;
                     }
                     continue;
                  }
                  else
                  {
                     bestMetricYet = Math.max(bestMetricYet, newMetricValue);
                     reallyBestMetricYet = Math.max(bestMetricYet, reallyBestMetricYet);
                  }

                  long currentTime = System.currentTimeMillis();
                  long elapsedTime = currentTime - lastMessageTime;
                  elapsedTime /= 1000; // elapsed seconds
                  elapsedTime /= 60; // elapsed minutes
                  if (elapsedTime >= 1)
                  {
                     System.out.println("Best metric so far: " + reallyBestMetricYet);
                     System.out.println("     Number of visited graphs: " + this.getStates().size());
                     lastMessageTime = currentTime;
                  }
               }

               // is the new graph already known?
               newJsonIdMap = (IdMap) new SDMLibIdMap("s").with(rule.getIdMap());
               newJsonIdMap.withSessionId("s");
               String newCertificate = newReachableState.computeCertificate(newJsonIdMap);

               ReachableStateSet candidateStates = this.getStateMap(newCertificate);

               LinkedHashMap<String, String> match = null;

               for (ReachableState oldState : candidateStates)
               {
                  match = match(oldState, newReachableState);

                  if (match != null)
                  {
                     // newReachableState is isomorphic to oldState. Just add a
                     // link from first to oldState
                     current.createRuleapplications().withRule(rule).withDescription("" + rule.getName()).withTgt(oldState);
                     break;
                  }
               }

               if (match == null)
               {
                  // no isomorphic old state, add new state
                  this.withStates(newReachableState).withTodo(newReachableState).withStateMap(newCertificate,
                     newReachableState);
                  current.createRuleapplications().withRule(rule).withDescription("" + rule.getName()).withTgt(newReachableState);
                  int size = this.getStates().size();
                  // progress bar, 30 steps
                  if (maxNoOfNewStates < 30 || size % (maxNoOfNewStates / 30) == 0 || changedIgnoreString)
                  {
                     changedIgnoreString = false;
                     System.out.print("Progress [");
                     for (int i = 0; i < 30; i++)
                     {
                        if (maxNoOfNewStates < 30 || i < (size / (maxNoOfNewStates / 30)))
                        {
                           System.out.print(".");
                        }
                        else
                        {
                           System.out.print(" ");
                        }
                     }
                     System.out.print("] (Generating " + maxNoOfNewStates + " graphs)" + ignoredString + "\r");
                  }

                  if ((mode == Searchmode.DEPTH || mode == Searchmode.DEPTHIGNORE)
                     && newReachableState.getMetricValue() > current.getMetricValue())
                  {
                     // new state is more interesting than current state,
                     // abort current state and continue with new state
                     this.withTodo(current);

                     continue doToDo;
                  }
               }
            }
         }
      }

      return currentStateNum;
   }


   public long exploreParallel()
   {
      this.maxNoOfNewStates = Long.MAX_VALUE;

      IdMap newJsonIdMap = (IdMap) new SDMLibIdMap("s");

      // inital states get certificates
      for (ReachableState s : this.getStates())
      {
         String newCertificate = s.computeCertificate(newJsonIdMap);
         this.withStateMap(newCertificate, s);
      }
      ExecutorService threadPool = Executors.newFixedThreadPool(12);

      while (!getTodo().isEmpty())
      {
         if (!getTodo().isEmpty())
         {
            try
            {
               ReachableState current = getTodo().take();
               Worker worker = new Worker(this, current, threadPool);
               threadPool.submit(worker);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         else
         {
            try
            {
               Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }

      }
      try
      {
         // wait for all task finished
         while (workercount > 0)
         {
            Thread.sleep(5);
         }

         threadPool.shutdown();
         threadPool.awaitTermination(20, TimeUnit.SECONDS);

      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

      return states.size();
   }

   private long workercount = 0;

   private class Worker implements Callable<Void>
   {
      private ReachabilityGraph hostgraph;

      private ReachableState current;

      private ExecutorService threadPool;


      public Worker(ReachabilityGraph hostgraph, ReachableState current, ExecutorService threadPool)
      {
         this.threadPool = threadPool;
         synchronized (hostgraph)
         {
            hostgraph.workercount++;
         }
         this.current = current;

         this.hostgraph = hostgraph;
      }


      @Override
      public Void call() throws Exception
      {
         try
         {
            long alreadyKnownMatches = current.noOfRuleMatchesDone;
            long noOfRuleMatchesDone = 0;
            for (Pattern rule : getRules())
            {

               rule = getMyRuleCopy(rule);

               PatternObject firstPO = (PatternObject) rule.getElements().first();

               rule.resetSearch();

               ((PatternObject) firstPO.withModifier(Pattern.BOUND)).setCurrentMatch(current.getGraphRoot());

               while (rule.findMatch())
               {
                  // count matches found on this graph
                  noOfRuleMatchesDone++;

                  if (noOfRuleMatchesDone <= alreadyKnownMatches)
                  {
                     // has been considered in previous expansions of this
                     // state.
                     // Those previous expansions have been aborted in order to
                     // expand more promising state.
                     // Now it is reconsidered. But we do not need to go through
                     // the already done expansions.

                     continue;
                  }
                  current.noOfRuleMatchesDone = noOfRuleMatchesDone;
                  // for each match get the new reachable state and add it to
                  // the
                  // reachability graph
                  Object newGraphRoot = firstPO.getCurrentMatch();

                  ReachableState newReachableState = new ReachableState().withGraphRoot(newGraphRoot);

                  // is the new graph already known?
                  IdMap newJsonIdMap = (IdMap) new SDMLibIdMap("s").with(rule.getIdMap());
                  newJsonIdMap.withSessionId("s");
                  String newCertificate = newReachableState.computeCertificate(newJsonIdMap);

                  LinkedHashMap<String, String> match;

                  synchronized (hostgraph.stateMap)
                  {
                     ReachableStateSet candidateStates = hostgraph.getStateMap(newCertificate);
                     match = null;
                     for (ReachableState oldState : candidateStates)
                     {
                        match = match(oldState, newReachableState);

                        if (match != null)
                        {
                           // newReachableState is isomorphic to oldState. Just
                           // add
                           // a
                           // link from first to oldState
                           current.createRuleapplications().withRule(rule).withDescription("" + rule.getName()).withTgt(oldState);
                           break;
                        }
                     }
                  }
                  if (match == null)
                  {
                     // no isomorphic old state, add new state
                     hostgraph.withStates(newReachableState.withNumber(hostgraph.getStates().size()));
                     hostgraph.withStateMap(newCertificate, newReachableState);

                     threadPool.submit(new Worker(hostgraph, newReachableState, threadPool));

                     current.createRuleapplications().withRule(rule).withDescription("" + rule.getName()).withTgt(newReachableState);
                     int size = hostgraph.getStates().size();

                  }
               }
            }
            hostgraph.workercount--;
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         return null;
      }

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

   HashMap<Long, HashMap<Pattern, Pattern>> ruleClones;


   public Pattern getMyRuleCopy(Pattern rule)
   {
      long threadId = Thread.currentThread().getId();

      if (ruleClones == null)
      {
         ruleClones = new HashMap<>();
      }

      HashMap<Pattern, Pattern> patternMap = ruleClones.get(threadId);
      if (patternMap == null)
      {
         patternMap = new HashMap<>();
         ruleClones.put(threadId, patternMap);
      }
      Pattern myClone = patternMap.get(rule);

      if (myClone == null)
      {
         IdMap origMap = (IdMap) new SDMLibIdMap("om").with(rule.getIdMap());
         IdMap cloneMap = (IdMap) new SDMLibIdMap("cm").with(rule.getIdMap());
         JsonArray jsonArray = origMap.toJsonArray(rule);
         myClone = (Pattern) cloneMap.decode(jsonArray);

         setIdMap(myClone, cloneMap);

         patternMap.put(rule, myClone);
      }

      return myClone;
   }


   private void setIdMap(Pattern pattern, IdMap idMap)
   {
      pattern.setIdMap(idMap);

      PatternElementSet elements = pattern.getElements();
      for (PatternElement patternElement : elements)
      {
         if (patternElement instanceof Pattern)
         {
            setIdMap((Pattern) patternElement, idMap);
         }
      }

   }


   public boolean match(ReachableState s1, JsonArray ja1, LinkedHashMap<String, JsonObject> joMap1,
         ReachableState s2, JsonArray ja2, LinkedHashMap<String, JsonObject> joMap2,
         String cn1, LinkedHashMap<String, String> fwdmapping, LinkedHashMap<String, String> bwdmapping)
   {
      String cn2 = fwdmapping.get(cn1);

      // a mapping for currentNode has just been added. Validate it and compute
      // mappings for neighbors
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
               if (!tgt2.equals(mappingOfTgt1))
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

               if (!match)
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
                        continue obj1RefLoop; // <==================== this ref
                                              // has a match, handle next ref in
                                              // outer loop
                     }
                  }

                  // inconsistent
                  return false; // <========================== did not work out
               }

               // loop through the refs of the other object
               for (Object o : currentProps2.getJsonArray(key))
               {
                  ref = (JsonObject) o;
                  String tgt2 = ref.getString(IdMap.ID);

                  // already used for other match?
                  if (bwdmapping.get(tgt2) != null)
                  {
                     continue; // <=========================== this one is not a
                               // match candidate, try another
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

                     if (!match)
                     {
                        // did not work
                        fwdmapping.remove(tgt1);
                        bwdmapping.remove(tgt2);
                     }
                     else
                     {
                        continue obj1RefLoop; // <==================== this ref
                                              // has a match, handle next ref in
                                              // outer loop
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
      if (value == null)
      {
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
      if (value == null)
      {
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
      if (value == null)
      {
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


   /**
    * 
    * @param pattern with concrete object model root
    */
   public void verify(Pattern... pattern)
   {
      for (ReachableState reachableState : states)
      {
         for (Pattern rule : pattern)
         {

            if (reachableState.isFailureState())
            {
               continue;
            }

            Object graphRoot = reachableState.getGraphRoot();

            PatternObject firstPO = (PatternObject) rule.getElements().first();

            rule.resetSearch();
            firstPO.withModifier(Pattern.BOUND);
            firstPO.setCurrentMatch(graphRoot);
            if (rule.findMatch())
            {
               reachableState.setFailureState(true);
            }

         }

      }
   }


   /**
    * @param pattern with ReachabilityGraph as root
    * @return true if no match could be found
    */
   public boolean verifyPath(Pattern pattern)
   {

      PatternObject firstPO = (PatternObject) pattern.getElements().first();

      pattern.resetSearch();
      firstPO.withModifier(Pattern.BOUND);
      firstPO.setCurrentMatch(this);

      return pattern.getHasMatch();

   }


   public void lookUpForEndStates()
   {
      for (ReachableState reachableState : states)
      {
         RuleApplicationSet ruleapplications = reachableState.getRuleapplications();
         if (ruleapplications.size() == 0)
         {
            // find a final State
            withFinalStates(reachableState);
         }

      }
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
