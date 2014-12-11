package commands;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public class TargetCommand implements Command
{

    private String target;

    public TargetCommand(String target)
    {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException
    {
        return target;
    }
}
