import java.util.ArrayList;
import java.util.Arrays;

/* TODO -- might be better to do recipes as nodes instead of items */

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
    public Craftable(String name, double[] count, double craftSpeed, double numProd, Item ... ingredients)
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

    @Override
    public String toString()
    {
        // for now just call all of the toStrings of ingredients with a multiplier
        String retString = (this.getName() + ":\n");

        // for each item in the list, indent that item, tab in, say its num and call its toString
        for (int i = 0; i < counts.length; i ++)
        {
            retString.concat("\t" + components.get(i).toString());
        }

        return retString;
    }

}
