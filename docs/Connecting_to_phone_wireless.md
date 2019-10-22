1. Click "Program and Manage" in the FTC robot controller on phone to pull up the name of the wifi and its password.
2. Connect to the wifi
3. Open "Terminal" on Android Studio
4. Connect Phone to USB and allow all popups on phone
5. Change directory to "C:/users/team/6085/AppData/Local/Android/Sdk/Platform-tools>"
6. Type "adb.exe devices" [Enter]
7. Make sure the device pops up on computer screen
8. Type "adb.exe tcpip 5555"[Enter] press allow on phone
9. Type "adb.exe connect [(192.168.49.1)The IP adress can change depending on which phone you are using]:5555"[Enter]
10. Then on the computer it should say connected and if it does you can unplug the phone
11. Next type adb.exe devices and it should say connected
12. Click play in the top left
