# JHotDraw

[![Java CI with Maven](https://github.com/wumpz/jhotdraw/actions/workflows/maven.yml/badge.svg)](https://github.com/wumpz/jhotdraw/actions/workflows/maven.yml)

JHotDraw is a modular Java framework for structured 2D drawing editors and diagram-based desktop applications.
This repository is a Maven multi-module codebase containing the core drawing model, UI/application layers,
I/O support, and runnable sample applications.

> Note: The 10.x line introduced major refactorings and API changes compared to older JHotDraw versions.

## Requirements

- Java: JDK 17 (configured in the build as source/target 17)
- Maven: Apache Maven 3.9+ recommended
- OS: Desktop environment required to run Swing samples

Quick checks:

```bash
java -version
mvn -version
```

## Build

All commands below assume you start from the parent workspace directory:

```bash
cd jhotdraw
mvn clean install
```

If you are already in the `jhotdraw` directory, run `mvn clean install` directly.

What this does:

- compiles all modules
- runs tests
- runs formatting and style checks configured in the build
- installs `10.3-SNAPSHOT` artifacts into your local Maven repository

Useful alternatives:

```bash
# Run tests only
mvn test

# Build one module and its required dependencies
mvn -pl jhotdraw-core -am clean test
```

## Module Structure

The project is organized as the following Maven modules:

- `jhotdraw-api`: shared public APIs and interfaces
- `jhotdraw-utils`: utility classes (geometry helpers, common utilities)
- `jhotdraw-core`: core drawing model, figures, handles, tools, and editor foundations
- `jhotdraw-actions`: reusable action implementations for editors/apps
- `jhotdraw-gui`: Swing UI components and GUI utilities
- `jhotdraw-app`: application framework integration (SDI/OS-specific app wiring)
- `jhotdraw-io`: input/output formats and persistence-related integration
- `jhotdraw-xml`: XML support utilities
- `jhotdraw-datatransfer`: data transfer helpers
- `jhotdraw-samples`: sample aggregator module containing:
  - `jhotdraw-samples-mini`: compact focused demos
  - `jhotdraw-samples-misc`: larger end-to-end sample applications

## Run a Sample Application

After a successful `mvn clean install`, you can run a sample directly with Maven.

Example (mini editor sample):

```bash
cd jhotdraw
cd jhotdraw-samples/jhotdraw-samples-mini
mvn -DskipTests org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=org.jhotdraw.samples.mini.EditorSample"
```

To run another mini sample, stay in `jhotdraw-samples/jhotdraw-samples-mini` and replace
`org.jhotdraw.samples.mini.EditorSample` with another class containing a `main` method
(for example `org.jhotdraw.samples.mini.SelectionToolSample`).

If you run from a headless environment (no display), Swing samples will fail to start.

## Use as a Dependency

Artifacts are not published to Maven Central in this setup.
Build locally first (`mvn clean install`), then depend on modules such as:

```xml
<dependency>
  <groupId>org.jhotdraw</groupId>
  <artifactId>jhotdraw-core</artifactId>
  <version>10.3-SNAPSHOT</version>
</dependency>
```

## License

- LGPL V2.1
- Creative Commons Attribution 2.5 License

## History

This project is a fork of JHotDraw from http://sourceforge.net/projects/jhotdraw.
