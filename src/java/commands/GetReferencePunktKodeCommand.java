package commands;

import entities.ReferencePunktKode;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public class GetReferencePunktKodeCommand extends TargetCommand
{

    public GetReferencePunktKodeCommand(String target)
    {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException
    {
        long borid = Long.parseLong(request.getParameter("borId"));
        long indtagsid = Long.parseLong(request.getParameter("indtagsId"));


        ReferencePunktKode refpktKode = new ReferencePunktKode();

        try
        {
            refpktKode = CommandFactory.findDBManager().getReferencePunktKode(borid, indtagsid);
        } catch (Exception e)
        {
            System.err.println("Error in refpktkode = " + e);
        }

        if (refpktKode != null)
        {
            request.setAttribute("refpktKode", refpktKode);
        } else
        {
            System.out.println("refpktKode object is null. Something is wrong!");
        }

        return super.execute(request);
    }
}
