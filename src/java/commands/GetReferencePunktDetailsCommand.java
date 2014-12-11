package commands;

import entities.ReferencePunkt;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public class GetReferencePunktDetailsCommand extends TargetCommand
{

    public GetReferencePunktDetailsCommand(String target)
    {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException
    {
        long borid = Long.parseLong(request.getParameter("borId"));
        long indtagsid = Long.parseLong("indtagsId");

        ReferencePunkt refPkt;

        try
        {
            //refPkt = CommandFactory.findDBManager().getReferencePunktKode(borid, indtagsid);
        } catch (Exception e)
        {
            System.err.println("Error in GetReferencePunktCommand = " + e);
        }

        return super.execute(request);
    }
}
