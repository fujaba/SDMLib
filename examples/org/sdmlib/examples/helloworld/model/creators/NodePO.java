package org.sdmlib.examples.helloworld.model.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.model.Node;
import org.sdmlib.examples.helloworld.model.creators.NodeSet;

public class NodePO extends PatternObject<NodePO, Node>
{

    public NodeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      NodeSet matches = new NodeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Node) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

