# Introduction

As a starting reference I have been reading [FTC SDK Wiki](https://github.com/FIRST-Tech-Challenge/SkyStone/wiki).

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

Android Studio while installing
![Android Studio After Import](/docs/00_AS_After_Import.PNG "Android Studio After Import")

Android Studio After import with SDK licenses error
![Android Studio After import with SDK licenses error](/docs/01_AS_Error.PNG "Android Studio After import with SDK licenses error")

Android Studio After Clicking on bottom link in window on lower right
![Android Studio After Clicking on bottom link in window on lower right](/docs/02_AS_Accept_License.PNG "Android Studio After Clicking on bottom link in window on lower right")

Android Studio Downloading SDK
![Android Studio Downloading SDK](/docs/03_AS_Downloading_SDK.PNG "Android Studio Downloading SDK")

Android Studio after import of FTC SDK SkyStone
![Android Studio after import of FTC SDK SkyStone](/docs/04_Final_SDK.PNG "Android Studio after import of FTC SDK SkyStone")



## Sample code

The FTC SDK includes lots of samples that you can build upon.

```
Sample opmodes exist in the FtcRobotController module.
To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

Expand the following tree elements:
 FtcRobotController / java / org.firstinspires.ftc.robotcontroller / external / samples
```
