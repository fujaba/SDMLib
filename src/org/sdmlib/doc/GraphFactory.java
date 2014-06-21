package org.sdmlib.doc;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.sdmlib.doc.GraphVizAdapter.GraphViz;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;

public class GraphFactory
{
   public static GraphFactory instance;
   private ArrayList<GuiAdapter> adapters=new ArrayList<GuiAdapter>();
   
   public static GraphFactory getInstance(){
      if(instance==null){
         instance = new GraphFactory();
      }
      return instance;
   }
   
   private GraphFactory(){
      // Add Defaults
      this.adapters.add(new GraphViz());
      this.adapters.add(new Javascript());
   }

   public static GuiAdapter getAdapter()
   {
      GraphFactory factory = getInstance();
      ArrayList<GuiAdapter> adapters = factory.getAdapters();
      if(adapters.size()>0){
         return adapters.get(0);
      }
      return null;
   }
   
   public static GuiAdapter getAdapter(String name)
   {
      GraphFactory factory = getInstance();
      ArrayList<GuiAdapter> adapters = factory.getAdapters();
      for(GuiAdapter item : adapters){
         if(item.getName().equalsIgnoreCase(name)){
            return item;
         }
      }
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
               Class<?> c = ucl.loadClass("org.sdmlib.doc." + plugin);
               Object p =c.newInstance();
               if( p instanceof GuiAdapter){
                  this.with((GuiAdapter)p);
               }else{
                  ArrayList<GuiAdapter> adapters = getAdapters();
                  for(GuiAdapter item : adapters){
                     if(item.getName().equalsIgnoreCase(plugin)){
                        item.withDrawer((GuiFileDrawer) p);
                     }
                  }
               }
            }
         }
         ucl.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public ArrayList<GuiAdapter> getAdapters()
   {
      return adapters;
   }

   public GraphFactory with(GuiAdapter... adapters)
   {
      if(adapters==null){
         return this;
      }
      for(GuiAdapter item : adapters){
         if(item!=null){
            this.adapters.add(item);
         }
      }
      return this;
   }

}
