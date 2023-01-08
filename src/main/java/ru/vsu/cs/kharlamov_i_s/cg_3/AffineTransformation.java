package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;

public class AffineTransformation {
    public Model RotationTransformation (Model model, double angle, char axis) {
        double cos = Math.cos (angle);
        double sin = Math.sin (angle);
        switch (axis){
            case 'x': double[][] Matrix = {{cos,-sin,0,0}, {sin, cos,0,0}, {0,0,1}};

        }
        return model;
    }
}
