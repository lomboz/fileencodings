package org.lomboz;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateUTF8File {

    public void generateFile(Path targetPathForFile) throws IOException {
        StringBuffer allPossibleChars = new StringBuffer();
        for (int i=0; i<65536; i++){
            allPossibleChars.append((char) i);
        }

        Files.deleteIfExists(targetPathForFile);
        Path txt = Files.createFile(targetPathForFile);
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txt.toFile()), "UTF-8"));
        bufferedWriter.append(allPossibleChars);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\temp","utf-8-chars.txt");
        new GenerateUTF8File().generateFile(path);
    }
}
