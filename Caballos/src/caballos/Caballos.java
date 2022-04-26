package caballos;

import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Caballos {

    Grafico Carrera = new Grafico();
    Scanner Input = new Scanner(System.in);

    int Caballos[] = new int[5];
    String Nombres[] = new String[5];

    int ValorApuesta() {
        int V = 0;
        System.out.print("Ingrese el valor que desea Apostar:");
        V = Input.nextInt();
        return V;
    }

    int Apuestas() {
        int A = 0;
        System.out.print("Ingrese la posicion del caballo por el que quiere apostar: ");
        A = Input.nextInt();
        return A;
    }

    void ResultadoApuestas(int A, int G1, int G2, int G3, int V) {
        if (A == G1) {
            System.out.println("Felicidades!!!, a ganado la apuesta en el primer lugar..Su premio es de: " + V * 10);
        }
        if (A == G2) {
            System.out.println("Felicidades!!!, a ganado la apuesta en el segundo lugar ...Su premio es de: " + V * 5);
        }
        if (A == G3) {
            System.out.println("Felicidades!!!, a ganado la apuesta en el tercer lugar ...Su premio es de: " + V * 2);
        }
        if (A != G1 && A != G2 && A != G3) {
            System.out.println("Lo sentimos.... :( , No ha ganado la apuesta");
        }
    }

    String Anuncio(int n) {
        String Orden = new String();
        if (n == 1) {
            Orden = "primer";
        }
        if (n == 2) {
            Orden = "segundo";
        }
        if (n == 3) {
            Orden = "tercer";
        }
        if (n == 4) {
            Orden = "cuarto";
        }
        if (n == 5) {
            Orden = "quinto";
        }
        return Orden;
    }

    void AnuncioPremiacion(int n, String Nombre) {
        System.out.println("El " + Anuncio(n) + " puesto de la carrera es: " + Nombre);
    }

    void Premiacion(int A, int V) {
        int G1 = 0;
        int G2 = 0;
        int G3 = 0;

        int P1 = 0;
        int P2 = 0;
        int P3 = 0;

        for (int G = 0; G < 3; G++) {
            for (int x = 0; x < 5; x++) {
                if (Caballos[x] > G1) {
                    G1 = Caballos[x];
                    P1 = x;
                }
                if (Caballos[x] > G2 && Caballos[x] < G1) {
                    G2 = Caballos[x];
                    P2 = x;
                }
                if (Caballos[x] > G3 && Caballos[x] < G2) {
                    G3 = Caballos[x];
                    P3 = x;
                }
            }
        }
        AnuncioPremiacion(1, Nombres[P1]);
        AnuncioPremiacion(2, Nombres[P2]);
        AnuncioPremiacion(3, Nombres[P3]);
        ResultadoApuestas(A, P1 + 1, P2 + 1, P3 + 1, V);
    }

    void Registro(String Orden, int n) {
        System.out.print("Ingrese el nombre de su " + Orden + " caballo, que participara en la posicion " + n + ":");
        Nombres[n - 1] = Input.next();
    }

    int Ganador() {
        int G = 0;
        int P = 0;
        for (int x = 0; x < 5; x++) {
            if (Caballos[x] > G) {
                G = Caballos[x];
                P = x;
            }
        }
        if (Caballos[P] < 100) {
            return 100;
        }
        return P;
    }

    void Camino() throws InterruptedException {
        for (int x = 0; x < 5; x++) {
            Caballos[x] += Pasos();
            Carrera.Posicion(Caballos[x], x);
        }
        for (int c = 0; c < 107; c++) {
            System.out.print("*");
        }
        System.out.println(" ");
        TimeUnit.SECONDS.sleep(1);
    }

    int Pasos() {
        int Paso = (int) Math.floor(Math.random() * 5 + 1);
        return Paso;
    }

    void Resultado(int Ganador) {
        System.out.println("");
        System.out.println("El caballo numero " + Ganador + " es el ganador del torneo\n");
        for (int x = 0; x < 5; x++) {
            System.out.println("El caballo numero " + (x + 1) + " recorrio " + Caballos[x] + " pasos");
        }
        System.out.println(" ");
    }

    void Proceso() throws InterruptedException {
        System.out.println("Bienvenid@ a la mejor carrera de todos los tiempos, vamos a presenciar la competencia de 5 caballos\n");
        for (int n = 1; n <= 5; n++) {
            Registro(Anuncio(n), n);
        }
        System.out.println(" ");
        int A = Apuestas();
        int V = ValorApuesta();
        for (int x = 0; x < 5; x++) {
            Caballos[x] = 0;
        }
        while (Ganador() == 100) {
            Camino();
        }
        Carrera.Final();
        TimeUnit.SECONDS.sleep(2);
        Resultado(Ganador() + 1);
        Premiacion(A, V);
    }

}
