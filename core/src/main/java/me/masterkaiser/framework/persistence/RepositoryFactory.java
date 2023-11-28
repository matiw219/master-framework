package me.masterkaiser.framework.persistence;

import eu.okaeri.persistence.PersistenceCollection;
import eu.okaeri.persistence.document.DocumentPersistence;
import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.RepositoryDeclaration;
import org.jetbrains.annotations.NotNull;

public class RepositoryFactory {
    private final DocumentPersistence documentPersistence;

    public RepositoryFactory(DocumentPersistence documentPersistence) {
        this.documentPersistence = documentPersistence;
    }

    public DocumentRepository createRepository(@NotNull Class<? extends DocumentRepository> clazz) {
        PersistenceCollection persistenceCollection = PersistenceCollection.of(clazz);
        this.documentPersistence.registerCollection(persistenceCollection);

        return RepositoryDeclaration.of((Class<? extends DocumentRepository>) clazz)
                .newProxy(this.documentPersistence, persistenceCollection, this.getClass().getClassLoader());
    }
}
