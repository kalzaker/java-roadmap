package m01.inventory.model;

public class Item {
    private final String name;
    private final double weight;
    private final int maxStackSize;

    public Item(String name, double weight, int maxStackSize){
        this.name = name;
        this.weight = weight;
        this.maxStackSize = maxStackSize;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public int getMaxStackSize() { return maxStackSize; }
}
