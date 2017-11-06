package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class PresidentPO extends PatternObject<PresidentPO, President>
{

    public PresidentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PresidentSet matches = new PresidentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((President) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PresidentPO(){
      newInstance(null);
   }

   public PresidentPO(President... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PresidentPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UniversityPO createUniversityPO()
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(President.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public UniversityPO createUniversityPO(String modifier)
   {
      UniversityPO result = new UniversityPO(new University[]{});
      
      result.setModifier(modifier);
      super.hasLink(President.PROPERTY_UNIVERSITY, result);
      
      return result;
   }

   public PresidentPO createUniversityLink(UniversityPO tgt)
   {
      return hasLinkConstraint(tgt, President.PROPERTY_UNIVERSITY);
   }

   public PresidentPO createUniversityLink(UniversityPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, President.PROPERTY_UNIVERSITY, modifier);
   }

   public University getUniversity()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((President) this.getCurrentMatch()).getUniversity();
      }
      return null;
   }

}
