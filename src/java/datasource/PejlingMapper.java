package datasource;

import entities.LayerSupertype;
import entities.Pejling;
import entities.PreviousPejling;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class PejlingMapper extends AbstractMapper
{

    @Override
    protected LayerSupertype doLoad(long id, ResultSet rs) throws SQLException
    {
        PreviousPejling result = Registry.getInstance().getPejling(id);
        if (result != null)
        {
            System.out.println("NOT NULL id is " + result.getBorId());
        } else
        {
            System.out.println("NULL object returned from registry");
        }
        if (result == null)
        {
            result = new PreviousPejling();
            result.setBorId(rs.getLong("borid"));
            result.setDgunr(rs.getString("dgunr"));
            result.setAnvendelse(rs.getString("anvendelse"));
            result.setFormaal(rs.getString("formaal"));
            result.setAar(rs.getLong("aar"));
            result.setDybde(rs.getString("dybde"));
            result.setAntalStammer(rs.getLong("antal_stammer"));
            result.setTekst(rs.getString("tekst"));
            result.setBreddegrad(rs.getString("breddegrad"));
            result.setLaengdegrad(rs.getString("laengdegrad"));
            result.setYeuref89(rs.getString("yeuref89"));
            result.setXeuref89(rs.getString("xeuref89"));
            Registry.getInstance().addPejling(result);
        }
        return result;
    }

    @Override
    protected void doInsert(LayerSupertype ls, PreparedStatement statement) throws SQLException
    {
        Pejling pejling = (Pejling) ls;
        statement.setLong(1, pejling.getBorId());
        statement.setLong(2, pejling.getIntagsId());
        statement.setString(3, pejling.getPejleTidspunkt());
        statement.setString(4, pejling.getReferencePunkt());
        statement.setString(5, pejling.getVandstand());
        statement.setString(6, pejling.getPejleProjekt());
        statement.setString(7, pejling.getPejleSituation());
        statement.setString(8, pejling.getPejleEkstremer());
    }

    @Override
    protected String findStatement()
    {
        return "SELECT b.borid, "
                + "REPLACE(b.dgunr, ' ') dgunr, "
                + "NVL(a.kort_tekst, f.kort_tekst) anvendelse, "
                + "f.kort_tekst formaal, "
                + "TO_CHAR(b.slutdato, 'YYYY') aar, "
                + "TRIM(TO_CHAR(b.boringsdybde, '999G990D00')) dybde, "
                + "(SELECT DECODE(COUNT(DISTINCT stammenr), 0, NULL, COUNT(DISTINCT stammenr)) FROM "
                + "bor$indtag i WHERE i.borid = b.borid) antal_stammer, "
                + "b.sted2 tekst, "
                + "TRIM(TO_CHAR(b.breddegrad, '9999999990.999999')) breddegrad, "
                + "TRIM(TO_CHAR(b.laengdegrad, '9999999990.999999')) laengdegrad, "
                + "TRIM(TO_CHAR(b.yutm32euref89, '9999999990')) yeuref89, "
                + "TRIM(TO_CHAR(b.xutm32euref89, '9999999990')) xeuref89 "
                + "FROM bor$boring$v b, "
                + "bor$anvend_liste a, "
                + "bor$formaal_liste f, "
                + "bor$fikspunktbeskr_liste fpb "
                + "WHERE "
                + "b.fikspktbeskr = fpb.kode (+) "
                + "AND b.anvendelse = a.kode (+) "
                + "AND b.formaal = f.kode (+) "
                + "AND b.borid = ?";
    }

    @Override
    protected String findAllStatement()
    {
        return "";
    }

    @Override
    protected String insertStatement()
    {
        return "INSERT INTO MIS.pej$pejling(BORID, INDTAGSID, PEJLETIDSPUNKT, REFERENCEPUNKT, "
                + "VANDSTAND, PEJLEPROJEKT, PEJLESITUATION, PEJLEEKSTREMER) "
                + "VALUES(? , TO_NUMBER(?), to_date(REPLACE(REPLACE(REPLACE(REPLACE(?,'/'),'-'),' '),':'), 'ddmmyyyyhh24mi'), "
                + "DECODE(UPPER(?),'M','M','T','T','K','K'), TO_NUMBER(REPLACE(?, ',', '.'), '999.999'), "
                + "?, TO_NUMBER(DECODE(?, '0', '0', '1', '1')), DECODE(UPPER(?),'O', 'O', 'T', 'T'))";
    }
}
