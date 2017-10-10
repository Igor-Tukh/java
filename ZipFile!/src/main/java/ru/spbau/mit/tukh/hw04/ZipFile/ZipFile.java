package ru.spbau.mit.tukh.hw04.ZipFile;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * ZipFiles class which search for all .zip files in directory and extract their content files, which matches given
 * regular expression.
 */
public class ZipFile {
    /**
     * method main. It search for all .zip files in given directory and extract (decompress) all of their content files
     * which names matches given regular expression. Files will be decompressed to the directory in which zips are looking
     * for.
     *
     * @param args is array which consist of two elements: path to directory to look up zip files and regular expression.
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.err.println("Incorrect input data.\nHint: <Path> <Regexp>");
            System.exit(1);
        }

        extractFiles(args[1], args[0], lookUpZips(args[0]));
    }

    private static ArrayList<String> lookUpZips(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            System.err.println("Given path isn't directory");
            System.exit(1);
        }

        Queue<File> cur = new PriorityQueue<>();
        cur.add(dir);

        ArrayList<String> zips = new ArrayList<>();
        while (!cur.isEmpty()) {
            File curFile = cur.remove();
            if (curFile.isDirectory()) {
                File[] files = curFile.listFiles();
                if (files == null)
                    continue;
                for (File newFile : files) {
                    if (newFile.getName().endsWith(".zip")) {
                        zips.add(newFile.getAbsolutePath());
                    }
                    cur.add(newFile);
                }
            }
        }

        return zips;
    }

    private static void extractFiles(String regex, String path, ArrayList<String> zips) throws IOException {
        byte[] buf = new byte[1024];
        File outputDir = new File(path);
        for (String curName : zips) {
            File curFile = new File(curName);
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(curFile))) {
                ZipEntry cur = zis.getNextEntry();

                for (; cur != null; cur = zis.getNextEntry()) {
                    if (cur.getName().matches(regex)) {
                        File newFile = new File(outputDir, cur.getName());
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        while ((len = zis.read(buf)) > 0) {
                            fos.write(buf, 0, len);
                        }
                        fos.close();
                    }
                }
                zis.close();
            }
        }
    }
}
