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

package org.sdmlib.storyboards;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;

import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.xml.HTMLEntities;
import org.sdmlib.utils.StrUtil;


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
   
   private static String rootDir = "src";
   
   public JsonToImg withRootDir(String rootDir)
   {
      this.rootDir = rootDir; 
      return this;
   }
   
   private JsonIdMap lastIdMap = null;
   private static LinkedHashMap<String, String> iconMap; 
   
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
      return toImg(imgName, objects, false, null);
   }
   
   public String toImg(String imgName, org.sdmlib.serialization.json.JsonArray objects, boolean omitRoot, String[] aggregationRoles)
   {
      String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
      link = link.replaceFirst("<imagename>", imgName + ".svg");
      
      // generate dot file
      String fileText = "graph ObjectDiagram {\n" +
      		"   node [shape = none, fontsize = 10, fontname = \"Arial\"];\n" +
      		"   edge [fontsize = 10, fontname = \"Arial\"];\n" +
      		"   compound=true;\n" +
      		"\n" +
      		"<nodes>\n" +
      		"<edges>" +
      		"}\n";
      
      
      StringBuilder nodeBuilder = new StringBuilder();
      
      StringBuilder edgeBuilder = new StringBuilder();
      
      fillNodeAndEdgeBuilders(imgName, objects, nodeBuilder, edgeBuilder, omitRoot, aggregationRoles);
      
      fileText = fileText.replaceFirst("<nodes>", Matcher.quoteReplacement(nodeBuilder.toString()));
      
      fileText = fileText.replaceFirst("<edges>", Matcher.quoteReplacement(edgeBuilder.toString()));
      
      CallDot.callDot(imgName, fileText);

      // generate link
      
      return link;
   }


   public static void fillNodeAndEdgeBuilders(String imgName, JsonArray objects, StringBuilder nodeBuilder, StringBuilder edgeBuilder, boolean omitRoot, String... aggregationRoles)
   {
      String omittedId = "";
      // collect all edges
      LinkedHashMap<String, EdgeLabels> edgeMap = new LinkedHashMap<String, EdgeLabels>();
      
      // collect all jsonIds
      LinkedHashSet<String> knownIds = new LinkedHashSet<String>();
      LinkedHashMap<String, LinkedHashSet<String>> aggregationMap = new LinkedHashMap<String, LinkedHashSet<String>>();
      LinkedHashMap<String, JsonObject> jsonObjectMap = new LinkedHashMap<String, JsonObject>();
      
      for (int i = 0; i < objects.size(); i++)
      {
         JsonObject jsonObject = objects.getJSONObject(i);
         String jsonId = jsonObject.getString(JsonIdMap.ID);
         
         if (omitRoot  && i == 0)
         {
            omittedId = jsonId;
         }
         else
         {
            knownIds.add(jsonId);
            aggregationMap.put(jsonId, new LinkedHashSet<String>());
            jsonObjectMap.put(jsonId, jsonObject);
         }
      }

      LinkedHashSet<String> aggregationRolesSet = new LinkedHashSet<String>();
      if (aggregationRoles != null && aggregationRoles.length > 0)
      {
         aggregationRolesSet.addAll(Arrays.asList(aggregationRoles));

         // all nodes
         for (String id : (LinkedHashSet<String>) knownIds.clone())
         {
            // all properties
            JsonObject properties = jsonObjectMap.get(id);

            JsonObject props = (JsonObject) properties.get(JsonIdMap.JSON_PROPS);
            
            if (props == null)
            {
               continue;
            }
            
            for (Iterator<String> iter = props.keys(); iter.hasNext(); )
            {
               String nextProp = iter.next();

               if (aggregationRolesSet.contains(nextProp))
               {
                  // found contained elements, restructure
                  Object propValue = props.get(nextProp);

                  if (propValue instanceof JsonArray)
                  {
                     JsonArray propArray = (JsonArray) propValue;

                     for(int i = 0; i < propArray.size(); i++)
                     {
                        JsonObject containedId = propArray.getJSONObject(i);

                        String subId = containedId.getString(JsonIdMap.ID);

                        // add to parent and remove from top level ids
                        aggregationMap.get(id).add(subId);
                        knownIds.remove(subId);
                     }
                  }
                  else
                  {
                     String subId = ((JsonObject) propValue).getString(JsonIdMap.ID);
                     
                     // add to parent and remove from top level ids
                     aggregationMap.get(id).add(subId);
                     knownIds.remove(subId);
                  }
               }
            }
         }

      }


      // list of nodes
      listOfNodes(nodeBuilder, omittedId, edgeMap, knownIds, aggregationMap, jsonObjectMap, imgName);
      
      
      // now generate edges from edgeMap
      for (String keyPair : edgeMap.keySet())
      {
         String[] split = keyPair.split(":");
         
         EdgeLabels edgeLabels = edgeMap.get(keyPair);
         
         if (aggregationRolesSet.contains(edgeLabels.headlabel) || aggregationRolesSet.contains(edgeLabels.taillabel))
         {
            continue;
         }
         
         String edgeLine = "<srcId> -- <tgtId> [headlabel = \"<headlabel>\" taillabel = \"<taillabel>\"];\n";
         edgeLine = edgeLine.replaceFirst("<srcId>", Matcher.quoteReplacement(split[0]));
         edgeLine = edgeLine.replaceFirst("<tgtId>", Matcher.quoteReplacement(split[1]));
         edgeLine = edgeLine.replaceFirst("<headlabel>", Matcher.quoteReplacement(edgeLabels.headlabel));
         String taillabel = edgeLabels.taillabel;
         if (taillabel.startsWith("_"))
         {
            taillabel = taillabel.substring(1);
         }
         edgeLine = edgeLine.replaceFirst("<taillabel>", Matcher.quoteReplacement(taillabel));
         
         edgeBuilder.append(edgeLine);
      }
   }

   private static void listOfNodes(StringBuilder nodeBuilder, String omittedId,
         LinkedHashMap<String, EdgeLabels> edgeMap, Set<String> knownIds,
         LinkedHashMap<String, LinkedHashSet<String>> aggregationMap,
         LinkedHashMap<String, JsonObject> jsonObjectMap, String imgName)
   {
      for (String topId : knownIds)
      {         
         JsonObject jsonObject = jsonObjectMap.get(topId);
         String nodeLine = "<id> [label=<<table border='0' cellborder='1' cellspacing='0'> <optionalImage><tr> <td href=\"<classRef>\"> <u><id> :<classname></u></td></tr></table>>];\n";
        
         boolean isCluster = false;
         if (aggregationMap.get(topId).size() > 0)
         {
            isCluster = true;
            
            // has kids, render as cluster
            nodeLine = "subgraph cluster_<id> { \n" +
            		"   <id> [label=<<table border='0' cellborder='0' cellspacing='0'> <optionalImage><tr> <td> <u><id> :<classname></u></td></tr></table>>];\n" +
            		"   \n";
         }
         
         String imageName = jsonObject.getString(JsonIdMap.CLASS) + ".svg";
         File imageFile = new File("doc/" + imageName);
         if (imageFile.exists())
         {
            nodeLine = nodeLine.replaceFirst("<optionalImage>", "<tr><td border='0'><IMG src=\"" + imageName + "\" /></td></tr>");
         }
         else if (iconMap != null && iconMap.get(topId) != null)
         {
            nodeLine = nodeLine.replaceFirst("<optionalImage>", "<tr><td border='0'><IMG src=\"" + iconMap.get(topId) + "\" /></td></tr>");
         }
         else
         {
            nodeLine = nodeLine.replaceFirst("<optionalImage>", "");
         }
         
         String jsonId = lastPartStartLow(jsonObject.getString(JsonIdMap.ID));
         nodeLine = nodeLine.replaceAll("<id>", Matcher.quoteReplacement(jsonId));
         
         String className = jsonObject.getString(JsonIdMap.CLASS);
         String classFileName = className;
         if (classFileName.indexOf('$') >= 0)
         {
            // inner class, cut down to real class
            classFileName = classFileName.substring(0, classFileName.indexOf('$'));
         }
         classFileName = classFileName.replaceAll("\\.", "/") + ".java";
         String localFileName = "./" + rootDir + "/" + classFileName;
         if (new File(localFileName).exists())
         {
            classFileName = "../" + rootDir + "/" + classFileName;
         }
         else
         {
            classFileName = "../../SDMLib/src/" + classFileName;
         }
         nodeLine = nodeLine.replaceAll("<classRef>", Matcher.quoteReplacement(classFileName));
         className = lastPart(className);
         nodeLine = nodeLine.replaceAll("<classname>", Matcher.quoteReplacement(className));
         
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
            String key = iter.next();
            
            if (! JsonIdMap.ID.equals(key) && ! JsonIdMap.CLASS.equals(key))
            {
               Object value = jsonObject.get(key);
               
               if (value instanceof Number || value instanceof Enum)
               {
                  // dump without quotes 
                  addAttrText = true;
                  
                  // add attribute line
                  String attrLine = "<tr><td align='left'><key> = <value></td></tr>";
                  attrLine = attrLine.replaceFirst("<key>", Matcher.quoteReplacement(key));
                  attrLine = attrLine.replaceFirst("<value>", Matcher.quoteReplacement(value.toString()));
                  
                  attrText = attrText.replaceFirst("</table>", Matcher.quoteReplacement(attrLine + "</table>"));
               }
               else if (value instanceof JsonObject)
               {
                  JsonObject tgtJsonObject = (JsonObject) value;
                  String tgtId = tgtJsonObject.getString(JsonIdMap.ID);
                  if (tgtJsonObject.get(SDMLibJsonIdMap.JSON_HYPERREF) != null)
                  {
                     addAttrText = true;

                     // add hyper ref line
                     String attrLine = "<tr><td align='left' href=\"<key>\">\"<value>\"</td></tr>";
                     attrLine = attrLine.replaceFirst("<key>", Matcher.quoteReplacement(imgName + "/" + tgtId + ".svg"));
                     attrLine = attrLine.replaceFirst("<value>", Matcher.quoteReplacement(imgName + "/" + tgtId + ".svg"));

                     attrText = attrText.replaceFirst("</table>", Matcher.quoteReplacement(attrLine + "</table>"));
                  }
                  else if ( ! omittedId.equals(tgtId))
                  {
                     tgtId = lastPartStartLow(tgtId);
                     addToEdges(edgeMap, jsonId, tgtId, key);
                  }
               }
               else if (value instanceof JsonArray)
               {
                  // array of links
                  JsonArray jsonArray = (JsonArray) value;
                  for (int j = 0; j < jsonArray.size(); j++)
                  {
                     JsonObject tgtJsonObject = jsonArray.getJSONObject(j);
                     String tgtId = tgtJsonObject.getString(JsonIdMap.ID);
                     if ( ! omittedId.equals(tgtId))
                     {
                        tgtId = lastPartStartLow(tgtId);
                        addToEdges(edgeMap, jsonId, tgtId, key);
                     }
                  }
               }
               else 
               {
                  addAttrText = true;

                  // add attribute line
                  String attrLine = "<tr><td align='left'><key> = \"<value>\"</td></tr>";
                  attrLine = attrLine.replaceFirst("<key>", Matcher.quoteReplacement(key));
                  String encode = new HTMLEntities().encode(value.toString());
                  int pos = attrLine.indexOf("<value>");
                  attrLine = attrLine.substring(0, pos)
                        + encode + attrLine.substring(pos + "<value>".length());
                  
                  attrText = attrText.replaceFirst("</table>", Matcher.quoteReplacement(attrLine + "</table>"));
               }
            }
         }
         
         if (addAttrText)
         {
            nodeLine = nodeLine.replaceFirst("</table>", Matcher.quoteReplacement(attrText + "</table>"));                  
         }
         
         nodeBuilder.append(nodeLine);

         // do contained elements
         if (isCluster)
         {
            // call recursive
            listOfNodes(nodeBuilder, omittedId, edgeMap, aggregationMap.get(topId), aggregationMap, jsonObjectMap, imgName);
            
            nodeBuilder.append("}\n");
         }
         

      }
   }
   
   private static void addToEdges(LinkedHashMap<String, EdgeLabels> edgeMap, String srcId,
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
   
   private static String lastPartStartLow(String dottedName)
   {
      return StrUtil.downFirstChar(lastPart(dottedName));
   }
   
   private static String lastPart(String dottedName)
   {
      int dotPos = dottedName.lastIndexOf('.');
      dottedName = dottedName.substring(dotPos+1);
      return dottedName;
   }

   
   public JsonToImg withIconMap(LinkedHashMap<String, String> iconMap)
   {
      this.iconMap = iconMap;
      return this;
   }
}
