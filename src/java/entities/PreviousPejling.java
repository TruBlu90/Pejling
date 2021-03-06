package entities;

import java.io.Serializable;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class PreviousPejling extends LayerSupertype implements Serializable
{
    
    private String dgunr;
    private String anvendelse;
    private String formaal;
    private long aar;
    private String dybde;
    private long antalStammer;
    private String tekst;
    private String breddegrad;
    private String laengdegrad;
    private String yeuref89;
    private String xeuref89;

    public PreviousPejling()
    {
    }

    public PreviousPejling(long borId, String dgunr, String anvendelse, String formaal,
            long aar, String dybde, long antalStammer, String tekst, String breddegrad,
            String laengdegrad, String yeuref89, String xeuref89)
    {
        super(borId);

        this.dgunr = dgunr;
        this.anvendelse = anvendelse;
        this.formaal = formaal;
        this.aar = aar;
        this.dybde = dybde;
        this.antalStammer = antalStammer;
        this.tekst = tekst;
        this.breddegrad = breddegrad;
        this.laengdegrad = laengdegrad;
        this.yeuref89 = yeuref89;
        this.xeuref89 = xeuref89;
    }

    public long getBorId()
    {
        return id;
    }

    public void setBorId(long borId)
    {
        this.id = borId;
    }

    public String getDgunr()
    {
        return dgunr;
    }

    public void setDgunr(String dgunr)
    {
        this.dgunr = dgunr;
    }

    public String getAnvendelse()
    {
        return anvendelse;
    }

    public void setAnvendelse(String anvendelse)
    {
        this.anvendelse = anvendelse;
    }

    public String getFormaal()
    {
        return formaal;
    }

    public void setFormaal(String formaal)
    {
        this.formaal = formaal;
    }

    public long getAar()
    {
        return aar;
    }

    public void setAar(long aar)
    {
        this.aar = aar;
    }

    public String getDybde()
    {
        return dybde;
    }

    public void setDybde(String dybde)
    {
        this.dybde = dybde;
    }

    public long getAntalStammer()
    {
        return antalStammer;
    }

    public void setAntalStammer(long antalStammer)
    {
        this.antalStammer = antalStammer;
    }

    public String getTekst()
    {
        return tekst;
    }

    public void setTekst(String tekst)
    {
        this.tekst = tekst;
    }

    public String getBreddegrad()
    {
        return breddegrad;
    }

    public void setBreddegrad(String breddegrad)
    {
        this.breddegrad = breddegrad;
    }

    public String getLaengdegrad()
    {
        return laengdegrad;
    }

    public void setLaengdegrad(String laengdegrad)
    {
        this.laengdegrad = laengdegrad;
    }

    public String getYeuref89()
    {
        return yeuref89;
    }

    public void setYeuref89(String yeuref89)
    {
        this.yeuref89 = yeuref89;
    }

    public String getXeuref89()
    {
        return xeuref89;
    }

    public void setXeuref89(String xeuref89)
    {
        this.xeuref89 = xeuref89;
    }
}
