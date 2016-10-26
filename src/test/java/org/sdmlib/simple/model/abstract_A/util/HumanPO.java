package org.sdmlib.simple.model.abstract_A.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_A.Human;
import org.sdmlib.simple.model.abstract_A.util.PersonPO;
import org.sdmlib.simple.model.abstract_A.Person;
import org.sdmlib.simple.model.abstract_A.util.HumanPO;

public class HumanPO extends PatternObject<HumanPO, Human>
{

    public HumanSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HumanSet matches = new HumanSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Human) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HumanPO(){
      newInstance(null);
   }

   public HumanPO(Human... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public HumanPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createHasPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public PersonPO createHasPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Human.PROPERTY_HAS, result);
      
      return result;
   }

   public HumanPO createHasLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS);
   }

   public HumanPO createHasLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Human.PROPERTY_HAS, modifier);
   }

   public Person getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Human) this.getCurrentMatch()).getHas();
      }
      return null;
   }

}
