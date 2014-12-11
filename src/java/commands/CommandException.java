package commands;

/**
 *
 * @author Jonathan Anastasiou
 */
public class CommandException extends Exception
{

    private static final long serialVersionUID = 1L;
    private String errorTarget;

    public CommandException(String message)
    {
        super(message);
    }

    public CommandException(String message, String errorTarget)
    {
        super(message);
        this.errorTarget = errorTarget;
    }

    public String getErrorTarget()
    {
        return errorTarget;
    }
}
