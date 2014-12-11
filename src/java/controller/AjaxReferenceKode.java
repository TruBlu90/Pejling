package controller;

import commands.CommandFactory;
import entities.ReferencePunktKode;
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
public class AjaxReferenceKode extends HttpServlet
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
            out.println("<title>Servlet AjaxIndtagsNr</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AjaxIndtagsNr at " + request.getContextPath() + "</h1>");
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
        long borid = 0;
        long indtagsid = 0;
        ReferencePunktKode refPktKode = new ReferencePunktKode();

        try
        {
            borid = DBFacade.getInstance().getBorIdByDGUnr(dgunr);
        } catch (SQLException ex)
        {
            Logger.getLogger(AjaxReferenceKode.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            indtagsid = CommandFactory.findDBManager().getIndtagsIdByBorId(borid);
        } catch (SQLException ex)
        {
            Logger.getLogger(AjaxReferenceKode.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            refPktKode = CommandFactory.findDBManager().getReferencePunktKode(borid, indtagsid);
        } catch (SQLException ex)
        {
            Logger.getLogger(AjaxReferenceKode.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (refPktKode != null)
        {
            String json = new Gson().toJson(refPktKode);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else
        {
            System.out.println("refpktKode object is null. Something is wrong!");
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
