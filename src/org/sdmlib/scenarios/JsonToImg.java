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

package org.sdmlib.scenarios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;


public class JsonToImg
{
   private static JsonToImg theInstance;

   public static JsonToImg get()
   {
      if (theInstance == null)
      {
         theInstance = new JsonToImg();
      }
      
      return theInstance;
   }
   
   private JsonIdMap lastIdMap = null; 
   
   public void setLastIdMap(JsonIdMap lastIdMap)
   {
      this.lastIdMap = lastIdMap;
   }
   
   public void dumpImage(String imgName, Object root)
   {
      JsonArray jsonArray = lastIdMap.toJsonArray(root);
      
      String imgLink = JsonToImg.get().toImg(imgName, jsonArray);
      
   }
   
   public void dumpImage(String imgName, JsonIdMap newIdMap, Object root)
   {
      lastIdMap = newIdMap; 
      
      JsonArray jsonArray = newIdMap.toJsonArray(root);
      
      String imgLink = JsonToImg.get().toImg(imgName, jsonArray);
      
   }
   
   public String toImg(String imgName, org.sdmlib.serialization.json.JsonArray objects)
   {
      String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
      link = link.replaceFirst("<imagename>", imgName + ".svg");
      
      // generate dot file
      String fileText = "graph ObjectDiagram {\n" +
      		"   node [shape = none, fontsize = 10];\n" +
      		"   edge [fontsize = 10];\n\n" +
      		"<nodes>\n" +
      		"<edges>" +
      		"}\n";
      
      
      StringBuilder nodeBuilder = new StringBuilder();
      
      StringBuilder edgeBuilder = new StringBuilder();
      
      fillNodeAndEdgeBuilders(objects, nodeBuilder, edgeBuilder);
      
      fileText = fileText.replaceFirst("<nodes>", nodeBuilder.toString());
      
      fileText = fileText.replaceFirst("<edges>", edgeBuilder.toString());
      
      CallDot.callDot(imgName, fileText);

      // generate link
      
      return link;
   }


