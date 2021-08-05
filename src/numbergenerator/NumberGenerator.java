package numbergenerator;

import java.util.*;

public class NumberGenerator {

    public static void main(String[] args) {
        int histograma1[] = new int[60];
        int histograma2[] = new int[60];
        int histograma3[] = new int[60];

        nativeRandomGeneratorPrint();
        linearCongruentGeneratorPrint();
        linearCombinedCongruentGeneratorPrint();

        for (int i = 0; i < 50; i++) {
            Object numerosgeradosAux1[] = nativeRandomGenerator();
            int numerosgeradosAux3[] = linearCongruentGenerator();
            int numerosgeradosAux4[] = linearCombinedCongruentGenerator();

            for (int j = 0; j < numerosgeradosAux1.length; j++) {
                histograma1[(int) numerosgeradosAux1[j] - 1]++;
                histograma2[numerosgeradosAux3[j] - 1]++;
                histograma3[numerosgeradosAux4[j] - 1]++;
            }
        }
        
        System.out.println("Gerador de numeros aleatórios nativo (Histograma)");

        for (int i = 0; i < histograma1.length; i++) {
            System.out.print(i + 1 + ": ");

            for (int j = 0; j < histograma1[i]; j++) {
                System.out.print("*");
            }

            System.out.println("");
        }
        
        System.out.println("\nGerador congruente linear (Histograma)");
        
        for (int i = 0; i < histograma2.length; i++) {
            System.out.print(i + 1 + ": ");

            for (int j = 0; j < histograma2[i]; j++) {
                System.out.print("*");
            }

            System.out.println("");
        }
        
        System.out.println("\nGerador congruente linear combinado (Histograma)");
        
        for (int i = 0; i < histograma3.length; i++) {
            System.out.print(i + 1 + ": ");

            for (int j = 0; j < histograma3[i]; j++) {
                System.out.print("*");
            }

            System.out.println("");
        }
    }

    public static Object[] nativeRandomGenerator() {
        //HashSet funciona de forma similar a um vetor, porém o tamanho não é fixo.
        //Não necessariamente armazena os elementos de forma ordenada, pois sua posição dá-se
        //a partir de seu Hash code. Além disso, nessa estrutura não é permitido  a inserção de elementos repetidos.
        Set<Integer> numerosGerados = new HashSet<>();

        while (numerosGerados.size() != 6) {
            int number = (int) (Math.random() * 60 + 1);

            numerosGerados.add(number);
        }

        return numerosGerados.toArray();
    }

    public static void nativeRandomGeneratorPrint() {
        System.out.println(" ");
        System.out.println("Números gerados através da função Math.Random:");
        System.out.println(Arrays.toString(nativeRandomGenerator()));
    }

    public static int[] linearCongruentGenerator() {
        //X0 = 7, Valor inicial; Deve ser X0 >= 0.
        //a = 7, Multiplicador; Deve ser a >= 0.
        //c = 7, Incremento; Deve ser c >= 0.
        //m = 6, Módulo; Deve ser m > X0, m > a, m > c.
        //x = (aXn + c) módulo m, n Deve ser n >= 0 n >= n.

        int numerosGerados[] = new int[6];

        int x = 7;

        for (int i = 1; i <= 6; i++) {
            x = ((x * 7) + 7) % 60;

            numerosGerados[i - 1] = x;
        }

        return numerosGerados;
    }

    public static void linearCongruentGeneratorPrint() {
        System.out.println(" ");
        System.out.println("Números gerados através da implementação matemática\nde um gerador de números pseudo aleatórios congruente linear:");

        System.out.println(Arrays.toString(linearCongruentGenerator()));
    }

    public static int[] linearCombinedCongruentGenerator() {
        //Combina dois geradores congruentes lineares, consequentemente aumentando o período, 
        //o que faz com leve mais tempo para haver repetições

        int a1 = 40014;
        int m1 = 2147483563;
        int a2 = 40692;
        int m2 = 2147483399;

        int y1 = (int) Math.floor(Math.random() * m1) + 1;
        int y2 = (int) Math.floor(Math.random() * m2) + 1;

        float r = 0;

        int numerosGeados[] = new int[6];

        for (int i = 1; i <= 6; i++) {

            y1 = (a1 * y1) % m1;
            y2 = (a2 * y2) % m2;

            float aux = (y1 - y2) % m1;

            if (aux > 0) {
                r = (aux / m1);
            } else if (aux < 0) {
                r = (aux / m1) + 1;
            } else {
                r = (m1 - 1) / m1;
            }

            numerosGeados[i - 1] = (int) (r * 60) + 1;
        }

        return numerosGeados;
    }

    public static void linearCombinedCongruentGeneratorPrint() {
        System.out.println(" ");
        System.out.println("Números gerados através da implementação matemática\nde um gerador de números pseudo aleatórios congruente linear combinado:");

        System.out.println(Arrays.toString(linearCombinedCongruentGenerator()));
    }
}
