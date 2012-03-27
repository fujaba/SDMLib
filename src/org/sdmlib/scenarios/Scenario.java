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


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;



public class Scenario 
{
   private String name; 
   
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
   
   public Scenario(String name)
   {
      setName(name);
      
      steps.add(name);
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
            .withHoursRemainingInTotal(newLogEntry.getHoursRemainingInTotal())
            .withHoursSpend(newLogEntry.getHoursSpend())
            .withPhase(newLogEntry.getPhase());

            // remove from newLogEntries
            newLogEntries.remove(oldDate);
         }
      }    
      
      for (String key : newLogEntries.keySet())
      {
         LogEntry newLogEntry = newLogEntries.get(key);
         kanbanEntry.addToLogEntries(newLogEntry);
      }
      
      // generate the html text
		String htmlText = "<html>\n" +
            "<body>\n" +
            "<p>Scenario scenarioName</p>\n" +
            "$text\n" +
            "</body>\n" +
            "</html>\n";

		String scenarioName = (String) steps.get(0);
		htmlText = htmlText.replaceFirst("scenarioName", scenarioName);
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
         BufferedWriter out = new BufferedWriter(new FileWriter("doc\\" + imgName));

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
}
