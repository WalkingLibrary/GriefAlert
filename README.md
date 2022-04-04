# Grief Alert Mincraft Spigot Plugin

![Rating](https://img.shields.io/badge/Rating-0%2F10-red)
![Sauce](https://img.shields.io/badge/100%25-Spaghetti%20Code-orange)
![Build Status](https://img.shields.io/badge/Build-Passing-green)

# About

- Alerts the Admin that a Player Broke Blocks that Someone else Placed

# Java Version

````bash
admin@MyLinuxPC:~/Java$ java -version
openjdk version "1.8.0_322"
OpenJDK Runtime Environment (build 1.8.0_322-8u322-b06-1~deb9u1-b06)
OpenJDK 64-Bit Server VM (build 25.322-b06, mixed mode)
````

# How to Build

````bash
admin@MyLinuxPC:~/Java$ sudo git clone https://github.com/WalkingLibrary/GriefAlert --tags
Cloning into 'GriefAlert'...
remote: Enumerating objects: 44, done.
remote: Counting objects: 100% (44/44), done.
remote: Compressing objects: 100% (17/17), done.
remote: Total 44 (delta 6), reused 44 (delta 6), pack-reused 0
Receiving objects: 100% (44/44), 61.95 KiB | 697.00 KiB/s, done.
Resolving deltas: 100% (6/6), done.
admin@MyLinuxPC:~/Java$ cd GriefAlert/
admin@MyLinuxPC:~/Java/GriefAlert$ sudo ./gradlew shadowjar
sudo: ./gradlew: command not found
admin@MyLinuxPC:~/Java/GriefAlert$ sudo chmod 700 ./gradlew
admin@MyLinuxPC:~/Java/GriefAlert$ sudo ./gradlew shadowjar

> Configure project :
Build Version: 0.0.3

> Task :shadowJar
The SimpleWorkResult type has been deprecated and is scheduled to be removed in Gradle 5.0. Please use WorkResults.didWork() instead.


BUILD SUCCESSFUL in 6s
3 actionable tasks: 3 executed
````

# How to Use

Place the Following Built Jar into your Spigot Server's Plugin Folder

````bash
admin@MyLinuxPC:~/Java/GriefAlert$ cd build/libs/
admin@MyLinuxPC:~/Java/GriefAlert/build/libs$ ls
GreifAlert-0.0.3-all.jar
admin@MyLinuxPC:~/Java/GriefAlert/build/libs$
````

License
----
![AUR license](https://img.shields.io/badge/License-MIT-blue)
