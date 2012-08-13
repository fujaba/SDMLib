package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.helloworld.GraphComponent;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.examples.helloworld.Graph;

public class GraphComponentSet extends LinkedHashSet<GraphComponent>
{
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (GraphComponent obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GraphComponentSet withText(String value)
   {
      for (GraphComponent obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (GraphComponent obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }
   public GraphComponentSet withParent(Graph value)
   {
      for (GraphComponent obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}

