package org.sdmlib.modelcouch.util;

import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.modelcouch.ModelDBListener;
import org.sdmlib.models.pattern.PatternObject;

public class ModelDBListenerPO extends PatternObject<ModelDBListenerPO, ModelDBListener>
{

    public ModelDBListenerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelDBListenerSet matches = new ModelDBListenerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelDBListener) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelDBListenerPO(){
      newInstance(org.sdmlib.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelDBListenerPO(ModelDBListener... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ModelCouchPO hasCouch()
   {
      ModelCouchPO result = new ModelCouchPO(new ModelCouch[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelDBListener.PROPERTY_COUCH, result);
      
      return result;
   }

   public ModelCouchPO createCouch()
   {
      return this.startCreate().hasCouch().endCreate();
   }

   public ModelDBListenerPO hasCouch(ModelCouchPO tgt)
   {
      return hasLinkConstraint(tgt, ModelDBListener.PROPERTY_COUCH);
   }

   public ModelDBListenerPO createCouch(ModelCouchPO tgt)
   {
      return this.startCreate().hasCouch(tgt).endCreate();
   }

   public ModelCouch getCouch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelDBListener) this.getCurrentMatch()).getCouch();
      }
      return null;
   }

   public ModelCouchPO filterCouch()
   {
      ModelCouchPO result = new ModelCouchPO(new ModelCouch[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelDBListener.PROPERTY_COUCH, result);
      
      return result;
   }

   public ModelDBListenerPO filterCouch(ModelCouchPO tgt)
   {
      return hasLinkConstraint(tgt, ModelDBListener.PROPERTY_COUCH);
   }


   public ModelDBListenerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ModelCouchPO createCouchPO()
   {
      ModelCouchPO result = new ModelCouchPO(new ModelCouch[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelDBListener.PROPERTY_COUCH, result);
      
      return result;
   }

   public ModelCouchPO createCouchPO(String modifier)
   {
      ModelCouchPO result = new ModelCouchPO(new ModelCouch[]{});
      
      result.setModifier(modifier);
      super.hasLink(ModelDBListener.PROPERTY_COUCH, result);
      
      return result;
   }

   public ModelDBListenerPO createCouchLink(ModelCouchPO tgt)
   {
      return hasLinkConstraint(tgt, ModelDBListener.PROPERTY_COUCH);
   }

   public ModelDBListenerPO createCouchLink(ModelCouchPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ModelDBListener.PROPERTY_COUCH, modifier);
   }

}
