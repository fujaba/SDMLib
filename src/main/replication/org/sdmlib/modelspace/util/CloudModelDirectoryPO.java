package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.CloudModelDirectory;
import org.sdmlib.modelspace.util.CloudModelFilePO;
import org.sdmlib.modelspace.CloudModelFile;
import org.sdmlib.modelspace.util.CloudModelDirectoryPO;
import org.sdmlib.modelspace.util.CloudModelFileSet;

public class CloudModelDirectoryPO extends PatternObject<CloudModelDirectoryPO, CloudModelDirectory>
{

    public CloudModelDirectorySet allMatches()
   {
      this.setDoAllMatches(true);
      
      CloudModelDirectorySet matches = new CloudModelDirectorySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CloudModelDirectory) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CloudModelDirectoryPO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CloudModelDirectoryPO(CloudModelDirectory... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public CloudModelFilePO hasFiles()
   {
      CloudModelFilePO result = new CloudModelFilePO(new CloudModelFile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(CloudModelDirectory.PROPERTY_FILES, result);
      
      return result;
   }

   public CloudModelFilePO createFiles()
   {
      return this.startCreate().hasFiles().endCreate();
   }

   public CloudModelDirectoryPO hasFiles(CloudModelFilePO tgt)
   {
      return hasLinkConstraint(tgt, CloudModelDirectory.PROPERTY_FILES);
   }

   public CloudModelDirectoryPO createFiles(CloudModelFilePO tgt)
   {
      return this.startCreate().hasFiles(tgt).endCreate();
   }

   public CloudModelFileSet getFiles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CloudModelDirectory) this.getCurrentMatch()).getFiles();
      }
      return null;
   }

}
