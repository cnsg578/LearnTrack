# JVM Basics

## JDK vs JRE vs JVM

- **JDK** (Java Development Kit): Complete toolkit for developers. Contains compiler (`javac`), debugger, and JRE. Used to **write and compile** Java code.

- **JRE** (Java Runtime Environment): Runtime package for running Java apps. Contains JVM + standard libraries. Users install JRE to **run** compiled `.class` files.

- **JVM** (Java Virtual Machine): Engine that **executes** bytecode. Manages memory, garbage collection, and security for each running Java program.

**Simple analogy**: JDK = Chef + Kitchen (cooking), JRE = Kitchen (eating), JVM = Stomach (digesting).

## What is bytecode?

Java source code (`.java` files) compiles to **bytecode** (`.class` files) using `javac`. Bytecode is platform-independent machine code that JVM understands.

Hello.java → javac → Hello.class (bytecode) → JVM → Runs on any OS


Bytecode is **not** native machine code - it's an intermediate format optimized for JVM execution.

## Write Once, Run Anywhere

Java programs compile to bytecode **once** on any machine. This same bytecode runs on **any operating system** (Windows, Mac, Linux) that has a compatible JVM - no recompilation needed.

**Example**: Compile `Hello.java` on Windows → same `Hello.class` runs on Mac/Linux instantly.

This happens because JVM translates bytecode to native machine code **at runtime** for each platform, hiding OS differences from developers.