   public static void fillNodeAndEdgeBuilders(JsonArray objects, StringBuilder nodeBuilder, StringBuilder edgeBuilder)
   {
      // collect all edges
      TreeMap<String, EdgeLabels> edgeMap = new TreeMap<String, EdgeLabels>();
      
      // collect all jsonIds
      TreeSet<String> knownIds = new TreeSet<String>();
      for (int i = 0; i < objects.size(); i++)
      {
         JsonObject jsonObject = objects.getJSONObject(i);
         String jsonId = jsonObject.getString(JsonIdMap.ID);
         knownIds.add(jsonId);
      }
      
      // list of nodes
      for (int i = 0; i < objects.size(); i++)
      {
         JsonObject jsonObject = objects.getJSONObject(i);
         String nodeLine = "<id> [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td> <u><id> :<classname></u></td></tr></table>>];\n";
        
         String jsonId = lastPartLow(jsonObject.getString(JsonIdMap.ID));
         nodeLine = nodeLine.replaceAll("<id>", jsonId);
         
         String className = lastPart(jsonObject.getString(JsonIdMap.CLASS));
         nodeLine = nodeLine.replaceAll("<classname>", className);
         
         // go through attributes
         if(jsonObject.has(JsonIdMap.JSON_PROPS)){
        	 jsonObject = jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
         }else{
        	 jsonObject=new JsonObject();
         }
         
         String attrText = "<tr><td><table border='0' cellborder='0' cellspacing='0'></table></td></tr>";
         boolean addAttrText = false;
         for (Iterator<String> iter = jsonObject.keys(); iter.hasNext();)
         {
            String key = (String) iter.next();
            
            if (! JsonIdMap.ID.equals(key) && ! JsonIdMap.CLASS.equals(key))
            {
               Object value = jsonObject.get(key);
               
               if (value instanceof Number || value instanceof Enum)
               {
                  // dump without quotes 
                  addAttrText = true;
                  
                  // add attribute line
                  String attrLine = "<tr><td><key> = <value></td></tr>";
                  attrLine = attrLine.replaceFirst("<key>", key);
                  attrLine = attrLine.replaceFirst("<value>", value.toString());
                  
                  attrText = attrText.replaceFirst("</table>", attrLine + "</table>");
               }
               else if (value instanceof JsonObject)
               {
                  JsonObject tgtJsonObject = (JsonObject) value;
                  String tgtId = tgtJsonObject.getString(JsonIdMap.ID);
                  tgtId = lastPartLow(tgtId);
                  addToEdges(edgeMap, jsonId, tgtId, key);
               }
               else if (value instanceof JsonArray)
               {
                  // array of links
                  JsonArray jsonArray = (JsonArray) value;
                  for (int j = 0; j < jsonArray.size(); j++)
                  {
                     JsonObject tgtJsonObject = jsonArray.getJSONObject(j);
                     String tgtId = tgtJsonObject.getString(JsonIdMap.ID);
                     tgtId = lastPartLow(tgtId);
                     addToEdges(edgeMap, jsonId, tgtId, key);
                  }
               }
               else 
               {
                  addAttrText = true;

                  // add attribute line
                  String attrLine = "<tr><td><key> = \"<value>\"</td></tr>";
                  attrLine = attrLine.replaceFirst("<key>", key);
                  attrLine = attrLine.replaceFirst("<value>", value.toString());

                  attrText = attrText.replaceFirst("</table>", attrLine + "</table>");
               }
            }
         }
         
         if (addAttrText)
         {
            nodeLine = nodeLine.replaceFirst("</table>", attrText + "</table>");                  
         }
         
         nodeBuilder.append(nodeLine);
      }
      
      // now generate edges from edgeMap
      for (String keyPair : edgeMap.keySet())
      {
         String[] split = keyPair.split(":");
         EdgeLabels edgeLabels = edgeMap.get(keyPair);
         
         String edgeLine = "<srcId> -- <tgtId> [headlabel = \"<headlabel>\" taillabel = \"<taillabel>\"];\n";
         edgeLine = edgeLine.replaceFirst("<srcId>", split[0]);
         edgeLine = edgeLine.replaceFirst("<tgtId>", split[1]);
         edgeLine = edgeLine.replaceFirst("<headlabel>", edgeLabels.headlabel);
         String taillabel = edgeLabels.taillabel;
         if (taillabel.startsWith("_"))
         {
            taillabel = taillabel.substring(1);
         }
         edgeLine = edgeLine.replaceFirst("<taillabel>", taillabel);
         
         edgeBuilder.append(edgeLine);
      }
   }
   
   private static void addToEdges(TreeMap<String, EdgeLabels> edgeMap, String srcId,
         String tgtId, String label)
   {
      // is this edge already known?
      EdgeLabels fwdEdgeLabels = edgeMap.get(srcId + ":" + tgtId);
      if (fwdEdgeLabels != null)
      {
         // add label to the headlabel
         fwdEdgeLabels.headlabel += "_" + label;
      }
      else 
      {
         EdgeLabels bwdEdgeLabels = edgeMap.get(tgtId + ":" + srcId);
         if (bwdEdgeLabels != null)
         {
            // add label to the taillabel
            bwdEdgeLabels.taillabel += "_" + label;
         }
         else
         {
            // unknown edge, create it
            fwdEdgeLabels = new EdgeLabels();
            fwdEdgeLabels.headlabel = label;
            edgeMap.put(srcId + ":" + tgtId, fwdEdgeLabels);
         }
      }
      
   }

   static class EdgeLabels
   {
      String headlabel = "";
      String taillabel = "";
   }
   
   private static String lastPartLow(String dottedName)
   {
      return lastPart(dottedName).toLowerCase();
   }
   
   private static String lastPart(String dottedName)
   {
      int dotPos = dottedName.lastIndexOf('.');
      dottedName = dottedName.substring(dotPos+1);
      return dottedName;
   }
}
