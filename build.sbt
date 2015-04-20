mainClass := Some("GUI.PlayCheckers")

(sourceDirectory in Compile) := baseDirectory.value / "src"

(javaSource in Compile) <<= (sourceDirectory in Compile)

(resourceDirectory in Compile) := baseDirectory.value / "resources"

(sourceDirectory in Test) := baseDirectory.value / "test"

(javaSource in Test) <<= (sourceDirectory in Test)

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

// javacOptions in Compile ++= Seq("-Xlint:deprecation", "-Xlint:unchecked")

crossPaths := true

excludeFilter in unmanagedSources in Compile := new FileFilter{
	def accept(n:File) = {
		val abPath = n.getAbsolutePath().replace('\\', '/')
		(
			(abPath endsWith "TableCommand.java") ||
			(abPath endsWith "OrderedCommand.java") ||
			(abPath endsWith "UnorderedCommand.java") ||
			(abPath endsWith "DictionaryCommand.java") ||
			false
		)
	}
}

excludeFilter in unmanagedSources in Test := new FileFilter{
	def accept(n:File) = {
		val abPath = n.getAbsolutePath().replace('\\', '/')
		(
			(abPath contains "Parser") ||
			false
		)
	}
}
