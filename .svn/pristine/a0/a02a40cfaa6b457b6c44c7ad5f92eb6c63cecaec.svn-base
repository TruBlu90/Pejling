package commands;

import datasource.DBFacade;
import entities.PreviousPejling;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public class GetPreviousPejlingCommand extends TargetCommand
{

    public GetPreviousPejlingCommand(String target)
    {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException
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
                System.out.println("BorID is not zero");
                prePej = DBFacade.getInstance().getPreviousPejlingInfo(borId);
            } catch (SQLException ex)
            {
                Logger.getLogger(GetPreviousPejlingCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            System.out.println("BorID is zero");
            prePej = null;
        }

        if (prePej != null)
        {
            request.setAttribute("previousPejling", prePej);
        } else
        {
            String error = "Something went wrong prePej = null";
            request.setAttribute("previousPejling", error);
        }


        return super.execute(request);

    }
}
