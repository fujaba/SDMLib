package org.sdmlib.modelcouch.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.modelcouch.util.ModelDBListenerPO;
import org.sdmlib.modelcouch.ModelDBListener;
import org.sdmlib.modelcouch.util.ModelCouchPO;

public class ModelCouchPO extends PatternObject<ModelCouchPO, ModelCouch>
{

    public ModelCouchSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelCouchSet matches = new ModelCouchSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelCouch) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelCouchPO(){
      newInstance(org.sdmlib.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelCouchPO(ModelCouch... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ModelCouchPO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCouch.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCouchPO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCouch.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCouchPO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCouch) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public ModelCouchPO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCouch) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public ModelCouchPO hasPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCouch.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCouchPO hasPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCouch.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCouchPO createPort(int value)
   {
      this.startCreate().hasPort(value).endCreate();
      return this;
   }
   
   public int getPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCouch) getCurrentMatch()).getPort();
      }
      return 0;
   }
   
   public ModelCouchPO withPort(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCouch) getCurrentMatch()).setPort(value);
      }
      return this;
   }
   
   public ModelDBListenerPO hasModelDBListener()
   {
      ModelDBListenerPO result = new ModelDBListenerPO(new ModelDBListener[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCouch.PROPERTY_MODELDBLISTENER, result);
      
      return result;
   }

   public ModelDBListenerPO createModelDBListener()
   {
      return this.startCreate().hasModelDBListener().endCreate();
   }

   public ModelCouchPO hasModelDBListener(ModelDBListenerPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCouch.PROPERTY_MODELDBLISTENER);
   }

   public ModelCouchPO createModelDBListener(ModelDBListenerPO tgt)
   {
      return this.startCreate().hasModelDBListener(tgt).endCreate();
   }

   public ModelDBListener getModelDBListener()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCouch) this.getCurrentMatch()).getModelDBListener();
      }
      return null;
   }

}
