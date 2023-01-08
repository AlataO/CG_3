package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.ObjReader.ObjReader;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;

import java.io.FileNotFoundException;
import java.io.PrintStream;


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

    public static CmdParams parseCmdArgs(String[] args) {
        CmdParams parameters = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                parameters.help = true;
                return parameters;
            }
            if (args[0].equals("-input")) {
                parameters.input = ObjReader.read(String.valueOf(args[1]), false);
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

    public static void main(String[] args) throws FileNotFoundException {
        CmdParams params = parseCmdArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        PrintStream out = (params.output != null) ? new PrintStream(params.output) : System.out;
        out.println("Transformation completed");
        out.close();
    }
}