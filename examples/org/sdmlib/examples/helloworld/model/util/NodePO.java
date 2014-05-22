package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.helloworld.model.Node;
import org.sdmlib.examples.helloworld.model.util.NodeSet;
import org.sdmlib.examples.helloworld.model.util.NodePO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.LinkConstraint;

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
   
   public NodePO(){
   }

   public NodePO(Node... hostGraphObject)
   {
      Pattern<Object> pattern = new Pattern<Object>();      NodePO value = new NodePO();
      pattern.addToElements(value);
      value.setModifier(this.getModifier());
      
      if(hostGraphObject!=null){
            if(hostGraphObject.length>1){
                  value.withCandidates(hostGraphObject);
            } else {
                  value.setCurrentMatch(hostGraphObject);
            }
      }
      pattern.findMatch();
      
   }

   public NodePO hasCopy()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_COPY, result);
      
      return result;
   }

   public NodePO createCopy()
   {
      return this.startCreate().hasCopy().endCreate();
   }

   public NodePO hasCopy(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_COPY);
   }

   public NodePO createCopy(NodePO tgt)
   {
      return this.startCreate().hasCopy(tgt).endCreate();
   }

   public Node getCopy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getCopy();
      }
      return null;
   }

   public NodePO hasOrig()
   {
      NodePO result = new NodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Node.PROPERTY_ORIG, result);
      
      return result;
   }

   public NodePO createOrig()
   {
      return this.startCreate().hasOrig().endCreate();
   }

   public NodePO hasOrig(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_ORIG);
   }

   public NodePO createOrig(NodePO tgt)
   {
      return this.startCreate().hasOrig(tgt).endCreate();
   }

   public Node getOrig()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getOrig();
      }
      return null;
   }

}

