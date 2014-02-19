package org.sdmlib.doc.svg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.CachedImageHandlerBase64Encoder;
import org.apache.batik.svggen.GenericImageHandler;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.sdmlib.doc.DocAdapter;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.creators.GenericObjectSet;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class JsonToSvg implements DocAdapter {

	private String rootDir;
	private LinkedHashMap<String, String> iconMap = new LinkedHashMap<>();

	@Override
	public DocAdapter withRootDir(String rootDir) {
		this.rootDir = rootDir;
		return this;
	}

	@Override
	public DocAdapter withIconMap(LinkedHashMap<String, String> iconMap) {
		this.iconMap = iconMap;
		return this;
	}

	@Override
	public String toImg(String imgName, JsonArray objects) {
		return toImg(imgName, objects, false, null);
	}

	@Override
	public String toImg(String imgName, JsonArray objects, boolean omitRoot,
			String[] aggregationRoles) {
		String link = "<embed type=\"image/svg+xml\" src='<imagename>'>\n";
		link = link.replaceFirst("<imagename>", imgName + ".svg");

		mxGraph graph = new mxGraph();
		Object defaultParent = graph.getDefaultParent();
		graph.getModel().beginUpdate();

		HashMap<JsonObject, Object> vertexMap = new HashMap<>();
		HashMap<JsonObject, List<String>> objectIdsMap = new HashMap<>();
		HashMap<String, JsonObject> idObjectMap = new HashMap<>();
		
		try {
			//parse nodes
			for (int i = 0; i < objects.size(); i++) {
				JsonObject object = objects.getJSONObject(i);
				String objectId = object.getString(JsonIdMap.ID);
				idObjectMap.put(objectId, object);
				
				JsonObject props = (JsonObject) object.get(JsonIdMap.JSON_PROPS);
				
				List<String> linkedIds = new LinkedList<>();
				
				Iterator<Entry<String, Object>> iterator = props.iterator();
				
				//parse edges
				while(iterator.hasNext()) {
					Entry<String, Object> next = iterator.next();
					Object value = next.getValue();
					//to 1
					if(value instanceof JsonObject) {
						String id = ((JsonObject)value).getString(JsonIdMap.ID);
						linkedIds.add(id);
					//to n
					} else if(value instanceof JsonArray) {
						JsonArray idArray = (JsonArray) value;
						for(int j=0; j<idArray.size(); j++) {
							JsonObject idObject = idArray.getJSONObject(j);
							String id = idObject.getString(JsonIdMap.ID);
							linkedIds.add(id);
						}
					}
				}
				
				Object vertex = graph.insertVertex(defaultParent, null,
						object.getString(JsonIdMap.CLASS), 20, 20, 80,
						30);
				
				objectIdsMap.put(object, linkedIds);
				vertexMap.put(object, vertex);
			}
			
			Set<JsonObject> jsonObjects = objectIdsMap.keySet();
			
			for (JsonObject jsonObject : jsonObjects) {
				List<String> linkedIds = objectIdsMap.get(jsonObject);
				for (String id : linkedIds) {
					JsonObject linkedObject = idObjectMap.get(id);
					Object v1 = vertexMap.get(jsonObject);
					Object v2 = vertexMap.get(linkedObject);
					
					graph.insertEdge(defaultParent, null, "Edge", v1, v2, "startArrow=none;endArrow=none;");
					
					Object[] incomingEdges = graph.getIncomingEdges(v1);
					for (Object object : incomingEdges) {
						
					}
					
				}
			}
			
		} finally {
			graph.getModel().endUpdate();
		}

		try {
			generateSVGGraphImage(graph, "doc/" + imgName + ".svg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return link;

	}

	public void generateSVGGraphImage(mxGraph graph, String fileName)
			throws IOException {
		// Get the Graph component from the mxGraph to create an image out of
		// this Graph
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		
		new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
//		new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
		
		// Create an instance of org.w3c.dom.Document
		String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
		DOMImplementation domImpl = SVGDOMImplementation.getDOMImplementation();
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);

		// Reuse our embedded base64-encoded image data.
		GenericImageHandler ihandler = new CachedImageHandlerBase64Encoder();
		ctx.setGenericImageHandler(ihandler);

		// Create SVG graphics2d Generator similar to the Graphich2d
		SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx, false);

		// First draw Graph to the SVGGrapgics2D object using graphcomponent
		// objects draw method
		graphComponent.getGraphControl().drawGraph(svgGenerator, true);

		// Once every thing is drawn on graphics find root element and update
		// this by adding additional values for the required fields.
		Element root = svgGenerator.getRoot();
		root.setAttributeNS(null, "width", graphComponent.getGraphControl()
				.getPreferredSize().width + "");
		root.setAttributeNS(null, "height", graphComponent.getGraphControl()
				.getPreferredSize().height + "");
		root.setAttributeNS(null, "viewBox", "0 0 "
				+ graphComponent.getGraphControl().getPreferredSize().width
				+ " "
				+ graphComponent.getGraphControl().getPreferredSize().height);

		// Print to the SVG Graphics2D object
		boolean useCSS = true; // we want to use CSS style attributes
		Writer out = new FileWriter(new File(fileName));
		try {
			svgGenerator.stream(root, out, useCSS, false);
		} catch (SVGGraphics2DIOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String addGenericObjectDiag(String diagramName, GenericGraph graph,
			GenericObjectSet hiddenObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dumpSwimlanes(String name, LogEntrySet entries) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dumpClassDiagram(String rootdir, String diagName,
			ClassModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String dumpTransformOp(String diagName, TransformOp model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fillNodeAndEdgeBuilders(String imgName, JsonArray objects,
			StringBuilder nodeBuilder, StringBuilder edgeBuilder,
			boolean omitRoot, String... aggregationRoles) {
		// TODO Auto-generated method stub

	}

	@Override
	public String dumpDiagram(String diagramName, String fileText) {
		// TODO Auto-generated method stub
		return null;
	}

}
