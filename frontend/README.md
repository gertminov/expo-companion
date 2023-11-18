# Fixes
## A lot of error messages, that some methods could not be found when caused by the Touchhelper
The drawing library for the epaper display is OLD. Google has since deprecated a bunch of methods, 
you can reactivate them by running the following command:
```shell
adb shell settings put global hidden_api_policy 1
```
> You either have to have adb installed globally or you need to find the folder where Android studio
> stores the adb and run the commend in this folder (if on windows replace `adb` with `.\adb.exe`)