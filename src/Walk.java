import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Walk {

    private final File inputFile;
    private final File outputFile;


    public Walk(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Создание массива файлов из входного файла
     * @param inputFile каждая строка файла - другой файл
     * @return listFile массив файлов
     */
    private List<String> readInputFile(File inputFile) throws IOException {
        List<String> listFile = new ArrayList<>();
        Reader is = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);

        try {
            StringBuilder buffer = new StringBuilder();
            int bit;
            while ((bit = is.read()) >= 0) {
                if (bit == '\n') {
                    String line = buffer.toString();

                    if (line.endsWith("\r")) {
                        line = line.substring(0, line.length() - 1);
                    }

                    if (!line.isEmpty()) {
                        listFile.add(line);
                    }
                    buffer.setLength(0);
                } else {
                    buffer.append((char) bit);
                }
            }
            String line = buffer.toString();

            if (line.endsWith("\r")) {
                line = line.substring(0, line.length() - 1);
            }

            if (!line.isEmpty()) {
                listFile.add(line);
            }
        } finally {
            is.close();
        }

        return listFile;
    }


    /**
     * Переводит файлы в хэш инфу
     * @param file - файл который надо перевести
     * @return int - хэш этого файла
     */
    private static int hashFile(File file) {
        try {
            InputStream is = new FileInputStream(file);
            try {
                int hash = 0x811c9dc5;
                byte[] b = new byte[1024];
                int c;
                while ((c = is.read(b)) >= 0) {
                    for (int i = 0; i < c; i++) {
                        hash *= 0x01000193;
                        hash ^= (b[i] & 0xff);
                    }
                }
                return hash;
            } finally {
                is.close();
            }

        } catch (IOException e) {
            return 0;
        }
    }


    public void run() throws IOException {
        List<String> listFile = readInputFile(inputFile);

        File parent = outputFile.getParentFile();
        if (parent != null) {
            //noinspection ResultOfMethodCallIgnored
            parent.mkdirs();
        }

        Writer writer = new OutputStreamWriter(
                new FileOutputStream(outputFile), StandardCharsets.UTF_8);

        try {
            for (String f: listFile) {
                int hash = hashFile(new File(f));
                writer.write(String.format("%08x %s%n", hash, f));
            }
        } finally {
            writer.close();
        }
    }



    public static void main(String[] args) {
        if ( args == null || args.length < 2 || args[0] == null || args[1] == null) {
            System.err.println("Usage: java Walk <input> <output>");
            return;
        }
        try {
            new Walk(new File(args[0]), new File(args[1])).run();
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
