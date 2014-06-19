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

package org.sdmlib.doc;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.doc.interfaze.GuiAdapter;
import org.sdmlib.logger.LogEntry;
import org.sdmlib.logger.util.LogEntrySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.xml.HTMLEntities;

public class GraphViz implements GuiAdapter {
   public static final String NAME="GraphViz";
   private String rootDir = "src";
	private JsonIdMap lastIdMap = null;
	private static LinkedHashMap<String, String> iconMap;

	@Override
   public GraphViz withRootDir(String rootDir) {
		this.rootDir = rootDir;
		return this;
	}
	
	public String getRootDir(){
		return rootDir;
	}


	public void setLastIdMap(JsonIdMap lastIdMap) {
		this.lastIdMap = lastIdMap;
	}

	public void dumpImage(String imgName, Object root) {
		JsonArray jsonArray = lastIdMap.toJsonArray(root);

		String imgLink = toImg(imgName, jsonArray);

	}

	public void dumpImage(String imgName, JsonIdMap newIdMap, Object root) {
		lastIdMap = newIdMap;

		JsonArray jsonArray = newIdMap.toJsonArray(root);

		String imgLink = toImg(imgName, jsonArray);

	}

	public String toImg(String imgName,
			JsonArray objects) {
		return toImg(imgName, objects, false, null);
	}

	public String toImg(String imgName,
			JsonArray objects, boolean omitRoot,
			String[] aggregationRoles) {
		String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
		link = link.replaceFirst("<imagename>", imgName + ".svg");

		// generate dot file
		String fileText = "graph ObjectDiagram {\n"
				+ "   node [shape = none, fontsize = 10, fontname = \"Arial\"];\n"
				+ "   edge [fontsize = 10, fontname = \"Arial\"];\n"
				+ "   compound=true;\n" + "\n" + "<nodes>\n" + "<edges>"
				+ "}\n";

		StringBuilder nodeBuilder = new StringBuilder();

		StringBuilder edgeBuilder = new StringBuilder();

		fillNodeAndEdgeBuilders(imgName, objects, nodeBuilder, edgeBuilder,
				omitRoot, aggregationRoles);

		fileText = fileText.replaceFirst("<nodes>",
				Matcher.quoteReplacement(nodeBuilder.toString()));

		fileText = fileText.replaceFirst("<edges>",
				Matcher.quoteReplacement(edgeBuilder.toString()));

		CallDot.callDot(imgName, fileText);

		// generate link

		return link;
	}

