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
	      		  .withTemplate("\n   public static final String PROPERTY_{{NAME}} = \"{{name}}\";\n   ");

	      Template attrGet = new Template()
	    		  .withSearch(Parser.METHOD + ":get" + StrUtil.upFirstChar(attribute.getName()) + "()")
	    		  .withTemplate( "\n   public {{type}} get{{Name}}();\n" );

	      Template attrSet = new Template()
	    		  .withSearch(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
	    		  .withTemplate( "\n   public void set{{Name}}({{type}} value);\n" );

	      Template attrWith = new Template()
	    		  .withSearch(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")")
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
	    		  .withCondition(!attribute.getClazz().isInterface())
	    		  .withCondition(!attribute.getVisibility().has(Modifier.STATIC))
	      		  .withTemplate("\n   public static final String PROPERTY_{{NAME}} = \"{{name}}\";\n   ");
		
		Template attrDecl = new Template(Parser.ATTRIBUTE + ":" + attribute.getName())
				.withTemplate("\n   {{modifier}} {{type}} {{name}}{{init}};\n");

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
	    	  
	    	  Template attrSetter = new Template(Parser.METHOD + ":set" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")");
	    	  attrSetter.withVariable(ReplaceText.create("pgold", Feature.PropertyChangeSupport, "\n         {{type}} oldValue = this.{{name}};"));
	    	  attrSetter.withVariable(ReplaceText.create("propertychange", Feature.PropertyChangeSupport, "\n         getPropertyChangeSupport().firePropertyChange(PROPERTY_{{NAME}}, oldValue, value);"));
	    	  attrSetter.withVariable(ReplaceText.create("valuecompare", "String".equalsIgnoreCase(attribute.getType().getValue()), StrUtil.class.getName(), " ! StrUtil.stringEquals(this.{{name}}, value)", "this.{{name}} != value"));
	    	  attrSetter.withTemplate("\n   public void set{{Name}}({{type}} value)" +
	                  "\n   {" +
	                  "\n      if ({{valuecompare}}) {" +
	                  "\n      {{pgold}}" +
	                  "\n         this.{{name}} = value;{{propertychange}}" +
	                  "\n      }" +
	                  "\n   }" +
	                  "\n   ");
	    	  Template attrWith = new Template(Parser.METHOD + ":with" + StrUtil.upFirstChar(attribute.getName()) + "(" + CGUtil.shortClassName(attribute.getType().getValue()) + ")");
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
	
	public static Template insertCaseInToString(Attribute attribute) {
		Template attrtoString = new Template(Parser.METHOD + ":toString()");
		attrtoString.withTemplate(
	               "\n" +
	                     "\n   @Override" +
	                     "\n   public String toString()\n" +
	                     "   {\n" +
	                     "      StringBuilder result = new StringBuilder();\n" +
	                     "      \n" +
	                     "      return result.substring(1);\n" +
	                     "   }\n\n"
	               );
		return attrtoString;
	}
}
