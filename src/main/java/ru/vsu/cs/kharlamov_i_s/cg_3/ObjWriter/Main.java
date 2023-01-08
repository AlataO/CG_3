package ru.vsu.cs.kharlamov_i_s.cg_3.ObjWriter;

import ru.vsu.cs.kharlamov_i_s.cg_3.ObjReader.ObjReader;
import ru.vsu.cs.kharlamov_i_s.cg_3.ObjWriter.ObjWriter;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Polygon;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;
import ru.vsu.cs.kharlamov_i_s.cg_3.ObjReader.ReaderExceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Main {

    public static void main(String[] args) {
        Model m1 = new Model();

        String fileSeparator = System.getProperty("file.separator");

        //чтение файла
        Path fileName = Path.of("D:\\Projects\\Year 2\\1 Semester\\CG_3\\src\\main\\java\\ru\\vsu\\cs\\kharlamov_i_s\\cg_3\\ObjReader\\Teapot.obj");

        Pattern pattern = Pattern.compile("[а-яё]");
        Matcher matcher = pattern.matcher(fileName.toString().toLowerCase());
        if (matcher.find()) {
            throw new ReaderExceptions.ObjReaderException("Can't read file with outsider symbols in its name", 1);
        }

        try {
            String fileContent = Files.readString(fileName);
            System.out.println("Loading model ...");
            m1 = ObjReader.read(fileContent, false);

            System.out.println("Vertices: " + m1.getVertices().size());
            System.out.println("Texture vertices: " + m1.getTextureVertices().size());
            System.out.println("Normals: " + m1.getNormals().size());
            System.out.println("Polygons: " + m1.getPolygons().size());
        } catch (IOException exception) {
            throw new ReaderExceptions.ObjReaderException("File not found", 1);
        }

        //запись файла
        String filePath = "G:" + fileSeparator + "text5.obj";
        try {
            System.out.println("Создаём файл");
            ObjWriter.createObjFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(filePath);
        try {
            System.out.println("Запись в файл");
            ObjWriter.writeToFile(m1, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Модель сохранена в файл");
    }
}
