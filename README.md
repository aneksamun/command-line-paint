# Command Line Paint

![Build status](https://github.com/aneksamun/command-line-paint/actions/workflows/scala.yml/badge.svg)

A basic command line tool for drawing in console. Currently supports rectangles, lines and bucket fills.

## Commands 

| Command       | Description |
| ------------- | ------------- |
| C w h         | Create a new canvas of width w and height h.  |
| L x1 y1 x2 y2 | Creates a new line from (x1,y1) to (x2,y2) . Currently only horizontal or vertical lines are supported. |
| R x1 y1 x2 y2 | Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). |
| B x y c       | Fills the entire area connected to (x,y) with specified character. |
| Q             | Quits the program. |

## Use case

```
Enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

Enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

Enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

Enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

Enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

Enter command: Q
```

## Build instructions

- compile
```
sbt clean
sbt compile
```
- assemble JAR
```
sbt assembly
```
- reload
```
sbt reload
```
- run
```
sbt run
```
- test
```
sbt test
```
