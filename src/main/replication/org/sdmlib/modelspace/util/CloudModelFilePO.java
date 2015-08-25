package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.CloudModelFile;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.modelspace.util.CloudModelDirectoryPO;
import org.sdmlib.modelspace.CloudModelDirectory;
import org.sdmlib.modelspace.util.CloudModelFilePO;

public class CloudModelFilePO extends PatternObject<CloudModelFilePO, CloudModelFile>
{

    public CloudModelFileSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CloudModelFileSet matches = new CloudModelFileSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((CloudModelFile) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CloudModelFilePO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CloudModelFilePO(CloudModelFile... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public CloudModelFilePO hasFileName(String value)
   {
      new AttributeConstraint()
      .withAttrName(CloudModelFile.PROPERTY_FILENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CloudModelFilePO hasFileName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(CloudModelFile.PROPERTY_FILENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CloudModelFilePO createFileName(String value)
   {
      this.startCreate().hasFileName(value).endCreate();
      return this;
   }
   
   public String getFileName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CloudModelFile) getCurrentMatch()).getFileName();
      }
      return null;
   }
   
   public CloudModelFilePO withFileName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloudModelFile) getCurrentMatch()).setFileName(value);
      }
      return this;
   }
   
   public CloudModelFilePO hasLastModifiedTime(long value)
   {
      new AttributeConstraint()
      .withAttrName(CloudModelFile.PROPERTY_LASTMODIFIEDTIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CloudModelFilePO hasLastModifiedTime(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(CloudModelFile.PROPERTY_LASTMODIFIEDTIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CloudModelFilePO createLastModifiedTime(long value)
   {
      this.startCreate().hasLastModifiedTime(value).endCreate();
      return this;
   }
   
   public long getLastModifiedTime()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CloudModelFile) getCurrentMatch()).getLastModifiedTime();
      }
      return 0;
   }
   
   public CloudModelFilePO withLastModifiedTime(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((CloudModelFile) getCurrentMatch()).setLastModifiedTime(value);
      }
      return this;
   }
   
   public CloudModelDirectoryPO hasDir()
   {
      CloudModelDirectoryPO result = new CloudModelDirectoryPO(new CloudModelDirectory[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(CloudModelFile.PROPERTY_DIR, result);
      
      return result;
   }

   public CloudModelDirectoryPO createDir()
   {
      return this.startCreate().hasDir().endCreate();
   }

   public CloudModelFilePO hasDir(CloudModelDirectoryPO tgt)
   {
      return hasLinkConstraint(tgt, CloudModelFile.PROPERTY_DIR);
   }

   public CloudModelFilePO createDir(CloudModelDirectoryPO tgt)
   {
      return this.startCreate().hasDir(tgt).endCreate();
   }

   public CloudModelDirectory getDir()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((CloudModelFile) this.getCurrentMatch()).getDir();
      }
      return null;
   }

}
