package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.ObjReader.ObjReader;
import ru.vsu.cs.kharlamov_i_s.cg_3.ObjWriter.ObjWriter;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;


public class Main {
    public static class CmdParams {
        private Model input;
        private String output;
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
                parameters.input = ObjReader.read(Path.of(args[1]), true);
            }
            if (args[2].equals("-output")) {
                parameters.output = args[3];
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
            out.println("  <cmd> <input-file> args (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> -input <path> // input model");
            out.println("  <cmd> -output <path> // output model");
            System.exit(params.error ? 1 : 0);
        }

        Scanner in = new Scanner(System.in);
        ObjWriter.writeToFile(params.input, new File(params.output));

            params.input = ObjReader.read(Path.of(params.output), false);
            System.out.println("Rotation Input: <angle> <axis>");
            ObjWriter.writeToFile(AffineTransformation.rotationTransformation(params.input, in.nextDouble(), in.nextLine()), new File(params.output));

            params.input = ObjReader.read(Path.of(params.output), false);
            System.out.println("Scale Input: <factorX> <factorY> <factorZ>");
            ObjWriter.writeToFile(AffineTransformation.scaleTransformation(params.input, in.nextFloat(), in.nextFloat(), in.nextFloat()), new File(params.output));

            params.input = ObjReader.read(Path.of(params.output), false);
            System.out.println("Translation Input: <factorX> <factorY> <factorZ>");
            ObjWriter.writeToFile(AffineTransformation.translateTransformation(params.input, in.nextFloat(), in.nextFloat(), in.nextFloat()), new File(params.output));

        System.out.println("Transformation completed");
        System.out.close();
    }
}