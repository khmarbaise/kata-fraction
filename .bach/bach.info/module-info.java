import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.*;

@ProjectInfo(
    compileModulesForJavaRelease = 16,
    tools = @Tools(skip = {"jdeps", "javadoc", "jlink"}),
    requires = {"org.junit.platform.console", "org.junit.jupiter", "org.assertj.core"},
    lookupExternal = {
      @External(module = "org.assertj.core", via = "org.assertj:assertj-core:3.19.0"),
    },
    lookupExternals = {
      @Externals(name = Externals.Name.JUNIT, version = "5.7.1"),
    },
    testTweaks = {
        @Tweak(tool = "junit(com.soebes.kata.fraction)", option = "--fail-if-no-tests"),
    }
)
module bach.info {
  requires com.github.sormuras.bach;
}
