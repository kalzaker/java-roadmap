package m01.inventory.model;

public class InventorySlot {
    private Item item;
    private int amount;

    public InventorySlot(){
        this.item = null;
        this.amount = 0;
    }

    public boolean isEmpty() { return item == null; }
    public Item getItem() { return item;}
    public int getAmount() { return amount; }

    public void setItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public void clear(){
        this.item = null;
        this.amount = 0;
    }

    public void addAmount(int count) { this.amount += count; }
    public void removeAmount(int count) { this.amount -= count; }
}