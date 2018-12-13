import java.util.ArrayList;
import java.util.Arrays;

public class Craftable extends Item
{
    // component information
    private ArrayList<Item> components;
    private double[] counts;

    // product information
    double craftSpeed;
    double numProd;

    /**
     *
     * @param name the name of this item
     * @param count the number of each of the items used to craft this item in order
     * @param craftSpeed the time it takes to craft this recipe
     * @param numProd the number of the item produced from the recipe
     * @param ingredients the ingredients to make this item
     */
    public Craftable(String name, double[] count, double craftSpeed, CrafterType craftedBy, double numProd, Item ... ingredients)
    {
        super(name);
        components = new ArrayList<Item>(Arrays.asList(ingredients));
        counts = count;
        this.craftSpeed = craftSpeed;
        this.numProd = numProd;
    }

    /**
     *
     * @return the counts of each component
     */
    public double[] getCompNum()
    {
        return counts;
    }

    /**
     *
     * @return the list of the component items
     */
    public ArrayList<Item> getComponents()
    {
        return components;
    }

    /**
     *
     * @return the time it takes to craft the item
     */
    public double getCraftSpeed()
    {
        return craftSpeed;
    }

    /**
     *
     * @return the number of the item produced in the recipe
     */
    public double getNumProd()
    {
        return numProd;
    }

}
