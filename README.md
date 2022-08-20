# Hospitality Menu Service
Project to manage a menu of hospitality services such as restaurants, bar, food-truck, etc.

![CI](https://github.com/rauligs/hospitality-menu-service/actions/workflows/ci.yml/badge.svg)

## Build project
To build and run the tests for all project modules, run `./gradlew clean check`

## Testing
- Service Unit and Integration Tests. `./gradlew :service:test`
  - At the moment, considering there are no source code but configuration you'll find most of the
  tests to be Integration Tests. Also metrics has no integration tests but only functional (to be
  added once business logic is added i.e. downstream response metrics, latency metrics etc.)
- Service Functional Tests. `./gradlew :service-functional:test`

### End-to-End testing
To be able to start the local environment, you required: 
* [Docker Engine](https://docs.docker.com/engine/install/)
* [Minikube](https://kubernetes.io/docs/tasks/tools/) 

## Checkstyle
Uses [Gradle Checkstyle Plugin](https://docs.gradle.org/current/userguide/checkstyle_plugin.html)
configured with [Google's Style](https://checkstyle.sourceforge.io/google_style.html)

### IntelliJ Setup
- Add the Google Code Style for IntelliJ by adding the
[intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) 
at _IntelliJIDEA > Preferences... > Editor > Code Style > Java > Scheme_

- Add the [google-java-format Plugin for IntelliJ](https://github.com/google/google-java-format). 
This also requires these [extra VM options](https://github.com/google/google-java-format/issues/787); 
Edit them in _Help > Edit Custom VM Options..._ and add the following configuration parameters:
```
--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED
--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED
--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED
--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED
```

## Resources
* Find tech stack documentation [Reference Documentation](./docs/reference.md)