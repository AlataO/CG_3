package ru.vsu.cs.kharlamov_i_s.cg_3.math.matrix;

import ru.vsu.cs.kharlamov_i_s.cg_3.math.vector.Vector;

public interface Matrix {
    Matrix sumMatrix(final Matrix m1, final Matrix m2);

    Matrix minusMatrix(final Matrix m1, final Matrix m2);

    Vector productMatrixOnVector(final Matrix m1, final Vector v1);

    Matrix productTwoMatrix(final Matrix m1, final Matrix m2);

    Matrix transpose(final Matrix m);

    int getSize();

    float[][] getValues();

    void setValue(float[][] value);

    void setZeroMatrix();

    void setSingleMatrix();


}
