package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

public class EdgePO extends PatternObject<EdgePO, Edge>
{

    public EdgeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      EdgeSet matches = new EdgeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Edge) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public EdgePO(){
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public EdgePO(Edge... hostGraphObject) {
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
   public EdgePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public EdgePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Edge) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public GraphPO hasGraph()
   {
      GraphPO result = new GraphPO(new Graph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Edge.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public EdgePO hasGraph(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, Edge.PROPERTY_GRAPH);
   }

   public EdgePO createGraph(GraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
   }

   public Graph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public NodePO hasSrc()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Edge.PROPERTY_SRC, result);
      
      return result;
   }

   public NodePO createSrc()
   {
      return this.startCreate().hasSrc().endCreate();
   }

   public EdgePO hasSrc(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Edge.PROPERTY_SRC);
   }

   public EdgePO createSrc(NodePO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public Node getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public NodePO hasTgt()
   {
      NodePO result = new NodePO(new Node[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Edge.PROPERTY_TGT, result);
      
      return result;
   }

   public NodePO createTgt()
   {
      return this.startCreate().hasTgt().endCreate();
   }

   public EdgePO hasTgt(NodePO tgt)
   {
      return hasLinkConstraint(tgt, Edge.PROPERTY_TGT);
   }

   public EdgePO createTgt(NodePO tgt)
   {
      return this.startCreate().hasTgt(tgt).endCreate();
   }

   public Node getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

   public EdgePO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgePO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EdgePO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Edge) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public EdgePO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Edge) getCurrentMatch()).setText(value);
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

   public EdgePO hasParent(GraphPO tgt)
   {
      return hasLinkConstraint(tgt, GraphComponent.PROPERTY_PARENT);
   }

   public EdgePO createParent(GraphPO tgt)
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

   public EdgePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public EdgePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public EdgePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public EdgePO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Edge.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}


