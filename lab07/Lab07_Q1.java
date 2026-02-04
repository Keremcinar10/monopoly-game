package lab07;

public class Lab07_Q1 {
    public static void main(String[] args) {
        int[][] matrixA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        int[][] matrixB = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1} };
        int[][] matrixC = {{1, 2, 3, 4}, {5, 1, 2, 3}, {6, 5, 1, 2} };

        System.out.println("Initially matrix A is: ");
        printMatrix(matrixA);
        System.out.println("Initially matrix B is: ");
        printMatrix(matrixB);
        System.out.println("Initially matrix C is: ");
        printMatrix(matrixC);
        int[][] sum = addMatrices(matrixA, matrixB);
        System.out.println("Sum of matrices:");
        printMatrix(sum);
        System.out.println("Is matrixA an identity matrix? " +
        isIdentityMatrix(matrixA));
        int[][] rotatedMatrix = rotateMatrix90Degrees(matrixA);
        System.out.println("MatrixA rotated 90 degrees clockwise:");
        printMatrix(rotatedMatrix);
        System.out.println("Is matrixA a Toeplitz matrix? " +
        isToeplitzMatrix(matrixA));
        System.out.println("Is matrixC a Toeplitz matrix? " +
        isToeplitzMatrix(matrixC));
    }

    public static void printMatrix(int[][] matrix)
    {
        for(int i=0 ; i<matrix.length ; i++)
        {
            for(int j=0 ; j<matrix[0].length ; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean isIdentityMatrix(int [][] matrix)
    {
        if(matrix[0].length!= matrix.length && matrix.length<1) return false;

        for(int i=0 ; i<matrix.length ; i++)
        {
            for(int j=0 ; j<matrix[0].length ; j++)
            {
                if(i!=j && matrix[i][j]!=0)
                {
                    return false;
                }
                else if(i==j && matrix[i][j]!=1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] rotateMatrix90Degrees(int [][] matrix)
    {
        if(matrix[0].length!= matrix.length && matrix.length<1) return null;

        int [][] arr= new int[matrix[0].length][matrix.length];

        for(int i=0 ; i<matrix[0].length ; i++)
        {
            for(int j=0; j< matrix.length;j++)
            {
                arr[j][matrix.length-1-i]= matrix[i][j];
            }
        }
        return arr;
    }

    public static int[][] addMatrices(int[][] matrixOne, int[][] matrixTwo)
    {
        if(matrixOne.length!= matrixTwo.length || matrixOne[0].length != matrixTwo[0].length) return null;      

        int[][] arr = new int[matrixOne[0].length][matrixOne.length];

        for (int i=0; i<matrixOne.length;i++)
        {
            for( int j=0;j<matrixTwo[0].length;j++)
            {
                arr[i][j] = matrixOne[i][j] + matrixTwo[i][j];
            }
        }
        return arr;
    }
    public static boolean isToeplitzMatrix(int[][] matrix)
    {
        for(int i=1; i<matrix.length;i++)
        {
            for(int j=1;j<matrix[0].length;j++)
            {
                if(matrix[i][j]!=matrix[i-1][j-1]) return false;
            }
        }
        return true;
    }

}
