name := "bbb-web-common"

organization := "org.bigbluebutton"

version := "0.0.1-SNAPSHOT"

// We want to have our jar files in lib_managed dir.
// This way we'll have the right path when we import
// into eclipse.
retrieveManaged := true

testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "html", "console", "junitxml")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/scalatest-reports")

libraryDependencies ++= {
  Seq(
    "com.google.code.gson"      %  "gson"              % "2.5",
    "redis.clients" % "jedis" % "2.7.2",
    "org.apache.commons" % "commons-pool2" % "2.3",
    "commons-lang" % "commons-lang" % "2.5",
    "commons-io" % "commons-io" % "2.4",
    "commons-httpclient" % "commons-httpclient" % "3.1",
    "com.zaxxer" % "nuprocess" % "1.1.0",
    "com.artofsolving" % "jodconverter" % "2.2.1",
    "org.openoffice" % "unoil" % "3.2.1",
    "org.openoffice" % "ridl" % "3.2.1",
    "org.openoffice" % "juh" % "3.2.1",
    "org.openoffice" % "jurt" % "3.2.1",
    "org.bigbluebutton" % "bbb-common-message" % "0.0.19-SNAPSHOT",
    "org.slf4j" % "slf4j-api" % "1.7.5"

  )}

libraryDependencies += "junit" % "junit" % "4.12" % "test"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

seq(Revolver.settings: _*)

//-----------
// Packaging
//
// Reference:
// http://xerial.org/blog/2014/03/24/sbt/
// http://www.scala-sbt.org/sbt-pgp/usage.html
// http://www.scala-sbt.org/0.13/docs/Using-Sonatype.html
// http://central.sonatype.org/pages/requirements.html
// http://central.sonatype.org/pages/releasing-the-deployment.html
//-----------

// Build pure Java lib (i.e. without scala)
// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

/***************************
* When developing, change the version above to x.x.x-SNAPSHOT then use the file resolver to
* publish to the local maven repo using "sbt publish"
*/
// Uncomment this to publish to local maven repo while commenting out the nexus repo
publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))


// Comment this out when publishing to local maven repo using SNAPSHOT version.
// To push to sonatype "sbt publishSigned"
//publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value)
//     Some("snapshots" at nexus + "content/repositories/snapshots")
//   else
//     Some("releases"  at nexus + "service/local/staging/deploy/maven2")
//}

// Enables publishing to maven repo
publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:bigbluebutton/bigbluebutton.git</url>
    <connection>scm:git:git@github.com:bigbluebutton/bigbluebutton.git</connection>
  </scm>
  <developers>
    <developer>
      <id>ritzalam</id>
      <name>Richard Alam</name>
      <url>http://www.bigbluebutton.org</url>
    </developer>
  </developers>)
  
licenses := Seq("LGPL-3.0" -> url("http://opensource.org/licenses/LGPL-3.0"))

homepage := Some(url("http://www.bigbluebutton.org"))
  