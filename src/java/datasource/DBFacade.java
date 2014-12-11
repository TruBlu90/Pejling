package datasource;

import entities.Boring;
import entities.Indtag;
import entities.LayerSupertype;
import entities.Pejling;
import entities.PreviousPejling;
import entities.ReferencePunktKode;
import entities.XYPoint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class DBFacade
{

    private static DBFacade instance = null;
    private PejlingMapper pdb = null;
    private BoringMapper bdb = null;
    private IndtagMapper idb = null;
    private XYPointMapper xydb = null;

    private DBFacade()
    {
        pdb = new PejlingMapper();
        bdb = new BoringMapper();
        idb = new IndtagMapper();
        xydb = new XYPointMapper();

        try
        {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex)
        {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static DBFacade getInstance()
    {
        if (instance == null)
        {
            instance = new DBFacade();
        }
        return instance;
    }

    /**
     *
     * @param dgunr
     * @return
     * @throws SQLException
     */
    public ArrayList<Indtag> getIntagsNrbyDGUnr(String dgunr) throws SQLException
    {
        Connection con = null;
        ArrayList<Indtag> indtagsnr = new ArrayList<>();
        try
        {
            con = getConnection();
            indtagsnr = idb.findIndtagsByDguNo(con, dgunr);
        } catch (SQLException e)
        {
            System.err.println("Error in method getIntagsNr = " + e);
        } finally
        {
            releaseConnection(con);
        }

        return indtagsnr;
    }

    /**
     *
     * @param borId
     * @return
     * @throws SQLException
     */
    public long getIndtagsIdByBorId(long borId) throws SQLException
    {
        Connection con = null;
        Indtag indtag = null;
        try
        {
            con = getConnection();
            indtag = (Indtag) idb.abstractFind(con, borId);
        } catch (SQLException e)
        {
            System.err.println("Error in method getIndtagsIdByBorId = " + e);
        } finally
        {
            releaseConnection(con);
        }

        return indtag.getIndtagsId();
    }

    /**
     *
     * @param pej
     */
    public void insertPejling(Pejling pej)
    {
        Connection con = null;
        try
        {
            con = getConnection();
            pdb.insert(con, pej);
        } catch (SQLException e)
        {
            System.err.println("Error in insertPejling = " + e);
        } finally
        {
            releaseConnection(con);
        }
    }

    /**
     *
     * @param pejlings
     */
    public void insertPejleSerie(ArrayList<Pejling> pejlings)
    {
        Connection con = null;
        try
        {
            con = getConnection();
            List<LayerSupertype> domainObjects = new ArrayList<>();
            for (Pejling pejling : pejlings)
            {
                domainObjects.add((LayerSupertype) pejling);
            }
            pdb.insertAll(con, domainObjects);
        } catch (SQLException e)
        {
            System.err.println("Error in insertPejling = " + e);
        } finally
        {
            releaseConnection(con);
        }
    }

    /**
     *
     * @param borId
     * @return
     * @throws SQLException
     */
    public PreviousPejling getPreviousPejlingInfo(long borId) throws SQLException
    {
        Connection con = null;
        PreviousPejling prePej = new PreviousPejling();
        try
        {
            con = getConnection();
            prePej = (PreviousPejling) pdb.abstractFind(con, borId);
        } catch (SQLException e)
        {
            System.err.println("error in getPrevPilingInfo = " + e);
        } finally
        {
            releaseConnection(con);
        }

        return prePej;
    }

    /**
     *
     * @param borId
     * @param indtagsId
     * @return
     * @throws SQLException
     */
    public ReferencePunktKode getReferencePunktKode(long borId, long indtagsId) throws SQLException
    {
        Connection con = null;
        ReferencePunktKode refpktKode = null;
        try
        {
            con = getConnection();
            refpktKode = bdb.getReferencePunktKode(con, borId, indtagsId);
        } catch (SQLException e)
        {
            System.err.println("Error in getReferencePunkt = " + e);
        } finally
        {
            releaseConnection(con);
        }

        return refpktKode;
    }

    /**
     *
     * @param dguNr
     * @return
     * @throws SQLException
     */
    public long getBorIdByDGUnr(String dguNr) throws SQLException
    {
        Connection con = null;
        Boring boring = null;
        try
        {
            con = getConnection();
            boring = (Boring) bdb.abstractFind(con, dguNr);
            
            System.out.println("Overloaded abstract find method returns boring with id of " + boring.getBorId());
            
        } catch (SQLException sqle)
        {
            System.err.println("Error in getBorIdByDGUnr = " + sqle);
        } finally
        {
            releaseConnection(con);
        }

        return boring.getBorId();
    }

    /**
     *
     * @param dgunr
     * @return
     */
    public ArrayList<XYPoint> getXYPointByDguNr(String dgunr)
    {
        Connection con = null;
        ArrayList<XYPoint> xyPoints = new ArrayList<>();

        try
        {
            con = getConnection();
            xyPoints = xydb.findXYPointsByDguNr(con, dgunr);
        } catch (SQLException e)
        {
            System.err.println("Error in get XYpoints: " + e);
        }

        return xyPoints;
    }

    private Connection getConnection() throws SQLException
    {
        Connection con = null;
        con = DriverManager.getConnection("jdbc:oracle:thin:@geusjup4:1685:jupt", "jan", "jan1234567");
        return con;
    }

    private void releaseConnection(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            } catch (SQLException sqle)
            {
                System.err.println("Error in releaseConnection = " + sqle);
            }
        }
    }
}
