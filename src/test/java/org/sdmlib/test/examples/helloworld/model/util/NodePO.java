package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public NodePO(Node... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
         this.withCandidates(hostGraphObject);
      } else {
         this.withCurrentMatch(hostGraphObject[0]);
         this.withModifier(Pattern.BOUND);
      }
      pattern.findMatch();
  }
   public NodePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public NodePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Node) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public NodePO hasGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_GRAPH);
   }

   public NodePO createGraph(GraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
   }

   public Graph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public EdgePO hasOutEdges()
   {
      EdgePO result = new EdgePO(new Edge[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_OUTEDGES, result);
      
      return result;
   }

   public EdgePO createOutEdges()
   {
      return this.startCreate().hasOutEdges().endCreate();
   }

   public NodePO hasOutEdges(EdgePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_OUTEDGES);
   }

   public NodePO createOutEdges(EdgePO tgt)
   {
      return this.startCreate().hasOutEdges(tgt).endCreate();
   }

   public EdgeSet getOutEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getOutEdges();
      }
      return null;
   }

   public EdgePO hasInEdges()
   {
      EdgePO result = new EdgePO(new Edge[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_INEDGES, result);
      
      return result;
   }

   public EdgePO createInEdges()
   {
      return this.startCreate().hasInEdges().endCreate();
   }

   public NodePO hasInEdges(EdgePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_INEDGES);
   }

   public NodePO createInEdges(EdgePO tgt)
   {
      return this.startCreate().hasInEdges(tgt).endCreate();
   }

   public EdgeSet getInEdges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getInEdges();
      }
      return null;
   }

   public NodePO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Node.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NodePO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public NodePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Node) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public GraphPO hasParent()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GraphComponent.PROPERTY_PARENT, result);
      
      return result;
   }

   public GraphPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public NodePO hasParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }

   public NodePO createParent(GraphPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public Graph getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GraphComponent) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public NodePO hasCopy()
   {
      NodePO result = new NodePO(new Node[]{});
      
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
      NodePO result = new NodePO(new Node[]{});
      
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

   public NodePO hasLinksTo()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_LINKSTO, result);
      
      return result;
   }

   public NodePO createLinksTo()
   {
      return this.startCreate().hasLinksTo().endCreate();
   }

   public NodePO hasLinksTo(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_LINKSTO);
   }

   public NodePO createLinksTo(NodePO tgt)
   {
      return this.startCreate().hasLinksTo(tgt).endCreate();
   }

   public NodeSet getLinksTo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getLinksTo();
      }
      return null;
   }

   public NodePO hasLinksFrom()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Node.PROPERTY_LINKSFROM, result);
      
      return result;
   }

   public NodePO createLinksFrom()
   {
      return this.startCreate().hasLinksFrom().endCreate();
   }

   public NodePO hasLinksFrom(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Node.PROPERTY_LINKSFROM);
   }

   public NodePO createLinksFrom(NodePO tgt)
   {
      return this.startCreate().hasLinksFrom(tgt).endCreate();
   }

   public NodeSet getLinksFrom()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Node) this.getCurrentMatch()).getLinksFrom();
      }
      return null;
   }

}



