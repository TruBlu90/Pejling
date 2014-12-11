package graphs;

import entities.XYPoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author Jonathan Anastasiou
 */
public class GraphGenerator
{

    public JFreeChart createGraph(ArrayList<XYPoint> xyPoints, double newVandstand, String newDate) throws ParseException
    {

        XYPoint xy = new XYPoint();
        String date;
        java.util.Date javaDate;
        java.util.Date newJDate;
        double vandstand;

        DateFormat dateFormater = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        DateFormat dateFormater2 = new SimpleDateFormat("dd/mm/yyyy HH:mm");

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries timeSeries1 = new TimeSeries("Vandstand");


        for (Iterator<XYPoint> i = xyPoints.iterator(); i.hasNext();)//iterates through arraylist of points and sets date and and value to each in time series
        {

            xy = i.next();
            date = xy.getDate();

            javaDate = dateFormater.parse(date);
            vandstand = xy.getVandstand();

            timeSeries1.addOrUpdate(new Day(javaDate), vandstand);

        }

        dataset.addSeries(timeSeries1);

        if (newVandstand != 0 && !newDate.equals("none"))
        {
            TimeSeries timeSeries2 = new TimeSeries("Ny Vanstand");

            newJDate = dateFormater2.parse(newDate);
            timeSeries2.add(new Day(newJDate), newVandstand);

            dataset.addSeries(timeSeries2);

        }



        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Vandstands Historik",
                "Dato",
                "Vandstand",
                dataset,
                true,
                true,
                false);

        XYPlot plot = (XYPlot) chart.getXYPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShapesFilled(1, true);

        return chart;


    }
}
