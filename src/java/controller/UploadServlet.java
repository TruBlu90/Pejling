package controller;

import datasource.DBFacade;
import entities.Pejling;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Richard Haley III & Jonathan Anastasiou
 */
@MultipartConfig
public class UploadServlet extends HttpServlet
{

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        context = getServletContext();
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        for (Part part : request.getParts())
        {
            InputStream input = request.getPart(part.getName()).getInputStream();
            int i = input.available();
            byte[] bytes = new byte[i];
            input.read(bytes);
            String filename = getFileName(part);

            context.log("filename: " + filename);
            FileOutputStream fos = new FileOutputStream("C:/" + filename);
            fos.write(bytes);

            input.close();
            fos.close();

            File file = new File("C:/" + filename);
            Scanner scanner = new Scanner(file);

            ArrayList<Pejling> pejlings = new ArrayList<>();

            while (scanner.hasNext())
            {
                String line = scanner.next();
                String[] split = line.split(";");

                long borId = Long.parseLong(split[0]);
                long indtagsId = Long.parseLong(split[1]);
                String pejleTidspunkt = split[2];
                String referencePunkt = split[3];
                String vandstand = split[4];
                String pejleProjekt = split[5];
                String pejleSituation = split[6];
                String pejleEkstremer = split[7];

                Pejling pejling = new Pejling(borId, indtagsId, pejleTidspunkt, referencePunkt, vandstand, pejleProjekt, pejleSituation, pejleEkstremer);
                pejlings.add(pejling);
            }

            
            for(Pejling p : pejlings)
            {
                DBFacade.getInstance().insertPejling(p);
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
        doGet(request, response);
    }

    private String getFileName(Part part)
    {

        for (String string : part.getHeader("content-disposition").split(";"))
        {
            if (string.trim().startsWith("filename"))
            {
                return string.substring(string.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
