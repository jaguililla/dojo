Game of Life
============

[Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) implemented in
[Scala](http://www.scala-lang.org/). This was the Kata used in
[Madrid Code Retreat 2013](http://madridcoderetreat.wordpress.com/).

It is implemented using inmutable entities and modeling the world as a list traversed iteratively.

Command line
------------
Run tests: `sbt test`

(Uncertain) Future
------------------
* Do it recursively (tail recursion)
* Use actors to split the world processing in several threads

Environment
-----------
* Scala 2.10
* SBT 0.13
* ScalaTest 2.0
