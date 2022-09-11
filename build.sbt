lazy val root = (project in file(".")).
  settings(
    name := "command-line-paint",
    version := "1.0",
    scalaVersion := "2.13.8",
    Compile / mainClass := Some("co.uk.redpixel.paint.PaintingApplication")
  )

lazy val enumeratumVersion = "1.7.0"
lazy val scalaTestVersion = "3.2.12"

libraryDependencies ++= Seq(
  "com.beachape" %% "enumeratum" % enumeratumVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
  "-feature",
  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:privates"
)
