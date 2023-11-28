package me.masterkaiser.framework.bukkit.transformer;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import lombok.NonNull;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class StringEnchantmentTransformer extends BidirectionalTransformer<String, Enchantment> {
    @Override
    public GenericsPair<String, Enchantment> getPair() {
        return this.genericsPair(String.class, Enchantment.class);
    }

    @Override
    public Enchantment leftToRight(@NonNull String data, @NonNull SerdesContext serdesContext) {
        String[] args = data.split("/");

        return Enchantment.getByKey(new NamespacedKey(args[0], args[1]));
    }

    @Override
    public String rightToLeft(@NonNull Enchantment data, @NonNull SerdesContext serdesContext) {
        return data.getKey().getNamespace() + "/" + data.getKey().getKey();
    }
}
