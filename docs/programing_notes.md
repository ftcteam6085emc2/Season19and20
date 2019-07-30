# Introduction

As a starting reference I have been reading [FTC SDK Wiki](https://github.com/ftctechnh/ftc_app/wiki).

## FTC SDK

SDK = Software Developer's Kit and includes the starting point of any FTC Robot.  The [FTC SDK is on GitHub](https://github.com/FIRST-Tech-Challenge/SkyStone)
this link is different than in the reference below but the same steps apply.  

### Ubuntu 

BEFORE installing the SDK but after Android Studio is installed you must accept
the Android liscnese by running this command

```bash
 yes | sudo ~/Android/Sdk/tools/bin/sdkmanager --licenses
```

### Windows

There is an issue when opening the SDK when Android Studio is trying to sync where it will say something along the lines of you not
accepting the license and that you are missing a few files or something. There should be a link under the error notification, clicking
this will open up a window where you can accept the license and download whatever is missing.


![Android Studio After Import](00_AS_After_Import.png "Android Studio After Import")

## Sample code

The FTC SDK includes lots of samples that you can build upon.

```
Sample opmodes exist in the FtcRobotController module.
To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

Expand the following tree elements:
 FtcRobotController / java / org.firstinspires.ftc.robotcontroller / external / samples
 ```
