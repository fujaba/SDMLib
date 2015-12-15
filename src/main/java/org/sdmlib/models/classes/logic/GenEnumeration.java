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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.sdmlib.CGUtil;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;
import org.sdmlib.models.classes.util.ClazzSet;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.GraphLiteral;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.list.SimpleSet;

public class GenEnumeration extends Generator<Clazz>{
	private Parser parser = null;

	public void generate(String rootDir, String helpersDir) {

		if (!model.isExternal()) {
			getOrCreateParser(rootDir);
			insertLicense(parser);
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

	private void insertImport(String fullName) {
		if ("String int double float boolean void".indexOf(fullName) >= 0) {
			return;
		}

		int pos = parser.indexOf(Parser.IMPORT);

		String prefix = "";
		if (parser.search(Parser.IMPORT, pos) < 0) {
			prefix = "\n";
		}

		SymTabEntry symTabEntry = parser.getSymTab().get(
				Parser.IMPORT + ":" + fullName);
		if (symTabEntry == null) {
			parser.insert(parser.getEndOfImports() + 1, prefix + "\nimport "
					+ fullName + ";");
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
		
		for (GraphLiteral valueNames : model.getValues()) {
			for (int i = 0; i < valueNames.getValues().size(); i++) {
				
				Object value = valueNames.getValues().get(i);
				if (symTabContains(enumEntriesInSymTab, value ))
					continue;
				enumCurrentPos = insertValue((String) value, enumCurrentPos, (i == valueNames.getValues().size()-1 && isNew) ? true : false);
			}
		}	
		
	}

	private boolean symTabContains(ArrayList<SymTabEntry> enumEntriesInSymTab,
			Object value) {
		String valueString = String.valueOf(value);
		
		if (valueString.matches("-?\\d+(.\\d+)?")) {
			value = "_" + value;
		}
		
		for (SymTabEntry symTabEnumEntry : enumEntriesInSymTab) {
			
			if (symTabEnumEntry.getMemberName().equals(value)) {
				return true;
			}
		}
		return false;
	}

	private int insertValue(String value, int enumCurrentPos, boolean end) {

		if (value.matches("-?\\d+(.\\d+)?")) {
			value = "_" + value;
		}
		if (enumCurrentPos < 0) {
			enumCurrentPos = parser.indexOf(Parser.ENUM);
			enumCurrentPos = parser.search(Parser.ENUM, enumCurrentPos);
			
			if (enumCurrentPos > -1) {
				enumCurrentPos = parser.search("{", enumCurrentPos);
			}
		}

		String text = (end) ? "\n		" + value + ";" : "\n		" + value + ",";

		parser.insert(enumCurrentPos+1, text);
		return enumCurrentPos+ text.length();
	}

	private void insertLicense(Parser parser) {
		// file should start with head comment
		int pos = parser.search("/*");
		if (pos < 0 || pos > 20) {
			// insert MIT License otherwise.
			String year = new SimpleDateFormat("yyyy").format(new Date(System
					.currentTimeMillis()));
			parser.replaceAll(
					0,
					"/*\n"
							+ "   Copyright (c) <year> <developer> \n"
							+ "   \n"
							+ "   Permission is hereby granted, free of charge, to any person obtaining a copy of this software \n"
							+ "   and associated documentation files (the \"Software\"), to deal in the Software without restriction, \n"
							+ "   including without limitation the rights to use, copy, modify, merge, publish, distribute, \n"
							+ "   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is \n"
							+ "   furnished to do so, subject to the following conditions: \n"
							+ "   \n"
							+ "   The above copyright notice and this permission notice shall be included in all copies or \n"
							+ "   substantial portions of the Software. \n"
							+ "   \n"
							+ "   The Software shall be used for Good, not Evil. \n"
							+ "   \n"
							+ "   THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING \n"
							+ "   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND \n"
							+ "   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, \n"
							+ "   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, \n"
							+ "   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. \n"
							+ " */\n" + "   \n", "<year>", year, "<developer>",
					System.getProperty("user.name"));
		}

	}

	public Parser getParser() {
		return parser;
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
