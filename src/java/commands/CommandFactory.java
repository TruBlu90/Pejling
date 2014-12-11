package commands;

import datasource.DBFacade;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jonathan Anastasiou
 */
public class CommandFactory
{

    public static CommandFactory instance = null;
    private Map<String, Command> commands = new HashMap<>();
    private DBFacade facade = DBFacade.getInstance();

    private CommandFactory()
    {
        commands.put("index", new IndexCommand("index.html"));
        commands.put("savePejling", new SavePejlingCommand("outputTest.jsp"));

    }

    public static CommandFactory getInstance()
    {
        if (instance == null)
        {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command findCommand(String key) throws CommandException
    {
        if (key == null)
        {
            key = "index";
        }

        Command cmd = commands.get(key);

        if (cmd == null)
        {
            throw new CommandException(key + " : not defined as a command string", "errorPage.jsp");
        }
        return cmd;
    }

    public static DBFacade findDBManager()
    {
        return getInstance().getDBManager();
    }

    public DBFacade getDBManager()
    {
        return facade;
    }
}
