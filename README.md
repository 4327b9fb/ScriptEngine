[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.4327b9fb.scriptengine/rhino-script-engine)](https://central.sonatype.com/artifact/io.github.4327b9fb.scriptengine/rhino-script-engine)

# Rhino script engine

This project aims to package a minimal Rhino script engine for Android.

The script engine source code is imported from openjdk implementation.

## Installation

Add the following repository and dependency to your `build.gradle`:

```
dependencies {
    implementation 'io.github.4327b9fb.scriptengine:rhino-script-engine:1.0.0'
}
```

## Usage

You can now call the Rhino script engine directly, i.e.:

```
RhinoScriptEngine rhino = new RhinoScriptEngine();
```