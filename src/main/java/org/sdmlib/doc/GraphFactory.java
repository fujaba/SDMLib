package org.sdmlib.doc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;

public class GraphFactory
{
   public static GraphFactory instance;
   private ArrayList<GuiAdapter> adapters=new ArrayList<GuiAdapter>();
   private String defaultName = Javascript.NAME;
   
   public static GraphFactory getInstance(){
      if(instance==null){
         instance = new GraphFactory();
      }
      return instance;
   }
   
   private GraphFactory(){
      // Add Defaults
      this.with(new Javascript());
      // this.with(new GraphViz());
      generate(".");
   }

   public static GuiAdapter getAdapter()
   {
      return getInstance().getInternAdapter(null);
   }
   
   public static GuiAdapter getAdapter(String name)
   {
      return getInstance().getInternAdapter(name);
   }
   public GuiAdapter getInternAdapter(String name){
      if(name==null){
         name = defaultName;
      }
      ArrayList<GuiAdapter> adapters = this.getAdapters();
      for(GuiAdapter item : adapters){
         if(item.getName().equalsIgnoreCase(name)){
            return item;
         }
      }
      return null;
   }
   
   public GraphFactory withAdapterName(String name){
      this.defaultName = name;
      return this;
   }
   
   
	public void generate(String path) {
		File dir = new File(path);
		String rootPath = dir.getPath();
		if (".".equals(path)) {
			rootPath = rootPath.substring(0, rootPath.length() - 1);
		}
		if (rootPath.length()>0 && !(rootPath.endsWith("\\") || rootPath.endsWith("/"))) {
			rootPath += File.separator;
		}
		ArrayList<URL> plugins = new ArrayList<URL>();
		if(dir.listFiles() != null) {
			for (File item : dir.listFiles()) {
				if (item.getName().toLowerCase().endsWith(".jar")) {
					try {
	
						plugins.add(new URL("file", "", rootPath+item.getName()));
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		loadPlugins(plugins);
		if (".".equals(path)) {
			URL resource = GraphFactory.class
					.getResource("/org/SDMLib.gwt.xml");
			if (resource == null) {
				return;
			}
			File lib = new File(resource.getPath());
			lib = lib.getParentFile().getParentFile().getParentFile();

			String libPath = lib.getPath();
			if (libPath.startsWith("file:\\")) {
				libPath = libPath.substring(6);
			}
			if (libPath.startsWith("file:/")) {
				libPath = libPath.substring(5);
			}
			if (!(libPath.endsWith(File.separator))) {
				libPath += File.separator;
			}

			if (libPath.startsWith(rootPath)) {
				generate(libPath.substring(rootPath.length()));
			}
		}
	}
   
   
   public void loadPlugins(List<URL> plugins) {
	   URLClassLoader ucl = null;
	   try {
		   ucl = new URLClassLoader(plugins.toArray(new URL[plugins.size()]), this.getClass().getClassLoader());
	   }catch (Exception e) {
	   }
	   if(ucl == null) {
		   return;
	   }

         for (URL plugin : plugins) {
            String name = plugin.getPath();
            int pos =name.lastIndexOf("/"); 
            String path=".";
            if (pos>0) {
            	path = name.substring(0, pos);
            	name = name.substring(pos+1);
            }
            pos = name.lastIndexOf("\\"); 
            if (pos>0) {
            	path = name.substring(0, pos);
            	name = name.substring(name.lastIndexOf("\\")+1);
            }
            if (name.indexOf(".")>0) {
               name = name.substring(0, name.indexOf("."));
            }
            if (name.indexOf("_")>0) {
               name = name.substring(0, name.indexOf("_"));
            }
            try {
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
	                     drawer.withPlugin(new File(path), plugin.getPath());
	                  }
	               }
	            }
            } catch (Exception e) {
                //e.printStackTrace();
             }
         }
         try {
			ucl.close();
		} catch (IOException e) {
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