	public void fillNodeAndEdgeBuilders(String imgName,
			JsonArray objects, StringBuilder nodeBuilder,
			StringBuilder edgeBuilder, boolean omitRoot,
			String... aggregationRoles) {
		String omittedId = "";
		// collect all edges
		LinkedHashMap<String, EdgeLabels> edgeMap = new LinkedHashMap<String, EdgeLabels>();

		// collect all jsonIds
		LinkedHashSet<String> knownIds = new LinkedHashSet<String>();
		LinkedHashMap<String, LinkedHashSet<String>> aggregationMap = new LinkedHashMap<String, LinkedHashSet<String>>();
		LinkedHashMap<String, JsonObject> jsonObjectMap = new LinkedHashMap<String, JsonObject>();

		for (int i = 0; i < objects.size(); i++) {
			JsonObject jsonObject = objects.getJSONObject(i);
			String jsonId = jsonObject.getString(JsonIdMap.ID);

			if (omitRoot && i == 0) {
				omittedId = jsonId;
			} else {
				knownIds.add(jsonId);
				aggregationMap.put(jsonId, new LinkedHashSet<String>());
				jsonObjectMap.put(jsonId, jsonObject);
			}
		}

		LinkedHashSet<String> aggregationRolesSet = new LinkedHashSet<String>();
		if (aggregationRoles != null && aggregationRoles.length > 0) {
			aggregationRolesSet.addAll(Arrays.asList(aggregationRoles));

			// all nodes
			for (String id : (LinkedHashSet<String>) knownIds.clone()) {
				// all properties
				JsonObject properties = jsonObjectMap.get(id);

				JsonObject props = (JsonObject) properties
						.get(JsonIdMap.JSON_PROPS);

				if (props == null) {
					continue;
				}

				for (Iterator<String> iter = props.keys(); iter.hasNext();) {
					String nextProp = iter.next();

					if (aggregationRolesSet.contains(nextProp)) {
						// found contained elements, restructure
						Object propValue = props.get(nextProp);

						if (propValue instanceof JsonArray) {
							JsonArray propArray = (JsonArray) propValue;

							for (int i = 0; i < propArray.size(); i++) {
								JsonObject containedId = propArray
										.getJSONObject(i);

								String subId = containedId
										.getString(JsonIdMap.ID);

								// add to parent and remove from top level ids
								aggregationMap.get(id).add(subId);
								knownIds.remove(subId);
							}
						} else {
							String subId = ((JsonObject) propValue)
									.getString(JsonIdMap.ID);

							// add to parent and remove from top level ids
							aggregationMap.get(id).add(subId);
							knownIds.remove(subId);
						}
					}
				}
			}

		}

		// list of nodes
		listOfNodes(nodeBuilder, omittedId, edgeMap, knownIds, aggregationMap,
				jsonObjectMap, imgName);

		// now generate edges from edgeMap
		for (String keyPair : edgeMap.keySet()) {
			String[] split = keyPair.split(":");

			EdgeLabels edgeLabels = edgeMap.get(keyPair);

			if (aggregationRolesSet.contains(edgeLabels.headlabel)
					|| aggregationRolesSet.contains(edgeLabels.taillabel)) {
				continue;
			}

			String edgeLine = "<srcId> -- <tgtId> [headlabel = \"<headlabel>\" taillabel = \"<taillabel>\"];\n";
			edgeLine = edgeLine.replaceFirst("<srcId>",
					Matcher.quoteReplacement(split[0]));
			edgeLine = edgeLine.replaceFirst("<tgtId>",
					Matcher.quoteReplacement(split[1]));
			edgeLine = edgeLine.replaceFirst("<headlabel>",
					Matcher.quoteReplacement(edgeLabels.headlabel));
			String taillabel = edgeLabels.taillabel;
			if (taillabel.startsWith("_")) {
				taillabel = taillabel.substring(1);
			}
			edgeLine = edgeLine.replaceFirst("<taillabel>",
					Matcher.quoteReplacement(taillabel));

			edgeBuilder.append(edgeLine);
		}
	}

