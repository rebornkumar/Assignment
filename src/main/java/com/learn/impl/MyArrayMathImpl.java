package com.learn.impl;



import com.learn.ArrayMath;
import com.learn.MyMap;

public class MyArrayMathImpl implements ArrayMath {

    @Override
    public boolean isSameCollection(int[] array1, int[] array2) {
        MyMap<Integer,Integer> map = new MyHashTableImpl(0.75);
        int n = array1.length;
        int m = array2.length;
        if(n != m)return false;
        for(int i = 0;i < n;i++) {
            if(map.contains(array1[i]) == null) {
                map.insert(array1[i],1);
            }
            else {
                map.insert(array1[i],1+map.contains(array1[i]));
            }
        }
        for(int i = 0;i < m;i++) {
            if(map.contains(array2[i]) != null) {
                if(map.contains(array2[i]) == 1) {
                    map.delete(array2[i]);
                    n--;
                }
                else {
                    map.insert(array2[i], map.contains(array2[i]) - 1);
                    n--;
                }
            }
        }
        if(n == 0)return true;
        else
            return false;
    }
    @Override
    public int minDifferences(int[] array1, int[] array2) {
        MergeSort mergeSort = new MergeSort();
        int[] A = mergeSort.divideAndConquer(array1);
        int[] B = mergeSort.divideAndConquer(array2);
        long squareSum = 0;
        int n = A.length;
        for(int i = 0;i < n;i++) {
            long diff = A[i]-B[i];
            squareSum += diff*diff;
        }
        return (int)(squareSum);
    }
    @Override
    public int[] getPercentileRange(int[] arr, int lower, int upper) {
        MergeSort mergeSort = new MergeSort();
        int[] A = mergeSort.divideAndConquer(arr);
        int n = A.length;
        int lowerIndex = (lower*n)/100;
        int upperIndex = (upper*n)/100;
        int m = upperIndex - lowerIndex;
        if(m == 0)return null;
        int[] ans = new int[m];
        for(int i = lowerIndex;i < upperIndex;i++) {
            ans[i-lowerIndex] = A[i];
        }
        return ans;
    }
}


class MergeSort {
    int[] merge(int[] A,int[] B) {
        int n = A.length;
        int m = B.length;
        int i = 0,j = 0,k = 0;
        int [] ans = new int[m+n];
        while(i < n && j < m) {
            if(A[i] < B[j]) {
                ans[k++] = A[i++];
            }
            else {
                ans[k++] = B[j++];
            }
        }
        while(i < n) {
            ans[k++] = A[i++];
        }
        while(j < m) {
            ans[k++] = B[j++];
        }
        return ans;
    }

    int[] divideAndConquer(int[] arr) {
        int n = arr.length;
        if(n <= 1)return arr;

        int mid = n/2;
        int [] A = new int[mid];
        int [] B = new int[n-mid];
        for(int i = 0;i < mid;i++) {
            A[i] = arr[i];
        }
        for(int i = mid;i < n;i++) {
            B[i-mid] = arr[i];
        }
        //left
        A = divideAndConquer(A);
        //right
        B = divideAndConquer(B);
        //merge sorted array
        arr = merge(A,B);
        return arr;
    }
}
