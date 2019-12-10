package org.lomboz;

import junitx.framework.FileAssert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class GenerateUTF8FileTest {

    @Test
    public void generateFile() throws IOException {
        Path targetPath = Files.createTempFile("utf8file",".txt");
        Path expectedPath = Paths.get("./src/test/resources/all-utf-8-chars.txt");

        new GenerateUTF8File().generateFile(targetPath);

        FileAssert.assertBinaryEquals(expectedPath.toFile(), targetPath.toFile());

    }
}
