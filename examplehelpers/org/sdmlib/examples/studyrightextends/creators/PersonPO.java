package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Person;
import org.sdmlib.examples.studyrightextends.creators.PersonSet;

public class PersonPO extends PatternObject<PersonPO, Person>
{
   public PersonSet allMatches()
   {
      this.setDoAllMatches(true);

      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   // ==========================================================================

   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).findMyPosition();
      }
   }

   // ==========================================================================

   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).findMyPosition(p0);
      }
   }

   // ==========================================================================

   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).findMyPosition(p0, p1);
      }
   }

}
