package me.masterkaiser.framework.yaml;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import me.masterkaiser.framework.GlobalSettings;

@YamlConfig(fileName = "database")
public class DatabaseConfig extends OkaeriConfig {
    @Comment("MySQL")
    @Comment("uri: 'jdbc:mysql://{host}:{port}/{database}?user={user}&password={password}'")
    @Comment("H2")
    @Comment("uri: 'jdbc:h2:file:./plugins/{database}/storage;mode=mysql")
    @Comment("FLAT")
    @Comment("uri: ''")
    public String uri = "";
    public String prefix = GlobalSettings.PREFIX;
}
