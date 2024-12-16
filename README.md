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

## License

[![License: GPL-3.0](https://img.shields.io/badge/License-GPL%203.0-lightgrey.svg)](https://tldrlegal.com/license/gnu-general-public-license-v3-gpl-3)