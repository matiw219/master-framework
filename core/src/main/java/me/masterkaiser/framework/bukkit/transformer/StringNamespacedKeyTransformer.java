package me.masterkaiser.framework.bukkit.transformer;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import lombok.NonNull;
import org.bukkit.NamespacedKey;

public class StringNamespacedKeyTransformer extends BidirectionalTransformer<String, NamespacedKey> {
    @Override
    public GenericsPair<String, NamespacedKey> getPair() {
        return this.genericsPair(String.class, NamespacedKey.class);
    }

    @Override
    public NamespacedKey leftToRight(@NonNull String data, @NonNull SerdesContext serdesContext) {
        String[] args = data.split("/");

        return new NamespacedKey(args[0], args[1]);
    }

    @Override
    public String rightToLeft(@NonNull NamespacedKey data, @NonNull SerdesContext serdesContext) {
        return data.getNamespace() + "/" + data.getKey();
    }
}
