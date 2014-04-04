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

package org.sdmlib.storyboards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.event.ListSelectionEvent;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.doc.GuiAdapter;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonArraySorted;
import org.sdmlib.serialization.json.JsonIdComparator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.storyboards.creators.KanbanEntryCreator;
import org.sdmlib.storyboards.creators.KanbanEntrySet;

public class StoryboardManager 
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   private static final String DOC_KANBAN_ENTRIES_JSON = "doc/kanbanEntries.json";

   private static StoryboardManager theCatalog = null;

   public static StoryboardManager get()
   {
      if (theCatalog == null)
      {
         theCatalog = new StoryboardManager();
         theCatalog.getPhases().add(ACTIVE);
         theCatalog.getPhases().add(BACKLOG);
         theCatalog.getPhases().add(IMPLEMENTATION);
         theCatalog.getPhases().add(DONE);
      }
      return theCatalog;
   }

   public Vector<Storyboard> storyboards = new Vector<Storyboard>();

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

      if (this.toBeRemoved != null)
      {
         KanbanEntry oldEntry = kanbanBoard.findOldEntry(this.toBeRemoved.getName());

         if (oldEntry != null)
         {
            oldEntry.removeYou();
         }

         this.toBeRemoved = null;
      }

      new File("doc").mkdirs();
      new File("doc/includes").mkdirs();

      // add javascript files
      copyDocFile("burndown", "d3.v3.js");
      copyDocFile("burndown", "nv.d3.css");
      copyDocFile("burndown", "nv.d3.js");
      copyDocFile("classmodel", "dagre.js");
      copyDocFile("classmodel", "drawer.js");
      copyDocFile("classmodel", "graph.js");
      copyDocFile("classmodel", "diagramstyle.css");

      dumpKanban();
   }

   private void copyDocFile(String dir, String file)
   {
      File target=new File("doc/includes/" + file);

      InputStream is = GuiAdapter.class.getResourceAsStream("js/" + dir + "/" + file);

      if(is!=null)
      {
         final int BUFF_SIZE = 5 * 1024; // 5KB
         final byte[] buffer = new byte[BUFF_SIZE];

         try
         {
            if(!target.exists()){
               target.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(target);

            while (true) {
               int count = is.read(buffer);
               if (count == -1)
                  break;
               out.write(buffer, 0, count);
            }
            out.close();
            is.close();
         }
         catch (Exception e)
         {
            // e.printStackTrace();
         }

      }
   }

   public void dumpKanban() 
   {
      // dump the storyboards
      TreeSet<String> fileSet = new TreeSet<String>();
      for (Storyboard storyboard : storyboards) 
      {
         // get first component			
         storyboard.dumpHTML(kanbanBoard);
         String filename = storyboard.getName();
         fileSet.add(filename);
      }

      refColumnBody = new StringBuffer();

      dumpKanbanBoard();

      dumpIndexHtml();

      refColumnBody.append("<br>");

      TreeSet<KanbanEntry> allEntries = new TreeSet<KanbanEntry>(collectEntriesFromTree(kanbanBoard));

      for (KanbanEntry entry : allEntries)
      {
         File htmlFile = new File("doc/" + entry.getName() + ".html");

         if (htmlFile.exists())
         {
            refColumnBody.append(refForFile(entry.getName()));
         }
      }

      // build index 
      String refHtml = "<html>\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n" +
            "<body>\n" + 
            "bodytext\n" + 
            "</body>\n" + 
            "</html>\n";

      refHtml = refHtml.replaceFirst("bodytext", refColumnBody.toString());

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
      new File("doc").mkdirs();

      // ensure style file
      File styleFile = new File("doc/style.css");

      if ( ! styleFile.exists())
      {
         String text = "" + 
               "BODY {color:#000000;background-color:#ffffff;font-family:Arial,Helvetica,Geneva,Sans-Serif}\n" + 
               "B {font-weight:bold;}\n" + 
               "\n" + 
               "H1 {font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" + 
               "H2 {color:#000000;font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" + 
               "H3 {color:#000000;font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" + 
               "\n" + 
               "P {font-family:Arial,Helvetica,Geneva,Sans-Serif;text-align:left;}\n" + 
               "PRE {font-family:Courier;text-align:left;font-size:12pt}\n" + 
               "\n" + 
               "TD {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" + 
               "TH {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" + 
               "\n" + 
               "DD {font-family:Arial,Helvetica,Geneva,Sans-Serif;}\n" + 
               "";

         printFile(styleFile, text);
      }

      // ensure index.html
      File file = new File("doc/index.html");

      if ( ! file.exists())
      {
         String text = 
               "<html>\n" +
                     "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\n" +
                     "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">" +
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
      JsonArraySorted jsonArray = new JsonArraySorted().withComparator(new JsonIdComparator());
      kanbanIdMap.toJsonArray(kanbanBoard, jsonArray, null);
      String jsonString = jsonArray.toString(2);

      printFile(new File(DOC_KANBAN_ENTRIES_JSON), jsonString);

      // generate html table for json data
      String htmlTableText = "<html>\n<body>\n</body>\n</html>\n";

      StringBuilder buf = new StringBuilder();
      // iterate through object
      for (int i = 0; i < jsonArray.size(); i++)
      {
         JsonObject jsonObject = jsonArray.getJSONObject(i);

         String objectLine = "<a name='objectId'><p></p></a>\n";
         objectLine = objectLine.replaceFirst("objectId", jsonObject.getString(JsonIdMap.ID));

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
               for (int j = 0; j < ((JsonArray) object).size(); j++)
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

         JsonArray jsonObject = new JsonArray().withValue(buf.toString());

         kanbanBoard = (KanbanEntry) kanbanIdMap.decode(jsonObject);
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
      JsonArraySorted jsonArray = kanbanIdMap.toJsonSortedArray(kanbanBoard, JsonIdMap.ID);
      String text = jsonArray.toString(2);
      File file = new File (DOC_KANBAN_ENTRIES_JSON);
      printFile(file, text);

      // collect subentries from tree structure
      LinkedHashSet<KanbanEntry> allEntries = collectEntriesFromTree(kanbanBoard);		

      dumpTimeLinesFor(allEntries);

      dumpBoardForKanbanEntry(kanbanBoard, kanbanBoard.getName()+"kanban");
   }

   private void dumpTimeLinesFor(LinkedHashSet<KanbanEntry> allEntries)
   {

      // for each entry, collect log entries ordered by time stamps
      for (KanbanEntry kanbanEntry : allEntries)
      {
         // burn down and time line 
         StringBuilder htmlText = new StringBuilder(
            "<html>\r\n" + 
                  "<head>\r\n" + 
                  "<meta charset=\"utf-8\">\n" +
                  "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=9\">\r\n" + 
                  "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n" + 
                  "<link href=\"includes/nv.d3.css\" rel=\"stylesheet\" type=\"text/css\">\r\n" +
                  "\r\n" + 
                  "<script src=\"includes/d3.v3.js\"></script>\r\n" + 
                  "<script src=\"includes/nv.d3.js\"></script>\r\n" + 
                  "" + 
                  "\r\n" + 



            "<script>\r\n" + 
            "data = [{ \"key\" : \"hours done\",\r\n" + 
            "   \"values\" : [\r\n" + 
            "        hoursSpendData\r\n" + 
            "      ]\r\n" + 
            "},{ \"key\" : \"hours planned\",\r\n" + 
            "   \"values\" : [\r\n" + 
            "        hoursRemainingData\r\n" + 
            "      ]\r\n" + 
            "}\r\n" + 
            "]\r\n" + 
            "\r\n" + 
            "nv.addGraph(function() {\r\n" + 
            "        var chart = nv.models.lineWithFocusChart();\r\n" + 
            "      chart.yAxis.axisLabel(\"Hours\");\r\n" + 
            "        chart.yAxis.tickFormat(d3.format(',.2f'));\r\n" + 
            "        chart.y2Axis.tickFormat(d3.format(',.2f'));\r\n" + 
            "        chart.xAxis.tickFormat(function(d) { return d3.time.format('%d %b %y')(new Date(d)) });\r\n" + 
            "        chart.x2Axis.tickFormat(function(d) { return d3.time.format('%d %b %y')(new Date(d)) });\r\n" + 
            "        \r\n" + 
            "      d3.select('#lineWithFocusChart svg')\r\n" + 
            "         .datum(data)\r\n" + 
            "         .call(chart);\r\n" + 
            "    return chart;\r\n" + 
            "});\r\n" + 
            "</script>" +

            "\r\n" + 
            "</head>\r\n" + 
            "<body>\r\n" + 
            "<p>Burn Down and Time Line for <a href='entryNameKanbanSuffix.html' type='text/x-java'>entryName</a></p>\r\n" + 
            "\r\n" + 
            "<div id=\"lineWithFocusChart\" class='with-3d-shadow with-transitions'>\r\n" + 
            "    <svg style=\"height: 700px;\"></svg>\r\n" + 
            "</div>" + 
            "\r\n" + 
            "timelineentries" + 
            "\r\n" + 
            "</body>\r\n" + 
            "</html>"
               );

         ArrayList<LogEntry> allLogEntries = kanbanEntry.getAllLogEntries();
         Collections.sort(allLogEntries);

         StringBuilder timeLogText = new StringBuilder();
         StringList hoursSpendData = new StringList();
         StringList hoursRemaingData = new StringList();

         double hoursSpend = 0.0;
         LinkedHashMap<KanbanEntry, Double> hoursRemainingMap = new LinkedHashMap<KanbanEntry, Double>();

         for (LogEntry logEntry : allLogEntries)
         {
            String logLine = "<p>time developer hours spend: hoursspend hours remaining: hoursremaining comment</p>\n";
            logLine = logLine.replaceFirst("time", ""+logEntry.getDate());
            logLine = logLine.replaceFirst("developer", ""+logEntry.getDeveloper());
            logLine = logLine.replaceFirst("hoursspend", ""+logEntry.getHoursSpend());
            logLine = logLine.replaceFirst("hoursremaining", ""+logEntry.getHoursRemainingInTotal());
            logLine = logLine.replaceFirst("time", ""+logEntry.getDate());
            logLine = logLine.replaceFirst("comment", ""+logEntry.getComment());


            timeLogText.insert(0, logLine);

            hoursSpend += logEntry.getHoursSpend();
            String dataLine = CGUtil.replaceAll(
               "{ \"x\" : millis, \"y\" : value}", 
               "millis", logEntry.getParsedDate().getTime(),
               "value", hoursSpend
                  );

            hoursSpendData.add(dataLine);

            hoursRemainingMap.put(logEntry.getKanbanEntry(), logEntry.getHoursRemainingInTotal());

            double sumOfHoursRemaining = 0;

            for (Double d : hoursRemainingMap.values())
            {
               sumOfHoursRemaining += d;
            }

            dataLine = CGUtil.replaceAll(
               "{ \"x\" : millis, \"y\" : value}", 
               "millis", logEntry.getParsedDate().getTime(),
               "value", hoursSpend + sumOfHoursRemaining 
                  );

            hoursRemaingData.add(dataLine);
         }

         String kanbanSuffix = "";
         if ( ! kanbanEntry.getSubentries().isEmpty())
         {
            kanbanSuffix = "kanban";
         }

         CGUtil.replaceAll(htmlText, 
            "entryName", kanbanEntry.getName(),
            "KanbanSuffix", kanbanSuffix,
            "hoursSpendData", hoursSpendData.concat(",\n               "),
            "hoursRemainingData", hoursRemaingData.concat(",\n               "),
            "timelineentries", timeLogText.toString()
               );

         printFile(
            new File("doc/"+kanbanEntry.getName()+"TimeLine.html"), htmlText.toString());

      }



   }

   private void dumpBoardForKanbanEntry(KanbanEntry rootEntry, String boardName)
   {
      LinkedHashSet<KanbanEntry> allEntries = collectEntriesFromTree(rootEntry);

      // generate the kanban board
      String buf = "<html>\n<body>\n<table border='3' frame='box'>\n<headerRow>\n<tableBody></table>\n</body>\n</html>";

      String headerRow = "<tr> </tr>";
      String tableBody = "<tr> </tr>\n";

      String[] split = rootEntry.getPhases().split(", ");

      for (String phase : split)
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
               "TimeLine.html'>burn down</a></td></tr>\n</table>");

         tableBody = tableBody.replaceFirst(phaseName + "</table>", cellText + phaseName + "</table>");

         if (entry != rootEntry && ! entry.getSubentries().isEmpty())
         {
            dumpBoardForKanbanEntry(entry, entry.getName()+"kanban");
         }			
      }	

      // remove column placeholders
      for (String phase : split)
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

      if (rootEntry.getPhases() == null)
      {
         rootEntry.setPhases("active, backlog, implementation, done" );
      }

      if (! rootEntry.getSubentries().isEmpty())
      {
         for (KanbanEntry subentry : rootEntry.getSubentries())
         {
            collectHours(subentry);
            hoursSpendSum += subentry.getHoursSpend();
            hoursRemainingSum += subentry.getHoursRemaining();

            learnKidPhases(rootEntry, subentry);
         }
      }

      // collect hours from log entries
      double logHoursSpend = 0.0;
      double logHoursRemaining = 0.0;

      Date latestLogEntryDate = null;
      for (LogEntry logEntry : rootEntry.getLogEntries())
      {
         logHoursSpend += logEntry.getHoursSpend();

         if (latestLogEntryDate == null || latestLogEntryDate.compareTo(logEntry.getParsedDate()) < 0)
         {
            latestLogEntryDate = logEntry.getParsedDate();
            logHoursRemaining = logEntry.getHoursRemainingInTotal();
            rootEntry.setPhase(logEntry.getPhase());
         }

         if (rootEntry.getPhases().indexOf(logEntry.getPhase()) < 0)
         {
            rootEntry.setPhases(rootEntry.getPhases() + ", " + logEntry.getPhase());
         }
      }

      hoursSpendSum += logHoursSpend;
      hoursRemainingSum += logHoursRemaining;

      rootEntry.setHoursSpend(hoursSpendSum);
      rootEntry.setHoursRemaining(hoursRemainingSum);
   }

   private void learnKidPhases(KanbanEntry rootEntry, KanbanEntry subentry)
   {
      String[] split = subentry.getPhases().split(", ");

      for (String phase : split)
      {
         if (rootEntry.getPhases().indexOf(phase) < 0)
         {
            rootEntry.setPhases(rootEntry.getPhases() + ", " + phase);
         }
      }
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

   //   private String createBurnupChart(KanbanEntry kanbanEntry,
   //         TimeSeries hoursEstimatedSeries, TimeSeries hoursSpendSeries)
   //   {
   //      TimeSeriesCollection dataset = new TimeSeriesCollection();
   //      dataset.addSeries(hoursEstimatedSeries);
   //      dataset.addSeries(hoursSpendSeries);
   //
   //      // Generate the graph
   //      XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
   //      DateAxis dateaxis = new DateAxis("Date");
   //      NumberAxis numberaxis = new NumberAxis("Hours");
   //      XYPlot xyplot = new XYPlot(dataset, dateaxis, numberaxis, xyLineAndShapeRenderer);
   //
   //      JFreeChart chart = new JFreeChart("Burnup Chart for " + kanbanEntry.getName(), xyplot);
   //      String chartFileName = null;
   //      try {
   //         chartFileName = "doc/" + kanbanEntry.getName()+ "BurnupChart.png";
   //         ChartUtilities.saveChartAsPNG(new File(chartFileName), chart, 800,
   //            700);
   //      } catch (IOException e) {
   //         System.err.println("Problem occurred creating chart.");
   //      }
   //
   //      return chartFileName;
   //   }

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
         String refForFile = StoryboardManager.get().refForFile(file);
         text.append(refForFile);
      }



      page = page.replaceAll("text", text.toString());

      File file = new File ("src/smartiocatalog/" + fileName);
      printFile(file, page);
      return fileName;
   }



   public static void printFile(File file, String text)
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

   public static String readFile(File file)
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
         e.printStackTrace();
      }

      return result.toString();
   }

   public StoryboardManager add(Storyboard storyboard)
   {
      storyboards.add(storyboard);
      return this;
   }

   private LinkedHashSet<KanbanEntry> newEntries  = new LinkedHashSet<KanbanEntry>();

   private StringBuffer refColumnBody;

   public DateFormat dateParser  = DateFormat.getInstance();

   public void addEntry(KanbanEntry sprint1)
   {
      newEntries.add(sprint1);
   }

   Storyboard toBeRemoved = null;

   public StoryboardManager remove(Storyboard storyboard)
   {
      this.toBeRemoved = storyboard;

      return this;
   }


}
