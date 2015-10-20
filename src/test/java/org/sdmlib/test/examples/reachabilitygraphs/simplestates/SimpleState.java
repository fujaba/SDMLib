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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
*/
   public class SimpleState implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutNodes(this.getNodes().toArray(new Node[this.getNodes().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
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
         return Node.EMPTY_SET;
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
               getPropertyChangeSupport().firePropertyChange(PROPERTY_NODES, null, item);
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
               item.setGraph(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_NODES, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
*/
   public Node createNodes()
   {
      Node value = new Node();
      withNodes(value);
      return value;
   } 
}
