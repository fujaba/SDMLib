package org.sdmlib.models.classes.templates;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Modifier;

public class AttributeTemplates {
	public static ExistTemplate insertPropertyInInterface(Attribute attribute) {
		ExistTemplate allTemplates=new ExistTemplate("\n   " +
	            "\n   //==========================================================================" +
	            "\n   ");
	      Template attrDecl = new Template()
	    		  .withSearch(Parser.ATTRIBUTE + ":PROPERTY_" + attribute.getName().toUpperCase())
	    		  .withVariable("name")
	      		  .withTemplate("\n   public static final String {{PROPERTY_NAME}} = \"{{name}}\";\n   ");

	      Template attrGet = new Template()
	    		  .withSearch(Parser.METHOD + ":get" + StrUtil.upFirstChar(attribute.getName()) + "()")
	    		  .withVariable( "type" )
	    		  .withVariable( "name" )
	    		  .withTemplate( "\n   public {{type}} get{{Name}}();\n" );

	      Template attrSet = new Template()
	    		  .withSearch(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
	    		  .withVariable( "name" )
	    		  .withVariable( "type" )
	    		  .withTemplate( "\n   public void set{{Name}}({{type}} value);\n" );

	      Template attrWith = new Template()
	    		  .withSearch(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
	    		  .withVariable( "ownerClass" )
	    		  .withVariable( "name" )
	    		  .withVariable( "type" )
	    		  .withTemplate( "\n   public {{ownerClass}} with{{Name}}({{type}} value);\n" );
	      

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
	    		  .withCondition(!attribute.getClazz().isInterface())
	    		  .withCondition(!attribute.getVisibility().has(Modifier.STATIC))
	    		  .withVariable("name")
	      		  .withTemplate("\n   public static final String {{PROPERTY_NAME}} = \"{{name}}\";\n   ");
		
		Template attrDecl = new Template(Parser.ATTRIBUTE + ":" + attribute.getName())
				.withVariable("modifier", "type", "name", "init")
				.withTemplate("\n   {{modifier}} {{type}} {{name}} {{init}};\n");

		allTemplates.withTemplates(attrPropertyDecl, attrDecl);
	      // if constant field -> return
	      if (attribute.getVisibility().has(Modifier.PUBLIC)
	            && attribute.getVisibility().has(Modifier.STATIC)
	            && attribute.getVisibility().has(Modifier.FINAL)
	            && attribute.getInitialization() != null)
	         return allTemplates;

	      
	      if (attribute.getVisibility().equals(Modifier.PRIVATE))
	      {
	    	  Template attrGetter;
	    	  if ("boolean".equalsIgnoreCase(attribute.getType().getValue()))
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
	    	  attrGetter.withVariable("type", "name");
	    	  
	    	  Template attrSetter = new Template(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")");
	    	  attrSetter.withVariable(ReplaceText.create("PGOLD", Feature.PropertyChangeSupport, "\n         type oldValue = this.name;"));
	    	  attrSetter.withVariable(ReplaceText.create("PROPERTYCHANGE", Feature.PropertyChangeSupport, "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);"));
	    	  attrSetter.withVariable(ReplaceText.create("valueCompare", "String".equalsIgnoreCase(attribute.getType().getValue()), StrUtil.class.getName(), " ! StrUtil.stringEquals(this.name, value)", "this.name != value"));
	    	  attrSetter.withVariable("name", "type", "valueCompare")
	    			  .withTemplate("\n   public void set{{Name}}({{type}} value)" +
	                  "\n   {" +
	                  "\n      if ({{valueCompare}})" +
	                  "\n      {{{PGOLD}}" +
	                  "\n         this.{{name}} = value;{{PROPERTYCHANGE}}" +
	                  "\n      }" +
	                  "\n   }" +
	                  "\n   ");
	    	  Template attrWith = new Template(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")");
	    	  attrWith.withTemplate("\n   public ownerClass withName(type value)" +
	                  "\n   {" +
	                  "\n      setName(value);" +
	                  "\n      return this;" +
	                  "\n   } " +
	                  "\n");
	    	  allTemplates.withTemplates(attrGetter, attrSetter, attrWith);
	      }
	      return allTemplates;
	}
}
