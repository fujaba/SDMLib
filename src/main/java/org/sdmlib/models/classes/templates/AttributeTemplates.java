package org.sdmlib.models.classes.templates;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.Attribute;

public class AttributeTemplates {
	public static ExistTemplate insertPropertyInInterface(Attribute attribute, Parser parser) {
		ExistTemplate allTemplates=new ExistTemplate();
		allTemplates.withTemplate("\n   " +
	            "\n   //==========================================================================" +
	            "\n   ");
	      Template attrDecl = new Template()
	    		  .withSearch(Parser.ATTRIBUTE + ":PROPERTY_" + attribute.getName().toUpperCase())
	    		  .withVariable("PROPERTY_NAME")
	    		  .withVariable( "\"name\"")
	      		  .withTemplate("\n   public static final String {{PROPERTY_NAME}} = \"{{name}}\";\n   ");

	      Template attrGet = new Template()
	    		  .withSearch(Parser.METHOD + ":get" + StrUtil.upFirstChar(attribute.getName()) + "()")
	    		  .withVariable( "type" )
	    		  .withVariable( "Name" )
	    		  .withTemplate( "\n   public {{type}} get{{Name}}();\n" );

	      Template attrSet = new Template()
	    		  .withSearch(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
	    		  .withVariable( "Name" )
	    		  .withVariable( "type" )
	    		  .withTemplate( "\n   public void set{{Name}}({{type}} value);\n" );

	      Template attrWith = new Template()
	    		  .withSearch(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
	    		  .withVariable( "ownerClass" )
	    		  .withVariable( "Name" )
	    		  .withVariable( "type" )
	    		  .withTemplate( "\n   public {{ownerClass}} with{{Name}}({{type}} value);\n" );
	      

	      allTemplates.withTemplates(attrDecl, attrGet, attrSet, attrWith);
	      return allTemplates;
	}
}
