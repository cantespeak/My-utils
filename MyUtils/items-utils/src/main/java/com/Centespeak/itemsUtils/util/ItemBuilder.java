package com.Centespeak.itemsUtils.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material){
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder setAmount(int value){
        item.setAmount(value);
        return this;
    }

    public ItemBuilder setName(String name){
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(List<String> strings){
        for(int i = 0; i<strings.size(); i++){
            strings.set(i, ChatColor.translateAlternateColorCodes('&', strings.get(i)));
        }
        meta.setLore(strings);
        return this;
    }

    public ItemBuilder addPersistentValue(String key, PersistentDataType type, Object object){
        meta.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString(key)), type, object);
        return this;
    }

    public ItemBuilder removePersistentValue(String key, PersistentDataType type, Object object){
        meta.getPersistentDataContainer().remove(Objects.requireNonNull(NamespacedKey.fromString(key)));
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level){
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment){
        meta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder setCustomModelData(int value){
        meta.setCustomModelData(value);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }
}
