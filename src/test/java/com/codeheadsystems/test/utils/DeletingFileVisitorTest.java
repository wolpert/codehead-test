package com.codeheadsystems.test.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The type Deleting file visitor test.
 */
class DeletingFileVisitorTest {

  private Path tempFile;
  private Path tempDir;

  private DeletingFileVisitor visitor;

  /**
   * Sets .
   *
   * @throws IOException the io exception
   */
  @BeforeEach
  void setup() throws IOException {
    tempFile = Files.createTempFile("DeletingFileVisitorTest-", "txt");
    tempDir = Files.createTempDirectory("DeletingFileVisitorTest-");
    visitor = new DeletingFileVisitor();
  }

  /**
   * Tear down.
   *
   * @throws IOException the io exception
   */
  @AfterEach
  void tearDown() throws IOException {
    if (Files.exists(tempFile)) {
      Files.delete(tempFile);
    }
    if (Files.exists(tempDir)) {
      Files.delete(tempDir);
    }
  }

  /**
   * Pre visit directory.
   *
   * @throws IOException the io exception
   */
  @Test
  void preVisitDirectory() throws IOException {
    assertThat(visitor.preVisitDirectory(tempDir, null))
        .isEqualTo(FileVisitResult.CONTINUE);
  }

  /**
   * Visit file.
   *
   * @throws IOException the io exception
   */
  @Test
  void visitFile() throws IOException {
    assertThat(Files.exists(tempFile)).isTrue();
    assertThat(visitor.visitFile(tempFile, null))
        .isEqualTo(FileVisitResult.CONTINUE);
    assertThat(Files.exists(tempFile)).isFalse();
  }

  /**
   * Visit file failed.
   *
   * @throws IOException the io exception
   */
  @Test
  void visitFileFailed() throws IOException {
    assertThat(visitor.visitFileFailed(tempDir, null))
        .isEqualTo(FileVisitResult.TERMINATE);
  }

  /**
   * Post visit directory.
   *
   * @throws IOException the io exception
   */
  @Test
  void postVisitDirectory() throws IOException {
    assertThat(Files.exists(tempDir)).isTrue();
    assertThat(visitor.postVisitDirectory(tempDir, null))
        .isEqualTo(FileVisitResult.CONTINUE);
    assertThat(Files.exists(tempDir)).isFalse();
  }
}