	private void listOfNodes(StringBuilder nodeBuilder,
			String omittedId, LinkedHashMap<String, EdgeLabels> edgeMap,
			Set<String> knownIds,
			LinkedHashMap<String, LinkedHashSet<String>> aggregationMap,
			LinkedHashMap<String, JsonObject> jsonObjectMap, String imgName) {
		for (String topId : knownIds) {
			JsonObject jsonObject = jsonObjectMap.get(topId);
			String nodeLine = "<id> [label=<<table border='0' cellborder='1' cellspacing='0'> <optionalImage><tr> <td href=\"<classRef>\"> <u><id> :<classname></u></td></tr></table>>];\n";

			boolean isCluster = false;
			if (aggregationMap.get(topId).size() > 0) {
				isCluster = true;

				// has kids, render as cluster
				nodeLine = "subgraph cluster_<id> { \n"
						+ "   <id> [label=<<table border='0' cellborder='0' cellspacing='0'> <optionalImage><tr> <td> <u><id> :<classname></u></td></tr></table>>];\n"
						+ "   \n";
			}

			String imageName = jsonObject.getString(JsonIdMap.CLASS) + ".svg";
			File imageFile = new File("doc/" + imageName);
			if (imageFile.exists()) {
				nodeLine = nodeLine.replaceFirst("<optionalImage>",
						"<tr><td border='0'><IMG src=\"" + imageName
								+ "\" /></td></tr>");
			} else if (iconMap != null && iconMap.get(topId) != null) {
				nodeLine = nodeLine.replaceFirst("<optionalImage>",
						"<tr><td border='0'><IMG src=\"" + iconMap.get(topId)
								+ "\" /></td></tr>");
			} else {
				nodeLine = nodeLine.replaceFirst("<optionalImage>", "");
			}

			String jsonId = lastPartStartLow(jsonObject.getString(JsonIdMap.ID));
			nodeLine = nodeLine.replaceAll("<id>",
					Matcher.quoteReplacement(jsonId));

			String className = jsonObject.getString(JsonIdMap.CLASS);
			String classFileName = className;
			if (classFileName.indexOf('$') >= 0) {
				// inner class, cut down to real class
				classFileName = classFileName.substring(0,
						classFileName.indexOf('$'));
			}
			classFileName = classFileName.replaceAll("\\.", "/") + ".java";
			String localFileName = "./" + rootDir + "/" + classFileName;
			if (new File(localFileName).exists()) {
				classFileName = "../" + rootDir + "/" + classFileName;
			} else {
				classFileName = "../../SDMLib/src/" + classFileName;
			}
			nodeLine = nodeLine.replaceAll("<classRef>",
					Matcher.quoteReplacement(classFileName));
			className = lastPart(className);
			nodeLine = nodeLine.replaceAll("<classname>",
					Matcher.quoteReplacement(className));

			// go through attributes
			if (jsonObject.has(JsonIdMap.JSON_PROPS)) {
				jsonObject = jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
			} else {
				jsonObject = new JsonObject();
			}

			String attrText = "<tr><td><table border='0' cellborder='0' cellspacing='0'></table></td></tr>";
			boolean addAttrText = false;
			for (Iterator<String> iter = jsonObject.keys(); iter.hasNext();) {
				String key = iter.next();

				if (!JsonIdMap.ID.equals(key) && !JsonIdMap.CLASS.equals(key)) {
					Object value = jsonObject.get(key);

					if (value instanceof Number || value instanceof Enum) {
						// dump without quotes
						addAttrText = true;

						// add attribute line
						String attrLine = "<tr><td align='left'><key> = <value></td></tr>";
						attrLine = attrLine.replaceFirst("<key>",
								Matcher.quoteReplacement(key));
						attrLine = attrLine.replaceFirst("<value>",
								Matcher.quoteReplacement(value.toString()));

						attrText = attrText
								.replaceFirst(
										"</table>",
										Matcher.quoteReplacement(attrLine
												+ "</table>"));
					} else if (value instanceof JsonObject) {
						JsonObject tgtJsonObject = (JsonObject) value;
						String tgtId = tgtJsonObject.getString(JsonIdMap.ID);
						if (tgtJsonObject.get(SDMLibJsonIdMap.JSON_HYPERREF) != null) {
							addAttrText = true;

							// add hyper ref line
							String attrLine = "<tr><td align='left' href=\"<key>\">\"<value>\"</td></tr>";
							attrLine = attrLine.replaceFirst(
									"<key>",
									Matcher.quoteReplacement(imgName + "/"
											+ tgtId + ".svg"));
							attrLine = attrLine.replaceFirst(
									"<value>",
									Matcher.quoteReplacement(imgName + "/"
											+ tgtId + ".svg"));

							attrText = attrText.replaceFirst(
									"</table>",
									Matcher.quoteReplacement(attrLine
											+ "</table>"));
						} else if (!omittedId.equals(tgtId)) {
							tgtId = lastPartStartLow(tgtId);
							addToEdges(edgeMap, jsonId, tgtId, key);
						}
					} else if (value instanceof JsonArray) {
						// array of links
						JsonArray jsonArray = (JsonArray) value;
						for (int j = 0; j < jsonArray.size(); j++) {
							JsonObject tgtJsonObject = jsonArray
									.getJSONObject(j);
							String tgtId = tgtJsonObject
									.getString(JsonIdMap.ID);
							if (!omittedId.equals(tgtId)) {
								tgtId = lastPartStartLow(tgtId);
								addToEdges(edgeMap, jsonId, tgtId, key);
							}
						}
					} else {
						addAttrText = true;

						// add attribute line
						String attrLine = "<tr><td align='left'><key> = \"<value>\"</td></tr>";
						attrLine = attrLine.replaceFirst("<key>",
								Matcher.quoteReplacement(key));
						String encode = new HTMLEntities().encode(value
								.toString());
						int pos = attrLine.indexOf("<value>");
						attrLine = attrLine.substring(0, pos) + encode
								+ attrLine.substring(pos + "<value>".length());

						attrText = attrText
								.replaceFirst(
										"</table>",
										Matcher.quoteReplacement(attrLine
												+ "</table>"));
					}
				}
			}

			if (addAttrText) {
				nodeLine = nodeLine.replaceFirst("</table>",
						Matcher.quoteReplacement(attrText + "</table>"));
			}

			nodeBuilder.append(nodeLine);

			// do contained elements
			if (isCluster) {
				// call recursive
				listOfNodes(nodeBuilder, omittedId, edgeMap,
						aggregationMap.get(topId), aggregationMap,
						jsonObjectMap, imgName);

				nodeBuilder.append("}\n");
			}

		}
	}

