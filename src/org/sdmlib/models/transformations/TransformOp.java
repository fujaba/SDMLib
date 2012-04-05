/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.models.transformations;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;

public class TransformOp implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_OPOBJECTS.equalsIgnoreCase(attrName))
      {
         return getOpObjects();
      }

      if (PROPERTY_STATEMENTS.equalsIgnoreCase(attrName))
      {
         return getStatements();
      }

      if (PROPERTY_LINKOPS.equalsIgnoreCase(attrName))
      {
         return getLinkOps();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_OPOBJECTS.equalsIgnoreCase(attrName))
      {
         addToOpObjects((OperationObject) value);
         return true;
      }
      
      if ((PROPERTY_OPOBJECTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOpObjects((OperationObject) value);
         return true;
      }

      if (PROPERTY_STATEMENTS.equalsIgnoreCase(attrName))
      {
         addToStatements((Statement) value);
         return true;
      }
      
      if ((PROPERTY_STATEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStatements((Statement) value);
         return true;
      }

      if (PROPERTY_LINKOPS.equalsIgnoreCase(attrName))
      {
         addToLinkOps((LinkOp) value);
         return true;
      }
      
      if ((PROPERTY_LINKOPS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLinkOps((LinkOp) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromOpObjects();
      removeAllFromStatements();
      removeAllFromLinkOps();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public TransformOp withName(String value)
   {
      setName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TransformOp ----------------------------------- OperationObject
    *              transformOp                   opObjects
    * </pre>
    */
   
   public static final String PROPERTY_OPOBJECTS = "opObjects";
   
   private LinkedHashSet<OperationObject> opObjects = null;
   
   public LinkedHashSet<OperationObject> getOpObjects()
   {
      if (this.opObjects == null)
      {
         return OperationObject.EMPTY_SET;
      }
   
      return this.opObjects;
   }
   
   public boolean addToOpObjects(OperationObject value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.opObjects == null)
         {
            this.opObjects = new LinkedHashSet<OperationObject>();
         }
         
         changed = this.opObjects.add (value);
         
         if (changed)
         {
            value.withTransformOp(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OPOBJECTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromOpObjects(OperationObject value)
   {
      boolean changed = false;
      
      if ((this.opObjects != null) && (value != null))
      {
         changed = this.opObjects.remove (value);
         
         if (changed)
         {
            value.setTransformOp(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OPOBJECTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public TransformOp withOpObjects(OperationObject value)
   {
      addToOpObjects(value);
      return this;
   } 
   
   public TransformOp withoutOpObjects(OperationObject value)
   {
      removeFromOpObjects(value);
      return this;
   } 
   
   public void removeAllFromOpObjects()
   {
      LinkedHashSet<OperationObject> tmpSet = new LinkedHashSet<OperationObject>(this.getOpObjects());
   
      for (OperationObject value : tmpSet)
      {
         this.removeFromOpObjects(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TransformOp ----------------------------------- Statement
    *              transformOp                   statements
    * </pre>
    */
   
   public static final String PROPERTY_STATEMENTS = "statements";
   
   private LinkedHashSet<Statement> statements = null;
   
   public LinkedHashSet<Statement> getStatements()
   {
      if (this.statements == null)
      {
         return Statement.EMPTY_SET;
      }
   
      return this.statements;
   }
   
   public boolean addToStatements(Statement value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.statements == null)
         {
            this.statements = new LinkedHashSet<Statement>();
         }
         
         changed = this.statements.add (value);
         
         if (changed)
         {
            value.withTransformOp(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATEMENTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromStatements(Statement value)
   {
      boolean changed = false;
      
      if ((this.statements != null) && (value != null))
      {
         changed = this.statements.remove (value);
         
         if (changed)
         {
            value.setTransformOp(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATEMENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public TransformOp withStatements(Statement value)
   {
      addToStatements(value);
      return this;
   } 
   
   public TransformOp withoutStatements(Statement value)
   {
      removeFromStatements(value);
      return this;
   } 
   
   public void removeAllFromStatements()
   {
      LinkedHashSet<Statement> tmpSet = new LinkedHashSet<Statement>(this.getStatements());
   
      for (Statement value : tmpSet)
      {
         this.removeFromStatements(value);
      }
   }


   public void updateFromSourceCode(String string)
   {
      // TODO Auto-generated method stub
      
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TransformOp ----------------------------------- LinkOp
    *              transformOp                   linkOps
    * </pre>
    */
   
   public static final String PROPERTY_LINKOPS = "linkOps";
   
   private LinkedHashSet<LinkOp> linkOps = null;
   
   public LinkedHashSet<LinkOp> getLinkOps()
   {
      if (this.linkOps == null)
      {
         return LinkOp.EMPTY_SET;
      }
   
      return this.linkOps;
   }
   
   public boolean addToLinkOps(LinkOp value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.linkOps == null)
         {
            this.linkOps = new LinkedHashSet<LinkOp>();
         }
         
         changed = this.linkOps.add (value);
         
         if (changed)
         {
            value.withTransformOp(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKOPS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLinkOps(LinkOp value)
   {
      boolean changed = false;
      
      if ((this.linkOps != null) && (value != null))
      {
         changed = this.linkOps.remove (value);
         
         if (changed)
         {
            value.setTransformOp(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKOPS, value, null);
         }
      }
         
      return changed;   
   }
   
   public TransformOp withLinkOps(LinkOp value)
   {
      addToLinkOps(value);
      return this;
   } 
   
   public TransformOp withoutLinkOps(LinkOp value)
   {
      removeFromLinkOps(value);
      return this;
   } 
   
   public void removeAllFromLinkOps()
   {
      LinkedHashSet<LinkOp> tmpSet = new LinkedHashSet<LinkOp>(this.getLinkOps());
   
      for (LinkOp value : tmpSet)
      {
         this.removeFromLinkOps(value);
      }
   }


   public String dumpTransformOpDiagram(String diagName)
   {
      // generate dot file 
      StringBuilder dotFileText = new StringBuilder
            (  "\n graph TrafoOpDiagram {" +
                  "\n    node [shape = none, fontsize = 10]; " +
                  "\n    edge [fontsize = 10];" +
                  "\n    " +
                  "\n    operationObjects" +
                  "\n    " +
                  "\n    statements" +
                  "\n    " +
                  //            "\n    g1 -- p2 " +
                  //            "\n    g1 -- p3 [headlabel = \"persons\" taillabel = \"groupAccounter\"];" +
                  "\n    " +
                  "\n    links" +
                  "\n}" +
                  "\n"
                  );

      // add operationOps
      StringBuilder operationObjectsText = new StringBuilder();

      for (OperationObject opObject : this.getOpObjects())
      {
         StringBuilder opObjectText = new StringBuilder
               (  "\n    objectName [label=<<table border='borderStyle' cellborder='1' cellspacing='0'> <tr> <td><u>objectName</u></td> </tr> attrCompartment </table>>];");
         
         StringBuilder attrCompartmentText = new StringBuilder(); 

         if (opObject.getAttributeOps().size() > 0)
         {
            attrCompartmentText.append("<tr><td><table border='0' cellborder='0' cellspacing='0'> attrRows </table></td></tr>");
            StringBuilder attrRowsText = new StringBuilder();
            for (AttributeOp attrOp : opObject.getAttributeOps())
            {
               StringBuilder oneAttrOpText = new StringBuilder(
                     "<tr><td align='left'>attr</td></tr>");

               CGUtil.replaceAll(oneAttrOpText, "attr", attrOp.getText());

               attrRowsText.append(oneAttrOpText.toString());
            }
            CGUtil.replaceAll(attrCompartmentText, "attrRows",
               attrRowsText.toString());
         }
         CGUtil.replaceAll(opObjectText, 
               "objectName", opObject.getName(),
               "attrCompartment", attrCompartmentText.toString(),
               "borderStyle", opObject.getSet() ? "1" : "0");

         operationObjectsText.append(opObjectText.toString());
      }
      

      // add statements
      StringBuilder statementsText = new StringBuilder();

      for (Statement stat : this.getStatements())
      {
         StringBuilder statText = new StringBuilder
               (  "\n    statName [label=<<table border='0' cellborder='0' cellspacing='0'><tr><td>statText</td></tr></table>>];");
         
         
         CGUtil.replaceAll(statText, 
               "statName", CGUtil.encodeJavaName(stat.getText()),
               "statText", CGUtil.encodeHTML(stat.getText()));

         statementsText.append(statText.toString());
      }

      // add links
      StringBuilder allLinksText = new StringBuilder();

      // linkOps
      for (LinkOp linkOp : this.getLinkOps())
      {
         StringBuilder oneLinkText = new StringBuilder(
            "\n    source -- target [headlabel = \"headLabel\" taillabel = \"tailLabel\"];");
      
         CGUtil.replaceAll(oneLinkText, 
            "source", linkOp.getSrc().getName(),
            "target", linkOp.getTgt().getName(),
            "headLabel", linkOp.getTgtText(),
            "tailLabel", linkOp.getSrcText());
         
         allLinksText.append(oneLinkText.toString());
      }
      
      // stat sequence links
      for (Statement stat : this.getStatements())
      {
         if (stat.getNext() != null)
         {
            StringBuilder oneLinkText = new StringBuilder(
                  "\n    source -- target [style = \"dotted\"];");
            
            CGUtil.replaceAll(oneLinkText, 
               "source", CGUtil.encodeJavaName(stat.getText()),
               "target", CGUtil.encodeJavaName(stat.getNext().getText()));
            allLinksText.append(oneLinkText.toString());
         }
         
         // and stat -- op links
         for(OperationObject targetOp : stat.getOperationObjects())
         {
            StringBuilder oneLinkText = new StringBuilder(
                  "\n    source -- target [style = \"dotted\"];");
            
            CGUtil.replaceAll(oneLinkText, 
               "target", CGUtil.encodeJavaName(stat.getText()),
               "source", targetOp.getName());
            allLinksText.append(oneLinkText.toString());
         }
      }

      CGUtil.replaceAll(dotFileText, 
            "operationObjects", operationObjectsText.toString(),
            "statements", statementsText.toString(),
            "links", allLinksText.toString());

      // write dot file 
      File docDir = new File("doc");
      docDir.mkdir();


      File dotFile = new File("doc/" + diagName + ".dot");
      ScenarioManager.get().printFile(dotFile, dotFileText.toString());

      // generate image
      String command = "";
      
      if ((System.getProperty("os.name").toLowerCase()).contains("mac")) 
      {
         command = "../SDMLib/tools/Graphviz/osx_lion/makeimage.command " + diagName;
      } 
      else 
      {
         command = "../SDMLib/tools/makeimage.bat " + diagName;
      }
      try 
      {
         Runtime.getRuntime().exec(command);
      } 
      catch (IOException e) 
      {
         e.printStackTrace();
      }

      return diagName + ".svg";   }
}

