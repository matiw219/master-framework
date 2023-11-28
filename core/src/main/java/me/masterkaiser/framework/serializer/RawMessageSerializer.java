package me.masterkaiser.framework.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.bukkit.message.RawMessage;
import me.masterkaiser.framework.bukkit.message.RawTitle;

import java.util.ArrayList;
import java.util.List;

public class RawMessageSerializer implements ObjectSerializer<RawMessage> {
    @Override
    public boolean supports(@NonNull Class<? super RawMessage> type) {
        return RawMessage.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull RawMessage object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        if (!object.getChat().isEmpty()) {
            if (object.getChat().size() == 1) {
                data.add("chat", object.getChat().get(0));
            } else {
                data.add("chat", object.getChat());
            }
        }

        if (object.getActionBar() != null) {
            data.add("actionBar", object.getActionBar());
        }

        if (object.getTitle() != null) {
            data.add("title", object.getTitle());
        }
    }

    @Override
    public RawMessage deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        List<String> chat = new ArrayList<>();

        if (data.containsKey("chat")) {
            try {
                chat = data.getAsList("chat", String.class);
            } catch (Exception e) {
                chat.add(data.get("chat", String.class));
            }
        }

        final String actionBar = data.get("actionBar", String.class);
        final RawTitle rawTitle = data.get("title", RawTitle.class);

        return new RawMessage(chat, actionBar, rawTitle);
    }
}
