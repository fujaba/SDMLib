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
import java.io.InputStream;
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
   
   public String toImg(String imgName, org.sdmlib.serialization.json.JsonArray objects)
   {
      String link = "<img src='<imagename>'>\n";
      link = link.replaceFirst("<imagename>", imgName + ".svg");
      
      // generate dot file
      String fileText = "graph ObjectDiagram {\n" +
      		"   node [shape = none, fontsize = 10];\n" +
      		"   edge [fontsize = 10];\n\n" +
      		"<nodes>\n" +
      		"<edges>" +
      		"}\n";
      
      // collect all edges
      TreeMap<String, EdgeLabels> edgeMap = new TreeMap<String, EdgeLabels>();
      
      // collect all jsonIds
      TreeSet<String> knownIds = new TreeSet<String>();
      for (int i = 0; i < objects.length(); i++)
      {
         JsonObject jsonObject = objects.getJSONObject(i);
         String jsonId = jsonObject.getString(JsonIdMap.JSON_ID);
         knownIds.add(jsonId);
      }
      
      // list of nodes
      StringBuilder nodeBuilder = new StringBuilder();
      for (int i = 0; i < objects.length(); i++)
      {
         JsonObject jsonObject = objects.getJSONObject(i);
         String nodeLine = "<id> [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td> <u><id> :<classname></u></td></tr></table>>];\n";
        
         String jsonId = lastPartLow(jsonObject.getString(JsonIdMap.JSON_ID));
         nodeLine = nodeLine.replaceAll("<id>", jsonId);
         
         String className = lastPart(jsonObject.getString(JsonIdMap.CLASS));
         nodeLine = nodeLine.replaceAll("<classname>", className);
         
         // go through attributes
         
         jsonObject = jsonObject.getJSONObject(JsonIdMap.JSON_PROPS);
         
         String attrText = "<tr><td><table border='0' cellborder='0' cellspacing='0'></table></td></tr>";
         boolean addAttrText = false;
         for (Iterator iter = jsonObject.keys(); iter.hasNext();)
         {
            String key = (String) iter.next();
            
            if (! JsonIdMap.JSON_ID.equals(key) && ! JsonIdMap.CLASS.equals(key))
            {
               Object value = jsonObject.get(key);
               
               if (value instanceof String)
               {
                  if (knownIds.contains(value))
                  {
                     addToEdges(edgeMap, jsonId, lastPartLow(value.toString()), key);
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
               else if (value instanceof Integer || value instanceof Double)
               {
                  addAttrText = true;
                  
                  // add attribute line
                  String attrLine = "<tr><td><key> = <value></td></tr>";
                  attrLine = attrLine.replaceFirst("<key>", key);
                  attrLine = attrLine.replaceFirst("<value>", value.toString());
                  
                  attrText = attrText.replaceFirst("</table>", attrLine + "</table>");
               }
               else if (value instanceof JsonArray)
               {
                  // array of links
                  JsonArray jsonArray = (JsonArray) value;
                  for (int j = 0; j < jsonArray.length(); j++)
                  {
                     String tgtId = lastPartLow(jsonArray.getString(j));
                     addToEdges(edgeMap, jsonId, tgtId, key);
                  }
               }
            }
         }
         
         if (addAttrText)
         {
            nodeLine = nodeLine.replaceFirst("</table>", attrText + "</table>");                  
         }
         
         nodeBuilder.append(nodeLine);
      }
      
      fileText = fileText.replaceFirst("<nodes>", nodeBuilder.toString());
      
      // now generate edges from edgeMap
      StringBuilder edgeBuilder = new StringBuilder();
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
      
      fileText = fileText.replaceFirst("<edges>", edgeBuilder.toString());
      
      File docDir = new File("doc");
      docDir.mkdir();
      
      BufferedWriter out;
      try
      {
         writeToFile(imgName, fileText);
         
         // generate image
         String command = "tools/makeimage.bat " + imgName;

         Process child = Runtime.getRuntime().exec(command);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // generate link
      
      return link;
   }
   
   private void addToEdges(TreeMap<String, EdgeLabels> edgeMap, String srcId,
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

   class EdgeLabels
   {
      String headlabel = "";
      String taillabel = "";
   }

   private void writeToFile(String imgName, String fileText) throws IOException
   {
      BufferedWriter out;
      out = new BufferedWriter(new FileWriter("doc\\" + imgName + ".dot"));

      out.write(fileText);
      out.close();
   }
   
   private String lastPartLow(String dottedName)
   {
      return lastPart(dottedName).toLowerCase();
   }
   
   private String lastPart(String dottedName)
   {
      int dotPos = dottedName.lastIndexOf('.');
      dottedName = dottedName.substring(dotPos+1);
      return dottedName;
   }
}
