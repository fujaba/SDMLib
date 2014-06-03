package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.StoryboardWall;
import org.sdmlib.storyboards.util.StoryboardWallSet;
import org.sdmlib.storyboards.util.StoryboardPO;
import org.sdmlib.storyboards.util.StoryboardWallPO;
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


   public StoryboardWallPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StoryboardWallPO(StoryboardWall... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public StoryboardPO hasStoryboard()
   {
      StoryboardPO result = new StoryboardPO(new org.sdmlib.storyboards.Storyboard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(StoryboardWall.PROPERTY_STORYBOARD, result);
      
      return result;
   }

   public StoryboardPO createStoryboard()
   {
      return this.startCreate().hasStoryboard().endCreate();
   }

   public StoryboardWallPO hasStoryboard(StoryboardPO tgt)
   {
      return hasLinkConstraint(tgt, StoryboardWall.PROPERTY_STORYBOARD);
   }

   public StoryboardWallPO createStoryboard(StoryboardPO tgt)
   {
      return this.startCreate().hasStoryboard(tgt).endCreate();
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
