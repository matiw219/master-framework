package me.masterkaiser.framework.serializer;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import lombok.NonNull;
import me.masterkaiser.framework.bukkit.message.RawTitle;

public class RawTitleSerializer implements ObjectSerializer<RawTitle> {
    @Override
    public boolean supports(@NonNull Class<? super RawTitle> type) {
        return RawTitle.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NonNull RawTitle object, @NonNull SerializationData data, @NonNull GenericsDeclaration generics) {
        data.add("title", object.getTitle());
        data.add("subTitle", object.getSubTitle());
        data.add("fadeIn", object.getFadeIn());
        data.add("stay", object.getStay());
        data.add("fadeOut", object.getFadeOut());
    }

    @Override
    public RawTitle deserialize(@NonNull DeserializationData data, @NonNull GenericsDeclaration generics) {
        final String title = data.get("title", String.class);
        final String subTitle = data.get("subTitle", String.class);
        final int fadeIn = data.get("fadeIn", Integer.class);
        final int stay = data.get("stay", Integer.class);
        final int fadeOut = data.get("fadeOut", Integer.class);

        return new RawTitle(title, subTitle, fadeIn, stay, fadeOut);
    }
}
