mainClass := Some("GUI.PlayCheckers")

(sourceDirectory in Compile) := baseDirectory.value / "src"

(javaSource in Compile) <<= (sourceDirectory in Compile)

(resourceDirectory in Compile) := baseDirectory.value / "resources"

(sourceDirectory in Test) := baseDirectory.value / "test"

(javaSource in Test) <<= (sourceDirectory in Test)

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

javacOptions in Compile ++= Seq("-Xlint:deprecation", "-Xlint:unchecked")

crossPaths := false

autoScalaLibrary := false
