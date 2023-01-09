package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.ObjReader.ObjReader;
import ru.vsu.cs.kharlamov_i_s.cg_3.ObjWriter.ObjWriter;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static class CmdParams {
        private Model input;
        private String output;
        private boolean rotationChange;
        private boolean scaleChange;
        private boolean translationChange;
        private boolean window;
        private boolean help;
        public boolean error;
    }

    public static CmdParams parseCmdArgs(String[] args) throws IOException {
        CmdParams parameters = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                parameters.help = true;
                return parameters;
            }
            if (args[0].equals("-input")) {
                parameters.input = ObjReader.read(String.valueOf(args[1]), true);
            }
            if (args[2].equals("-r") || args[3].equals("-r") || args[4].equals("-r")) {
                parameters.rotationChange = true;
            }
            if (args[2].equals("-s") || args[3].equals("-s") || args[4].equals("-s")) {
                parameters.scaleChange = true;
            }
            if (args[2].equals("-t") || args[3].equals("-t") || args[4].equals("-t")) {
                parameters.translationChange = true;
            }
            if (args[2].equals("-output") || args[4].equals("-output") || args[5].equals("-output")) {
                parameters.output = args[5];
            }
            return parameters;
        } else {
            parameters.help = true;
            parameters.error = true;
            return parameters;
        }
    }

    public static void main(String[] args) throws IOException {
        CmdParams params = parseCmdArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> -input  // input model");
            out.println("  <cmd> -r  // rotate model");
            out.println("  <cmd> -s  // scale model");
            out.println("  <cmd> -t  // translate model");
            out.println("  <cmd> -output  // output model");
            //System.exit(params.error ? 1 : 0);
            Path fileName = Path.of("D:\\Projects\\Year 2\\1 Semester\\CG_3\\src\\main\\java\\ru\\vsu\\cs\\kharlamov_i_s\\cg_3\\ObjModels\\Trash\\Teapot.obj");
            String fileContent;
            fileContent = Files.readString(fileName);
            params.input = ObjReader.read(fileContent, true);
            ObjWriter.writeToFile(AffineTransformation.RotationTransformation(params.input,45, 'z'), new File("D:\\test.obj"));
        }

        PrintStream out = (params.output != null) ? new PrintStream(params.output) : System.out;
        out.println("Transformation completed");
        out.close();
    }
}