package algoritmo2;

public class Algoritmo2 {

    public static void main(String[] args) {
        int[][] A = {
                { 1, 2 },
                { 2, 3 }
        };

        int[][] B = {
                { 2, 4 },
                { 7, 8 }
        };

        int[][] result = multiply(A, B);

        // print result
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Função principal que aplica o algoritmo de multiply
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            // Caso base: multiplicação simples de 1x1
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        // Divida as matrizes em submatrizes
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        // Preencher as submatrizes A e B
        split(A, A11, A12, A21, A22);
        split(B, B11, B12, B21, B22);

        // Cálculo das 7 multiplicações
        int[][] M1 = multiply(add(A11, A22), add(B11, B22));
        int[][] M2 = multiply(add(A21, A22), B11);
        int[][] M3 = multiply(A11, subtract(B12, B22));
        int[][] M4 = multiply(A22, subtract(B21, B11));
        int[][] M5 = multiply(add(A11, A12), B22);
        int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

        // Combinando as submatrizes para formar a matriz resultado
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        // Montando a matriz resultado final
        int[][] result = new int[n][n];
        join(C11, C12, C21, C22, result);

        return result;
    }

    // Função para dividir uma matriz em 4 submatrizes
    public static void split(int[][] parent, int[][] A11, int[][] A12, int[][] A21, int[][] A22) {
        int n = parent.length / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A11[i][j] = parent[i][j];
                A12[i][j] = parent[i][j + n];
                A21[i][j] = parent[i + n][j];
                A22[i][j] = parent[i + n][j + n];
            }
        }
    }

    // Função para juntar 4 submatrizes em uma matriz
    public static void join(int[][] C11, int[][] C12, int[][] C21, int[][] C22, int[][] result) {
        int n = C11.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = C11[i][j];
                result[i][j + n] = C12[i][j];
                result[i + n][j] = C21[i][j];
                result[i + n][j + n] = C22[i][j];
            }
        }
    }

    // Função para somar duas matrizes
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // Função para subtrair duas matrizes
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }
}