	private static void addToEdges(LinkedHashMap<String, EdgeLabels> edgeMap,
			String srcId, String tgtId, String label) {
		// is this edge already known?
		EdgeLabels fwdEdgeLabels = edgeMap.get(srcId + ":" + tgtId);
		if (fwdEdgeLabels != null) {
			// add label to the headlabel
			fwdEdgeLabels.headlabel += "_" + label;
		} else {
			EdgeLabels bwdEdgeLabels = edgeMap.get(tgtId + ":" + srcId);
			if (bwdEdgeLabels != null) {
				// add label to the taillabel
				bwdEdgeLabels.taillabel += "_" + label;
			} else {
				// unknown edge, create it
				fwdEdgeLabels = new EdgeLabels();
				fwdEdgeLabels.headlabel = label;
				edgeMap.put(srcId + ":" + tgtId, fwdEdgeLabels);
			}
		}

	}

	static class EdgeLabels {
		String headlabel = "";
		String taillabel = "";
	}

	private static String lastPartStartLow(String dottedName) {
		return StrUtil.downFirstChar(lastPart(dottedName));
	}

	private static String lastPart(String dottedName) {
		int dotPos = dottedName.lastIndexOf('.');
		dottedName = dottedName.substring(dotPos + 1);
		return dottedName;
	}

	public GraphViz withIconMap(LinkedHashMap<String, String> iconMap) {
		this.iconMap = iconMap;
		return this;
	}
	
	public LinkedHashMap<String, String> getIconMap(){
		return iconMap;
	}

	private int objNo;

	@Override
	public String addGenericObjectDiag(String diagramName, GenericGraph graph,
			GenericObjectSet hiddenObjects) {
		objNo = 0;

		// name all objects
		LinkedHashMap<GenericObject, String> allObjects = new LinkedHashMap<GenericObject, String>();

		// String imgLink = JsonToImg.get().toImg(this.getName() +
		// (this.steps.size()+1), jsonArray);
		String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
		link = link.replaceFirst("<imagename>", diagramName + ".svg");

		// generate dot file
		String fileText = "graph ObjectDiagram {\n"
				+ "   node [shape = none, fontsize = 10];\n"
				+ "   edge [fontsize = 10];\n\n" + "<nodes>\n" + "<edges>"
				+ "}\n";

		// list of nodes
		StringBuilder nodeBuilder = new StringBuilder();
		for (GenericObject currentObject : graph.getObjects()) {
			if (hiddenObjects.contains(currentObject)) {
				continue;
			}

			StringBuilder nodeLine = new StringBuilder(
					"<id> [label=<<table border='0' cellborder='borderSize' cellspacing='0'> iconrow<tr> <td> <u><id> :<classname></u></td></tr>attrText</table>>];\n");

			String borderSize = "1";

			allObjects.put(currentObject, findNameFor(currentObject));

			String iconRow = "";

			if (currentObject.getIcon() != null) {
				iconRow = "<tr><td border='0'><img src=\""
						+ currentObject.getIcon() + "\"/></td></tr>";
				borderSize = "0";
			}

			String type = "_";
			if (currentObject.getType() != null) {
				type = currentObject.getType();
			}

			// go through attributes
			String attrText = "<tr><td border='borderSize'><table border='0' cellborder='0' cellspacing='0'></table></td></tr>";

			attrText = attrText.replaceFirst("borderSize", borderSize);

			for (GenericAttribute attr : currentObject.getAttrs()) {
				String attrLine = "<tr><td><key> = \"<value>\"</td></tr>";
				attrLine = attrLine.replaceFirst("<key>", attr.getName());
				attrLine = attrLine.replaceFirst("<value>", attr.getValue());
				attrLine = attrLine.replaceFirst("borderSize", borderSize);

				attrText = attrText.replaceFirst("</table>", attrLine
						+ "</table>");
			}

			if (currentObject.getAttrs().isEmpty()) {
				attrText = "";
			}

			CGUtil.replaceAll(nodeLine, "iconrow", iconRow, "<id>",
					allObjects.get(currentObject), "<classname>", type,
					"attrText", attrText, "borderSize", borderSize);

			nodeBuilder.append(nodeLine.toString());
		}

		fileText = fileText.replaceFirst("<nodes>", nodeBuilder.toString());

		// now generate edges from edgeMap
		StringBuilder edgeBuilder = new StringBuilder();
		for (GenericLink currentLink : graph.getLinks()) {
			if (hiddenObjects.contains(currentLink.getSrc())
					|| hiddenObjects.contains(currentLink.getTgt())) {
				continue;
			}

			String edgeLine = "<srcId> -- <tgtId> [headlabel = \"<headlabel>\" taillabel = \"<taillabel>\"];\n";
			edgeLine = edgeLine.replaceFirst("<srcId>",
					allObjects.get(currentLink.getSrc()));
			edgeLine = edgeLine.replaceFirst("<tgtId>",
					allObjects.get(currentLink.getTgt()));
			String headLabel = currentLink.getTgtLabel();
			if (headLabel == null) {
				headLabel = " ";
			}
			edgeLine = edgeLine.replaceFirst("<headlabel>",
					currentLink.getTgtLabel());
			String taillabel = currentLink.getSrcLabel();
			if (taillabel == null) {
				taillabel = " ";
			}
			edgeLine = edgeLine.replaceFirst("<taillabel>", taillabel);

			edgeBuilder.append(edgeLine);
		}

		fileText = fileText.replaceFirst("<edges>", edgeBuilder.toString());

		CallDot.callDot(diagramName, fileText);

		return link;
	}

