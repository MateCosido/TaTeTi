import java.util.*;

public class TaTeTi {

    static ArrayList<Integer> fichaJugador = new ArrayList<Integer>();
    static ArrayList<Integer> fichaCpu = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] tableroJuego = {{' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '},
                                 {'-', '+', '-', '+', '-'},
                                 {' ', '|', ' ', '|', ' '}};

        imprimirTablero(tableroJuego);

        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese el sitio donde quiera colocar su ficha (1-9):");
            int jugadorPos = scan.nextInt();
            while(fichaJugador.contains(jugadorPos) || fichaCpu.contains(jugadorPos)) {
                System.out.println("Ya existe una pieza en este sitio, intente en otro lugar.");
                jugadorPos = scan.nextInt();
            }

            ponerPieza(tableroJuego, jugadorPos, "jugador");

            String resultado = comprobarGanador();
             if(resultado.length() > 0) {
                 System.out.println(resultado);
                 break;
             }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(fichaJugador.contains(cpuPos) || fichaCpu.contains(cpuPos)) {
                cpuPos = scan.nextInt(9) + 1;
            }
            ponerPieza(tableroJuego, cpuPos, "cpu");

            imprimirTablero(tableroJuego);

            resultado = comprobarGanador();
            if(resultado.length() > 0){
                System.out.println(resultado);
                break;
            }
        }
    }
    public static void imprimirTablero(char[][] tableroJuego) {
        for (char[] row : tableroJuego) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void ponerPieza(char[][] tableroJuego, int pos, String jugador){

        char symbol = ' ';

        if(jugador.equals("jugador")){
            symbol = 'X';
            fichaJugador.add(pos);
        }else if(jugador.equals("cpu")){
            symbol ='O';
            fichaCpu.add(pos);
        }

        switch(pos){
            case 1:
                tableroJuego[0][0] = symbol;
                break;
            case 2:
                tableroJuego[0][2] = symbol;
                break;
            case 3:
                tableroJuego[0][4] = symbol;
                break;
            case 4:
                tableroJuego[2][0] = symbol;
                break;
            case 5:
                tableroJuego[2][2] = symbol;
                break;
            case 6:
                tableroJuego[2][4] = symbol;
                break;
            case 7:
                tableroJuego[4][0] = symbol;
                break;
            case 8:
                tableroJuego[4][2] = symbol;
                break;
            case 9:
                tableroJuego[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String comprobarGanador() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> ganar = new ArrayList<List>();
        ganar.add(topRow);
        ganar.add(midRow);
        ganar.add(botRow);
        ganar.add(leftCol);
        ganar.add(midCol);
        ganar.add(rightCol);
        ganar.add(cross1);
        ganar.add(cross2);

        for(List l : ganar){
            if(fichaJugador.containsAll(l)){
                return "GANASTE";
            } else if(fichaCpu.containsAll(l)){
                return "Fue derrotado por el CPU";
            } else if(fichaJugador.size() + fichaCpu.size() == 9){
                return "Empate";
            }
        }
        return "";
    }
}
