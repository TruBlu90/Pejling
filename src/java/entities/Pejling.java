package entities;

import java.io.Serializable;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class Pejling extends LayerSupertype implements Serializable
{

    private long intagsId;
    private String pejleTidspunkt;
    private String referencePunkt;
    private String vandstand;
    private String pejleProjekt;
    private String pejleSituation;
    private String pejleEkstremer;

    public Pejling()
    {
    }

    public Pejling(long borId, long intagsId, String pejleTidspunkt, String referencePunkt, String vandstand, String pejleProjekt, String pejleSituation, String pejleEkstremer)
    {
        this.id = borId;
        this.intagsId = intagsId;
        this.pejleTidspunkt = pejleTidspunkt;
        this.referencePunkt = referencePunkt;
        this.vandstand = vandstand;
        this.pejleProjekt = pejleProjekt;
        this.pejleSituation = pejleSituation;
        this.pejleEkstremer = pejleEkstremer;
    }

    public long getBorId()
    {
        return id;
    }

    public void setBorId(long borId)
    {
        this.id = borId;
    }

    public long getIntagsId()
    {
        return intagsId;
    }

    public void setIntagsId(long intagsId)
    {
        this.intagsId = intagsId;
    }

    public String getPejleTidspunkt()
    {
        return pejleTidspunkt;
    }

    public void setPejleTidspunkt(String pejleTidspunkt)
    {
        this.pejleTidspunkt = pejleTidspunkt;
    }

    public String getReferencePunkt()
    {
        return referencePunkt;
    }

    public void setReferencePunkt(String referencePunkt)
    {
        this.referencePunkt = referencePunkt;
    }

    public String getVandstand()
    {
        return vandstand;
    }

    public void setVandstand(String vandstand)
    {
        this.vandstand = vandstand;
    }

    public String getPejleProjekt()
    {
        return pejleProjekt;
    }

    public void setPejleProjekt(String pejleProjekt)
    {
        this.pejleProjekt = pejleProjekt;
    }

    public String getPejleSituation()
    {
        return pejleSituation;
    }

    public void setPejleSituation(String pejleSituation)
    {
        this.pejleSituation = pejleSituation;
    }

    public String getPejleEkstremer()
    {
        return pejleEkstremer;
    }

    public void setPejleEkstremer(String pejleEkstremer)
    {
        this.pejleEkstremer = pejleEkstremer;
    }
}
