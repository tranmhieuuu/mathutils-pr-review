package com.practice.math;

import java.nio.file.Path;

/** Tao duong dan bao cao phu hop voi moi he dieu hanh. */
public final class ReportPathResolver {

    private ReportPathResolver() {
        // Utility class.
    }

    /** Tao duong dan reports/summary.txt ben trong thu muc goc. */
    public static Path reportPath(Path baseDirectory) {
        // Path.resolve se dung separator phu hop voi OS dang chay.
        return baseDirectory.resolve("reports").resolve("summary.txt");
    }
}
