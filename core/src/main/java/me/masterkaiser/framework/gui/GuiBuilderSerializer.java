package me.masterkaiser.framework.gui;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.MasterAppException;
import me.masterkaiser.framework.item.ItemBuilder;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.Map;

public class GuiBuilderSerializer implements ObjectSerializer<GuiBuilder> {
    @Override
    public boolean supports(@NonNull Class<? super GuiBuilder> type) {
        return GuiBuilder.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull GuiBuilder object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("name", object.getName());

        if (object.getInventoryType() == null) {
            data.add("rows", object.getRows());
        } else {
            data.add("type", object.getInventoryType());
        }

        if (object.getBackground() != null) {
            data.add("background", object.getBackground());
        }

        if (!object.getItems().isEmpty()) {
            data.add("items", object.getItems());
        }

        if (object.getActions() != null) {
            data.add("actions", object.getActions());
        }
    }

    @Override
    public GuiBuilder deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final String name = data.get("name", String.class);

        final Map<Integer, ItemBuilder> items = data.containsKey("items")
                ? data.getAsMap("items", Integer.class, ItemBuilder.class)
                : new HashMap<>();

        final Map<String, Integer> actions = data.containsKey("actions")
                ? data.getAsMap("actions", String.class, Integer.class)
                : null;

        final ItemBuilder background = data.containsKey("background")
                ? data.get("background", ItemBuilder.class)
                : null;

        if (data.containsKey("rows")) {
            final int rows = data.get("rows", Integer.class);

            return new GuiBuilder(name, rows).setActions(actions).setItems(items).setBackground(background);
        }

        final InventoryType inventoryType = data.containsKey("type")
                ? data.get("type", InventoryType.class)
                : null;

        if (inventoryType == null) {
            throw new MasterAppException("Inventory cannot be null!");
        }

        return new GuiBuilder(name, inventoryType).setActions(actions).setItems(items).setBackground(background);
    }
}
