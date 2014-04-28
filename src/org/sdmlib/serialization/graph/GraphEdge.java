package org.sdmlib.serialization.graph;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */

public class GraphEdge
{
   private GraphEdgeLabel source;
   private GraphEdgeLabel target;

   public GraphEdgeLabel getSource()
   {
      return source;
   }

   public GraphEdge withSource(GraphEdgeLabel source)
   {
      this.source = source;
      return this;
   }

   public GraphEdge withSource(GraphNode item)
   {
      this.source = new GraphEdgeLabel().withItem(item);
      return this;
   }

   public GraphEdgeLabel getTarget()
   {
      return target;
   }

   public GraphEdge withTarget(GraphEdgeLabel target)
   {
      this.target = target;
      return this;
   }

   public GraphEdge withTarget(GraphNode item)
   {
      this.target = new GraphEdgeLabel().withItem(item);
      return this;
   }

   public GraphEdge withTarget(GraphNode item, String cardinality,
         String property)
   {
      this.target = new GraphEdgeLabel().withItem(item)
         .withCardinality(cardinality).withProperty(property);
      return this;
   }
}
