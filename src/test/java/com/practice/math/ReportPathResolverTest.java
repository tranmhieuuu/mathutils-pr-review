package com.practice.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Kiem thu duong dan cho matrix Windows, Linux va macOS. */
class ReportPathResolverTest {

    @Test
    void shouldBuildPortableReportPath() {
        Path baseDirectory = Path.of("workspace");
        Path expected = Path.of("workspace", "reports", "summary.txt");

        Path actual = ReportPathResolver.reportPath(baseDirectory);

        // Ban Windows-only se FAIL tren Linux/macOS tai assert nay.
        assertEquals(expected, actual);
    }

    @Test
    void shouldWriteReportInsideTemporaryDirectory(@TempDir Path tempDir)
            throws IOException {
        Path expected = tempDir.resolve("reports").resolve("summary.txt");
        Path actual = ReportPathResolver.reportPath(tempDir);

        // Kiem tra truoc khi ghi file de khong tao file ngoai thu muc tam.
        assertEquals(expected, actual);
        Files.createDirectories(actual.getParent());
        Files.writeString(actual, "Matrix OK", StandardCharsets.UTF_8);

        assertTrue(Files.exists(expected));
        assertEquals("Matrix OK", Files.readString(expected, StandardCharsets.UTF_8));
    }
}
