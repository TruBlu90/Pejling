package entities;

import java.io.Serializable;

/**
 *
 * @author Jonathan Anastasiou & Richard Haley III
 */
public class Boring extends LayerSupertype implements Serializable
{

    private String dguNr;

    public Boring()
    {
    }

    public Boring(long borId, String dguNr)
    {
        super(borId);
        this.dguNr = dguNr;
    }

    public long getBorId()
    {
        return id;
    }

    public void setBorId(long borId)
    {
        this.id = borId;
    }

    public String getDGUnr()
    {
        return dguNr;
    }

    public void setDGUnr(String dgunr)
    {
        this.dguNr = dgunr;
    }
}
