/*
   Copyright (c) 2012 Albert Z�ndorf

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.sdmlib.scenarios.creators.KanbanEntryCreator;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonFilter;
import org.sdmlib.serialization.json.JsonIdComparator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.serialization.json.JsonSortedArray;

public class ScenarioManager 
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   private static final String DOC_KANBAN_ENTRIES_JSON = "doc/kanbanEntries.json";

   private static ScenarioManager theCatalog = null;
	
	public static ScenarioManager get()
	{
		if (theCatalog == null)
		{
			theCatalog = new ScenarioManager();
			theCatalog.getPhases().add(ACTIVE);
			theCatalog.getPhases().add(BACKLOG);
			theCatalog.getPhases().add(MODELING);
			theCatalog.getPhases().add(IMPLEMENTATION);
			theCatalog.getPhases().add(DONE);
		}
		return theCatalog;
	}
	
	public Vector<Scenario> scenarios = new Vector<Scenario>();
	
	public TreeMap<String, TreeSet<String>> msgUsages = new TreeMap<String, TreeSet<String>>();
	
	public void addToMsgUsages(String type, String pathname) 
	{
		TreeSet<String> treeSet = msgUsages.get(type);
		
		if (treeSet == null)
		{
			treeSet = new TreeSet<String>();
			msgUsages.put(type, treeSet);
		}
		treeSet.add(pathname);
	}
	
	private KanbanEntry kanbanBoard;

   private JsonIdMap kanbanIdMap;

   private static LinkedHashSet<String> phases = new LinkedHashSet<String>(); 
	
   public LinkedHashSet<String> getPhases()
   {
      return phases;
   }
	
   public void dumpHTML()
   {
      loadOldKanbanEntries();
      
      new File("doc").mkdirs();
      
      dumpKanban();
   }
   
	public void dumpKanban() 
	{
		// dump the scenarios
		TreeSet<String> fileSet = new TreeSet<String>();
		for (Scenario scenario : scenarios) 
		{
			// get first component			
			scenario.dumpHTML(kanbanBoard);
			String filename = scenario.getName();
			fileSet.add(filename);
		}
		
		refColumnBody = new StringBuffer();
		
		dumpKanbanBoard();
		
		dumpIndexHtml();
		
		refColumnBody.append("<br>");
		
		LinkedHashSet<KanbanEntry> allEntries = collectEntriesFromTree(kanbanBoard);
		
		for (KanbanEntry entry : allEntries)
      {
         if (entry.getSubentries().isEmpty())
         {
            refColumnBody.append(refForFile(entry.getName()));
         }
      }
		
		// build index 
		String refHtml = "<html>\n<body>\ntext\n</body>\n</html>\n";
		
		refHtml = refHtml.replaceFirst("text", refColumnBody.toString());
				
		File file = new File ("doc/refs.html");
		try {
			PrintStream out = new PrintStream(file);
			out.println(refHtml);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		dumpKanbanEntriesToJson();
	}
	
	private void dumpIndexHtml()
   {
      // ensure index.html
	   new File("doc").mkdirs();
	   File file = new File("doc/index.html");
	   
	   if ( ! file.exists())
	   {
	      String text = 
	            "<html>\n" +
	      		"<frameset cols='250,*'>\n" +
	      		"<frame src='refs.html' name='Index'>\n" +
	      		"<frame name='Main'>a</frame>\n" +
	      		"<noframes>\n" +
	      		"  <body>\n" +
	      		"     <h2><projectTitle></h2>\n" +
	      		"        <p><a href='refs.html'>Index</a> <a href='refs.html'>Main</a></p>\n" +
	      		"  </body>\n" +
	      		"</noframes>\n" +
	      		"</frameset>\n" +
	      		"</html>\n";
	      
	    text = text.replaceFirst("<projectTitle>", kanbanBoard.getName());
	    
	    printFile(file, text);
	   }
   }

   private void dumpKanbanEntriesToJson()
   {
      // store json data
      JsonSortedArray jsonSortedArray = new JsonSortedArray(new JsonIdComparator(), JsonIdMap.JSON_ID);
	   JsonArray jsonArray = kanbanIdMap.toJsonArray(kanbanBoard, jsonSortedArray, new JsonFilter());
	   String jsonString = jsonArray.toString(2);
     
	   printFile(new File(DOC_KANBAN_ENTRIES_JSON), jsonString);
     
	   // generate html table for json data
	   String htmlTableText = "<html>\n<body>\n</body>\n</html>\n";
	   
	   StringBuilder buf = new StringBuilder();
	   // iterate through object
	   for (int i = 0; i < jsonArray.length(); i++)
      {
         JsonObject jsonObject = jsonArray.getJSONObject(i);
         
         String objectLine = "<a name='objectId'><p></p></a>\n";
         objectLine = objectLine.replaceFirst("objectId", jsonObject.getString(JsonIdMap.JSON_ID));
         
         // iterate through keys
         for (Iterator<String> iter = jsonObject.keys(); iter.hasNext();)
         {
            
            String key = (String) iter.next();
            
            String cellString =  key + ": ";
            Object object = jsonObject.get(key);
            
            if (object.toString().startsWith("KE."))
            {
               // its a reference create a link
               cellString += "<a href='#" + object.toString() + "'>"+ object.toString() + "</a> ";
            }
            else if (object instanceof JsonArray)
            {
               // list of references
               cellString += "";
               for (int j = 0; j < ((JsonArray) object).length(); j++)
               {
                  String ref = ((JsonArray) object).getString(j);
                  cellString += "<a href='#" + ref + "'>"+ ref + "</a> ";
               }
            }
            else
            {
               cellString += object.toString() + " ";
            }
            objectLine = objectLine.replaceFirst("</p>", cellString+"<br/></p>");
         }
         buf.append(objectLine);
      }
	   
	   htmlTableText = htmlTableText.replaceFirst("</body>", buf.toString()+"</body>");
	   
	   printFile(new File("doc/objectData.html"), htmlTableText);
	     
   }

   public KanbanEntry loadOldKanbanEntries() 
	{
		// load catalog
		File file = new File (DOC_KANBAN_ENTRIES_JSON);
		
      kanbanIdMap = KanbanEntryCreator.createIdMap("KE");
      
		try 
		{
			URL catalogURL = file.toURI().toURL();
			StringBuffer buf = new StringBuffer();
			InputStream in = catalogURL.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while (true)
			{
				String readLine = reader.readLine();
				
				if (readLine == null) break; 
				
				buf.append(readLine + '\n');
			}
			
			JsonArray jsonObject = new JsonArray(buf.toString());
			
			kanbanBoard = (KanbanEntry) kanbanIdMap.readJson(jsonObject);
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
		}
		
      if (kanbanBoard == null)
      {
         kanbanBoard = new KanbanEntry()
         .withName("Project")
         .withPhase("active");
      }

      return kanbanBoard;
	}


		
	private void dumpKanbanBoard()
	{
		String userName = System.getProperty("user.name");
		
      collectHours(kanbanBoard);
      
		// store entries into file for next run
		JsonArray jsonArray = kanbanIdMap.toJsonSortedArray(kanbanBoard, JsonIdMap.JSON_ID);
		String text = jsonArray.toString(2);
		File file = new File (DOC_KANBAN_ENTRIES_JSON);
		printFile(file, text);
		
		// collect subentries from tree structure
		LinkedHashSet<KanbanEntry> allEntries = collectEntriesFromTree(kanbanBoard);		
		
		for (KanbanEntry kanbanEntry : allEntries)
      {
		   LogEntry lastLogEntry = null;
		   double sumOfHoursSpend = 0;
		   
		   // initialize new entries
		   if (kanbanEntry.getPhase() == null)
		   {
		      kanbanEntry.setPhase("backlog");
		   }
		   
		   // prepare burnup chart series
		   TimeSeries hoursEstimatedSeries = new TimeSeries("Hours Estimated");
		   TimeSeries hoursSpendSeries = new TimeSeries("Hours Spend");
         
		   for (LogEntry logEntry : kanbanEntry.getLogEntries())
		   {
		      phases.add(logEntry.getPhase());
		      lastLogEntry = logEntry;
		      sumOfHoursSpend += logEntry.getHoursSpend();

		      try
		      {
		         repairDate(logEntry);

		         Date theDate = dateParser.parse(logEntry.getDate());
		         Second theDay = new Second(theDate);
		         double hoursEstimated = sumOfHoursSpend
		               + Math.max(logEntry.getHoursRemainingInPhase(),
		                  logEntry.getHoursRemainingInTotal());
		         hoursEstimatedSeries.addOrUpdate(theDay, hoursEstimated);
		         hoursSpendSeries.addOrUpdate(theDay, sumOfHoursSpend);
		      }
		      catch (ParseException e)
		      {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		   }
		   
		   // create burnup chart for kanban entry
		   createBurnupChart(kanbanEntry, hoursEstimatedSeries, hoursSpendSeries);

		   if (lastLogEntry != null)
         {
            kanbanEntry
                  .withLastDeveloper(lastLogEntry.getDeveloper())
                  .withPhase(lastLogEntry.getPhase())
                  .withHoursSpend(sumOfHoursSpend)
                  .withHoursRemaining(
                        Math.max(lastLogEntry.getHoursRemainingInPhase(),
                              lastLogEntry.getHoursRemainingInTotal()));
         }
      }
		
		dumpTimeLinesFor(allEntries);

		dumpBoardForKanbanEntry(kanbanBoard, kanbanBoard.getName()+"kanban");
	}

   private void dumpTimeLinesFor(LinkedHashSet<KanbanEntry> allEntries)
   {
      // for each entry, collect log entries ordered by time stamps
      for (KanbanEntry kanbanEntry : allEntries)
      {
         StringBuilder text = new StringBuilder();
         // for entry node, dump html page
         for (LogEntry logEntry : kanbanEntry.getLogEntries())
         {
            String logLine = "<p>time developer hours spend: hoursspend hours remaining: hoursremaining comment</p>\n";
            logLine = logLine.replaceFirst("time", ""+logEntry.getDate());
            logLine = logLine.replaceFirst("developer", ""+logEntry.getDeveloper());
            logLine = logLine.replaceFirst("hoursspend", ""+logEntry.getHoursSpend());
            logLine = logLine.replaceFirst("hoursremaining", ""+logEntry.getHoursRemainingInTotal());
            logLine = logLine.replaceFirst("time", ""+logEntry.getDate());
            logLine = logLine.replaceFirst("comment", ""+logEntry.getComment());

            text.append(logLine);
         }
         
         printFile(
               new File("doc/"+kanbanEntry.getName()+"TimeLine.html"), 
               "<html>\n<body>\n" +
               "<p>Time line for " + kanbanEntry.getName() + "</p>" + 
               text.toString() + 
               "</body>\n</html>");
      }
      
      
      
   }

   private void dumpBoardForKanbanEntry(KanbanEntry rootEntry, String boardName)
   {
      LinkedHashSet<KanbanEntry> allEntries = collectEntriesFromTree(rootEntry);

      // generate the kanban board
		String buf = "<html>\n<body>\n<table border='3' frame='box'>\n<headerRow>\n<tableBody></table>\n</body>\n</html>";
		
		String headerRow = "<tr> </tr>";
      String tableBody = "<tr> </tr>\n";

      for (String phase : phases)
      {
         headerRow = headerRow.replaceFirst("</tr>", "<th>" + phase + "</th> </tr>");
         tableBody = tableBody.replaceFirst("</tr>", "<td valign='top'><table border ='0'>" + phase + "</table></td>\n </tr>");
      }
      
      buf = buf.replaceFirst("<headerRow>", headerRow);
      
		for (KanbanEntry entry : allEntries)
		{
			String phaseName = entry.getPhase();

			String cellText = "<tr><td><table border='1' rules='none' bgcolor='#fafad2'></table><tr><td>\n";
			
			if ( ! entry.getSubentries().isEmpty())
			{
			   // create link to sprint kanban board
            cellText = cellText.replaceFirst("</table>", 
                  "<tr><td><a href='" + entry.getName() + "kanban.html'>" + 
                        entry.getName()+"</a></td></tr>\n</table>");
			}
			else 
			{
            cellText = cellText.replaceFirst("</table>", 
                  "<tr><td><a href='" + entry.getName() + ".html'>" + 
                        entry.getName()+"</a></td></tr>\n</table>");
			}
			
			if (entry.getParent() != null)
         {
            cellText = cellText.replaceFirst("</table>", 
                  "<tr><td><a href='"+entry.getParent().getName()+"kanban.html'>"
                  +entry.getParent().getName()+"</a></td></tr>\n</table>");
         }
         cellText = cellText.replaceFirst("</table>", "<tr><td>developer = "+entry.getLastDeveloper()+"</td></tr>\n</table>");
         cellText = cellText.replaceFirst("</table>", "<tr><td>hours spend = "+entry.getHoursSpend()+"</td></tr>\n</table>");
         cellText = cellText.replaceFirst("</table>", "<tr><td>hours remaining = "+entry.getHoursRemaining()+"</td></tr>\n</table>");
         cellText = cellText.replaceFirst("</table>", "<tr><td><a href='" + entry.getName()+
         		"BurnupChart.png'>burnup</a></td></tr>\n</table>");
         cellText = cellText.replaceFirst("</table>", "<tr><td><a href='" + entry.getName()+
               "TimeLine.html'>time line</a></td></tr>\n</table>");
         
			tableBody = tableBody.replaceFirst(phaseName + "</table>", cellText + phaseName + "</table>");
			
			if (entry != rootEntry && ! entry.getSubentries().isEmpty())
			{
			    dumpBoardForKanbanEntry(entry, entry.getName()+"kanban");
			}			
		}	
		
		// remove column placeholders
		for (String phase : phases)
      {
         tableBody = tableBody.replaceFirst(phase+"</table>", "</table>");
      }
		
		buf = buf.replaceFirst("<tableBody>", tableBody);		
		
		File kanbanFile = new File ("doc/" + boardName + ".html");
		printFile(kanbanFile, buf);
		
		refColumnBody.insert(0, refForFile(boardName));

   }

   private void collectHours(KanbanEntry rootEntry)
   {
      double hoursSpendSum = 0;
      double hoursRemainingSum = 0;
      
      if (! rootEntry.getSubentries().isEmpty())
      {
         LogEntry latestLogEntry = null;
         for (KanbanEntry subentry : rootEntry.getSubentries())
         {
            collectHours(subentry);
            hoursSpendSum += subentry.getHoursSpend();
            hoursRemainingSum += subentry.getHoursRemaining();
            latestLogEntry = findLatestLogEntry(latestLogEntry, subentry);
         }
         
         double oldHoursSpend = rootEntry.getHoursSpend();
         double oldHoursRemaining = rootEntry.getHoursRemaining();
         
         if (hoursSpendSum != oldHoursSpend || hoursRemainingSum != oldHoursRemaining)
         {
            // store new hours
            rootEntry.withHoursSpend(hoursSpendSum)
            .withHoursRemaining(hoursRemainingSum);
             
            // create log entry
            String latestDate;
            String latestDeveloper;
            String latestComment = null;
            
            if (latestLogEntry == null)
            {
               latestDate = dateParser.format(new Date(System.currentTimeMillis()));
               latestDeveloper = System.getProperty("user.name");
            }
            else
            {
               latestDate = latestLogEntry.getDate();
               latestDeveloper = latestLogEntry.getDeveloper();
               latestComment = latestLogEntry.getKanbanEntry().getName() + ": " 
                     + latestLogEntry.getComment();
            }
            
            LogEntry newLogEntry = new LogEntry()
            .withHoursSpend(hoursSpendSum-oldHoursSpend)
            .withHoursRemainingInTotal(hoursRemainingSum)
            .withDate(latestDate)
            .withDeveloper(latestDeveloper)
            .withPhase(rootEntry.getPhase())
            .withComment(latestComment)
            .withKanbanEntry(rootEntry);
         }
      }
      else // no subentries
      {         
         // collect hours from log entries
         double logHoursSpend = 0.0;
         double logHoursRemaining = 0.0;
         
         for (LogEntry logEntry : rootEntry.getLogEntries())
         {
            logHoursSpend += logEntry.getHoursSpend();
            logHoursRemaining = Math.max(logEntry.getHoursRemainingInPhase(), logEntry.getHoursRemainingInTotal());
         }
 
         hoursSpendSum = logHoursSpend;
         hoursRemainingSum = logHoursRemaining;
      }
         
      rootEntry.setHoursSpend(hoursSpendSum);
      rootEntry.setHoursRemaining(hoursRemainingSum);
   }

   private LogEntry findLatestLogEntry(LogEntry latestLogEntry,
         KanbanEntry subentry)
   {
      for (LogEntry logEntry : subentry.getLogEntries())
      {
         if (latestLogEntry == null)
         {
            latestLogEntry = logEntry;
         }
         else
         {
            try
            {
               repairDate(latestLogEntry);
               repairDate(logEntry);

               long latestTime = dateParser.parse(latestLogEntry.getDate()).getTime();
               long thisTime = dateParser.parse(logEntry.getDate()).getTime();

               if (thisTime > latestTime)
               {
                  latestLogEntry = logEntry;
               }
            }
            catch (ParseException e)
            {
               // not that important AZ
            }
         }
      }

      return latestLogEntry;
   }

   private void repairDate(LogEntry logEntry)
   {
      String dateText = logEntry.getDate();
      
      if (dateText == null)
      {
         dateText = dateParser.format(new Date(System.currentTimeMillis()));
         logEntry.setDate(dateText);
      }
      else 
      {
         if (dateText.indexOf(':') < 0)
         {
            dateText = dateText + " 12:00:00";
            logEntry.setDate(dateText);
         }
      }
            
      
   }

   private LinkedHashSet<KanbanEntry> collectEntriesFromTree(KanbanEntry rootEntry)
   {
      // collect subentries from tree structure
      LinkedHashSet<KanbanEntry> allEntries = new LinkedHashSet<KanbanEntry>();
      LinkedList<KanbanEntry> todo = new LinkedList<KanbanEntry>();
      todo.add(rootEntry);
      
      while ( ! todo.isEmpty())
      {
         KanbanEntry pop = todo.pop();
         allEntries.add(pop);
         if ( ! pop.getSubentries().isEmpty())
         {
            todo.addAll(pop.getSubentries());
         }
      }
      return allEntries;
   }

   private String createBurnupChart(KanbanEntry kanbanEntry,
         TimeSeries hoursEstimatedSeries, TimeSeries hoursSpendSeries)
   {
      TimeSeriesCollection dataset = new TimeSeriesCollection();
      dataset.addSeries(hoursEstimatedSeries);
      dataset.addSeries(hoursSpendSeries);
      
      // Generate the graph
      XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
      DateAxis dateaxis = new DateAxis("Date");
      NumberAxis numberaxis = new NumberAxis("Hours");
      XYPlot xyplot = new XYPlot(dataset, dateaxis, numberaxis, xyLineAndShapeRenderer);
      
      JFreeChart chart = new JFreeChart("Burnup Chart for " + kanbanEntry.getName(), xyplot);
      String chartFileName = null;
      try {
         chartFileName = "doc/" + kanbanEntry.getName()+ "BurnupChart.png";
         ChartUtilities.saveChartAsPNG(new File(chartFileName), chart, 800,
              700);
      } catch (IOException e) {
          System.err.println("Problem occurred creating chart.");
      }
      
      return chartFileName;
   }

	public String refForFile(String filename) {
		String ref = "<a href=\"filename.html\" target=\"Main\">filename</a><br>\n ";
		ref = ref.replaceAll("filename", filename);
		return ref;
	}

	protected String dumpMessagePage(Entry<String, TreeSet<String>> entry) 
	{
		String fileName = "Message_" + entry.getKey() + ".html";
		
		// list the component files
		String page = "<html>\n<body>\ntext\n</body>\n</html>\n";
		StringBuffer text = new StringBuffer();
		String header = "<h2>Messages of type name are used by components: </h2>\n";
		header = header.replaceAll("name", entry.getKey());
		text.append(header);
		
		for (String file : entry.getValue()) 
		{
			String refForFile = ScenarioManager.get().refForFile(file);
			text.append(refForFile);
		}
		
		
		
		page = page.replaceAll("text", text.toString());
		
		File file = new File ("src/smartiocatalog/" + fileName);
		printFile(file, page);
		return fileName;
	}
	
	

	public void printFile(File file, String text)
	{
		try {
		   File parentFile = file.getParentFile();
		   if ( ! parentFile.exists())
		   {
		      parentFile.mkdirs();
		   }
			PrintStream out = new PrintStream(file);
			out.println(text);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile(File file)
	{
      StringBuilder result = new StringBuilder();
	   try
      {
         BufferedReader in = new BufferedReader(new FileReader(file));
         
         String line = in.readLine();
         while (line != null)
         {
            result.append(line).append('\n');
            line = in.readLine();
         }
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
	   
	   return result.toString();
	}

   public ScenarioManager add(Scenario scenario)
   {
      // TODO Auto-generated method stub
      scenarios.add(scenario);
      return this;
   }

   private LinkedHashSet<KanbanEntry> newEntries  = new LinkedHashSet<KanbanEntry>();

   private StringBuffer refColumnBody;

   public SimpleDateFormat dateParser  = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
   
   public void addEntry(KanbanEntry sprint1)
   {
      // TODO Auto-generated method stub
      newEntries.add(sprint1);
   }

	
}
