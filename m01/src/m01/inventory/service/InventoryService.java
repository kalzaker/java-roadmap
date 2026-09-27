package m01.inventory.service;


import m01.inventory.model.InventorySlot;
import m01.inventory.model.Item;

import java.util.ArrayList;

public class InventoryService {
    private final ArrayList<InventorySlot> slots;
    private final double maxWeight;

    public InventoryService(int capacity, double maxWeight) {
        this.maxWeight = maxWeight;
        this.slots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            slots.add(new InventorySlot());
        }
    }

    public double getCurrentWeight() {
        double total = 0;
        for (InventorySlot slot : slots) {
            if (!slot.isEmpty()) {
                total += slot.getItem().getWeight() * slot.getAmount();
            }
        }
        return total;
    }

    public boolean addItem(Item item, int amount) {
        if (amount <= 0) return false;
        if (getCurrentWeight() + (item.getWeight() * amount) > maxWeight) {
            return false;
        }

        amount = fillExistingStacks(item, amount);
        if (amount > 0) {
            amount = fillEmptySlots(item, amount);
        }
        return amount == 0;
    }

    private int fillExistingStacks(Item item, int amount) {
        for (InventorySlot slot : slots) {
            if (amount <= 0) break;
            if (!slot.isEmpty() && slot.getItem().getName().equalsIgnoreCase(item.getName())) {
                int availableSpace = slot.getItem().getMaxStackSize() - slot.getAmount();
                int toAdd = Math.min(availableSpace, amount);
                slot.addAmount(toAdd);
                amount -= toAdd;
            }
        }
        return amount;
    }

    private int fillEmptySlots(Item item, int amount) {
        for (InventorySlot slot : slots) {
            if (amount <= 0) break;
            if (slot.isEmpty()) {
                int toAdd = Math.min(item.getMaxStackSize(), amount);
                slot.setItem(item, toAdd);
                amount -= toAdd;
            }
        }
        return amount;
    }

    public boolean removeItem(int slotIndex, int amount) {
        if (isInvalidIndex(slotIndex) || amount <= 0 || slots.get(slotIndex).isEmpty()) {
            return false;
        }
        InventorySlot slot = slots.get(slotIndex);
        if (amount >= slot.getAmount()) {
            slot.clear();
        } else {
            slot.removeAmount(amount);
        }
        return true;
    }

    public boolean moveItem(int fromIndex, int toIndex) {
        if (isInvalidIndex(fromIndex) || isInvalidIndex(toIndex) || fromIndex == toIndex) return false;
        InventorySlot from = slots.get(fromIndex);
        InventorySlot to = slots.get(toIndex);
        if (from.isEmpty()) return false;

        if (to.isEmpty()) {
            to.setItem(from.getItem(), from.getAmount());
            from.clear();
            return true;
        }

        if (from.getItem().getName().equalsIgnoreCase(to.getItem().getName())) {
            int space = to.getItem().getMaxStackSize() - to.getAmount();
            if (space <= 0) return false;

            int toMove = Math.min(space, from.getAmount());
            to.addAmount(toMove);
            from.removeAmount(toMove);
            if (from.getAmount() == 0) from.clear();
            return true;
        }
        return false;
    }

    public void searchByName(String query) {
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);
            if (!slot.isEmpty() && slot.getItem().getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println("  Слот " + i + ": " + slot.getItem().getName() + " x" + slot.getAmount());
            }
        }
    }

    public void printInventory() {
        System.out.println("\n=== ИНВЕНТАРЬ (Вес: " + getCurrentWeight() + " / " + maxWeight + ") ===");
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);
            if (slot.isEmpty()) {
                System.out.println("[" + i + "]: Пусто");
            } else {
                System.out.println("[" + i + "]: " + slot.getItem().getName() + " x" + slot.getAmount() + " (Макс. стек: " + slot.getItem().getMaxStackSize() + ")");
            }
        }
    }

    private boolean isInvalidIndex(int index) {
        return index < 0 || index >= slots.size();
    }
}
