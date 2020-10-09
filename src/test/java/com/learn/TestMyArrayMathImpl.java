package com.learn;


import com.learn.impl.MyArrayMathImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestMyArrayMathImpl {

    @Test
    void TestIsSameColection() {

	int[] array1 = { 10, 1, 7, 10 };
	int[] array2 = { 1, 10, 7, 10 };
	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertTrue(mymath.isSameCollection(array1, array2));

	int[] array3 = { 10, 1, 7, 9 };
	Assertions.assertFalse(mymath.isSameCollection(array2, array3));

    }

    @Test
    void TestMinDifferences() {
	int[] array1 = { 2, 5, 3, 9 };
	int[] array2 = { 15, 12, 1, 3 };
	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertEquals(86,mymath.minDifferences(array1,array2));
    }

    @Test
    void TestGetPercentileRange() {
	int[] array = { 20000, 160, -2, 4, 100, 6, 120, 8, 140, 1800 };
	int[] solution = { 4, 6, 8, 100 };
	ArrayMath mymath = new MyArrayMathImpl();
	Assertions.assertArrayEquals(solution,mymath.getPercentileRange(array,10,50));
    }

}
