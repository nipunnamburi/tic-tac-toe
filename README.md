# Tic-Tac-Toe (JavaFX)

Simple beginner Java project using JavaFX. Designed for IntelliJ IDEA on macOS (Apple Silicon / M-series).

## Project structure
- `pom.xml` — Maven build file
- `src/main/java/com/example/tictactoe/Main.java` — JavaFX application

## How to open in IntelliJ
1. Unzip the project.
2. In IntelliJ IDEA choose **File → Open** and select the `pom.xml` in the project root.
3. Let IntelliJ import the Maven project.

## Running (macOS, Apple Silicon)
You might need to add a platform classifier for JavaFX when running via Maven. Example:

```
mvn clean javafx:run -Djavafx.platform=mac-aarch64
```

Or with explicit dependency classifier in `pom.xml` (replace `mac-aarch64` if needed).

## Notes
- Java 17+ is recommended.
- Source is intentionally small and commented for learning.
