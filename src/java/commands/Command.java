package commands;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan Anastasiou
 */
public interface Command
{

    String execute(HttpServletRequest request) throws CommandException;
}
