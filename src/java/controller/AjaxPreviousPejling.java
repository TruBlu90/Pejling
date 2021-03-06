package controller;

import commands.GetPreviousPejlingCommand;
import entities.PreviousPejling;
import com.google.gson.Gson;
import datasource.DBFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan Anastasiou
 */
public class AjaxPreviousPejling extends HttpServlet
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
            out.println("<title>Servlet AjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxServlet at " + request.getContextPath() + "</h1>");
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
        long borId = 0;
        try
        {
            borId = DBFacade.getInstance().getBorIdByDGUnr(dgunr);
        } catch (SQLException ex)
        {
            Logger.getLogger(GetPreviousPejlingCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreviousPejling prePej = new PreviousPejling();

        if (borId != 0)
        {
            try
            {
                prePej = DBFacade.getInstance().getPreviousPejlingInfo(borId);
            } catch (SQLException ex)
            {
                Logger.getLogger(GetPreviousPejlingCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            prePej = null;
        }

        if (prePej != null)
        {
            Gson gson = new Gson();
            String json = gson.toJson(prePej);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else
        {
            String error = "Something went wrong prePej = null";
            request.setAttribute("previousPejling", error);
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
}
