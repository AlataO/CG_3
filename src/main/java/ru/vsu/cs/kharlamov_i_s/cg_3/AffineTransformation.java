package ru.vsu.cs.kharlamov_i_s.cg_3;

import ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f;
import ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector3f;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Model;
import ru.vsu.cs.kharlamov_i_s.cg_3.model.Polygon;

import javax.vecmath.*;
import java.util.ArrayList;
import java.util.List;

public class AffineTransformation {

    public static Model rotationTransformation (Model model, double angle, String axis) {
        float cos = (float) Math.cos (Math.toRadians(angle));
        float sin = (float) Math.sin (Math.toRadians(angle));
        Matrix4f matrix4f = rotateScaleTranslateMatrix4();
        Matrix3f matrix3f;
        switch (axis) {
            case (" x"):
                matrix4f.m11=cos; matrix4f.m12=-sin; matrix4f.m21=sin; matrix4f.m22=cos;
                matrix3f= rotateScaleTranslateMatrix3FromMatrix4(matrix4f);
                return modelMatrixMultiplication(model, matrix4f, matrix3f);
            case (" y"):
                matrix4f.m00=cos; matrix4f.m20=-sin; matrix4f.m02=sin; matrix4f.m22=cos;
                matrix3f= rotateScaleTranslateMatrix3FromMatrix4(matrix4f);
                return modelMatrixMultiplication(model, matrix4f, matrix3f);
            case (" z"):
                matrix4f.m00=cos; matrix4f.m01=-sin; matrix4f.m10=sin; matrix4f.m11=cos;
                matrix3f= rotateScaleTranslateMatrix3FromMatrix4(matrix4f);
                return modelMatrixMultiplication(model, matrix4f, matrix3f);
        }
        return model;
    }

    public static Model scaleTransformation (Model model, float factorX, float factorY, float factorZ){
        Matrix4f matrix4f = rotateScaleTranslateMatrix4();
        matrix4f.m00=factorX;
        matrix4f.m11=factorY;
        matrix4f.m22=factorZ;
        Matrix3f matrix3f = rotateScaleTranslateMatrix3FromMatrix4(matrix4f);

        return modelMatrixMultiplication(model, matrix4f, matrix3f);
    }

    public static Model translateTransformation (Model model, float factorX, float factorY, float factorZ){
        Matrix4f matrix4f = rotateScaleTranslateMatrix4();
        Matrix3f matrix3f = rotateScaleTranslateMatrix3();
        matrix4f.m03=factorX; matrix4f.m13=factorY; matrix4f.m23=factorZ;
        matrix3f.m02=factorX; matrix3f.m12=factorY;
        return modelMatrixMultiplication(model, matrix4f, matrix3f);
    }

    protected static Model modelMatrixMultiplication (Model model, Matrix4f matrix4f, Matrix3f matrix3f){
        List<Vector3f> tempV = new ArrayList<>();
        List<Vector3f> tempN = new ArrayList<>();
        List<ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f> tempVt = new ArrayList<>();
        productOfMatrix4Multiplication(tempV, model.getVertices(), matrix4f);
        productOfMatrix4Multiplication(tempN, model.getNormals(), matrix4f);
        productOfMatrix3Multiplication(tempVt, model.getTextureVertices(), matrix3f);
        model.setVertices(tempV);
        model.setNormals(tempN);
        model.setTextureVertices(tempVt);
        return model;
    }


    protected static void productOfMatrix4Multiplication(List<Vector3f> temp, List<Vector3f> model, Matrix4f matrix4f){
        for (int i = 0; i < model.size(); i++) {
            temp.add(multiplyMatrix4ByVector3(matrix4f, model.get(i)));
        }
    }

    protected static void productOfMatrix3Multiplication(List<ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f> temp, List<ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f> model, Matrix3f matrix3f){
        for (int i = 0; i < model.size(); i++) {
            temp.add(multiplyMatrix3ByVector2(matrix3f, model.get(i)));
        }
    }

    protected static Vector3f multiplyMatrix4ByVector3 (final Matrix4f matrix, final Vector3f vector) {
        final float x = (vector.getX() * matrix.m00) + (vector.getY() * matrix.m01) + (vector.getZ() * matrix.m02) + matrix.m03;
        final float y = (vector.getX() * matrix.m10) + (vector.getY() * matrix.m11) + (vector.getZ() * matrix.m12) + matrix.m13;
        final float z = (vector.getX() * matrix.m20) + (vector.getY() * matrix.m21) + (vector.getZ() * matrix.m22) + matrix.m23;
        final float w = (vector.getX() * matrix.m30) + (vector.getY() * matrix.m31) + (vector.getZ() * matrix.m32) + matrix.m33;
        return new Vector3f(x, y, z);
    }

    protected static ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f multiplyMatrix3ByVector2 (final Matrix3f matrix, final ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f vector) {
        final float x = (vector.getX() * matrix.m00) + (vector.getY() * matrix.m01) + matrix.m02;
        final float y = (vector.getX() * matrix.m10) + (vector.getY() * matrix.m11) + matrix.m12;
        final float w = (vector.getX() * matrix.m20) + (vector.getY() * matrix.m21) + matrix.m22;
        return new Vector2f(x, y);
    }


    private static Matrix4f rotateScaleTranslateMatrix4() {
        float[] matrix = new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1};
        return new Matrix4f(matrix);
    }

    protected static Matrix3f rotateScaleTranslateMatrix3FromMatrix4(Matrix4f m4) {
        float[] matrix = new float[]{
                m4.m00, m4.m01, m4.m02,
                m4.m10, m4.m11, m4.m12,
                m4.m20, m4.m21, m4.m22};
        return new Matrix3f (matrix);
    }

    static Matrix3f rotateScaleTranslateMatrix3() {
        float[] matrix = new float[]{
                1, 0, 0,
                0, 1, 0,
                0, 0, 1};
        return new Matrix3f (matrix);
    }
}
