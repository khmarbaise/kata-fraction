import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.*;
import com.github.sormuras.bach.ProjectInfo.Metadata.Checksum;

@ProjectInfo(
    compileModulesForJavaRelease = 16,
    tools = @Tools(skip = {"jdeps", "javadoc", "jlink"}),
    requires = {"org.junit.platform.console"},
    lookupExternal = {
        @External(module = "org.assertj.core", via = "org.assertj:assertj-core:3.19.0"),
        @External(module = "org.apiguardian.api", via = "org.apiguardian:apiguardian-api:1.1.1"),
        @External(module = "org.junit.jupiter", via = "org.junit.jupiter:junit-jupiter:5.7.1"),
        @External(module = "org.junit.platform.console", via = "org.junit.platform:junit-platform-console:1.7.1"),
    },
//    lookupExternal = @External(module = "org.assertj.core", via = "org.assertj:assertj-core:3.19.0"),
    lookupExternals = @Externals(name = Externals.Name.JUNIT, version = "5.7.1"),
    testTweaks = @Tweak(tool = "junit", option = "--fail-if-no-tests"),
    metadata = {
        @Metadata(module = "org.apiguardian.api", size = 6452, checksums = @Checksum("6d7c20e025e5ebbaca430f61be707579")),
    }
)
module bach.info {
  requires com.github.sormuras.bach;
}
