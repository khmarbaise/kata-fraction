import com.github.sormuras.bach.ProjectInfo;
@ProjectInfo(
    compileModulesForJavaRelease = 16,
    modules = "src/main/java",
    testModules = "src/test/{java,java-module}"
)
module bach.info {
  requires com.github.sormuras.bach;

}