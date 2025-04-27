package algoritmo2;

public class Algoritmo2 {

    public static void main(String[] args) {
        
        // Definindo a primeira matriz A
        int[][] A = {
                { 1, 2 },
                { 2, 3 }
        };

        // Definindo a segunda matriz B
        int[][] B = {
                { 2, 4 },
                { 7, 8 }
        };

        // Multiplicando as duas matrizes usando o método multiply
        int[][] result = multiply(A, B);

        // Imprimindo o resultado da multiplicação de matrizes
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Método principal que aplica o algoritmo de multiplicação de matrizes.
     * Implementa uma abordagem parecida com o algoritmo de Strassen para dividir e conquistar.
     * @param A Matriz A
     * @param B Matriz B
     * @return  Matriz resultante da multiplicação de A por B
     */
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        // Caso base: se as matrizes forem 1x1, faz a multiplicação diretamente
        if (n == 1) {
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        // Divide as matrizes em 4 submatrizes menores
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        // Preenche as submatrizes com os valores das matrizes originais
        split(A, A11, A12, A21, A22);
        split(B, B11, B12, B21, B22);

        // Realiza as 7 multiplicações intermediárias (combinação de somas e subtrações)
        int[][] M1 = multiply(add(A11, A22), add(B11, B22));
        int[][] M2 = multiply(add(A21, A22), B11);
        int[][] M3 = multiply(A11, subtract(B12, B22));
        int[][] M4 = multiply(A22, subtract(B21, B11));
        int[][] M5 = multiply(add(A11, A12), B22);
        int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

        // Calcula as submatrizes da matriz resultado
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        // Junta as submatrizes para formar a matriz resultado final
        int[][] result = new int[n][n];
        join(C11, C12, C21, C22, result);

        return result;
    }

    /**
     * Método que divide uma matriz em quatro submatrizes iguais.
     * @param parent Matriz original
     * @param A11 Parte superior esquerda
     * @param A12 Parte superior direita
     * @param A21 Parte inferior esquerda
     * @param A22 Parte inferior direita
     */
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

    /**
     * Método que junta quatro submatrizes em uma matriz maior.
     * @param C11 Parte superior esquerda
     * @param C12 Parte superior direita
     * @param C21 Parte inferior esquerda
     * @param C22 Parte inferior direita
     * @param result Matriz destino que irá armazenar o resultado da junção
     */
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

    /**
     * Método que realiza a soma de duas matrizes.
     * @param A Primeira matriz
     * @param B Segunda matriz
     * @return  Matriz resultante da soma
     */
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

    /**
     * Método que realiza a subtração de duas matrizes.
     * @param A Primeira matriz
     * @param B Segunda matriz
     * @return  Matriz resultante da subtração
     */
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
