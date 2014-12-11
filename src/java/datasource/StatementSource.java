package datasource;

/**
 *
 * @author Richard Haley III
 */
public interface StatementSource
{
    
    String sql();
    
    Object[] parameters();
}
