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
