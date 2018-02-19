lazy val root = (project in file(".")).
  settings(
    name := "command-line-paint",
    version := "0.1",
    scalaVersion := "2.12.4",
    mainClass in Compile := Some("com.springernature.io.paint.PaintingApplication")
  )

lazy val enumeratumVersion = "1.5.12"
lazy val scalaTestVersion = "3.0.5"

libraryDependencies ++= Seq(
  "com.beachape" %% "enumeratum" % enumeratumVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)
