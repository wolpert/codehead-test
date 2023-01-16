package com.codeheadsystems.test.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeletingFileVisitorTest {

  private Path tempFile;
  private Path tempDir;

  private DeletingFileVisitor visitor;

  @BeforeEach
  void setup() throws IOException {
    tempFile = Files.createTempFile("DeletingFileVisitorTest-", "txt");
    tempDir = Files.createTempDirectory("DeletingFileVisitorTest-");
    visitor = new DeletingFileVisitor();
  }

  @AfterEach
  void tearDown() throws IOException {
    if (Files.exists(tempFile)) {
      Files.delete(tempFile);
    }
    if (Files.exists(tempDir)) {
      Files.delete(tempDir);
    }
  }

  @Test
  void preVisitDirectory() throws IOException {
    assertThat(visitor.preVisitDirectory(tempDir, null))
        .isEqualTo(FileVisitResult.CONTINUE);
  }

  @Test
  void visitFile() throws IOException {
    assertThat(Files.exists(tempFile)).isTrue();
    assertThat(visitor.visitFile(tempFile, null))
        .isEqualTo(FileVisitResult.CONTINUE);
    assertThat(Files.exists(tempFile)).isFalse();
  }

  @Test
  void visitFileFailed() throws IOException {
    assertThat(visitor.visitFileFailed(tempDir, null))
        .isEqualTo(FileVisitResult.TERMINATE);
  }

  @Test
  void postVisitDirectory() throws IOException {
    assertThat(Files.exists(tempDir)).isTrue();
    assertThat(visitor.postVisitDirectory(tempDir, null))
        .isEqualTo(FileVisitResult.CONTINUE);
    assertThat(Files.exists(tempDir)).isFalse();
  }
}