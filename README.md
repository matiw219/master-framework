# master-framework

Specify repository type: ***releases*** or ***snapshots***.
```groovy
repositories {
    maven {
        name "masterRepositoryReleases"
        url "https://repo.masterkaiser.me/{type}"
    }
}
```

```groovy
dependencies {
    implementation "me.masterkaiser.framework:core:{version}"
    implementation "me.masterkaiser.framework:redis:{version}"
}
```
