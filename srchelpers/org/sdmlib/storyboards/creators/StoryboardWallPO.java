package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardWall;

public class StoryboardWallPO extends PatternObject<StoryboardWallPO, StoryboardWall>
{
   public StoryboardWallSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoryboardWallSet matches = new StoryboardWallSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StoryboardWall) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StoryboardPO hasStoryboard()
   {
      StoryboardPO result = new StoryboardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StoryboardWall.PROPERTY_STORYBOARD, result);
      
      return result;
   }

   public StoryboardWallPO hasStoryboard(StoryboardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StoryboardWall.PROPERTY_STORYBOARD)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Storyboard getStoryboard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StoryboardWall) this.getCurrentMatch()).getStoryboard();
      }
      return null;
   }

   public StoryboardPO createStoryboard()
   {
      return this.startCreate().hasStoryboard().endCreate();
   }

   public StoryboardWallPO createStoryboard(StoryboardPO tgt)
   {
      return this.startCreate().hasStoryboard(tgt).endCreate();
   }

}


