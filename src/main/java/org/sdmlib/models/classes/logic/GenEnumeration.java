/*
   Copyright (c) 2014 NeTH 
   
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

package org.sdmlib.models.classes.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.FeatureProperty;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;
import org.sdmlib.models.modelsets.SDMSet;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Literal;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.list.SimpleList;
import de.uniks.networkparser.list.SimpleSet;

public class GenEnumeration extends GenClazzEntity{
	public void generate(String rootDir, String helpersDir) {
		if (!model.isExternal()) {
			getOrCreateParser(rootDir);
			insertLicense(parser);
			generateAttributes(rootDir, helpersDir);
			insertValues();
			insertMethods(rootDir, helpersDir);
			printFile(parser);
			ClassModel classModel = (ClassModel) model.getClassModel();
			if (classModel.hasFeature(Feature.SERIALIZATION, model))
		    {
		         getOrCreateParserForModelSetFile(helpersDir);
		         printFile(modelSetParser);
		    }
		}
	}

	private void insertMethods(String rootDir, String helpersDir) {
		for (Method method : model.getMethods()) {
			GenMethod generator = getGenerator(method);
			if(generator==null) {
				//TODO Its Enumeration skip it
				continue;
			}
			generator.generate(rootDir, helpersDir);

			String signature = method.getName(false);
			parser.parse();
			ArrayList<SymTabEntry> symTabEntries = parser
					.getSymTabEntriesFor(signature);

			if (symTabEntries.size() > 0) {
				SymTabEntry symTabEntry = symTabEntries.get(0);
				parser.parseMethodBody(symTabEntry);
				LinkedHashMap<String, LocalVarTableEntry> localVarTable = parser
						.getLocalVarTable();
				String[] array = localVarTable.keySet().toArray(new String[0]);
				for (String key : array) {
					LocalVarTableEntry localVarTableEntry = localVarTable
							.get(key);
					if (localVarTableEntry == null) {
						continue;
					}
					String type = localVarTableEntry.getType();
					SimpleSet<Clazz> classes = this.getModel().getClassModel()
							.getClazzes();
					for (Clazz clazz : classes) {
						if (clazz.getName().equals(type)
								|| clazz.getName().endsWith("." + type)) {
							insertImport(clazz.getName(false));
						}
					}
				}
			}
		}
	}
	
	public Parser getOrCreateParserForModelSetFile(String rootDir)
	   {
			if (getRepairClassModel().hasFeature(Feature.SETCLASS) == false
					&& getRepairClassModel().hasFeature(Feature.SERIALIZATION) == false) {
				return null;
			}
			if (((ClassModel) model.getClassModel()).hasFeature(Feature.SETCLASS) == false) {
				return null;
			}

	      if (modelSetParser == null)
	      {
	         if (model.getName(false).equals("java.util.Date"))
	         {
	            System.out.println("ups");
	         }
	         // try to find existing file
	         String name = model.getName(false);
	         int pos = name.lastIndexOf('.');

	         String packageName = name.substring(0, pos) + GenClassModel.UTILPATH;

	         if (model.isExternal())
	         {
	            packageName = getRepairClassModel().getName() + GenClassModel.UTILPATH;
	         }

	         String fullEntityClassName = name;

	         String entitiyClassName = name.substring(pos + 1);

	         String modelSetClassName = entitiyClassName + "Set";

	         String fileName = packageName + "." + modelSetClassName;

	         fileName = fileName.replaceAll("\\.", "/");

	         fileName = rootDir + "/" + fileName + ".java";

	         File modelSetJavaFile = new File(fileName);

	         FeatureProperty feature = ((ClassModel) model.getClassModel()).getFeature(Feature.SERIALIZATION);
	         
	         if (!modelSetJavaFile.exists()  && feature != null) {
	            HashSet<String> featureSet = feature.getPath();

	            for (String featureValue : featureSet)
	            {
	               String alternativePackageName = featureValue;
	               String alternativeFileName = alternativePackageName + "." + modelSetClassName;
	               alternativeFileName = alternativeFileName.replaceAll("\\.", "/");
	               alternativeFileName = rootDir + "/" + alternativeFileName + ".java";
	               File alternativeJavaFile = new File(alternativeFileName);

	               if (alternativeJavaFile.exists())
	               {
	                  fileName = alternativeFileName;
	                  modelSetJavaFile = alternativeJavaFile;
	                  break;
	               }
	            }
	         }

	         modelSetParser = new Parser()
	            .withFileName(fileName);

	         // found old one?
	         if (modelSetJavaFile.exists() && !isShowDiff())
	         {
	            modelSetParser.withFileBody(CGUtil.readFile(modelSetJavaFile));
	         }
	         else
	         {
	            StringBuilder text = new StringBuilder("" +
	               "package packageName;\n" +
	               "\n" +
	               "import sdmsetimport;\n" +
	               "import fullEntityClassName;\n" +
	               "\n" +
	               "public class modelSetClassName extends SDMSet<entitiyClassName>\n" +
	               "{\n" +
	               "}\n");

	            CGUtil.replaceAll(text,
	               "modelSetClassName", modelSetClassName,
	               "entitiyClassName", entitiyClassName,
	               "fullEntityClassName", fullEntityClassName,
	               "packageName", packageName,
	               "sdmsetimport", SDMSet.class.getName(),
	               "Item", entitiyClassName
	               );
	            modelSetParser.withFileBody(text).withFileChanged(true);
	         }
	         insertLicense(modelSetParser);
	         insertEmptySetDecl(modelSetParser, modelSetClassName);
	         insertSetEntryType(modelSetParser);
//	         insertSetWithWithout(modelSetParser);
	      }

	      return modelSetParser;
	   }
	
	private void insertEmptySetDecl(Parser parser, String modelSetClassName) {
		int partnerPos = parser.indexOf(Parser.ATTRIBUTE + ":EMPTY_SET");

		if (partnerPos < 0) {
			// add attribute declaration in class file
			partnerPos = parser.indexOf(Parser.CLASS_END);

			StringBuilder partnerText = new StringBuilder(
					"\n   public static final type EMPTY_SET = new type().withFlag(type.READONLY);" + "\n");

			CGUtil.replaceAll(partnerText, "type", modelSetClassName);

			parser.insert(partnerPos, partnerText.toString());
		}
	}

	private void insertSetEntryType(Parser parser) {
		String searchString = Parser.METHOD + ":getEntryType()";
		int pos = parser.indexOf(searchString);

		if (pos < 0) {
			StringBuilder text = new StringBuilder("\n\n" + "   public String getEntryType()\n" + "   {\n"
					+ "      return \"ModelType\";\n" + "   }\n");

			CGUtil.replaceAll(text, "ModelType", model.getName(false));

			pos = parser.indexOf(Parser.CLASS_END);

			parser.insert(pos, text.toString());
		}
	}

	private void insertValues() {
		parser.parse();
		int enumCurrentPos = -1;
		String signature = Parser.ENUMVALUE + ":";
		ArrayList<SymTabEntry> enumEntriesInSymTab = parser.getSymTabEntriesFor(signature);
		
		HashSet<String> knownValues = new HashSet<String>();
		for (SymTabEntry symTabEnumEntry : enumEntriesInSymTab) {
			int endPos = symTabEnumEntry.getEndPos();
			if (endPos > -1 && endPos < enumCurrentPos) 
			{
			   enumCurrentPos = endPos;
			}
			String memberName = symTabEnumEntry.getMemberName();
			if (memberName.startsWith("_"))
			{
			   memberName = memberName.substring(1);
			}
         knownValues.add(memberName);
		}
		
		SimpleSet<Literal> values = model.getValues();
		for(int i=0;i<values.size();i++) {
			Literal valueNames =values.get(i);
			if (! knownValues.contains(valueNames.getName()))
			{
			   enumCurrentPos = insertValue(valueNames, enumCurrentPos, i == values.size() - 1);
			}
		}
	}
	
	private void generateAttributes(String rootDir, String helpersDir)
	   {
	      for (Attribute attr : model.getAttributes())
	      {
	         if ("PropertyChangeSupport".equals(attr.getType()))
	            continue;
	         
	         getGenerator(attr).generate(rootDir, helpersDir, false);
	      }
	}
	

	private int insertValue(Literal value, int enumCurrentPos, boolean end) {
		StringBuilder sb = new StringBuilder(value.getName());
		if (sb.toString().matches("-?\\d+(.\\d+)?")) {
			sb = sb.insert(0, "_");
		}
		// add Values to Enumeration
		SimpleList<Object> values = value.getValues();
		if(values != null) {
			sb.append("(");
			for (int i = 0; i < values.size(); i++) {
				if(i>0) {
					sb.append(", ");
				}
				sb.append(values.get(i).toString());
			}
			sb.append(")");
		}
		
		if (enumCurrentPos < 0) {
			enumCurrentPos = parser.indexOf(Parser.ENUM);
			enumCurrentPos = parser.search(Parser.ENUM, enumCurrentPos);
			
			if (enumCurrentPos > -1) {
				enumCurrentPos = parser.search("{", enumCurrentPos);
			}
		}
		
		String enumeration = sb.toString(); 
		String text = (end) ? "\n		" + enumeration + ";" : "\n		" + enumeration + ",";

		parser.insert(enumCurrentPos+1, text);
		return enumCurrentPos+ text.length();
	}

	public Parser getOrCreateParser(String rootDir) {
		if (parser == null) {
			// try to find existing file
			String name = model.getName(false);
			int pos = name.lastIndexOf('.');

			String packageName = name.substring(0, pos);
			String fileName = name;

			String className = name.substring(pos + 1);

			fileName = fileName.replaceAll("\\.", "/");

			fileName = rootDir + "/" + fileName + ".java";

			File javaFile = new File(fileName);

			parser = new Parser().withFileName(fileName);
			// found old one?
			if (javaFile.exists() && !isShowDiff()) {
				parser.withFileBody(CGUtil.readFile(javaFile));
			} else {
				parser.replaceAll("" + "package packageName;\n" + "\n"
						+ "public enum enumName\n" + "{\n" + "}\n", "enumName",
						className, "packageName", packageName);
				parser.withFileChanged(true);
			}
		}

		return parser;
	}

	public boolean isShowDiff() {
		ClassModel model = (ClassModel) getModel().getClassModel();
		if (model != null) {
			return model.getGenerator().getShowDiff() != DIFF.NONE;
		}
		return false;
	}

	public void printFile(Parser parser) {
		if (!isShowDiff()) {
			CGUtil.printFile(parser);
		}
	}
	@Override
	ClassModel getClazz() {
		return (ClassModel) this.getModel().getClassModel();
	}

}
