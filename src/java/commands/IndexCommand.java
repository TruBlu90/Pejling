package commands;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public class IndexCommand extends TargetCommand
{

    public IndexCommand(String target)
    {
        super(target);
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException
    {
        return super.execute(request);
    }
}
