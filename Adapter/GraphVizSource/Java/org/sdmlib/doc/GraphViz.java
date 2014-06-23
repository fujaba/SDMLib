package org.sdmlib.doc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;
public class GraphViz implements GuiFileDrawer
{
   private String path;

   @Override
   public GraphViz withPlugin(File path, String pluginName)
   {
      this.path = path.getAbsolutePath();
      return this;
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
      File docDir = new File("doc");

      docDir.mkdir();

      int lastSlash = imgName.lastIndexOf('/');
      if (lastSlash >= 0)
      {
         String subDir = imgName.substring(0, lastSlash);
         docDir = new File("doc/" + subDir);

         docDir.mkdirs();
      }

      String dotPath="dot";
      if ((System.getProperty("os.name").toLowerCase()).contains("windows"))
      {
         File root = new File(this.path+"/Adapter/GraphViz/Resources/win32/dot.exe");
         dotPath = root.getPath().replace("file:\\", "");
//         command = new String[] { rootPath, imgName, this.path };
      }
//      else
//      { 
//         //Must Install GraphViz
//       
//         // let's assume it's linux'ish (works also for mac)
//         command = new String[]
//         { "dot", "doc/" + imgName + ".dot", "-Tsvg", "-o",
//               "doc/" + imgName + ".svg" };
//      }
      String[] command = new String[]
            { dotPath, "doc/" + imgName + ".dot", "-Tsvg", "-o",
            "doc/" + imgName + ".svg" };
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
