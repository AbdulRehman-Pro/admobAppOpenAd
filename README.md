[![Version](https://img.shields.io/badge/version-1.0.0-green.svg)](https://shields.io/)
[![Open Source Love svg1](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)


# admobAppOpenAd
Implement admob open ads in your android app.<br/><br/>
 <img src="https://user-images.githubusercontent.com/46995327/122922701-8b862b80-d381-11eb-8431-4030ef740f81.jpg" width="250"  alt="DEMO"/>

# How to Implement
To get a Git project into your build:
> Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories: <br/>
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
    
> Step 2. Add the dependency

Add it in your root app. gradle at the end of repositories: <br/> <br/>
[![](https://jitpack.io/v/rehmankhan8360/admobAppOpenAd.svg)](https://jitpack.io/#rehmankhan8360/admobAppOpenAd)

```gradle
dependencies {
	...
	implementation 'com.google.android.gms:play-services-ads:22.2.0'
	implementation 'com.github.rehmankhan8360:admobAppOpenAd:version'
	...
}
```

# How do I use Admob open Ads
Simple use cases will look something like this:
> Step 1. Create a class like MyApplication.class <br/>
```Kotlin
AppOpenAdManager(context, "ADMOB_OPEN_ADS_ID");
```
> Step 2. Update menifest
```xml
<application 
	name=".MyApplication"
	...
>
   <!-- Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 -->
    <meta-data
        android:name="com.google.android.gms.ads.APPLICATION_ID"
        android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>

</application>
```

<br/>

# Connect With Us
<a href="https://www.instagram.com/mani_.khan"> <img src="https://img.shields.io/badge/Instagram-Rehman-orange" /></a>
<a href="https://www.linkedin.com/in/abdul-rehman-android"> <img src="https://img.shields.io/badge/LinkedIn-Rehman-orange" /></a>
