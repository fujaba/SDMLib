package org.sdmlib.doc.GraphViz;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.TransformOp;

public class TransformOpTemplate {
	public StringBuilder dump(TransformOp model){
		// generate dot file 
	      StringBuilder dotFileText = new StringBuilder
	            (  "\n digraph TrafoOpDiagram {" +
	                  "\n    node [shape = none, fontsize = 10]; " +
	                  "\n    edge [fontsize = 10];" +
	                  "\n    " +
	                  "\n    operationObjects" +
	                  "\n    " +
	                  "\n    statements" +
	                  "\n    " +
	                  "\n    links" +
	                  "\n}" +
	                  "\n"
	                  );

	      // add operationOps
	      StringBuilder operationObjectsText = new StringBuilder();

	      for (OperationObject opObject : model.getOpObjects())
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

	      for (Statement stat : model.getStatements())
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
	      for (LinkOp linkOp : model.getLinkOps())
	      {
	         StringBuilder oneLinkText = new StringBuilder(
	            "\n    source -> target [headlabel = \"headLabel\" taillabel = \"tailLabel\" arrowhead = \"none\" ];");
	      
	         CGUtil.replaceAll(oneLinkText, 
	            "source", linkOp.getSrc().getName(),
	            "target", linkOp.getTgt().getName(),
	            "headLabel", linkOp.getTgtText(),
	            "tailLabel", linkOp.getSrcText());
	         
	         allLinksText.append(oneLinkText.toString());
	      }
	      
	      // stat sequence links
	      for (Statement stat : model.getStatements())
	      {
	         if (stat.getNext() != null)
	         {
	            StringBuilder oneLinkText = new StringBuilder(
	                  "\n    source -> target [style = \"dotted\", arrowhead = \"vee\"];");
	            
	            CGUtil.replaceAll(oneLinkText, 
	               "source", CGUtil.encodeJavaName(stat.getText()),
	               "target", CGUtil.encodeJavaName(stat.getNext().getText()));
	            allLinksText.append(oneLinkText.toString());
	         }
	         
	         // and stat -- op links
	         for(OperationObject targetOp : stat.getOperationObjects())
	         {
	            StringBuilder oneLinkText = new StringBuilder(
	                  "\n    source -> target [style = \"dotted\" arrowhead = \"none\"];");
	            
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
	      
	      return dotFileText;
	}
}
