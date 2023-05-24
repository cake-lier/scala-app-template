import Dependencies._
import java.time.{LocalDateTime, ZoneId}

Global / onChangedBuildSource := ReloadOnSourceChanges

Global / excludeLintKeys := Set(idePackagePrefix)

ThisBuild / scalaVersion := "3.2.2"

ThisBuild / scalafixDependencies ++= Seq(
  "com.github.liancheng" %% "organize-imports" % "0.6.0",
  "io.github.ghostbuster91.scalafix-unified" %% "unified" % "0.0.8",
  "net.pixiv" %% "scalafix-pixiv-rule" % "4.5.1"
)

ThisBuild / idePackagePrefix := Some("io.github.cakelier")

lazy val root = project
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "scala-app-template",
    scalacOptions ++= Seq(
      "-deprecation",
      "-Xfatal-warnings"
    ),
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    libraryDependencies ++= Seq(
      scalactic,
      scalatest
    ),
    wartremoverErrors ++= Warts.allBut(Wart.ImplicitParameter),
    version := "0.0.0",
    coverageEnabled := false,
    coverageMinimumStmtTotal := 80,
    coverageMinimumBranchTotal := 80,
    headerLicense := Some(HeaderLicense.MIT(
      LocalDateTime.now(ZoneId.of("UTC+1")).getYear.toString,
      "Matteo Castellucci"
    )),
    assembly / assemblyJarName := "main.jar",
    assembly / mainClass := Some("io.github.cakelier.main"),
    docker / dockerfile := NativeDockerfile(file("Dockerfile")),
    docker / imageNames := Seq(ImageName(
      namespace = Some("matteocastellucci3"),
      repository = name.value,
      tag = Some("latest")
    ))
  )
