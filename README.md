<a id="readme-top"></a>

<h1 align="center">
  <br>
  <a href="https://github.com/mineklub/MinePay/releases"><img src="https://raw.githubusercontent.com/bondegaard/MinePay/main/.github/mineclub_logo.svg" alt="MineClub Logo"></a>
</h1>

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
  <a href="#features">Features</a> •
  <a href="#symbols">Symbols</a> •
  <a href="#binds">Binds</a> •
  <a href="#wiki">Wiki</a> •
  <a href="#contributing">Contributing</a> •
  <a href="#deprecated">Deprecated</a> •
  <a href="#credits">Credits</a> •
  <a href="#support">Support</a> •
  <a href="#license">License</a>
</p>

---

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

<a id="gradle"></a>
```
### Gradle Repository
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
> Test.