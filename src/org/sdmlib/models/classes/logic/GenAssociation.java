package org.sdmlib.models.classes.logic;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;

public class GenAssociation
{
   private Association model;

   public void setModel(Association value)
   {
      if (this.model != value)
      {
         Association oldValue = this.model;
         if (this.model != null)
         {
            this.model = null;
            oldValue.setGenerator(null);
         }
         this.model = value;
         if (value != null)
         {
            value.setGenerator(this);
         }
      }
   }

   public GenAssociation generate(String rootDir, String helperDir)
   {
      generate(rootDir, helperDir, true);
      
      return this;
   }
   
   public GenAssociation generate(String rootDir, String helperDir, boolean doGenerate)
   {
      // open source class and get or insert role implementation
      model.getSource().generate(rootDir, helperDir, model.getTarget(), doGenerate);
      
      // also for subclasses
      for (Clazz kidClass : model.getSource().getClazz().getKidClassesClosure())
      {
         boolean needsImplementation = kidClass.getInterfaces().contains(model.getSource().getClazz());
         model.getSource().generate(kidClass, rootDir, helperDir, model.getTarget(), doGenerate, ! needsImplementation);
      }

      // open target class and get or insert role implementation
      model.getTarget().generate(rootDir, helperDir, model.getSource(), doGenerate);

      // also for subclasses
      for (Clazz kidClass : model.getTarget().getClazz()
            .getKidClassesClosure())
      {
         boolean needsImplementation = kidClass.getInterfaces().contains(model.getTarget().getClazz());
         model.getTarget().generate(kidClass, rootDir, helperDir, model.getSource(), doGenerate, ! needsImplementation);
      }
      
      return this;
   } 

}
