package walk;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Walk {

    private final File inputFile;
    private final File outputFile = new File("outputFile");


    public Walk(File inputFile) {
        this.inputFile = inputFile;
    }

    public Walk(String inputFile) {
        this.inputFile = new File(inputFile);
    }

    /**
     * Создание массива файлов из входного файла
     * @param inputFile каждая строка файла - другой файл
     * @return listFile массив файлов
     */
    private List<File> readInputFile(File inputFile) {
        List<File> listFile = new ArrayList<>();
        InputStream is = new FileInputStream(inputFile);

        try {
        }
    }
}
