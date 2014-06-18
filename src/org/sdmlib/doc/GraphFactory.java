package org.sdmlib.doc;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class GraphFactory
{
   public static GraphFactory instance;
   
   public static GraphFactory getInstance(){
      if(instance==null){
         instance = new GraphFactory();
      }
      return instance;
   }
   
   private GraphFactory(){
      
   }

   public static GuiAdapter getAdapter()
   {
      return null;
   }
   
   public static GuiAdapter getAdapter(String name)
   {
      return null;
   }
   
   
   public void generate(String path){
      File dir  = new File(path);
      ArrayList<String> plugins=new ArrayList<String>();
      for(File item : dir.listFiles()){
         if(item.getName().toLowerCase().endsWith(".jar")){
            plugins.add(item.getName());
         }
      }
      loadPlugins(path, plugins);
   }
   
   
   public void loadPlugins(String path, List<String> plugins) {
      try {
         URL classUrl = new File(path).toURI()
               .toURL();
         URL[] classUrls = { classUrl };
         URLClassLoader ucl = new URLClassLoader(classUrls);

         for (String plugin : plugins) {
            if (plugin.startsWith("path")) {
               classUrl = new File(plugin).toURI().toURL();
            } else {
               Class<?> c = ucl.loadClass("de.uks.dp.plugins." + plugin);
               GuiAdapter p = (GuiAdapter) c.newInstance();

               this.add(p);
            }

         }

         ucl.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void add(GuiAdapter p)
   {
      // TODO Auto-generated method stub
      
   }

}