	private String findNameFor(GenericObject currentObject) {
		if (currentObject.getName() != null) {
			return currentObject.getName();
		}

		objNo++;

		return "_" + objNo;
	}

	@Override
	public String dumpSwimlanes(String name, LogEntrySet entries) {
		StringBuilder text = new StringBuilder(" digraph TaskFlowDiagram {\n"
				+ "    \n" + "swimlanes\n" + "    \n" + "links\n" + "}");

		StringBuilder swimlanesText = dumpSwimlanes(entries);

		StringBuilder linksText = dumpLinks(entries);

		CGUtil.replaceAll(text, "swimlanes", swimlanesText.toString(), "links",
				linksText.toString());

		// StoryboardManager.printFile(new File("doc/Logger.dot"),
		// text.toString());

		CallDot.callDot(name, text.toString());
		return name;
	}

	private StringBuilder dumpSwimlanes(LogEntrySet entries) {
		StringBuilder swimlanesText = new StringBuilder();

		StringList nodeNames = entries.getNodeName();

		for (String nodeName : nodeNames) {
			// add one lane
			StringBuilder laneText = new StringBuilder(
					"    subgraph clusterLaneName {\n"
							+ "    	rankdir=\"LR\";\n" + "    	style=filled;\n"
							+ "		color=lightgrey;\n" + "innerTasks\n"
							+ "    	label = \"LaneName\";\n" + "    }\n"
							+ "    \n");

			StringBuilder innerTasksText = dumpInnerTasks(nodeName, entries);

			CGUtil.replaceAll(laneText, "LaneName", nodeName, "innerTasks",
					innerTasksText.toString());

			swimlanesText.append(laneText.toString());
		}
		return swimlanesText;
	}

	private StringBuilder dumpInnerTasks(String nodeName, LogEntrySet entries) {
		StringBuilder innerTasksText = new StringBuilder();

		LinkedHashSet<String> taskNames = new LinkedHashSet<String>();
		for (LogEntry entry : entries) {
			if (nodeName.equals(entry.getNodeName())) {
				taskNames.add(entry.getTaskName());
			}
		}

		for (String taskName : taskNames) {
			innerTasksText.append("        " + nodeName + "_" + taskName
					+ "[label=\"" + taskName + "\"];\n");
		}

		return innerTasksText;
	}

	private StringBuilder dumpLinks(LogEntrySet entries) {
		StringBuilder linksText = new StringBuilder();

		for (LogEntry entry : entries) {
		   LogEntry previousEntry = entry.getParent();
			if (previousEntry != null) {
				linksText.append("    " + previousEntry.getNodeName() + "_"
						+ previousEntry.getTaskName() + " -> "
						+ entry.getNodeName() + "_" + entry.getTaskName()
						+ "; \n");
			}
		}

		return linksText;
	}

	@Override
	public String dumpClassDiagram(String diagName,
			ClassModel model) {
		StringBuilder dotFileText = new ClassModelTemplate().dump(rootDir,
				diagName, model);

		// write dot file
		CallDot.callDot(diagName, dotFileText.toString());

		return diagName + ".svg";
	}


	@Override
	public String dumpDiagram(String diagramName, String fileText) {
		CallDot.callDot(diagramName, fileText);
		return diagramName;
	}

   @Override
   public String getName()
   {
      return NAME;
   }
}
