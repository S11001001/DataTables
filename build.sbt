parallelExecution := true

name := "datatables-static"

organization := "com.clarifi"

version := "1.9.0"

licenses := Seq("GNU General Public License (GPL), Version 2"
                  -> url("http://www.gnu.org/licenses/old-licenses/gpl-2.0.html"),
                "BSD 3-Clause"
                  -> url("http://opensource.org/licenses/BSD-3-Clause"))

homepage := Some(url("http://www.datatables.net"))

autoScalaLibrary := false // don't dep on scala-library

sourcesInBase := false

crossVersion := CrossVersion.Disabled // don't add _2.9.2 to artifact name

resourceDirectory <<= baseDirectory(_ / "media")
