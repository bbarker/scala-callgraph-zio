import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    crossScalaVersions := Seq("3.0.2"),
    scalaVersion := crossScalaVersions.value.head,
    scalacOptions := Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-encoding",
      "utf-8", // Specify character encoding used by source files.
      "-feature",
      "-unchecked",
      "-Xfatal-warnings", // Fail on warnings, not just errors
      "-source:future",   // Choices: future and future-migration. I use this to force future deprecation warnings, etc.
      "-new-syntax",      // Require `then` and `do` in control expressions.
      // "-language:strictEquality",          // Require +derives Eql+ for using == or != comparisons
      // "-rewrite",                          // Attempt to fix code automatically. Use with -indent and ...-migration.
      // "-scalajs",                          // Compile in Scala.js mode (requires scalajs-library.jar on the classpath).
    ),
    organization := "in.nvilla",
    scalaJSLinkerConfig ~= { _.withSourceMap(true) },
    licenses := Seq(("MIT", url("http://opensource.org/licenses/MPL-2.0"))),
    homepage := Some(url("https://github.com/bbarker/scala-callgraph-zio")),
    developers := List(
      Developer(
        "bbarker",
        "Brandon Barker",
        "brandon.barker@gmail.com",
        url("https://github.com/bbarker/"),
      ),
    ),
  ),
)

val scalajsdom = "1.1.0"

publish / skip := true

lazy val zioV = "2.0.0-M2"
lazy val `callgraphJS`  = `callgraph`.js
lazy val `callgraphJVM` = `callgraph`.jvm
lazy val `callgraph` = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .jvmSettings(publish / skip := true)
  .jsSettings(testSettings)
  .settings(libraryDependencies ++= List(
    "dev.zio"                    %%% "zio" % zioV,
    "dev.zio"                    %%% "zio-test"            % zioV % Test,
    "dev.zio"                    %%% "zio-test-sbt"        % zioV % Test,
    ))

lazy val `examples` = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(`callgraphJS`)
  .settings(
    testSettings,
    publish / skip := true,
    Test / test := {},
  )

lazy val testSettings = Seq(
  // Test / jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(), // Note: enable jsdom dep build.properties for this
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)
