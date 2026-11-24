import java.util.Scanner;

public class InputUtils {

    // Fent la variable estàtica, assegurem que només es fa servir un Scanner
    // i que, a més, tothom fa servir el mateix.
    private static Scanner scan = new Scanner(System.in);

    // La lectura d'un String mai pot donar un error
    public static String readLine() {
        return scan.nextLine();
    }

    // Revisa si el llegit és un nombre enter
    // Retorna un missatge en cas que no ho sigui
    // O retorna el nombre si ho és
    public static int readInt() {
        // Inicialitzem la variable
        int resultat = 0;
        // Per defecte posem a false la variable de comprobació de validessa
        boolean esValid = false;
        do {
            // Si es un enter assignem el nombre i posem a true la variable de validessa
            if (scan.hasNextInt()) {
                resultat = scan.nextInt();
                esValid = true;
            } else {
                // En altre cas ignorarem el valor de l'entrada
                scan.next();
                // I mostrarem un missatge d'error
                System.out.println("El valor ha de ser numèric, torna a intentar-ho.\n");
            }
            // Tornarem a llegir
            scan.nextLine();
        } while (!esValid); // Si no es vàlid continuem
        return resultat;
    }

    // Revisa si el llegit és un nombre enter i està comprès dins d'un rang.
    // Retorna un missatge en cas que no ho sigui o no estigui dins el rang
    // O retorna el nombre si ho és
    public static int readInt(int valorMinim, int valorMaxim) {
        // Inicialitzem la variable
        int resultat = 0;
        // Per defecte posem a false la variable de comprobació de validesa
        boolean esValid = false;
        do {
            // Si es un enter assignem el nombre i posem a true la variable de validessa
            if (scan.hasNextInt()) {
                resultat = scan.nextInt();
                esValid = true;
                // Verifica que sigui un valor dins del rang
                if (resultat < valorMinim || resultat > valorMaxim) {
                    // En cas que no estigui dins del rang, retornarà el missatge d'error.
                    System.out.println("El valor introduït és fora de rang, torna a intentar-ho\n");
                    // I posarem a false la variable de validessa
                    esValid = false;
                }
            } else {
                // En cas que no sigui un nombre ignorarem el valor de l'entrada
                scan.next();
                // I mostrarem un missatge d'error
                System.out.println("El valor ha de ser numèric, torna a intentar-ho.\n");                
            }
            // Tornarem a llegir
            scan.nextLine();
        } while (!esValid); // Si no es vàlid continuem
        return resultat;
    }

    // Revisa si el llegit és un nombre real
    // Retorna un missatge en cas que no ho sigui
    // O retorna el nombre si ho és
    public static double readReal() {
        // Inicialitzem la variable
        double resultat = 0.0;
        // Per defecte posem a false la variable de comprobació de validesa
        boolean esValid = false;
        do {
            // Si es un nombre real assignem el nombre i posem a true la variable de validessa
            if (scan.hasNextDouble()) {
                resultat = scan.nextDouble();
                esValid = true;
            } else {
                // En altre cas ignorarem el valor de l'entrada
                scan.next();
                // I mostrarem un missatge d'error
                System.out.println("El valor ha de ser numèric, torna a intentar-ho.\n");
            }
            // Tornarem a llegir
            scan.nextLine();
        } while (!esValid); // Si no es vàlid continuem
        return resultat;
    }

    // Revisa si el llegit és un nombre real i està comprès dins d'un rang.
    // Retorna un missatge en cas que no ho sigui o no estigui dins el rang
    // O retorna el nombre si ho és
    public static double readReal(double valorMinim, double valorMaxim) {
        // Inicialitzem la variable
        double resultat = 0.0;
        // Per defecte posem a false la variable de comprobació de validesa
        boolean esValid = false;
        do {
            // Si es un nombre real assignem el nombre i posem a true la variable de validessa
            if (scan.hasNextDouble()) {
                resultat = scan.nextDouble();
                esValid = true;
                // Verifica que sigui un valor dins del rang
                if (resultat < valorMinim || resultat > valorMaxim) {
                    // En cas que no estigui dins el rang, retornarà el missatge d'error.
                    System.out.println("El valor introduït és fora de rang, torna a intentar-ho\n");
                    // I posarem a false la variable de validessa
                    esValid = false;
                }
            } else {
                // En cas que no sigui un nombre ignorarem el valor de l'entrada
                scan.next();
                // I mostrarem un missatge d'error
                System.out.println("El valor ha de ser numèric, torna a intentar-ho.\n");
            }
            // Tornarem a llegir
            scan.nextLine();
        } while (!esValid); // Si no es vàlid continuem
        return resultat;
    }

    // Cal definir un mètode per tancar l'Scanner
    public static void close() {
        scan.close();
    }
}
