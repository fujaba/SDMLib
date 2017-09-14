package org.sdmlib.models.classes.templates;

import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.ClazzType;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Modifier;

public class AttributeTemplates {
	public static ExistTemplate insertPropertyInInterface(Attribute attribute) {
		ExistTemplate allTemplates=new ExistTemplate("\n   " +
	            "\n   //==========================================================================" +
	            "\n   ");
	      Template attrDecl = new Template()
	    		  .withSearch(Parser.ATTRIBUTE + ":PROPERTY_{{NAME}}")
	      		  .withTemplate("\n   public static final String PROPERTY_{{NAME}} = \"{{name}}\";\n   ");

	      Template attrGet = new Template()
	    		  .withSearch(Parser.METHOD + ":get" + StrUtil.upFirstChar(attribute.getName()) + "()")
	    		  .withTemplate( "\n   public {{type}} get{{Name}}();\n" );

	      Template attrSet = new Template()
	    		  .withSearch(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + attribute.getType().getName(true) + ")")
	    		  .withTemplate( "\n   public void set{{Name}}({{type}} value);\n" );

	      Template attrWith = new Template()
	    		  .withSearch(Parser.METHOD + ":with{{Name}}(" + attribute.getType().getName(true) + ")")
	    		  .withTemplate( "\n   public {{ownerclass}} with{{Name}}({{type}} value);\n" );
	      

	      allTemplates.withTemplates(attrDecl, attrGet, attrSet, attrWith);
	      return allTemplates;
	}
	public static ExistTemplate insertPropertyInClass(Attribute attribute) {
		ExistTemplate allTemplates=new ExistTemplate(
	            "\n   " +
	            "\n   //==========================================================================" +
	            "\n   "
	            );
		Template attrPropertyDecl = new Template()
	    		  .withSearch(Parser.ATTRIBUTE + ":PROPERTY_" + attribute.getName().toUpperCase())
	    		  .withCondition(GraphUtil.isInterface(attribute.getClazz()) == false)
	    		  .withCondition((attribute.getModifier().has(Modifier.STATIC) && attribute.getModifier().has(Modifier.PUBLIC)) == false)
	      		  .withTemplate("\n   public static final String PROPERTY_{{NAME}} = \"{{name}}\";\n   ");
		
		Template attrDecl = new Template(Parser.ATTRIBUTE + ":" + attribute.getName())
				.withTemplate("\n   {{modifier}} {{type}} {{name}}{{init}};\n");

		allTemplates.withTemplates(attrPropertyDecl, attrDecl);
	      // if constant field -> return
	      if (attribute.getModifier().has(Modifier.PUBLIC)
	            && attribute.getModifier().has(Modifier.STATIC)
	            && attribute.getModifier().has(Modifier.FINAL)
	            && attribute.getValue() != null)
	         return allTemplates;

	      
	      if (attribute.getModifier().equals(Modifier.PRIVATE) || attribute.getModifier().equals(Modifier.PROTECTED) )
	      {
	    	  Template attrGetter;
	    	  if ("boolean".equalsIgnoreCase(attribute.getType().getName(false)))
		      {
	    		  attrGetter = new Template(Parser.METHOD + ":is" + StrUtil.upFirstChar(attribute.getName()) + "()")
			  			.withTemplate("\n   public {{type}} is{{Name}}()" +
				                  "\n   {" +
				                  "\n      return this.{{name}};" +
				                  "\n   }" +
				                  "\n   ");
		      } else {
		    	  attrGetter = new Template(Parser.METHOD + ":get" + StrUtil.upFirstChar(attribute.getName()) + "()")
				  			.withTemplate("\n   public {{type}} get{{Name}}()" +
					                  "\n   {" +
					                  "\n      return this.{{name}};" +
					                  "\n   }" +
					                  "\n   ");
		      }
	    	  
	    	  Template attrSetter = new Template(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + attribute.getType().getName(true) + ")");
	    	  attrSetter.withVariable(ReplaceText.create("pgold", Feature.PROPERTYCHANGESUPPORT, "\n         {{type}} oldValue = this.{{name}};"));
	    	  ReplaceText propertyChange = ReplaceText.create("propertychange", Feature.PROPERTYCHANGESUPPORT, "\n         this.firePropertyChange(PROPERTY_{{NAME}}, oldValue, value);");
	    	  propertyChange.withCondition(attribute.getClazz().getType()!=ClazzType.ENUMERATION);

	    	  attrSetter.withVariable(propertyChange);
	    	  attrSetter.withVariable(ReplaceText.create("valuecompare", "String".equalsIgnoreCase(attribute.getType().getName(false)), EntityUtil.class.getName(), " ! EntityUtil.stringEquals(this.{{name}}, value)", "this.{{name}} != value"));
	    	  attrSetter.withTemplate("\n   public void set{{Name}}({{type}} value)" +
	                  "\n   {" +
	                  "\n      if ({{valuecompare}}) {" +
	                  "\n      {{pgold}}" +
	                  "\n         this.{{name}} = value;{{propertychange}}" +
	                  "\n      }" +
	                  "\n   }" +
	                  "\n   ");
	    	  Template attrWith = new Template(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + attribute.getType().getName(true) + ")");
	    	  attrWith.withTemplate("\n   public {{ownerclass}} with{{Name}}({{type}} value)" +
	                  "\n   {" +
	                  "\n      set{{Name}}(value);" +
	                  "\n      return this;" +
	                  "\n   } " +
	                  "\n");
	    	  allTemplates.withTemplates(attrGetter, attrSetter, attrWith);
	      }
	      return allTemplates;
	}
}
