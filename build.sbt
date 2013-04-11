parallelExecution := true

name := "datatables-static"

organization := "com.clarifi"

version := "1.9.0"

autoScalaLibrary := false // don't dep on scala-library

sourcesInBase := false

crossVersion := CrossVersion.Disabled // don't add _2.9.2 to artifact name

resourceDirectory <<= baseDirectory(_ / "media")
