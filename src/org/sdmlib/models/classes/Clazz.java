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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import javax.swing.text.DateFormatter;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.scenarios.ScenarioManager;

public class Clazz
{
   public static final LinkedHashSet<Clazz> EMPTY_SET = new LinkedHashSet<Clazz>();

   public static Clazz clazz = null;
   
   public Clazz()
   {
      clazz = this;
      setClassModel(ClassModel.classModel);
      ClassModel.classModel.addToClasses(this);
   }
   
   private String name = null; 
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public Clazz withName(String name)
   {
      setName(name);
      return this;
   }
   
   private ClassModel classModel = null;
   
   private StringBuilder fileBody;

   private boolean fileHasChanged;
   
   public StringBuilder getFileBody()
   {
      return fileBody;
   }
   
   public ClassModel getClassModel()
   {
      return classModel;
   }
   
   public void setClassModel(ClassModel classModel)
   {
      this.classModel = classModel;
   }
   
   public Clazz withClassModel(ClassModel classModel)
   {
      setClassModel(classModel);
      return this;
   }

   public Clazz generate(String rootDir)
   {
      getOrCreateParser(rootDir);
                 
      insertLicense(parser);
      
      for (Attribute attr : this.getAttributes())
      {
         attr.generate(rootDir, false);
      }
      
      printFile(fileHasChanged);
      
      return this;
   }

   public void printFile(boolean really)
   {
      if (really)
      {
         CGUtil.printFile(javaFile, fileBody.toString());
      }
   }

   private void insertLicense(Parser parser)
   {
      // file should start with head comment
      int pos = fileBody.indexOf("/*");
      if (pos < 0 || pos > 20)
      {
         // insert MIT License otherwise. 
         fileHasChanged = true;
         StringBuilder text = new StringBuilder(
               "/*\n" +
               "   Copyright (c) <year> <developer> \n" +
               "   \n" +
               "   Permission is hereby granted, free of charge, to any person obtaining a copy of this software \n" +
               "   and associated documentation files (the \"Software\"), to deal in the Software without restriction, \n" +
               "   including without limitation the rights to use, copy, modify, merge, publish, distribute, \n" +
               "   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is \n" +
               "   furnished to do so, subject to the following conditions: \n" +
               "   \n" +
               "   The above copyright notice and this permission notice shall be included in all copies or \n" +
               "   substantial portions of the Software. \n" +
               "   \n" +
               "   The Software shall be used for Good, not Evil. \n" +
               "   \n" +
               "   THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING \n" +
               "   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND \n" +
               "   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, \n" +
               "   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, \n" +
               "   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. \n" +
               " */\n" +
               "   \n");
         
         String year = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
         CGUtil.replace(text, "<year>", year);
         
         CGUtil.replace(text, "<developer>", System.getProperty("user.name"));
         
         fileBody.replace(0, 0, text.toString());
      }
      
   }
   
   private LinkedHashSet<Attribute> attributes = null;
   
   public LinkedHashSet<Attribute> getAttributes()
   {
      if (attributes == null)
      {
         return Attribute.EMPTY_SET;
      }
      
      return attributes;
   }
   

   public Clazz withAttributes(Attribute value)
   {
      if (attributes == null)
      {
         attributes = new LinkedHashSet<Attribute>();
      }
      
      attributes.add(value);
      return this;
   }

   Parser parser = null;

   private File javaFile;
   
   public File getJavaFile()
   {
      return javaFile;
   }
   
   public Parser getOrCreateParser(String rootDir)
   {
      if (parser == null)
      {
         // try to find existing file
         int pos = name.lastIndexOf('.');
         
         String packageName = name.substring(0, pos);
         String fileName = name;
         
         String className = name.substring(pos+1);
         
         fileName = fileName.replaceAll("\\.", "/");
         
         fileName = rootDir + "/" + fileName + ".java";
         
         javaFile = new File(fileName);
         
         // found old one?
         if (javaFile.exists())
         {
            fileBody = CGUtil.readFile(javaFile);
         }
         else
         {
            fileBody = new StringBuilder();

            StringBuilder text = new StringBuilder(
                  "package packageName;\n" +
                  "\n" +
                  "public class className\n" +
                  "{\n" +
                  "}\n");
            
            CGUtil.replace(text, "className", className);        
            CGUtil.replace(text, "packageName", packageName);
            
            fileBody.append(text.toString());
         }
         
         parser = new Parser()
         .withFileBody(fileBody);

      }
      
      return parser;
   }

   public boolean isFileHasChanged()
   {
      return fileHasChanged;
   }
   
   public void setFileHasChanged(boolean fileHasChanged)
   {
      this.fileHasChanged = fileHasChanged;
   }

}
