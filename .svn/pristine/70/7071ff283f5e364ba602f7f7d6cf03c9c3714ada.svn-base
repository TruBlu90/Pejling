package datasource;

import entities.LayerSupertype;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Richard Haley III
 */
public abstract class AbstractMapper
{

    abstract protected LayerSupertype doLoad(long id, ResultSet rs) throws SQLException;

    abstract protected void doInsert(LayerSupertype ls, PreparedStatement statement) throws SQLException;

    abstract protected String findStatement();

    abstract protected String findAllStatement();

    abstract protected String insertStatement();

    protected LayerSupertype load(ResultSet rs) throws SQLException
    {
        return doLoad(rs.getLong(1), rs);
    }

    protected List loadAll(ResultSet rs) throws SQLException
    {
        List result = new ArrayList();
        while (rs.next())
        {
            result.add(load(rs));
        }
        return result;
    }

    protected LayerSupertype abstractFind(Connection con, long id)
    {
        LayerSupertype result = null;
        PreparedStatement findStatement = null;
        try
        {
            findStatement = con.prepareStatement(findStatement());
            findStatement.setLong(1, id);
            ResultSet rs = findStatement.executeQuery();
            if (rs.next())
            {
                result = load(rs);
                return result;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            closePreparedStatement(findStatement);
        }
        return result;
    }

    protected LayerSupertype abstractFind(Connection con, String dguNr)
    {
        LayerSupertype result = null;
        PreparedStatement findStatement = null;
        try
        {
            findStatement = con.prepareStatement(findStatement());
            findStatement.setString(1, dguNr);
            ResultSet rs = findStatement.executeQuery();
            if (rs.next())
            {
                System.out.println("ResultSet has next");
                result = load(rs);
                return result;
            } else
            {
                System.out.println("ResultSet in abstractFind() is empty");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            closePreparedStatement(findStatement);
        }
        return result;
    }

    protected List<LayerSupertype> abstractFindAll(Connection con)
    {
        List<LayerSupertype> result = new ArrayList<>();
        PreparedStatement findStatement = null;
        try
        {
            findStatement = con.prepareStatement(findAllStatement());
            ResultSet rs = findStatement.executeQuery();
            while (rs.next())
            {
                result.add(load(rs));
            }
            return result;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            closePreparedStatement(findStatement);
        }
        return result;
    }

    protected List<LayerSupertype> findMany(Connection con, StatementSource ss)
    {
        List<LayerSupertype> result = new ArrayList<>();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try
        {
            findStatement = con.prepareStatement(ss.sql());
            for (int i = 0; i < ss.parameters().length; i++)
            {
                findStatement.setObject(i + 1, ss.parameters()[i]);
            }
            rs = findStatement.executeQuery();
            result = loadAll(rs);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            closePreparedStatement(findStatement);
        }
        return result;
    }

    protected boolean insert(Connection con, LayerSupertype ls)
    {
        int rowsInserted = 0;
        PreparedStatement insertStatement = null;
        try
        {
            insertStatement = con.prepareStatement(insertStatement());
            doInsert(ls, insertStatement);
            rowsInserted = insertStatement.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            closePreparedStatement(insertStatement);
        }
        return rowsInserted == 1;
    }

    protected boolean insertAll(Connection con, List<LayerSupertype> list)
    {
        boolean success = false;
        for (LayerSupertype ls : list)
        {
            success = insert(con, ls);
        }
        return success;
    }

    protected void closePreparedStatement(PreparedStatement ps)
    {
        try
        {
            ps.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
