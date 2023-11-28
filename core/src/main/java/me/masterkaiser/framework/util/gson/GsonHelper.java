package me.masterkaiser.framework.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GsonHelper {
    private static final @Getter Gson gson = new GsonBuilder().create();
    private static final @Getter Gson pretty = new GsonBuilder().setPrettyPrinting().create();
}
