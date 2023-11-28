package me.masterkaiser.framework.item.base;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseItemsSerializer implements ObjectSerializer<BaseItems> {
    @Override
    public boolean supports(@NonNull Class<? super BaseItems> type) {
        return BaseItems.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull BaseItems object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("items", Base64ItemStackUtil.itemStackArrayToBase64(object.getItems().toArray(new ItemStack[0])));
    }

    @Override
    public BaseItems deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final String stringItems = data.get("items", String.class);
        ItemStack[] items;
        try {
            items = Base64ItemStackUtil.itemStackArrayFromBase64(stringItems);
        } catch (IOException e) {
            items = null;
        }

        if (items == null) {
            return new BaseItems(new ArrayList<>());
        }

        return new BaseItems(List.of(items));
    }
}
