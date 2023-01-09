package ru.vsu.cs.kharlamov_i_s.cg_3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector2f;
import ru.vsu.cs.kharlamov_i_s.cg_3.math.Vector3f;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;


import static org.junit.jupiter.api.Assertions.assertEquals;

class AffineTransformationTest {


    @Test
    @DisplayName(
            "Should return a vector with the same coordinates when the matrix is a scale matrix")
    void multiplyMatrix4ByVector3WhenMatrixIsScaleThenReturnVectorWithTheSameCoordinates() {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Vector3f vector = new Vector3f(1, 2, 3);
        Vector3f expected = new Vector3f(1, 2, 3);
        assertEquals(expected, AffineTransformation.multiplyMatrix4ByVector3(matrix, vector));
    }

    @Test
    @DisplayName(
            "Should return a vector with the same coordinates when the matrix is an identity matrix")
    void multiplyMatrix4ByVector3WhenMatrixIsIdentityThenReturnVectorWithTheSameCoordinates() {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Vector3f vector = new Vector3f(1, 2, 3);
        Vector3f expected = new Vector3f(1, 2, 3);
        Vector3f actual = AffineTransformation.multiplyMatrix4ByVector3(matrix, vector);
        assertEquals(expected, actual);
    }



    @Test
    @DisplayName(
            "Should return a vector with the same coordinates when the matrix is a translation matrix")
    void multiplyMatrix4ByVector3WhenMatrixIsTranslationThenReturnVectorWithTheSameCoordinates() {
        Matrix4f matrix =
                new Matrix4f(
                        new float[]{
                                1, 0, 0, 0,
                                0, 1, 0, 0,
                                0, 0, 1, 0,
                                0, 0, 0, 1
                        });
        Vector3f vector = new Vector3f(1, 2, 3);
        Vector3f expected = new Vector3f(1, 2, 3);

        Vector3f actual = AffineTransformation.multiplyMatrix4ByVector3(matrix, vector);

        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
        assertEquals(expected.getZ(), actual.getZ());
    }

    @Test
    @DisplayName(
            "Should return a new vector2f with the correct values when both matrix and vector are not null")
    void
    multiplyMatrix3ByVector2WhenBothMatrixAndVectorAreNotNullThenReturnNewVector2fWithCorrectValues() {
        Matrix3f matrix =
                new Matrix3f(
                        new float[]{
                                1, 0, 0,
                                0, 1, 0,
                                0, 0, 1
                        });
        Vector2f vector = new Vector2f(1, 2);
        Vector2f expected = new Vector2f(1, 2);

        Vector2f actual = AffineTransformation.multiplyMatrix3ByVector2(matrix, vector);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return a new vector2f with the correct values when the vector is not null")
    void multiplyMatrix3ByVector2WhenVectorIsNotNullThenReturnNewVector2fWithCorrectValues() {
        Matrix3f matrix =
                new Matrix3f(
                        new float[]{
                                1, 0, 1,
                                0, 0, 0,
                                1, 0, 1
                        });
        Vector2f vector = new Vector2f(1, 2);
        Vector2f expected = new Vector2f(2, 0);

        Vector2f actual = AffineTransformation.multiplyMatrix3ByVector2(matrix, vector);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return a new vector2f with the correct values when the matrix is not null")
    void multiplyMatrix3ByVector2WhenMatrixIsNotNullThenReturnNewVector2fWithCorrectValues() {
        Matrix3f matrix =
                new Matrix3f(
                        new float[]{
                                1, 1, 1,
                                1, 1, 1,
                                1, 1, 1
                        });
        Vector2f vector = new Vector2f(5, 2);
        Vector2f expected = new Vector2f(8, 8);

        Vector2f actual = AffineTransformation.multiplyMatrix3ByVector2(matrix, vector);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return a matrix with the same values as in the input matrix")
    void
    rotateScaleTranslateMatrix3FromMatrix4ShouldReturnAMatrixWithTheSameValuesAsInTheInputMatrix() {
        Matrix3f expected = new Matrix3f(new float[]{1, 2, 3, 5, 6, 7, 9, 10, 11});
        Matrix3f actual = AffineTransformation.rotateScaleTranslateMatrix3FromMatrix4(new Matrix4f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
        assertEquals(expected,actual);
    }

}