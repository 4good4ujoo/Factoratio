import java.util.ArrayList;

public class CraftableCrafter extends Craftable implements Crafter
{
    private ArrayList<Item> components;
    private int[] counts;
    private double speed;

    /**
     *
     * @param name the name of the item
     * @param count the number of each component used to craft this item
     * @param craftSpeed the time it takes to craft this item
     * @param numProd the number of this item that is produced
     * @param speed the speed at which this item crafts other items
     * @param ingredients the ingredients to make this item
     */
    public CraftableCrafter(String name, double[] count, double craftSpeed, double numProd, double speed, Item ... ingredients)
    {
        super(name, count, craftSpeed, numProd, ingredients);

        this.speed = speed;
    }

    /**
     *
     * @return the speed at which this crafter crafts
     */
    public double getSpeed()
    {
        return speed;
    }

}
