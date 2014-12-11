package controller;

import commands.CommandException;
import commands.Command;
import commands.CommandFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan Anastasiou
 */
public class FrontControllerServlet extends HttpServlet
{

    private String findKey(HttpServletRequest request)
    {
        String command = null;
        for (String name : request.getParameterMap().keySet())
        {
            if (name.endsWith("Button"))
            {
                command = name.substring(0, name.length() - 6);
            }
        }

        if (command == null)
        {
            command = "index";
        }
        return command;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try
        {

            String key = findKey(request);

            Command command = CommandFactory.getInstance().findCommand(key);
            String target = command.execute(request);

            RequestDispatcher dis = request.getRequestDispatcher(target);
            dis.forward(request, response);

        } catch (CommandException ce)
        {
            String errorTarget = ce.getErrorTarget();
            request.setAttribute("ErrorMessage", ce.getMessage());
            RequestDispatcher dis = request.getRequestDispatcher(errorTarget);
            dis.forward(request, response);
        } catch (RuntimeException re)
        {
            out.print("<html><body><h3>" + "DDFG" + re.getMessage() + "</h3><hr/><pre>");
            re.printStackTrace(out);
            out.println("</pre></body></html>");
        } finally
        {
            out.close();
        }


    }
}
