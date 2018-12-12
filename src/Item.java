public class Item
{
    private String name;

    /**
     *
     * @param name the name of the item
     */
    public Item(String name)
    {
        this.name = name;
    }
    /**
     *
     * @return the string representing this item.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the name of this item
     */
    public String getName()
    {
        return this.name;
    }
}
