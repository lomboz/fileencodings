package org.lomboz;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateUTF8File {

    public void generateFile(Path targetPathForFile, boolean cleanInvalidXMLChars) throws IOException {
        StringBuffer allPossibleChars = new StringBuffer();
        for (int i=0; i<65536; i++){
            allPossibleChars.append((char) i);
        }

        Files.deleteIfExists(targetPathForFile);
        Path txt = Files.createFile(targetPathForFile);
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txt.toFile()), "UTF-8"));

        if(cleanInvalidXMLChars){
            // In XML some characters are not allowed. https://stackoverflow.com/questions/730133/what-are-invalid-characters-in-xml
            String re = "[^\\x09\\x0A\\x0D\\x20-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFFF]";
            bufferedWriter.append(allPossibleChars.toString().replaceAll(re,""));
        }else{
            bufferedWriter.append(allPossibleChars);
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\temp","utf-8-chars-xml-valid.txt");
        new GenerateUTF8File().generateFile(path, true);
    }
}
