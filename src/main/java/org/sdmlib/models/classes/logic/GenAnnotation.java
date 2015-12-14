package org.sdmlib.models.classes.logic;

import java.util.ArrayList;
import java.util.Set;

import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.GraphMember;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.list.SimpleList;

public class GenAnnotation extends Generator<Annotation>
{
   public GenAnnotation generate(String rootDir, String helperDir)
   {
      if (model.getParent() instanceof Clazz)
         return generate((Clazz)model.getParent(), rootDir, helperDir);
      if (model.getParent() instanceof Method)
         return generate((Method)model.getParent(), rootDir, helperDir);
      if (model.getParent() instanceof Attribute)
         return generate((Attribute)model.getParent(), rootDir, helperDir);
      return this;
   }

   private GenAnnotation generate(Attribute attribute, String rootDir, String helperDir)
   {
      Parser parser = getGenerator(attribute.getClazz()).getOrCreateParser(rootDir);
      parser.parse();

      // ArrayList<SymTabEntry> tabEntries =
      // parser.getSymTabEntriesFor(attribute.getName());
      return generate(parser, getStartPos(parser));
   }

   private int getStartPos(Parser parser)
   {
      return parser.indexOf(Parser.ATTRIBUTE + ":" + model.getParent().getName());
   }

   private GenAnnotation generate(Method method, String rootDir, String helperDir)
   {
      Parser parser = getGenerator(method.getClazz()).getOrCreateParser(rootDir);
      parser.parse();

      ArrayList<SymTabEntry> tabEntries = parser.getSymTabEntriesFor(method.getName(false));
      return generate(parser, getStartPos(tabEntries));
   }

   private GenAnnotation generate(Clazz clazz, String rootDir, String helperDir)
   {
      Parser parser = getGenerator(clazz).getOrCreateParser(rootDir);
      parser.parse();

      ArrayList<SymTabEntry> tabEntries = parser.getSymTabEntriesFor(clazz.getName());
      return generate(parser, getStartPos(tabEntries));
   }

   private int getStartPos(ArrayList<SymTabEntry> tabEntries)
   {
      SymTabEntry symTabEntry = null;

      if (tabEntries.size() > 0)
      {
         symTabEntry = tabEntries.get(0);
      }

      if (symTabEntry == null || annotationAlreadyInserted(symTabEntry))
         return -1;

      return symTabEntry.getStartPos();
   }

   private boolean annotationAlreadyInserted(SymTabEntry symTabEntry)
   {
      boolean inserted = false;
      if (symTabEntry.getAnnotations() != null && symTabEntry.getAnnotations().contains(model.getName()))
      {
         inserted = true;
         for (Annotation value : model.getValue())
         {
            if (!symTabEntry.getAnnotations().contains(value.toString()))
            {
               inserted = false;
               break;
            }
         }
      }

      return inserted;
   }

   private GenAnnotation generate(Parser parser, int startPos)
   {

      if (startPos == -1)
      {
         return this;
      }

      StringBuilder sb = new StringBuilder();
      if(!model.getName().startsWith("@")) {
    	  sb.append("@");
      }
      sb.append(model.getName());

      SimpleList<Annotation> values = model.getValue();
      if (values.size() == 1)
      {
         sb.append("(");
         sb.append(values.toArray(new String[values.size()])[0]);
         sb.append(")");
      }
      else if (values.size() > 1)
      {
         sb.append("({");
         for (Annotation value : values)
         {
            sb.append("\"");
            sb.append(value.toString());
            sb.append("\", ");
         }
         sb.replace(sb.length() - 2, sb.length(), "");
         sb.append("})");
      }

      sb.append("\n");
      // CGUtil
      
      String newAnnotation = sb.toString();
      int indexOf = parser.getFileBody().indexOf(newAnnotation, startPos-newAnnotation.length()-5);
      if (indexOf < 0 || indexOf > startPos)
      {
         parser.insert(startPos, newAnnotation);
         parser.parse();
      }

      return this;
   }

	@Override
	ClassModel getClazz() {
		GraphMember owner = getModel().getParent();
		if (owner instanceof Clazz)
	         return (ClassModel)((Clazz)owner).getClassModel();
	      if (owner instanceof Method)
	         return (ClassModel)((Method)owner).getClazz().getClassModel();
	      if (owner instanceof Attribute)
	    	  return (ClassModel)((Attribute)owner).getClazz().getClassModel();
	      return null;
	}

}
