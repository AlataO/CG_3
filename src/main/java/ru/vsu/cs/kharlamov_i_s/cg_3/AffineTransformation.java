package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector3f;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;
import javax.vecmath.*;
import java.util.ArrayList;
import java.util.List;

public class AffineTransformation {

    public static Model RotationTransformation (Model model, double angle, char axis) {
        float cos = (float) Math.cos (angle);
        float sin = (float) Math.sin (angle);
        Matrix4f matrix4f = rotateScaleTranslate();

        List<Vector3f> temp = new ArrayList<>();
        System.out.println(model.getVertices().size());
        switch (axis) {
            case ('x'):
                matrix4f.m11=cos; matrix4f.m12=-sin; matrix4f.m21=sin; matrix4f.m22=cos;
                for (int i = 0; i < model.getVertices().size(); i++) {
                    temp.add(multiplyMatrix4ByVector3(matrix4f, model.getVertices().get(i)));
                }
                break;
            case ('y'):
                matrix4f.m00=cos; matrix4f.m20=-sin; matrix4f.m02=sin; matrix4f.m22=cos;
                for (int i = 0; i < model.getVertices().size(); i++) {
                    temp.add(multiplyMatrix4ByVector3(matrix4f, model.getVertices().get(i)));
                }
                break;
            case ('z'):
                matrix4f.m00=cos; matrix4f.m01=-sin; matrix4f.m10=sin; matrix4f.m11=cos;
                for (int i = 0; i < model.getVertices().size(); i++) {
                    temp.add(multiplyMatrix4ByVector3(matrix4f, model.getVertices().get(i)));
                }
                break;
        }
        model.setVertices(temp);
        return model;
    }

    private static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix, final Vector3f vertex) {
        final float x = (vertex.getX() * matrix.m00) + (vertex.getY() * matrix.m10) + (vertex.getZ() * matrix.m20) + matrix.m30;
        final float y = (vertex.getX() * matrix.m01) + (vertex.getY() * matrix.m11) + (vertex.getZ() * matrix.m21) + matrix.m31;
        final float z = (vertex.getX() * matrix.m02) + (vertex.getY() * matrix.m12) + (vertex.getZ() * matrix.m22) + matrix.m32;
        final float w = (vertex.getX() * matrix.m03) + (vertex.getY() * matrix.m13) + (vertex.getZ() * matrix.m23) + matrix.m33;
        return new Vector3f(x / w, y / w, z / w);
    }


    private static Matrix4f rotateScaleTranslate() {
        float[] matrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1};
        return new Matrix4f(matrix);
    }
}
