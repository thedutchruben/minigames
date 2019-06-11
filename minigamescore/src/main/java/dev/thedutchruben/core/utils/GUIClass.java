package dev.thedutchruben.core.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GUIClass {

    private Inventory inventory;
    private List<ItemStack> allItems;
    private UUID id;
    private int currentPage;

    public GUIClass(InventoryHolder holder, int size, String name, List<ItemStack> items) {
        inventory = Bukkit.createInventory(holder, size, ChatColor.translateAlternateColorCodes('&', name));
        allItems = items;
        id = UUID.randomUUID();
    }

    public GUIClass(InventoryHolder holder, int size, String name) {
        this(holder, size, name, new ArrayList<ItemStack>());
    }

    public GUIClass(int size, String name, List<ItemStack> items) {
        this(null, size, name, items);
    }

    public GUIClass(String name, List<ItemStack> items) {
        this(null, 54, name, items);
    }

    public GUIClass(InventoryHolder holder, int size, List<ItemStack> items) {
        this(null, size, "Inventory", items);
    }

    public GUIClass(InventoryHolder holder, String name, List<ItemStack> items) {
        this(null, 54, name, items);
    }

    public GUIClass(InventoryHolder holder, int size){
        this(holder, size, "Inventory", new ArrayList<ItemStack>());
    }

    public GUIClass(int size, String name) {
        this(null, size, name, new ArrayList<ItemStack>());
    }

    public GUIClass(InventoryHolder holder) {
        this(holder, 54, "Inventory", new ArrayList<ItemStack>());
    }

    public GUIClass(int size) {
        this(null, size, "Inventory", new ArrayList<ItemStack>());
    }

    public GUIClass(String name) {
        this(null, 54, name, new ArrayList<ItemStack>());
    }

    public GUIClass addItem(ItemStack item) {
        inventory.addItem(item);
        return this;
    }

    public GUIClass addItem(ItemStack item, int slot) {
        if (inventory.getItem(slot) == null) {
            inventory.setItem(slot, item);
        } else if (inventory.getItem(slot).getType() == Material.AIR) {
            inventory.setItem(slot, item);
        }
        return this;
    }

    public GUIClass remove(ItemStack item) {
        inventory.remove(item);
        return this;
    }

    public GUIClass remove(Material material) {
        inventory.remove(material);
        return this;
    }

    public GUIClass removeItem(ItemStack... item) {
        inventory.removeItem(item);
        return this;
    }

    public GUIClass setContents(ItemStack[] items){
        inventory.setContents(items);
        return this;
    }

    public int getPages() {
        if (allItems == null)
            return 0;
        if (allItems.size() == 0)
            return 0;
        return partition(allItems, inventory.getSize()).size();
    }

    private <T> List<List<T>> partition(List<T> originalList, int partitionSize) {
        List<List<T>> partitions = new LinkedList<List<T>>();
        for (int i = 0; i < originalList.size(); i += partitionSize) {
            partitions.add(originalList.subList(i,
                    Math.min(i + partitionSize, originalList.size())));
        }
        return partitions;
    }

    public GUIClass open(Player p) {
        if (inventory == null)
            return this;
        p.openInventory(inventory);
        return this;
    }

    public GUIClass open(List<Player> players) {
        if (inventory == null)
            return this;
        for (Player p : players) {
            p.openInventory(inventory);
        }
        return this;
    }

    public GUIClass open(Player[] players) {
        if (inventory == null)
            return this;
        for (Player p : players) {
            p.openInventory(inventory);
        }
        return this;
    }

    public GUIClass setPage(int page) {
        int pages = this.getPages();
        if (pages == 0)
            return this;
        if (page > pages) {
            page = pages;
        } else if (page < 1) {
            page = 1;
        }
        List<List<ItemStack>> splits = partition(allItems, inventory.getSize());
        List<ItemStack> currentPage = splits.get(page - 1);
        int count = 0;
        for (ItemStack item : currentPage) {
            this.addItem(item, count);
            count++;
        }
        this.currentPage = page;
        return this;
    }

    public GUIClass clearInventory() {
        inventory.clear();
        return this;
    }

    public GUIClass clearItems() {
        allItems.clear();
        return this.clearInventory();
    }

    public GUIClass setName(String name) {
        Inventory temp = Bukkit.createInventory(inventory.getHolder(), inventory.getSize(),
                ChatColor.translateAlternateColorCodes('&', name));
        temp.setContents(inventory.getContents());
        inventory = temp;
        return this;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public boolean canFit(ItemStack item) {
        if (inventory.firstEmpty() != -1)
            return true;
        int amount = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.getItemMeta().equals(item.getItemMeta())) {
                amount += 64 - it.getAmount();
            }
        }
        if (amount >= item.getAmount())
            return true;
        return false;
    }

    public GUIClass replaceAll(ItemStack from, ItemStack to) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.equals(from)) {
                this.setItem(count, to);
            }
            count++;
        }
        return this;
    }

    public GUIClass replaceFirst(ItemStack from, ItemStack to) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.equals(from)) {
                return this.setItem(count, to);
            }
            count++;
        }
        return this;
    }

    public GUIClass replaceAll(Material from, ItemStack to) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.getType() == from) {
                this.setItem(count, to);
            }
            count++;
        }
        return this;
    }

    public GUIClass replaceFirst(Material from, ItemStack to) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.getType() == from) {
                return this.setItem(count, to);
            }
            count++;
        }
        return this;
    }

    public GUIClass setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    public UUID getUniqueID() {
        return this.id;
    }

    public GUIClass clearFirst(Material item) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.getType() == item) {
                return this.setItem(count, new ItemStack(Material.AIR, 1));
            }
            count++;
        }
        return this;
    }

    public GUIClass clearAll(Material item) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.getType() == item) {
                this.setItem(count, new ItemStack(Material.AIR, 1));
            }
            count++;
        }
        return this;
    }

    public GUIClass clearFirst(ItemStack item) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.equals(item)) {
                return this.setItem(count, new ItemStack(Material.AIR, 1));
            }
            count++;
        }
        return this;
    }

    public GUIClass clearAll(ItemStack item) {
        int count = 0;
        for (ItemStack it : inventory.getContents()) {
            if (it.equals(item)) {
                this.setItem(count, new ItemStack(Material.AIR, 1));
            }
            count++;
        }
        return this;
    }

    public boolean contains(Material item) {
        return inventory.contains(item);
    }

    public boolean contains(ItemStack item) {
        return inventory.contains(item);
    }

    public boolean contains(Material item, int slot) {
        return inventory.contains(item, slot);
    }

    public boolean contains(ItemStack item, int slot) {
        return inventory.contains(item, slot);
    }

    public GUIClass addRawItem(ItemStack item) {
        allItems.add(item);
        return this;
    }

    public GUIClass addRawItem(int index, ItemStack item) {
        allItems.add(index, item);
        return this;
    }

    public GUIClass update() {
        this.setPage(currentPage);
        return this;
    }

    public GUIClass removeRawItem(ItemStack item) {
        allItems.remove(item);
        return this;
    }

    public GUIClass removeRawItem(int index) {
        allItems.remove(index);
        return this;
    }

    public List<ItemStack> getRawList() {
        return allItems;
    }

    public GUIClass setRawList(List<ItemStack> list) {
        allItems = list;
        return this;
    }

    public GUIClass clearRawList() {
        allItems = new ArrayList<ItemStack>();
        return this;
    }

    public ItemStack getRawItem(int index) {
        return allItems.get(index);
    }

    public boolean rawListContains(ItemStack item) {
        return allItems.contains(item);
    }

    public boolean isRawListEmpty() {
        return allItems.isEmpty();
    }

    public int rawIndexOf(ItemStack item) {
        return allItems.indexOf(item);
    }

    public GUIClass nextPage() {
        int pages = this.getPages();
        if (currentPage >= pages)
            return this;
        return this.setPage(currentPage + 1);
    }

    public GUIClass previousPage() {
        if (currentPage <= 1)
            return this;
        return this.setPage(currentPage - 1);
    }

    public GUIClass setMaxStackSize(int size) {
        inventory.setMaxStackSize(size);
        return this;
    }

    public Inventory getInventory() {
        return inventory;
    }

}
