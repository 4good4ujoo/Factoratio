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

    /**
     * Generates a string containing the base time to craft this item and the number of ingredients to make it
     * in the previous level. (i.e. iron plates take 3.5 seconds to craft and 1 iron ore).
     * @return a string giving the primary recipe for the item.
     */
    public String getBaseRecipe()
    {
        String retstring = numProd + " " + this.getName() + ": " + this.getCraftSpeed() + " seconds ";

        for (int i = 0; i < components.size(); i++)
        {
            retstring.concat("+ " + counts[i] + " " + components.get(i));
        }

        return retstring;
    }

    /**
     * Uses the time it takes to produce this item and the components that it uses in order to determine consumption
     * of primary ingredients per minute.
     *
     * @param perMinute the number of this item that you want to produce per minute
     * @return a string that gives the number of input components needed to produce this many of this item per minute
     */
    public String getRecipePerMinute(double perMinute)
    {
        // if the recipe produces more than one item per cycle, then the consumption per output item is not the number
        // of the items that goes into the recipe
        double inputRatio = 1.0 / numProd;

        double[] modifiedInputs = new double[counts.length];
        for (int i = 0; i < counts.length; i ++)
        {
            modifiedInputs[i] = counts[i] * inputRatio;
        }

        String retstr = perMinute + " " + this.getName() + " per minute: ";

        for (int i = 0; i < modifiedInputs.length; i++)
        {
            if (i == 0)
            {
                retstr.concat(modifiedInputs[i] + " " + components.get(i));
            }
            else
            {
                retstr.concat(" + " + modifiedInputs[i] + " " + components.get(i));
            }
        }

        return retstr;
    }
}
