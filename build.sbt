import Settings._

val `messaging` = project
  .in(file("."))
  .enablePlugins(DeployApp, DockerPlugin)
  .settings(defaultSettings: _*)
  .settings(
    libraryDependencies ++= Dependencies.Service
  )


