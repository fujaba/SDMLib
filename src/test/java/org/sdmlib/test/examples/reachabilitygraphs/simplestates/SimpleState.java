/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#ReachabilitGraphSameCertificatesNonIsomorphic
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#ReachabilityGraphSimpleIsomorphismTest
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#LazyReachabilityGraphAttrsAndNodes
 */
   public  class SimpleState implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      for (Node obj : new NodeSet(this.getNodes())) { obj.removeYou(); }
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SimpleState ----------------------------------- Node
    *              graph                   nodes
    * </pre>
    */
   
   public static final String PROPERTY_NODES = "nodes";

   private NodeSet nodes = null;
   
   public NodeSet getNodes()
   {
      if (this.nodes == null)
      {
         return NodeSet.EMPTY_SET;
      }
   
      return this.nodes;
   }

   public SimpleState withNodes(Node... value)
   {
      if(value==null){
         return this;
      }
      for (Node item : value)
      {
         if (item != null)
         {
            if (this.nodes == null)
            {
               this.nodes = new NodeSet();
            }
            
            boolean changed = this.nodes.add (item);

            if (changed)
            {
               item.withGraph(this);
               firePropertyChange(PROPERTY_NODES, null, item);
            }
         }
      }
      return this;
   } 

   public SimpleState withoutNodes(Node... value)
   {
      for (Node item : value)
      {
         if ((this.nodes != null) && (item != null))
         {
            if (this.nodes.remove(item))
            {
               item.withoutGraph(this);
               firePropertyChange(PROPERTY_NODES, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#ReachabilitGraphSameCertificatesNonIsomorphic
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#ReachabilityGraphSimpleIsomorphismTest
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#LazyReachabilityGraphAttrsAndNodes
 */
   public Node createNodes()
   {
      Node value = new Node();
      withNodes(value);
      return value;
   } 
}
