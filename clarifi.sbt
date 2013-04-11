// You'll also need externalIvySettings & publishTo, in zbuild.sbt.

publishMavenStyle := false

publishArtifact in (Compile, packageDoc) := false

publishArtifact in (Compile, packageSrc) := false

// Drop version numbers from our generated artifacts (jars)
artifactName := { (config, module: ModuleID, artifact: Artifact) =>
  artifact.name + "." + artifact.extension
}
