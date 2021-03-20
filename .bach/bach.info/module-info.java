import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.Tweak;
import com.github.sormuras.bach.ProjectInfo.Tools;

@ProjectInfo(
    compileModulesForJavaRelease = 16,
    modules = "src/main/java",
    testModules = "src/test/{java,java-module}",
    requires = "org.junit.platform.console",
    tools = @Tools(skip = {"jdeps", "javadoc", "jlink"}),
    testTweaks = {
        @Tweak(tool = "junit(com.soebes.kata.fraction)", option = "--fail-if-no-tests"),
    }
)
module bach.info {
  requires com.github.sormuras.bach;
}
