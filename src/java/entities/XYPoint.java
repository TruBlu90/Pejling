package entities;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class XYPoint extends LayerSupertype
{

    private String date;
    private double vandstand;

    public XYPoint()
    {
    }

    public XYPoint(String date, double vandstand)
    {
        this.date = date;
        this.vandstand = vandstand;

    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getVandstand()
    {
        return vandstand;
    }

    public void setVandstand(double vandstand)
    {
        this.vandstand = vandstand;
    }

    public long getPejlingsid()
    {
        return id;
    }

    public void setPejlingsid(long pejlingsid)
    {
        this.id = pejlingsid;
    }
}
