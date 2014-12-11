package datasource;

import entities.Boring;
import entities.Indtag;
import entities.PreviousPejling;
import entities.XYPoint;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Richard Haley III
 */
public class Registry
{

    private static Registry instance = null;
    private Map<Long, Boring> boringIdentityMap = new HashMap<>();
    private Map<Long, Indtag> indtagIdentityMap = new HashMap<>();
    private Map<Long, PreviousPejling> pejlingIdentityMap = new HashMap<>();
    private Map<Long, XYPoint> xyPointIdentityMap = new HashMap<>();

    private Registry()
    {
    }

    public static Registry getInstance()
    {
        if (instance == null)
        {
            instance = new Registry();
        }
        return instance;
    }

    public void addBoring(Boring boring)
    {
        boringIdentityMap.put(boring.getBorId(), boring);
    }

    public Boring getBoring(long borId)
    {
        return boringIdentityMap.get(borId);
    }

    public void addIndtag(Indtag indtag)
    {
        indtagIdentityMap.put(indtag.getBorid(), indtag);
    }

    public Indtag getIndtag(long borId)
    {
        return indtagIdentityMap.get(borId);
    }

    public void addPejling(PreviousPejling pejling)
    {
        pejlingIdentityMap.put(pejling.getBorId(), pejling);
    }

    public PreviousPejling getPejling(long borId)
    {
        return pejlingIdentityMap.get(borId);
    }

    public void addXYPoint(XYPoint xy)
    {
        xyPointIdentityMap.put(xy.getPejlingsid(), xy);
    }

    public XYPoint getXYPoint(long pejlingsid)
    {
        return xyPointIdentityMap.get(pejlingsid);
    }
}
