name := "Scala-DynProg"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.4"

// libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.10.1"
//
// libraryDependencies += "com.typesafe.play" % "play-jdbc_2.11" % "2.4.0-M1"
// 
// libraryDependencies += "com.typesafe.play" % "anorm_2.11" % "2.4.0-M1"
// 
// libraryDependencies += "org.postgresql" % "postgresql" % "9.3-1102-jdbc41"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

// assemblyMergeStrategy in assembly := {
//   case PathList("javax", "xml", xs @ _*) => MergeStrategy.last
//   case PathList(ps @ _*) if ps.last.contains("play.modules") => MergeStrategy.last
//   case x =>
//     val oldStrategy = (assemblyMergeStrategy in assembly).value
//     oldStrategy(x)
// }

QuickFixKeys.vimExecutable := "vim"

packAutoSettings
