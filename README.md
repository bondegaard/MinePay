<a id="readme-top"></a>

<h1 align="center">
  <br>
  <a href="https://github.com/mineklub/MinePay/releases"><img src="https://raw.githubusercontent.com/bondegaard/MinePay/main/.github/mineclub_logo.svg" alt="MineClub Logo"></a>
</h1>

<h2 align="center">MinePay.</h2>

<h4 align="center">Plugin til håndtering af betalinger med mønter på Mineclub.dk.</h4>

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
> Ældre versioner af MinePay understøttes muligvis ikke. Det anbefales derfor altid at bruge den nyeste version.

<a id="build"></a>
## Build
Brug følgende Gradle-kommando til at bygge MinePay:

```gradle
gradle server-bukkit:build
```
> [!NOTE]  
> Den genererede MinePay-fil gemmes i mappen `build/libs/MinePay.jar`.
>

<a id="eksemplar"></a>
## Eksemplar

### Plugin
Herunder findes eksempler på, hvordan MinePay kan bruges i dit plugin.

### Basic Opsætning
For at bruge MinePay skal det initialiseres ved plugin-opstart og deaktiveres ved plugin-stop.

> [!IMPORTANT]  
> MinePay skal initialiseres og deaktiveres for at fungere korrekt.


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

### Opret et produkt og anmod spiller om køb
```java
Player player = ...; // Spiller som gerne vil lave et køb
MinePayApi minePayApi = MinePayApi.initApi(this); // Hent MinePayApi

// Opret et produkt med navnet "Diamond", ID'et "diamond", en pris på 10 mønter og et antal på 1.
StoreProduct storeProduct = new StoreProduct("Diamond", "diamond", 10, 1);

// Send en købsanmodning til spilleren via MinePay
minePayApi.getRequestManager().createRequest(player.getUniqueId(), new StoreProduct[] {storeProduct})
```

### Opret flere producter og anmod spiller om køb
```java
Player player = ...; // // Spilleren, som ønsker at foretage et køb
MinePayApi minePayApi = MinePayApi.initApi(this); // Hent MinePayApi

// Opret et produkt med navnet "Diamond", ID'et "diamond", en pris på 10 mønter og et antal på 1.
StoreProduct storeProduct1 = new StoreProduct("Diamond", "diamond", 10, 1);


// Opret et produkt med navnet "Guld", ID'et "guld", en pris på 5 mønter, et antal på 2 og metadata med navn="test".
HashMap<String, String> metadata = new HashMap<>();
metadata.put("navn", "test");
        
StoreProduct storeProduct2 = new StoreProduct("Guld", "guld", 5, 2, metadata);

// Send en købsanmodning til spilleren via MinePay
minePayApi.getRequestManager().createRequest(player.getUniqueId(), new StoreProduct[] {storeProduct, storeProduct2})
```

## Håndter spiller, som accepterer/afviser et køb
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

        // Gennemgå alle købte produkter
        or (StoreProduct product : event.getRequest().getProducts()) {
            message.append(product.getName()).append(", ");

            // Tjek, om det købte produkt er "diamond"
            if (product.getId().equals("diamond")) {
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            }
        }
        message.append("for ").append(event.getRequest().getPrice()).append(" mønter.");

        // Accepter købet via MinePay
        // Hvis dette ikke bliver gjordt vil MinePay forsøge at give spilleren købet flere gange indtil købet enden er accepteret eller afvist
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