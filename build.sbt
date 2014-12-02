name := "gem"

scalaVersion := "2.11.4"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.slick"  %% "slick"        % "2.1.0",
  "com.typesafe"         % "config"       % "1.2.1",
  "org.postgresql"       % "postgresql"   % "9.3-1101-jdbc41",
  "org.slf4j"            % "slf4j-nop"    % "1.6.4",
  "org.specs2"          %% "specs2"       % "2.4.12" % "test",
  "org.scalacheck"      %% "scalacheck"   % "1.12.0" % "test",
  "org.scalaz"          %% "scalaz-core"  % "7.1.0",
  "com.typesafe.play"   %% "play-json"    % "2.3.2",
  "com.chuusai"         %% "shapeless"    % "2.0.0"
)
