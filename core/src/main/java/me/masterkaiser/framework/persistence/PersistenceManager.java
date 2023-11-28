package me.masterkaiser.framework.persistence;

import com.zaxxer.hikari.HikariConfig;
import eu.okaeri.configs.json.gson.JsonGsonConfigurer;
import eu.okaeri.persistence.PersistencePath;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.flat.FlatPersistence;
import eu.okaeri.persistence.jdbc.H2Persistence;
import eu.okaeri.persistence.jdbc.MariaDbPersistence;
import eu.okaeri.persistence.raw.RawPersistence;
import me.masterkaiser.framework.GlobalSettings;
import me.masterkaiser.framework.MasterAppException;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.yaml.DatabaseConfig;
import org.springframework.context.annotation.Bean;

import java.io.File;

public class PersistenceManager {
    private final DatabaseConfig databaseConfig;
    private final MasterPlugin masterPlugin;

    public PersistenceManager(DatabaseConfig databaseConfig, MasterPlugin masterPlugin) {
        this.databaseConfig = databaseConfig;
        this.masterPlugin = masterPlugin;
    }

    @Bean
    public DocumentPersistence documentPersistence() {
        final PersistencePath persistencePath = PersistencePath.of(this.databaseConfig.prefix);

        if (databaseConfig.uri.isEmpty()) {
            return create(new FlatPersistence(new File(GlobalSettings.DATA_FOLDER), ".json"));
        } else if (databaseConfig.uri.startsWith("jdbc:mysql:")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            final HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(this.databaseConfig.uri);

            return create(new MariaDbPersistence(persistencePath, hikariConfig));
        } else if (databaseConfig.uri.startsWith("jdbc:h2:file:")) {
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            final HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(this.databaseConfig.uri);

            return create(new H2Persistence(persistencePath, hikariConfig));
        } else {
            throw new MasterAppException("");
        }
    }

    private DocumentPersistence create(RawPersistence rawPersistence) {
        return new DocumentPersistence(
                rawPersistence,
                JsonGsonConfigurer::new,
                masterPlugin.serializationPack()
        );
    }
}
