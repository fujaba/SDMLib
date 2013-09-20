package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.StoryboardWall;
import org.sdmlib.storyboards.creators.StoryboardWallSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.storyboards.creators.StoryboardPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.storyboards.creators.StoryboardWallPO;
import org.sdmlib.storyboards.Storyboard;

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

}

