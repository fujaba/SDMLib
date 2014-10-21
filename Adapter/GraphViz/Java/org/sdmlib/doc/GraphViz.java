package org.sdmlib.doc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;

public class GraphViz implements GuiFileDrawer
{
   @Override
   public GraphViz withPlugin(File path, String pluginName)
   {
      try
      {
         unpack(pluginName);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return this;
   }

   
   public void unpack(String pluginName) throws IOException{
      JarFile jar = new JarFile(pluginName);
      Enumeration<JarEntry> enumEntries = jar.entries();
      int count = jar.size();
      int i=0;
      while (enumEntries.hasMoreElements()) {
    	  java.util.jar.JarEntry file = (java.util.jar.JarEntry) enumEntries.nextElement();
    	  System.out.println("Unpack "+file.getName()+": "+(++i)+" / "+count);

          if(file.getName().startsWith("Adapter\\") 
        		  || file.getName().startsWith("Adapter/")
        		  || file.getName().startsWith("Drawer\\")
        		  || file.getName().startsWith("Drawer/")
        		  ){
        	  continue;
          }
          if(file.getName().startsWith("META-INF") || file.getName().startsWith("org")){
             continue;
          }
          java.io.File f = new java.io.File(file.getName());
          if (file.isDirectory()) { // if its a directory, create it
        	  if(file.getName().startsWith("Adapter")){
        		  continue;
        	  }
        	  if(file.getName().startsWith("Drawer")){
        		  continue;
        	  }
              f.mkdir();
              continue;
          }
          if(f.exists()) {
        	  continue;
          }
          java.io.InputStream is = jar.getInputStream(file); // get the input stream
          java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
          while (is.available() > 0) {  // write contents of 'is' to 'fos'
              fos.write(is.read());
          }
          fos.close();
          is.close();
      }
      jar.close();
   }
   public GraphViz(){
      
   }
   
   private static void writeToFile(String imgName, String fileText)
         throws IOException
   {
      File docDir = new File("doc");

      docDir.mkdir();

      int lastSlash = imgName.lastIndexOf('/');
      if (lastSlash >= 0)
      {
         String subDir = imgName.substring(0, lastSlash);
         docDir = new File("doc/" + subDir);

         docDir.mkdirs();
      }

      BufferedWriter out;
      out = new BufferedWriter(new FileWriter("doc/" + imgName + ".dot"));

      out.write(fileText);
      out.close();
   }

   @Override
   public boolean drawImg(String imgName, String context)
   {
      String[] command = null;

      File docDir = new File("doc");

      docDir.mkdir();

      int lastSlash = imgName.lastIndexOf('/');
      if (lastSlash >= 0)
      {
         String subDir = imgName.substring(0, lastSlash);
         docDir = new File("doc/" + subDir);

         docDir.mkdirs();
      }

//      URL absolutePath = GraphViz.class.getResource("../../..");
//      if (absolutePath == null)
//      {
//         return false;
//      }
      if ((System.getProperty("os.name").toLowerCase()).contains("windows"))
      {
    	  File root = new File(".");
         String rootPath = root.getPath().replace("file:\\", "");
         String makeimageFile = rootPath + "\\win32\\makeimage.bat";
         command = new String[]
         { makeimageFile, "doc\\" + imgName, rootPath };
      }
//      else if ((System.getProperty("os.name").toLowerCase()).contains("mac"))
//      {
//    	  File root = new File(".");
//         String rootPath = root.getPath().replace("file:", "");
//         String makeimageFile = rootPath
//            + "/osx_lion/makeimage.command";
//
//         command = new String[]
//         { makeimageFile, imgName, rootPath };
//      }
      else
      { // let's assume it's linux'ish (works also for mac)
         command = new String[]
         { "dot", "doc/" + imgName + ".dot", "-Tsvg", "-o",
               "doc/" + imgName + ".svg" };
      }
      try
      {
         writeToFile(imgName, context);
         ProcessBuilder processBuilder = new ProcessBuilder(command);
         processBuilder.redirectErrorStream(true);
         Process child = processBuilder.start();
         InputStream inputStream = child.getInputStream();
         BufferedReader buf = new BufferedReader(new InputStreamReader(
               inputStream));
         String line = null;
         while ((line = buf.readLine()) != null)
         {
            System.out.println(line);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return true;
   }


   @Override
   public String getVersion()
   {
      String result = GraphViz.class.getPackage()
            .getImplementationVersion();
      if (result == null) {
         return "0.42.DEBUG";
      }
      return result;
   }
}
