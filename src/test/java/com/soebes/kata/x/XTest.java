package com.soebes.kata.x;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

class XTest {

  private static final BigDecimal GIGA_BYTES = BigDecimal.valueOf(2).pow(10);

  @Test
  void name() throws IOException {
    FileStore fileStore = Files.getFileStore(Path.of("/"));
    long usableSpace = fileStore.getUsableSpace();
    long totalSpace = fileStore.getTotalSpace();
    long unallocatedSpace = fileStore.getUnallocatedSpace();

    long avail = totalSpace - usableSpace;
    System.out.println("totalSpace = " + BigDecimal.valueOf(totalSpace).divide(GIGA_BYTES));
    System.out.println("usableSpace = " + BigDecimal.valueOf(usableSpace).divide(GIGA_BYTES));
    System.out.println("unallocatedSpace = " + BigDecimal.valueOf(unallocatedSpace).divide(GIGA_BYTES));
    System.out.println("avail = " + BigDecimal.valueOf(avail).divide(GIGA_BYTES));
  }
}
