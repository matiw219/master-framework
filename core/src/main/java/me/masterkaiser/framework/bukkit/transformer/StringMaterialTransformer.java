package me.masterkaiser.framework.bukkit.transformer;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;

public class StringMaterialTransformer extends BidirectionalTransformer<String, Material> {
    @Override
    public GenericsPair<String, Material> getPair() {
        return this.genericsPair(String.class, Material.class);
    }

    @Override
    public Material leftToRight(@NonNull String data, @NonNull SerdesContext serdesContext) {
        String[] args = data.split("/");

        return Registry.MATERIAL.get(new NamespacedKey(args[0], args[1]));
    }

    @Override
    public String rightToLeft(@NonNull Material data, @NonNull SerdesContext serdesContext) {
        return data.getKey().getNamespace() + "/" + data.getKey().getKey();
    }
}
