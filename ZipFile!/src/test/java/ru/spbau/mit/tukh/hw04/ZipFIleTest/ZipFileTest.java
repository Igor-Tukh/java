package ru.spbau.mit.tukh.hw04.ZipFIleTest;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.*;

public class ZipFileTest {
    private File createZip(String zipName) {
        return new File(zipName);
    }

    private void addFile(File[] files, File zip) throws IOException {
        byte[] buf = new byte[1024];
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);

            zos.putNextEntry(new ZipEntry(file.getName()));
            int len;
            while ((len = fis.read(buf)) > 0) {
                zos.write(buf, 0, len);
            }
            fis.close();
        }
        zos.close();
    }

    private File createFile(String FileName, int[] data) throws IOException {
        File file = new File(FileName);
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        for (int intData : data) {
            fos.write(intData);
        }
        fos.close();
        return file;
    }

    private void deleteFile(File file) throws IOException {
        Files.delete(file.toPath());
    }

    private boolean compare(File f1, File f2) throws IOException {
        return f1.getName().equals(f2.getName()) &&
                Arrays.equals(Files.readAllBytes(f1.toPath()), Files.readAllBytes(f2.toPath()));
    }

    private boolean checkSize(int len, int extra) {
        File dir = new File("tst/");
        try {
            return dir.listFiles().length == len + extra;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Test
    public void testMatches() throws Exception {
        File zip = createZip("tst/1.zip");
        zip.getParentFile().mkdirs();
        File f1 = createFile("tst/src/1.txt", new int[]{4, 3, 2, 1});
        File f2 = createFile("tst/src/2.txt", new int[]{1, 2, 3, 4});
        File f3 = createFile("tst/src/3.tx", new int[]{1, 2, 3, 4});
        addFile(new File[]{f1, f2, f3}, zip);
        ru.spbau.mit.tukh.hw04.ZipFile.ZipFile.main(new String[]{"tst/", ".*.tx.+"});
        assertTrue(compare(f1, new File("tst/1.txt")));
        assertTrue(compare(f2, new File("tst/2.txt")));
        assertTrue(checkSize(2, 2));
        deleteFile(f1);
        deleteFile(f2);
        deleteFile(f3);
        deleteFile(zip);
        deleteFile(new File("tst/1.txt"));
        deleteFile(new File("tst/2.txt"));
        deleteFile(new File("tst/src"));
        deleteFile(new File("tst"));
    }

    @Test
    public void testZips() throws Exception {
        File zip = createZip("tst/1.zip");
        zip.getParentFile().mkdirs();
        File zip1 = createZip("tst/hidden/1.zip");
        zip1.getParentFile().mkdirs();
        File f1 = createFile("tst/src/1.txt", new int[]{4, 3, 2, 1});
        File f2 = createFile("tst/src/2.txt", new int[]{1, 2, 3, 4});
        addFile(new File[]{f1}, zip);
        addFile(new File[]{f2}, zip1);
        ru.spbau.mit.tukh.hw04.ZipFile.ZipFile.main(new String[]{"tst/", "[1-2].txt"});
        assertTrue(compare(f1, new File("tst/1.txt")));
        assertTrue(compare(f2, new File("tst/2.txt")));
        assertTrue(checkSize(2, 3));
        deleteFile(f1);
        deleteFile(f2);
        deleteFile(zip);
        deleteFile(zip1);
        deleteFile(new File("tst/1.txt"));
        deleteFile(new File("tst/2.txt"));
        deleteFile(new File("tst/src"));
        deleteFile(new File("tst/hidden"));
        deleteFile(new File("tst"));
    }
}