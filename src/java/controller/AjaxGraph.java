package controller;

import commands.CommandFactory;
import entities.XYPoint;
import graphs.GraphGenerator;
import com.sun.xml.wss.impl.misc.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Jonathan Anastasiou
 */
public class AjaxGraph extends HttpServlet
{

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenerateGraph</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerateGraph at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String dgunr = request.getParameter("dguNr");
        double vandstand = Double.parseDouble(request.getParameter("vandstand"));
        String newDate = request.getParameter("pejleTidspunkt");
        ArrayList<XYPoint> xyPoints = new ArrayList<>();
        ArrayList<XYPoint> filteredXYPoints = new ArrayList<>();
        PrintWriter out = response.getWriter();
        XYPoint xy = new XYPoint();
        XYPoint latestDate = new XYPoint();
        String date;
        String tempSDate;
        Date javaDate;
        Date tempDate;
        int year;
        int tempYear;
        int yearsToFilter;

        xyPoints = CommandFactory.findDBManager().getXYPointByDguNr(dgunr);


        GraphGenerator gg = new GraphGenerator();

        yearsToFilter = findAmountOfYears(request);

        if (yearsToFilter == 0)
        {

            try
            {
                JFreeChart chart1 = gg.createGraph(xyPoints, vandstand, newDate);

                BufferedImage bufferedImg = chart1.createBufferedImage(800, 600);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try
                {
                    ImageIO.write(bufferedImg, "png", baos);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                byte[] byteArray = baos.toByteArray();
                String encodedImg = Base64.encode(byteArray);

                out.println(encodedImg);
            } catch (ParseException ex)
            {
                Logger.getLogger(AjaxGraph.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else
        {

            for (Iterator<XYPoint> i = xyPoints.iterator(); i.hasNext();)
            {
                xy = i.next();
                date = xy.getDate();
                DateFormat dateFormater = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
                try
                {
                    javaDate = dateFormater.parse(date);

                    latestDate = xyPoints.get(xyPoints.size() - 1);//get last element i.e the object with the latest date
                    tempSDate = latestDate.getDate();
                    tempDate = dateFormater.parse(tempSDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(tempDate);
                    year = cal.get(Calendar.YEAR);
                    cal.setTime(javaDate);
                    tempYear = cal.get(Calendar.YEAR);

                    if ((year - tempYear) <= yearsToFilter)
                    {
                        filteredXYPoints.add(xy);
                    }


                } catch (ParseException ex)
                {
                    Logger.getLogger(AjaxGraph.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

            try
            {
                JFreeChart chart2 = gg.createGraph(filteredXYPoints, vandstand, newDate);

                BufferedImage bufferedImg = chart2.createBufferedImage(800, 600);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try
                {
                    ImageIO.write(bufferedImg, "png", baos);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                byte[] byteArray = baos.toByteArray();
                //Base64.encode(byteArray);
                String encodedImg = Base64.encode(byteArray);
                out.println(encodedImg);
            } catch (ParseException ex)
            {
                Logger.getLogger(AjaxGraph.class.getName()).log(Level.SEVERE, null, ex);
            }



        }

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    private int findAmountOfYears(HttpServletRequest request)
    {
        int amountOfYears;
        String name = request.getParameter("nrOfYears");

        if (name.equals("sidste10"))
        {
            amountOfYears = 10;
        } else
        {
            if (name.equals("sidste20"))
            {
                amountOfYears = 20;
            } else
            {
                if (name.equals("sidste30"))
                {
                    amountOfYears = 30;
                } else
                {
                    if (name.equals("sidste1"))
                    {
                        amountOfYears = 1;
                    } else
                    {
                        amountOfYears = 0;
                    }
                }
            }
        }
        return amountOfYears;
    }
}
