# Introduction

As a starting reference I have been reading [FTC SDK Wiki](https://github.com/ftctechnh/ftc_app/wiki).
This is an older reference ["2017-2018 FIRST ® Tech Challenge Android Studio Programming Training Manual"](https://www.firstinspires.org/sites/default/files/uploads/resource_library/ftc/android-studio-tutorial.pdf)

It is a little dated but it seems to be working.

## FTC SDK

SDK = Software Developer's Kit and includes the starting point of any FTC Robot.  The [FTC SDK is on GitHub](https://github.com/FIRST-Tech-Challenge/SkyStone)
this link is different than in the reference below but the same steps apply.  BEFORE installing the SDK you must accept
the Android liscnese by running this command

```bash
 yes | sudo ~/Android/Sdk/tools/bin/sdkmanager --licenses
```

There is an issue when opening the SDK when Android Studio is trying to sync where it will say something along the lines of you not
accepting the license and that you are missing a few files or something. There should be a link under the error notification, clicking
this will open up a window where you can accept the license and download whatever is missing.

## Sample code

The FTC SDK includes lots of samples that you can build upon.

```
Sample opmodes exist in the FtcRobotController module.
To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

Expand the following tree elements:
 FtcRobotController / java / org.firstinspires.ftc.robotcontroller / external / samples
 ```
