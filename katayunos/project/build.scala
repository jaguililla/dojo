import sbt._
import Keys._

object ApplicationBuild extends Build {
  name := "katayunos"
  version := "0.0-SNAPSHOT"
  libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
}
