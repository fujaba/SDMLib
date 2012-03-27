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

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.junit.Test;

public class ChartTest
{
   @Test
   public void testSimpleChart()
   {
      XYSeries hoursSpend = new XYSeries("Hours Spend");
      hoursSpend.add(1, 1);
      hoursSpend.add(2, 2);
      hoursSpend.add(3, 1);
      hoursSpend.add(4, 9);
      hoursSpend.add(5, 10);

      TimeSeries hoursEstimated = new TimeSeries("Hours Estimated");
      hoursEstimated.add(new Day(1,2,2012), 38);
      hoursEstimated.add(new Day(2,2,2012), 39);
      hoursEstimated.add(new Day(5,2,2012), 42);
      hoursEstimated.add(new Day(6,2,2012), 40);
      hoursEstimated.add(new Day(10,2,2012), 50);
      hoursEstimated.add(new Day(20,2,2012), 42);
      //         Add the series to your data set
      TimeSeriesCollection dataset = new TimeSeriesCollection();
      dataset.addSeries(hoursEstimated);
      
      //         Generate the graph
      XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
      DateAxis dateaxis = new DateAxis("Date");
      NumberAxis numberaxis = new NumberAxis("Value");
      XYPlot xyplot = new XYPlot(dataset, dateaxis, numberaxis, xyLineAndShapeRenderer);
      
      JFreeChart chart = new JFreeChart("Time Period Values Demo 3", xyplot);
    
      try {
         File chartFile = new File("./doc/jfreechart") ;
         chartFile.mkdirs();
         chartFile = new File("./doc/jfreechart/chart.png");
         ChartUtilities.saveChartAsPNG(chartFile, chart, 500,
              300);
      } catch (IOException e) {
          System.err.println("Problem occurred creating chart.");
          e.printStackTrace();
      }
   }

   @Test
   public void testBurnupChart()
   {
      TimePeriodValues timeperiodvalues = new TimePeriodValues("Series 1");
      DateFormat dateformat = DateFormat.getInstance();
      try
      {
          java.util.Date date = dateformat.parse("5.11.2003 0:00:00.000");
          java.util.Date date1 = dateformat.parse("5.11.2003 0:15:00.000");
          java.util.Date date2 = dateformat.parse("5.11.2003 0:30:00.000");
          java.util.Date date3 = dateformat.parse("5.11.2003 0:45:00.000");
          java.util.Date date4 = dateformat.parse("5.11.2003 1:00:00.001");
          java.util.Date date5 = dateformat.parse("5.11.2003 1:14:59.999");
          java.util.Date date6 = dateformat.parse("5.11.2003 1:30:00.000");
          java.util.Date date7 = dateformat.parse("5.11.2003 1:45:00.000");
          java.util.Date date8 = dateformat.parse("5.11.2003 2:00:00.000");
          java.util.Date date9 = dateformat.parse("5.11.2003 2:15:00.000");
          timeperiodvalues.add(new SimpleTimePeriod(date, date1), 0.39000000000000001D);
          timeperiodvalues.add(new SimpleTimePeriod(date2, date3), 0.22500000000000001D);
          timeperiodvalues.add(new SimpleTimePeriod(date3, date4), 0.23499999999999999D);
          timeperiodvalues.add(new SimpleTimePeriod(date4, date5), 0.23799999999999999D);
          timeperiodvalues.add(new SimpleTimePeriod(date5, date6), 0.23599999999999999D);
          timeperiodvalues.add(new SimpleTimePeriod(date6, date7), 0.25D);
          timeperiodvalues.add(new SimpleTimePeriod(date7, date8), 0.23799999999999999D);
          timeperiodvalues.add(new SimpleTimePeriod(date8, date9), 0.215D);
      }
      catch(Exception exception)
      {
          exception.printStackTrace();
      }
      
      TimePeriodValuesCollection timeperiodvaluescollection = new TimePeriodValuesCollection();
      timeperiodvaluescollection.addSeries(timeperiodvalues);
      
      
      XYLineAndShapeRenderer xybarrenderer = new XYLineAndShapeRenderer();
      DateAxis dateaxis = new DateAxis("Date");
      NumberAxis numberaxis = new NumberAxis("Value");
      XYPlot xyplot = new XYPlot(timeperiodvaluescollection, dateaxis, numberaxis, xybarrenderer);
      
      JFreeChart chart = new JFreeChart("Time Period Values Demo 3", xyplot);
     
      try {
          ChartUtilities.saveChartAsPNG(new File("./doc/jfreechart/chart2.png"), chart, 500,
              300);
      } catch (IOException e) {
          System.err.println("Problem occurred creating chart.");
      }
   }

}
