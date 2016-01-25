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
import java.util.LinkedHashMap;
import org.sdmlib.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

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

	private void insertValues() {
		parser.parse();
		int enumCurrentPos = -1;
		String signature = Parser.ENUMVALUE + ":";
		ArrayList<SymTabEntry> enumEntriesInSymTab = parser.getSymTabEntriesFor(signature);
		
		for (SymTabEntry symTabEnumEntry : enumEntriesInSymTab) {
			int endPos = symTabEnumEntry.getEndPos();
			if (endPos > -1 && endPos < enumCurrentPos)
				enumCurrentPos = endPos;
		}
		
		boolean isNew = false;
		if (enumCurrentPos < 0)
			isNew  = true;
		
		for (Literal valueNames : model.getValues()) {
			enumCurrentPos = insertValue(valueNames, enumCurrentPos, true);
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
