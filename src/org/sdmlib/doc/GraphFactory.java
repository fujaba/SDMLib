package org.sdmlib.doc;

import java.io.File;
import java.net.MalformedURLException;
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
      this.adapters.add(new Javascript());
      this.adapters.add(new GraphViz());
      
      generate(".");
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
      ArrayList<URL> plugins=new ArrayList<URL>();
      for(File item : dir.listFiles()){
         if(item.getName().toLowerCase().endsWith(".jar")){
            try
            {
               
               plugins.add(new URL("file", "", item.getName()));
            }
            catch (MalformedURLException e)
            {
               e.printStackTrace();
            }
         }
      }
      loadPlugins(plugins);
   }
   
   
   public void loadPlugins(List<URL> plugins) {
      try {
         URLClassLoader ucl = new URLClassLoader(plugins.toArray(new URL[plugins.size()]), this.getClass().getClassLoader());

         for (URL plugin : plugins) {
            String name = plugin.getPath();
            if (name.indexOf(".")>0) {
               name = name.substring(0, name.indexOf("."));
            }
            if (name.indexOf("_")>0) {
               name = name.substring(0, name.indexOf("_"));
            }
            Class<?> c = ucl.loadClass("org.sdmlib.doc." + name);
            Object p =c.newInstance();
            if( p instanceof GuiAdapter){
               this.with((GuiAdapter)p);
            }else if(p instanceof GuiFileDrawer){
               GuiFileDrawer drawer = (GuiFileDrawer) p;
               ArrayList<GuiAdapter> adapters = getAdapters();
               for(GuiAdapter item : adapters){
                  if(item.getName().equalsIgnoreCase(name)){
                     item.withDrawer(drawer);
                     drawer.withPlugin(new File("."), plugin.getPath());
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
