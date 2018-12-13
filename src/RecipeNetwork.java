import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class RecipeNetwork
{
    private Hashtable<String, Item> items;

    public RecipeNetwork()
    {
        items = new Hashtable<>();
        // initialize recipes from the ground up
        // start with ores/raw

        /* TODO -- fluids are crafted outside of assemblers, so maybe need other stuff for that */
        /* TODO -- barrels contain 50 of a fluid; may be useful eventually to be able to indicate that */
        /* TODO -- some items have multiple recipes */
        /* TODO -- how do we handle recipes that have multiple upstream possibilities? */
        /* TODO -- might need a specific "crafted by" (i.e. assmbler vs furnace vs chem plant, etc) field for items */
        /* TODO -- since fluids kinda do their own thing, I think in recipe cascades I'll treat them as raw, and then
           have a separate cascade for them. Note that I think this is a fairly complicated problem.
         */

        // Raw inputs
        Item rawWood = new Item(ItemNames.RAW_WOOD_STR);
        items.put(ItemNames.RAW_WOOD_STR, rawWood);
        Item coal = new Item(ItemNames.COAL_STR);
        items.put(ItemNames.COAL_STR, coal);
        Item stone = new Item(ItemNames.STONE_STR);
        items.put(ItemNames.STONE_STR, stone);
        Item ironOre = new Item(ItemNames.IRON_ORE_STR);
        items.put(ItemNames.IRON_ORE_STR, ironOre);
        Item copperOre = new Item(ItemNames.COPPER_ORE_STR);
        items.put(ItemNames.COPPER_ORE_STR, copperOre);
        Item uraniumOre = new Item(ItemNames.URANIUM_ORE_STR);
        items.put(ItemNames.URANIUM_ORE_STR, uraniumOre);
        Item rawFish = new Item(ItemNames.RAW_FISH_STR);
        items.put(ItemNames.RAW_FISH_STR, rawFish);
        Item crudeOil = new Item(ItemNames.CRUDE_OIL_STR);
        items.put(ItemNames.CRUDE_OIL_STR, crudeOil);
        Item water = new Item(ItemNames.WATER_STR);
        items.put(ItemNames.WATER_STR, water);
        Item steam = new Item(ItemNames.STEAM_STR); // Technically produced from water
        items.put(ItemNames.STEAM_STR, steam);

        // Second level items
        /* TODO -- heavy oil has multiple recipes */
        Item heavyOil = new Item(ItemNames.HEAVY_OIL_STR);
        items.put(ItemNames.HEAVY_OIL_STR, heavyOil);
        /* TODO -- light oil has multiple recipes */
        Item lightOil = new Item(ItemNames.LIGHT_OIL_STR);
        items.put(ItemNames.LIGHT_OIL_STR, lightOil);
        /* TODO -- petroleum gas has multiple recipes */
        Item petroleumGas = new Item(ItemNames.PETROLEUM_GAS_STR);
        items.put(ItemNames.PETROLEUM_GAS_STR, petroleumGas);
        /* TODO -- solid fuel has multiple recipes */
        Item solidFuel = new Item(ItemNames.SOLID_FUEL_STR);
        items.put(ItemNames.SOLID_FUEL_STR, solidFuel);
        /* TODO -- uranium processing has two OUTPUTS --> and they're fractional */
        Item uraniumProcessing = new Item(ItemNames.URANIUM_PROCESSING_STR);
        items.put(ItemNames.URANIUM_PROCESSING_STR, uraniumProcessing);
        /* TODO -- has two outputs */
        Item kovarexEnrichmentProcess = new Item(ItemNames.KOVAREX_ENRICHMENT_PROCESS_STR);
        items.put(ItemNames.KOVAREX_ENRICHMENT_PROCESS_STR, kovarexEnrichmentProcess);


        Item wood = genCraftableRecipe(ItemNames.WOOD_STR, 0.5, 2, CrafterType.ASSEMBLER,
                new double[]{1}, rawWood);
        Item ironPlate = genCraftableRecipe(ItemNames.IRON_PLATE_STR, 3.5, 1, CrafterType.FURNACE,
                new double[]{1}, ironOre);
        Item copperPlate = genCraftableRecipe(ItemNames.COPPER_PLATE_STR, 3.5, 1,
                CrafterType.FURNACE, new double[]{1}, copperOre);
        Item lubricant = genCraftableRecipe(ItemNames.LUBIRCANT_STR, 1, 10,
                CrafterType.CHEMICAL_PLANT, new double[]{10}, heavyOil);
        Item sulfur = genCraftableRecipe(ItemNames.SULFUR_STR, 1, 2, CrafterType.CHEMICAL_PLANT,
                new double[]{30, 30}, petroleumGas, water);
        Item sulfuricAcid = genCraftableRecipe(ItemNames.SULFURIC_ACID_STR, 1, 50,
                CrafterType.CHEMICAL_PLANT, new double[]{1, 5, 100}, ironPlate, sulfur, water);
        Item steelPlate = genCraftableRecipe(ItemNames.STEEL_PLATE_STR, 17.5, 1,
                CrafterType.FURNACE, new double[]{5}, ironPlate);
        Item plasticBar = genCraftableRecipe(ItemNames.PLASTIC_BAR_STR, 1, 2,
                CrafterType.CHEMICAL_PLANT, new double[]{1, 20}, coal, petroleumGas);
        Item battery = genCraftableRecipe(ItemNames.BATTERY_STR, 5, 1, CrafterType.CHEMICAL_PLANT,
                new double[]{1, 1, 20}, copperPlate, copperPlate, sulfuricAcid);
        Item explosives = genCraftableRecipe(ItemNames.EXPLOSIVES_STR, 5, 2,
                CrafterType.CHEMICAL_PLANT, new double[]{1, 1, 10}, coal, sulfur, water);
        Item emptyBarrel = genCraftableRecipe(ItemNames.EMPTY_BARREL_STR, 1, 1,
                CrafterType.ASSEMBLER, new double[]{1}, steelPlate);
        Item crudeOilBarrel = genCraftableRecipe(ItemNames.CRUDE_OIL_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, crudeOil);
        Item heavyOilBarrel = genCraftableRecipe(ItemNames.HEAVY_OIL_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, heavyOil);
        Item lightOilBarrel = genCraftableRecipe(ItemNames.LIGHT_OIL_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, lightOil);
        Item lubricantBarrel = genCraftableRecipe(ItemNames.LUBRICANT_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, lubricant);
        Item petroleumGasBarrel = genCraftableRecipe(ItemNames.PETROLEUM_GAS_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, petroleumGas);
        Item sulfuricAcidBarrel = genCraftableRecipe(ItemNames.SULFURIC_ACID_BARREL_STR, 0.2, 1,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, petroleumGas);
        Item waterBarrel = genCraftableRecipe(ItemNames.WATER_BARREL_STR, 0.2, 50,
                CrafterType.ASSEMBLER, new double[]{1, 50}, emptyBarrel, water);
        Item copperCable = genCraftableRecipe(ItemNames.COPPER_CABLE_STR, 0.5, 2,
                CrafterType.ASSEMBLER, new double[]{1}, copperPlate);
        Item ironStick = genCraftableRecipe(ItemNames.IRON_STICK_STR, 0.5, 2, CrafterType.ASSEMBLER,
                new double[]{1}, ironPlate);
        Item ironGearWheel = genCraftableRecipe(ItemNames.IRON_GEAR_WHEEL_STR, 0.5, 1,
                CrafterType.ASSEMBLER, new double[]{2}, ironPlate);
        Item electronicCircuit = genCraftableRecipe(ItemNames.ELECTRONIC_CIRCUIT_STR, 0.5, 1,
                CrafterType.ASSEMBLER, new double[]{3, 1}, copperCable, ironPlate);
        Item advancedCircuit = genCraftableRecipe(ItemNames.ADVANCED_CIRCUI_STR, 6, 1,
                CrafterType.ASSEMBLER, new double[]{4, 2, 2}, copperCable, electronicCircuit, plasticBar);
        Item processingUnit = genCraftableRecipe(ItemNames.PROCESSING_UNIT_STR, 10, 1,
                CrafterType.ASSEMBLER, new double[]{2, 20, 5}, advancedCircuit, electronicCircuit, sulfuricAcid);
        Item pipe = genCraftableRecipe(ItemNames.PIPE_STR, 0.5, 1, CrafterType.ASSEMBLER,
                new double[]{1}, ironPlate);
        Item engineUnit = genCraftableRecipe(ItemNames.ENGINE_UNIT_STR, 10, 1,
                CrafterType.ASSEMBLER, new double[]{1, 2, 1}, ironGearWheel, pipe, steelPlate);
        Item electricEngineUnit = genCraftableRecipe(ItemNames.ELECTRIC_ENGINE_UNIT_STR, 10, 1,
                CrafterType.ASSEMBLER, new double[]{2, 1, 15}, electronicCircuit, engineUnit, lubricant);
        Item flyingRobotFrame = genCraftableRecipe(ItemNames.FLYING_ROBOT_FRAME_STR, 20, 1,
                CrafterType.ASSEMBLER, new double[]{2, 1, 3, 1}, battery, electricEngineUnit, electronicCircuit,
                steelPlate);
        Item accumulator = genCraftableRecipe(ItemNames.ACCUMULATOR_STR, 10, 1,
                CrafterType.ASSEMBLER, new double[]{5, 2}, battery, ironPlate);
        Item lowDensityStructure = genCraftableRecipe(ItemNames.LOW_DENSITY_STRUCTURE_STR, 30, 1,
                CrafterType.ASSEMBLER, new double[]{5, 5, 10}, copperPlate, plasticBar, steelPlate);
        Item radar = genCraftableRecipe(ItemNames.RADAR_STR, 0.5, 1, CrafterType.ASSEMBLER,
                new double[]{5, 5, 10}, electronicCircuit, ironGearWheel, ironPlate);
        Item rocketFuel = genCraftableRecipe(ItemNames.ROCKET_FUEL_STR, 30, 1,
                CrafterType.ASSEMBLER, new double[]{10}, solidFuel);
        Item solarPanel = genCraftableRecipe(ItemNames.SOLAR_PANEL_STR, 10, 1,
                CrafterType.ASSEMBLER, new double[]{5, 15,5}, copperPlate, electronicCircuit, steelPlate);
        Item satellite = genCraftableRecipe(ItemNames.SATELLITE_STR, 5, 1, CrafterType.ASSEMBLER,
                new double[]{100, 100, 100, 5, 50, 100}, accumulator, lowDensityStructure, processingUnit, radar,
                rocketFuel, solarPanel);
        Item speedModule = genCraftableRecipe(ItemNames.SPEED_MODULE_STR, 15, 1,
                CrafterType.ASSEMBLER, new double[]{5, 5}, advancedCircuit, electronicCircuit);
        Item rocketControlUnit = genCraftableRecipe(ItemNames.ROCKET_CONTROL_UNIT_STR, 30, 1,
                CrafterType.ASSEMBLER, new double[]{1, 1}, processingUnit, speedModule);
        Item rocketPart = genCraftableRecipe(ItemNames.ROCKET_PART_STR, 3, 1, CrafterType.ASSEMBLER,
                new double[]{10, 10, 10}, lowDensityStructure, rocketControlUnit, rocketFuel);
        Item uranium235 = genCraftableRecipe(ItemNames.URANIUM_235_STR, 10, 0.007,
                CrafterType.CENTRIFUGE, new double[]{10}, uraniumOre);
        Item nuclearFuel = genCraftableRecipe(ItemNames.NUCLEAR_FUEL_STR, 60, 1,
                CrafterType.CENTRIFUGE, new double[]{1, 1}, rocketFuel, uranium235);
        Item uranium238 = genCraftableRecipe(ItemNames.URANIUM_238_STR, 10, 0.993,
                CrafterType.CENTRIFUGE, new double[]{10}, uraniumOre);
        Item uraniumFuelCell = genCraftableRecipe(ItemNames.URANIUM_FUEL_CELL_STR, 10, 10,
                CrafterType.ASSEMBLER, new double[]{10, 1, 19}, ironPlate, uranium235, uranium238);
        Item usedUpUraniumFuelCell = genCraftableRecipe(ItemNames.USED_UP_URANIUM_FUEL_CELL_STR, 200,
                1, CrafterType.HEATER, new double[]{1}, uraniumFuelCell);

        /* TODO -- this produces uranium238 */
        Item nuclearFuelReprocessing = genCraftableRecipe(ItemNames.NUCLEAR_FUEL_REPROCESSING_STR, 50,
                3, CrafterType.CENTRIFUGE, new double[]{5}, usedUpUraniumFuelCell);


        // Everything else (mostly)

        // Science last

        // umm... what to do with things with multiple recipes
        /*
        solid fuel
        petroleum gas
         */

    }

    /* TODO -- need to be able to return an item on demand and its recipe cascade in ratios */
    /* TODO -- need to be able to give assembler counts based on desired output */

    /**
     * Determines the recipe cascade for an item to determine the number of each element needed
     * to produce the output per minute
     *
     * @param itemName the name of the item being crafted
     * @param output the desired number of the item to be produced per minute
     * @return the recipe cascade
     */
    public String getRecipe(String itemName, double output)
    {
        /* TODO -- something for the items: there needs to be a scaling with levels of assemblers */
        Item item = items.get(itemName);
        if (item != null)
        {
            return item.toString();
        }
        else
        {
            return "That item is no currently in the recipe book.\n";
        }

    }

    /**
     * Generate the craftable item based on parameters and adds it to hashtable
     *
     * @param itemName
     * @param craftingTime
     * @param outCount
     * @param ingredientCount
     * @param ingredients
     *
     * @return the craftable item
     */
    private Craftable genCraftableRecipe(String itemName,
                                         double craftingTime,
                                         double outCount,
                                         CrafterType crafter,
                                         double[] ingredientCount,
                                         Item ... ingredients)
    {
        Craftable newItem = new Craftable(itemName, ingredientCount, craftingTime, crafter, outCount, ingredients);

        items.put(itemName, newItem);

        return newItem;
    }
}
