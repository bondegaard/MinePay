<a id="readme-top"></a>

<h1 align="center">
  <br>
  <a href="https://github.com/mineklub/MinePay/releases"><img src="https://raw.githubusercontent.com/bondegaard/MinePay/main/.github/mineclub_logo.svg" alt="MineClub Logo"></a>
</h1>

<h2 align="center">MinePay.</h2>

<h4 align="center">Plugin til at håndtere betallinger af mønter på Mineclub.dk.</h4>

<p align="center">
    <a href="https://github.com/mineklub/MinePay/commits/main">
    <img src="https://img.shields.io/github/last-commit/mineklub/MinePay.svg?style=flat-square&logo=github&logoColor=white"
         alt="GitHub last commit">
    <a href="https://github.com/mineklub/MinePay/issues">
    <img src="https://img.shields.io/github/issues-raw/mineklub/MinePay.svg?style=flat-square&logo=github&logoColor=white"
         alt="GitHub issues">
    <a href="https://github.com/mineklub/MinePay/pulls">
    <img src="https://img.shields.io/github/issues-pr-raw/mineklub/MinePay.svg?style=flat-square&logo=github&logoColor=white"
         alt="GitHub pull requests">
</p>

<p align="center">
  <a href="#maven">Maven</a> •
  <a href="#gradle">Gradle</a> •
  <a href="#build">Build</a> •
  <a href="#eksemplar">Eksemplar</a> •
  <a href="https://discord.gg/ePxVMN5ACh">Discord</a> •
  <a href="#license">License</a>
</p>

---
## Deppendency

<a id="maven"></a>
### Maven Repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.mineklub</groupId>
    <artifactId>MinePay</artifactId>
    <version>d7be9c0fa4</version>
    <scope>provided</scope>
</dependency>
```
### Gradle Repository
<a id="gradle"></a>
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

```

```gradle
dependencies {
    implementation 'com.github.mineklub:MinePay:d7be9c0fa4'
}

```

> [!IMPORTANT]  
> Gamle versioner af MinePay vil muligvis ikke blive understøttet og der opfordres derfor til altid at bruge den nyeste version.

<a id="build"></a>
## Build
Brug følgende gradle kommando for at bygge Minepay

```gradle
gradle server-bukkit:build
```
> [!NOTE]  
> MinePay vil blive bygget til mappen `build/libs/MinePay.jar`
>

<a id="eksemplar"></a>
## Eksemplar

### Plugin
Under findes der eksemplar på hvordan MinePay kan bruges i dit plugin.

### Basic Opsætning
For at bruge MinePay skal du initilizere ved opstart og disable det ved stop af dit plugin.

```java
private MinePayApi minePayApi;

@Override
public void onEnable() {
    minePayApi = MinePayApi.initApi(this);
}

@Override
public void onDisable() {
    minePayApi.disable();
}
```

## Opret et product og anmod spiller om køb
```java
Player player = ...; // Spiller som gerne vil lave et køb
MinePayApi minePayApi = MinePayApi.initApi(this); // Hent MinePayApi

// Lav et product ved navn "Diamond" med id "diamond" som koster 10 mønter og har antal 1.
StoreProduct storeProduct = new StoreProduct("Diamond", "diamond", 10, 1);

// Send købsanmodning til Spilleren igennem MinePay
minePayApi.getRequestManager().createRequest(player.getUniqueId(), new StoreProduct[] {storeProduct})
```

## Opret flere producter og anmod spiller om køb
```java
Player player = ...; // Spiller som gerne vil lave et køb
MinePayApi minePayApi = MinePayApi.initApi(this); // Hent MinePayApi

// Lav et product ved navn "Diamond" med id "diamond" som koster 10 mønter og har antal 1.
StoreProduct storeProduct1 = new StoreProduct("Diamond", "diamond", 10, 1);


// Lav et product ved navn "Guld" med id "guld" som koster 5 mønter og har antal 2 med Metadata navn="test".
HashMap<String, String> metadata = new HashMap<>();
metadata.put("navn", "test");
        
StoreProduct storeProduct2 = new StoreProduct("Guld", "guld", 5, 2, metadata);

// Send købsanmodning til Spilleren igennem MinePay
minePayApi.getRequestManager().createRequest(player.getUniqueId(), new StoreProduct[] {storeProduct, storeProduct2})
```

## Håndter spiller som acceptere/afviser et køb
```java
import dk.minepay.server.bukkit.MinePayApi;
import dk.minepay.server.bukkit.classes.StoreProduct;
import dk.minepay.server.bukkit.events.StoreRequestAcceptOnlineEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private MinePayApi minePayApi;

    @Override
    public void onEnable() {
        minePayApi = MinePayApi.initApi(this);
    }

    @Override
    public void onDisable() {
        minePayApi.disable();
    }


    @EventHandler
    public void onStoreRequestAccept(StoreRequestAcceptOnlineEvent event) {
        Player player =  event.getPlayer();
        
        StringBuilder message = new StringBuilder("§2[MinePay] " + event.getPlayer().getName() + " har købt: ");
        
        // Gå igennem alle købte produkter
        or (StoreProduct product : event.getRequest().getProducts()) {
            message.append(product.getName()).append(", ");
            
            // Tjek om det købte product er "diamond"
            if (product.getId().equals("diamond")) {
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            }
        }
        message.append("for ").append(event.getRequest().getPrice()).append(" mønter.");

        // Accepter købet igennem MinePay, ellers vil minepay blive ved med at køre købet indtil serveren siger at spillerne har modtaget købet.
        Bukkit.getScheduler().runTaskAsynchronously(MinePayApi.getINSTANCE().getPlugin(), () -> {
            MinePayApi.getINSTANCE()
                    .getRequestManager()
                    .acceptRequest(event.getRequest().get_id());
        });
        Bukkit.broadcastMessage(message.toString());
    }

    @EventHandler
    public void onStoreRequestCancelJoin(StoreRequestCancelOnlineEvent event) {
        StringBuilder message = new StringBuilder("§cDu har annulleret købet af: ");

        for (StoreProduct product : event.getRequest().getProducts()) {
            message.append(product.getName()).append(", ");
        }

        message.append("for ").append(event.getRequest().getPrice()).append(" mønter.");
        event.getPlayer().getPlayer().sendMessage(message.toString());
    }
}
```

## License

[![License: GPL-3.0](https://img.shields.io/badge/License-GPL%203.0-lightgrey.svg)](https://tldrlegal.com/license/gnu-general-public-license-v3-gpl-3)