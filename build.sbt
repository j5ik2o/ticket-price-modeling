name := "ticket-price-modeling"

version := "0.1"

scalaVersion := "2.13.0"

val enumeratumVersion = "1.5.13"
libraryDependencies ++= Seq(
  "com.beachape" %% "enumeratum" % enumeratumVersion
)