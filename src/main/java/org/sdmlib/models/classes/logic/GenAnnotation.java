package org.sdmlib.models.classes.logic;

import java.util.ArrayList;

import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;

public class GenAnnotation extends Generator<Annotation>
{
   public GenAnnotation generate(String rootDir, String helperDir) 
   {
      if(model.getClazz() != null)
         return generate(model.getClazz(), rootDir, helperDir);
      if(model.getMethod() != null)
         return generate(model.getMethod(), rootDir, helperDir);
      return this;
   }

   private GenAnnotation generate(Method method, String rootDir, String helperDir)
   {
      Parser parser = getGenerator(method.getClazz()).getOrCreateParser(rootDir);
      parser.parse();
      
      ArrayList<SymTabEntry> tabEntries = parser.getSymTabEntriesFor(method.getSignature(false));
      SymTabEntry symTabEntry = tabEntries.get(0);
      
      if (symTabEntry == null || symTabEntry.getAnnotations().contains(model.getName()))
         return null;
      
      int startPos = symTabEntry.getStartPos();
      
      StringBuilder sb = new StringBuilder();
      sb.append("@");
      sb.append(model.getName());
      sb.append("\n");
      
//      CGUtil 
      
      parser.insert(startPos, sb.toString());
      parser.parse();
      return this;
   }

   private GenAnnotation generate(Clazz clazz, String rootDir, String helperDir)
   {
      Parser parser = getGenerator(clazz).getOrCreateParser(rootDir);
      parser.parse();
      
      ArrayList<SymTabEntry> tabEntries = parser.getSymTabEntriesFor(clazz.getFullName());
      SymTabEntry symTabEntry = tabEntries.get(0);
      
      if (symTabEntry == null|| symTabEntry.getAnnotations().contains(model.getName()))
         return null;
      
      int startPos = symTabEntry.getStartPos();
      
      StringBuilder sb = new StringBuilder();
      sb.append("@");
      sb.append(model.getName());
      sb.append("\n");
      
//      CGUtil 
      
      parser.insert(startPos, sb.toString());
      parser.parse();
      return this;
   }
   
}
