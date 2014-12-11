package datasource;

import entities.LayerSupertype;
import entities.XYPoint;
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
public class XYPointMapper extends AbstractMapper
{

    public ArrayList<XYPoint> findXYPointsByDguNr(Connection con, String dguNr)
    {
        ArrayList<XYPoint> result = new ArrayList<>();
        List<LayerSupertype> domainObjects = findMany(con, new FindXYPointsByDguNr(dguNr));
        for (LayerSupertype ls : domainObjects)
        {
            result.add((XYPoint) ls);
        }
        return result;
    }

    @Override
    protected LayerSupertype doLoad(long id, ResultSet rs) throws SQLException
    {
        XYPoint result = Registry.getInstance().getXYPoint(id);
        if (result == null)
        {
            result = new XYPoint(rs.getString(2), rs.getDouble(3));
            result.setPejlingsid(rs.getLong(1));
            Registry.getInstance().addXYPoint(result);
        }
        return result;
    }

    @Override
    protected void doInsert(LayerSupertype ls, PreparedStatement statement) throws SQLException
    {
    }

    @Override
    protected String findStatement()
    {
        return "";
    }

    @Override
    protected String findAllStatement()
    {
        return "";
    }

    @Override
    protected String insertStatement()
    {
        return "";
    }

    static class FindXYPointsByDguNr implements StatementSource
    {

        private String dguNr;

        public FindXYPointsByDguNr(String dguNr)
        {
            this.dguNr = dguNr;
        }

        @Override
        public String sql()
        {
            return "SELECT p.pejlingsid, TO_CHAR(p.pejletidspunkt, 'dd/mm/yyyy hh:mm:ss'), p.vandstand "
                    + "FROM pej$pejling p, bor$boring$v b "
                    + "WHERE p.borid (+) = b.borid "
                    + "AND REPLACE(b.dgunr, ' ', '') = ? "
                    + "ORDER BY p.pejletidspunkt ASC";
        }

        @Override
        public Object[] parameters()
        {
            Object[] result =
            {
                dguNr
            };
            return result;
        }
    }
}
