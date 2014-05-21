package org.sdmlib.doc.GraphViz;

import org.sdmlib.CGUtil;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;

public class ClassModelTemplate {
	public StringBuilder dump(String rootdir, String diagName, ClassModel model){
		// generate dot file
		StringBuilder dotFileText = new StringBuilder(
				"\n digraph ClassDiagram {" 
						+ "\n    node [shape = none, fontsize = 10]; " 
						+ "\n    edge [fontsize = 10];" 
						+ "\n    "
						+ "\n    modelClasses" 
						+ "\n    " +
						// "\n    g1 -- p2 " +
						// "\n    g1 -- p3 [headlabel = \"persons\" taillabel = \"groupAccounter\"];" +
						"\n    " 
						+ "\n    modelAssocs" 
						+ "\n}" 
						+ "\n");

		// add classes
		StringBuilder modelClassesText = new StringBuilder();
		
		model.getGenerator().addHelperClassesForUnknownAttributeTypes();
		
		for (Clazz clazz : model.getClasses())
		{
			StringBuilder modelClassText = new StringBuilder(
					"\n    _className [label=<<table border='0' cellborder='1' cellspacing='0'> <tr> <td HREF=\"classfilename\">className</td> </tr> attrCompartment methodCompartment </table>>];");

			if (clazz.isInterface())
				CGUtil.replaceAll(modelClassText, "table border", "table color='lightgrey' border");

			CGUtil.replaceAll(modelClassText, 
					"className", CGUtil.shortClassNameHTMLEncoded(clazz.getName()),
					"classfilename", "../" + rootdir + "/" + clazz.getName().replaceAll("\\.", "/") + ".java", 
					"attrCompartment", dumpAttributes(clazz), 
					"methodCompartment", dumpMethods(clazz));

			modelClassesText.append(modelClassText.toString());
		}


		StringBuilder allAssocsText = new StringBuilder();

		// add class inheritance	
		for (Clazz clazz : model.getClasses())
		{
			if (clazz.getSuperClass() != null)
			{

				StringBuilder oneSuperClassText = new StringBuilder("\n    _superClass ->  _mClass [dir = \"back\" arrowtail = \"empty\"];");

				CGUtil.replaceAll(oneSuperClassText, "superClass", CGUtil.shortClassName(clazz.getSuperClass().getName())
						, "mClass", CGUtil.shortClassName(clazz.getName()));

				allAssocsText.append(oneSuperClassText.toString());
			}
		}

		// add interface inheritance
		for (Clazz clazz : model.getClasses())
		{
			for (Clazz interfaceClass : clazz.getInterfaces())
			{	
				if (interfaceClass.isInterface())
				{
					StringBuilder oneSuperClassText = new StringBuilder("\n    _interfaceClass ->  _mClass [dir = \"back\" arrowtail = \"empty\"];");

					CGUtil.replaceAll(oneSuperClassText, 
							"interfaceClass", CGUtil.shortClassName(interfaceClass.getName()), 
							"mClass", CGUtil.shortClassName(clazz.getName()));

					allAssocsText.append(oneSuperClassText.toString());
				}
			}
		}

		// add associations
		for (Association assoc : model.getGenerator().getAssociations())
		{
			StringBuilder oneAssocText = new StringBuilder("\n    _sourceClass -> _targetClass [headlabel = \"targetRole\" taillabel = \"sourceRole\" arrowhead = \"none\" ];");

			CGUtil.replaceAll(oneAssocText, 
					"sourceClass", CGUtil.shortClassName(assoc.getSource().getClazz().getName()), 
					"targetClass", CGUtil.shortClassName(assoc.getTarget().getClazz().getName()), 
					"sourceRole", assoc.getSource().labelForRole(), 
					"targetRole", assoc.getTarget().labelForRole());

			allAssocsText.append(oneAssocText.toString());
		}

		// add assocs for complex attributes
		for (Attribute attr : model.getClasses().getAttributes())
		{

			if (CGUtil.isPrimitiveType(attr.getType().getValue()))
			{
				continue;
			}

//			R tgtCard = model.findRoleCard(attr.getType());
			String tgtClassName = model.getGenerator().findPartnerClassName(attr.getType().getValue());
			tgtClassName = CGUtil.shortClassName(tgtClassName);

			StringBuilder oneAssocText = new StringBuilder("\n    _sourceClass -> _targetClass [headlabel = \"targetRole\" taillabel = \"sourceRole\" arrowhead = \"vee\" ];");

			CGUtil.replaceAll(oneAssocText, 
					"sourceClass", CGUtil.shortClassName(attr.getClazz().getName()), 
					"targetClass", tgtClassName, 
					"sourceRole", "", 
					"targetRole", attr.getName());

			allAssocsText.append(oneAssocText.toString());
		}

		CGUtil.replaceAll(dotFileText, "modelClasses", modelClassesText.toString(), "modelAssocs", allAssocsText.toString());

		return dotFileText;
	}
	
	public String dumpAttributes(Clazz clazz)
	{
		StringBuilder allAttrsText = new StringBuilder("<tr><td><table border='0' cellborder='0' cellspacing='0'> attrRow </table></td></tr>");

		if (clazz.getAttributes().size() > 0)
		{
			for (Attribute attr : clazz.getAttributes())
			{
				StringBuilder oneAttrText = new StringBuilder("<tr><td align='left'>attrDecl</td></tr>");

				CGUtil.replaceAll(oneAttrText, "attrDecl", attr.getName() + " :" + CGUtil.shortClassNameHTMLEncoded(attr.getType().getValue()));

				CGUtil.replaceAll(allAttrsText, "attrRow", oneAttrText.append(" attrRow").toString());
			}

			CGUtil.replaceAll(allAttrsText, "attrRow", "");
		}
		else
		{
			CGUtil.replaceAll(allAttrsText, "attrRow", "<tr><td> </td></tr>");
		}

		return allAttrsText.toString();
	}
	
	public String dumpMethods(Clazz clazz)
	{
		StringBuilder allMethodsText = new StringBuilder("<tr><td><table border='0' cellborder='0' cellspacing='0'> methodRow </table></td></tr>");

		if (clazz.getMethods().size() > 0)
		{
			for (Method method : clazz.getMethods())
			{
				StringBuilder oneMethodText = new StringBuilder("<tr><td align='left'>methodDecl</td></tr>");

				CGUtil.replaceAll(oneMethodText, "methodDecl", CGUtil.shortClassNameHTMLEncoded(method.getSignature(true)));

				CGUtil.replaceAll(allMethodsText, "methodRow", oneMethodText.append(" methodRow").toString());
			}

			CGUtil.replaceAll(allMethodsText, "methodRow", "");
		}
		else
		{
			CGUtil.replaceAll(allMethodsText, "methodRow", "<tr><td> </td></tr>");
		}

		return allMethodsText.toString();
	}
}
