/*
   Copyright (c) 2012 Albert Zündorf

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

package org.sdmlib.scenarios;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.junit.Assert;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.codegen.Parser;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;



public class Scenario 
{
   private String name;

   private String javaTestFileName; 
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public Scenario withName(String name)
   {
      setName(name);
      return this;
   }
   
   public Scenario()
   {
      
   }
   
   public Scenario(String rootDir, String name)
   {
      setName(name);
      
      steps.add(name);
      
      try
      {
         throw new RuntimeException();
      }
      catch (Exception e)
      {
         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[1];
         javaTestFileName = "../" + rootDir + "/" + callEntry.getClassName().replaceAll("\\.", "/") + ".java";
      }

   }
   
	public Vector<String> steps = new Vector<String>();

	private static String backlog = "backlog";;

	public void dumpHTML(KanbanEntry kanbanBoard) 
	{
	   // get kanbanEntry
		KanbanEntry kanbanEntry = kanbanBoard.findOldEntry(this.getName()); 
		
      if (kanbanEntry == null)
		{
         Date today = new Date(System.currentTimeMillis());
         SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
         String todayString = df.format(today);
		   kanbanEntry = new KanbanEntry()
		   .withName(this.getName())
		   .withPhase(backlog)
		   .withParent(kanbanBoard)
		   .withLogEntries(		         
		      new LogEntry()
		      .withDate(todayString)
		      .withPhase(backlog)
		      .withDeveloper(System.getProperty("user.name"))
		      .withHoursRemainingInTotal(0.0));
		}
      
      // compute total remaining time
      double sumOfRemainingTime = 0.0;
      for (LogEntry newEntry : newLogEntries.values())
      {
         sumOfRemainingTime += newEntry.getHoursRemainingInTotal();
      }
		
      // update log entries
      for (LogEntry oldEntry : kanbanEntry.getLogEntries())
      {
         // do I have a new entry for this date
         String oldDate = oldEntry.getDate();
         LogEntry newLogEntry = newLogEntries.get(oldDate);

         if (newLogEntry != null)
         {
            // transfer values
            oldEntry.withDeveloper(newLogEntry.getDeveloper())
            .withHoursRemainingInPhase(newLogEntry.getHoursRemainingInPhase())
            .withHoursRemainingInTotal(sumOfRemainingTime)
            .withHoursSpend(newLogEntry.getHoursSpend())
            .withPhase(newLogEntry.getPhase());

            // remove from newLogEntries
            newLogEntries.remove(oldDate);
         }
      }    
      
      for (String key : newLogEntries.keySet())
      {
         LogEntry newLogEntry = newLogEntries.get(key);
         newLogEntry.setHoursRemainingInTotal(sumOfRemainingTime);
         kanbanEntry.addToLogEntries(newLogEntry);
      }
      
      // generate the html text
		String htmlText = "<html>\n" +
            "<body>\n" +
            "<p>Scenario <a href='testfilename' type='text/x-java'>scenarioName</a></p>\n" +
            "$text\n" +
            "</body>\n" +
            "</html>\n";

		String scenarioName = (String) steps.get(0);
		
		htmlText = htmlText.replaceFirst("scenarioName", scenarioName);
		htmlText = htmlText.replaceFirst("testfilename", javaTestFileName);
      kanbanEntry.setName(scenarioName);
		
		String shortFileName = "" + scenarioName + ".html";
		String pathname = "doc/" + shortFileName;

		StringBuffer text = new StringBuffer();
		
		for (int i = 1; i < steps.size(); i++)
		{
			// steps may be strings
			Object object = steps.get(i);

			if (object instanceof String)
			{
				String content = (String) object;

				if (content.startsWith("["))
				{
					// yuml object diagram text, wrap into img
					String imgText = "<img src='http://yuml.me/diagram/scale:80/class/objDiagramText' />\n";
					imgText = imgText.replaceFirst("objDiagramText", content);
					text.append(imgText);
				}
				else if (content.startsWith("<"))
            {
				   // already html
				   text.append(content);
            }
				else if (content.startsWith("screendump="))
				{
					String[] split = content.split("=");
					content = split.clone()[1];
					String imgText = "<img src='" + content + "' />\n";
					
					text.append(imgText);
				}
				else
				{
					text.append("<pre>" + object + "</pre>\n");
				}
			}
			else 
			{
				// in general serialize it to JSON
				String json = "don't know what to do"; // new JsonObject(object).toString(2);
				text.append("<pre>" + json + "</pre>\n");
			}
		} // for
		
		htmlText = htmlText.replaceFirst("\\$text", text.toString());
		
		writeToFile(shortFileName, htmlText);
	}
	
   private void writeToFile(String imgName, String fileText)
   {
      try
      {
         BufferedWriter out = new BufferedWriter(new FileWriter("doc/" + imgName));

         out.write(fileText);
         out.close();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   


   public void add(String string)
   {
      steps.add(string);      
   }

   public void addObjectDiag(JsonIdMap jsonIdMap, Object root)
   {
      // TODO Auto-generated method stub
      JsonArray jsonArray = jsonIdMap.toJsonArray(root);
      
      String imgLink = JsonToImg.get().toImg(this.getName() + (this.steps.size()+1), jsonArray);
      
      steps.add(imgLink);
   }

   public void setKanbanPhase(String string)
   {
      // TODO Auto-generated method stub
      
   }
   
   private LinkedHashMap<String,LogEntry> newLogEntries = new LinkedHashMap<String,LogEntry>();

   public LinkedHashMap<String, LogEntry> getNewLogEntries()
   {
      return newLogEntries;
   }
   
   public void addLogEntry(LogEntry entry)
   {
      newLogEntries.put(entry.getDate(), entry);
   }

   public void addImage(String imageFile)
   {
      steps.add("<img src='" + imageFile + "'>");     
   }

   public void add(String string, String phase, String developer, String date, double hoursSpend, double hoursRemaining)
   {
      add(string);
      addLogEntry(new LogEntry()
      .withDate(date)
      .withPhase(phase)
      .withDeveloper(developer)
      .withHoursSpend(hoursSpend)
      .withHoursRemainingInTotal(hoursRemaining)
      .withComment("Achieved: " + string));
   }

   private int codeStartLineNumber = -1;

   private ByteArrayOutputStream systemOutRecorder;
   
   public ByteArrayOutputStream getSystemOut()
   {
      return systemOutRecorder;
   }
   
   public void markCodeStart()
   {
      // store code start line number
      try
      {
         throw new RuntimeException();
      }
      catch (Exception e)
      {
         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[1];
         codeStartLineNumber = callEntry.getLineNumber();
      }
      
   }

   public void addCode(String rootDir)
   {
      String className = "";
      // store code end line number
      int codeEndLineNumber = -1;
      try
      {
         throw new RuntimeException();
      }
      catch (Exception e)
      {
         StackTraceElement[] stackTrace = e.getStackTrace();
         StackTraceElement callEntry = stackTrace[1];
         codeEndLineNumber = callEntry.getLineNumber();
         
         className = callEntry.getClassName();
      }
      
      // open java file and copy code lines
      String fileName = rootDir + "/" + className.replaceAll("\\.", "/") + ".java";
      File file = new File(fileName);
      
      if (file.exists())
      {
         try
         {
            BufferedReader in = new BufferedReader(new FileReader(file));
            
            String line = "";
            int lineNo = 0;
            
            StringBuilder buf = new StringBuilder();
            
            while (true)
            {
               line = in.readLine();
               
               if (line != null)
               {
                  lineNo++;
                  
                  if (lineNo > codeStartLineNumber && lineNo < codeEndLineNumber)
                  {
                     buf.append(line).append('\n');
                  }
                  
                  if (lineNo >= codeEndLineNumber)
                  {
                     this.add(buf.toString());
                     return;
                  }
               }
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

   public void recordSystemOut()
   {
      systemOutRecorder = new ByteArrayOutputStream();
      System.setOut(new PrintStream(systemOutRecorder));      
   }

   public String getMethodText(String rootDir, String className, String methodSignature)
   {
      ClassModel model = new ClassModel();
      
      Clazz clazz = new Clazz(className);
      
      Parser parser = clazz.getOrCreateParser(rootDir);
      
      int pos = parser.indexOf(Parser.METHOD + ":" + methodSignature);

      SymTabEntry symTabEntry = parser.getSymTab().get(Parser.METHOD + ":" + methodSignature);

      String methodText = "   " + parser.getFileBody().substring(symTabEntry.getStartPos(), symTabEntry.getEndPos()+1);

      return methodText;
   }
}
