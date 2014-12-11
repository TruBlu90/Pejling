package datasource;

import entities.Indtag;
import entities.LayerSupertype;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class IndtagMapper extends AbstractMapper
{

    public ArrayList<Indtag> findIndtagsByDguNo(Connection con, String dguNo)
    {
        ArrayList<Indtag> result = new ArrayList<>();
        List<LayerSupertype> domainObjects = findMany(con, new IndtagMapper.FindIndtagsByDguNo(dguNo));
        for (LayerSupertype ls : domainObjects)
        {
            result.add((Indtag) ls);
        }
        return result;
    }

    @Override
    protected LayerSupertype doLoad(long id, ResultSet rs) throws SQLException
    {
        Indtag result = Registry.getInstance().getIndtag(id);
        if (result == null)
        {
            long indtagsNr = rs.getLong(2);
            long indtagsId = rs.getLong(2);
            result = new Indtag(id, indtagsNr);
            result.setIndtagsId(indtagsId);
            Registry.getInstance().addIndtag(result);
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
        return "SELECT borid, indtagsnr, indtagsid FROM bor$indtag WHERE borId = ?";
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

    static class FindIndtagsByDguNo implements StatementSource
    {

        private String dguNo;

        public FindIndtagsByDguNo(String dguNo)
        {
            this.dguNo = dguNo;
        }

        @Override
        public String sql()
        {
            return "SELECT b.borid, i.indtagsnr FROM bor$indtag i, bor$boring$v b "
                    + "WHERE b.borid = i.borid (+) "
                    + "AND b.slutdato IS NULL  "
                    + "AND REPLACE(b.dgunr, ' ','') = ? ";
        }

        @Override
        public Object[] parameters()
        {
            Object[] result =
            {
                dguNo
            };
            return result;
        }
    }
}
