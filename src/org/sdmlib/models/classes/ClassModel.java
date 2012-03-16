/*
   Copyright (c) 2012 Albert Zündorf

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib.models.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.utils.StrUtil;

public class ClassModel
{
   public static ClassModel classModel = null;
   
   public ClassModel()
   {
      classModel = this;
   }

   public ClassModel generate(String rootDir)
   {
      for (Clazz clazz : getClasses())
      {
         clazz.generate(rootDir);
      }
      
      for (Association assoc : getAssociations())
      {
         assoc.generate(rootDir);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CLASSES = "classes";
   
   private LinkedHashSet<Clazz> classes;
   
   public LinkedHashSet<Clazz> getClasses()
   {
      if (classes == null)
      {
         return Clazz.EMPTY_SET;
      }
      return this.classes;
   }
   
   public ClassModel withClasses(Clazz value)
   {
      addToClasses(value);
      return this;
   }

   public void addToClasses(Clazz value)
   {
      if (this.classes == null)
      {
         this.classes = new LinkedHashSet<Clazz>();
      }
      
      this.classes.add(value);
   }

   public String dumpClassDiag(String diagName)
   {
      // generate dot file 
      StringBuilder dotFileText = new StringBuilder
         (  "\n graph ClassDiagram {" +
            "\n    node [shape = none, fontsize = 10]; " +
            "\n    edge [fontsize = 10];" +
            "\n    " +
            "\n    modelClasses" +
            "\n    " +
//            "\n    g1 -- p2 " +
//            "\n    g1 -- p3 [headlabel = \"persons\" taillabel = \"groupAccounter\"];" +
            "\n    " +
            "\n    modelAssocs" +
            "\n}" +
            "\n"
            );
      
      // add classes
      StringBuilder modelClassesText = new StringBuilder();

      for (Clazz clazz : this.getClasses())
      {
         StringBuilder modelClassText = new StringBuilder
            (  "\n    className [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td>className</td> </tr> attrCompartment </table>>];");

         CGUtil.replaceAll(modelClassText, 
            "className", CGUtil.shortClassNameHTMLEncoded(clazz.getName()),
            "attrCompartment", dumpAttributes(clazz));

         modelClassesText.append(modelClassText.toString());
      }
            
      // add associations
      StringBuilder allAssocsText = new StringBuilder();
      
      for (Association assoc : getAssociations())
      {
         StringBuilder oneAssocText = new StringBuilder
            (  "\n    sourceClass -- targetClass [headlabel = \"targetRole\" taillabel = \"sourceRole\"];" );
         
         CGUtil.replaceAll(oneAssocText, 
            "sourceClass", CGUtil.shortClassName(assoc.getSource().getClazz().getName()),
            "targetClass", CGUtil.shortClassName(assoc.getTarget().getClazz().getName()),
            "sourceRole", assoc.getSource().getName(),
            "targetRole", assoc.getTarget().getName());
         
         allAssocsText.append(oneAssocText.toString());
      }

      CGUtil.replaceAll(dotFileText, 
         "modelClasses", modelClassesText.toString(),
         "modelAssocs", allAssocsText.toString());
      
      // write dot file 
      File docDir = new File("doc");
      docDir.mkdir();
      
      BufferedWriter out;
      try
      {
         File dotFile = new File("doc/" + diagName + ".dot");
         ScenarioManager.get().printFile(dotFile, dotFileText.toString());
        
         // generate image
         String command = "..\\SDMLib\\tools\\makeimage.bat " + diagName;

         Process child = Runtime.getRuntime().exec(command);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return diagName + ".svg";
   }

   private String dumpAttributes(Clazz clazz)
   {
      StringBuilder allAttrsText = new StringBuilder
         ( "<tr><td><table border='0' cellborder='0' cellspacing='0'> attrRow </table></td></tr>");

      if (clazz.getAttributes().size() > 0)
      {
         for (Attribute attr : clazz.getAttributes())
         {
            StringBuilder oneAttrText = new StringBuilder(
               "<tr><td>attrDecl</td></tr>");

            CGUtil.replaceAll(oneAttrText, "attrDecl", attr.getName() + " :" +
               CGUtil.shortClassNameHTMLEncoded(attr.getType()));

            CGUtil.replaceAll(allAttrsText, "attrRow",
               oneAttrText.append(" attrRow").toString());
         }

         CGUtil.replaceAll(allAttrsText, "attrRow", "");
      }
      else
      {
         CGUtil.replaceAll(allAttrsText, "attrRow", "<tr><td> </td></tr>");
      }
      
      return allAttrsText.toString();
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ClassModel ----------------------------------- Association
    *              model                   associations
    * </pre>
    */
   
   public static final String PROPERTY_ASSOCIATIONS = "associations";
   
   private LinkedHashSet<Association> associations = null;
   
   public LinkedHashSet<Association> getAssociations()
   {
      if (this.associations == null)
      {
         return Association.EMPTY_SET;
      }
   
      return this.associations;
   }
   
   public boolean addToAssociations(Association value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.associations == null)
         {
            this.associations = new LinkedHashSet<Association>();
         }
         
         changed = this.associations.add (value);
         
         if (changed)
         {
            value.withModel(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromAssociations(Association value)
   {
      boolean changed = false;
      
      if ((this.associations != null) && (value != null))
      {
         changed = this.associations.remove (value);
         
         if (changed)
         {
            value.setModel(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSOCIATIONS, null, value);
         }
      }
         
      return changed;   
   }
   
   public ClassModel withAssociations(Association value)
   {
      addToAssociations(value);
      return this;
   } 
   
   public ClassModel withoutAssociations(Association value)
   {
      removeFromAssociations(value);
      return this;
   } 
   
   public void removeAllFromAssociations()
   {
      LinkedHashSet<Association> tmpSet = new LinkedHashSet<Association>(this.getAssociations());
   
      for (Association value : tmpSet)
      {
         this.removeFromAssociations(value);
      }
   }

   public void updateFromCode(String string, String string2)
   {
      // find java files
      
      // parse each java file
      for (Clazz clazz : getClasses())
      {
         // get list of members
         Parser parser = clazz.getOrCreateParser("examples");
         
         parser.indexOf(Parser.CLASS_END);
         
         LinkedHashMap<String, SymTabEntry> symTab = parser.getSymTab();
         
         for (String memberName : symTab.keySet())
         {
            SymTabEntry entry = symTab.get(memberName);
            // do something with it.
         }
      }
      
      // add model creation code at invocation place, if not yet there
      
      
   }

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }
}



