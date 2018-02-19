Build instruction
-----------------

Please use sbt to run following commands:
- `sbt clean`
- `sbt compile`

if you wish to run tool from JAR you can use one in

target/scala-2.12/command-line-paint-assembly-0.1.jar

or assemble it by yourself by:

`sbt assembly`

if you will any problems with sbt try:

`sbt reload`


Frameworks
----------

I used ScalaTest framework fro testing purposes and
Enumeratum which solves various issues around Enumeration Scala has.

https://github.com/lloydmeta/enumeratum

Decision to use this library has made after reading following article:

http://pedrorijo.com/blog/scala-enums/
