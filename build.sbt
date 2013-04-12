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

resourceDirectory in Compile <<= baseDirectory(_ / "media")

excludeFilter in (Compile, unmanagedResources) <<=
  (resourceDirectory in Compile, excludeFilter in (Compile, unmanagedResources)) {
    (resd, ef) =>
    val rbase = resd.toURI
    ef || new SimpleFileFilter({s =>
      (rbase relativize s.toURI getPath) startsWith "unit_testing/"})
}

classDirectory in Compile ~= (_ / "com" / "clarifi" / "datatablesstatic")
/*copyResources in Compile <<= (, copyResources in Compile) map {
  (, crres) =>
  println(crres)
  crres
}
 */
