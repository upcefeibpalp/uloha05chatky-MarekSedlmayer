package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    static final int VELIKOST_KEMPU = 10;
    static final int MAX_VELIKOST_CHATKY = 4;
    // Definovani pole podle velikosti kempu (poctu chatek)
    static int[] chatky = new int[VELIKOST_KEMPU];

    static Scanner scanner = new Scanner(System.in);
    static String menu = """
                   MENU:
                   1 - vypsani vsech chatek
                   2 - vypsani konkretni chatky
                   3 - Pridani navstevniku
                   4 - Odebrani navstevniku
                   5 - Celkova obsazenost kempu
                   6 - Vypis prazdne chatky
                   0 - Konec programu
                   """;

    public static void main(String[] args) {
        Volba volba;
        do {
            System.out.println(menu);
            System.out.print("Zadej volbu: ");
            int input = scanner.nextInt();
            volba = Volba.fromValue(input);

            switch (volba) {
                case VYPIS_CHATEK ->
                    vypisChatek();
                case VYPIS_KONKRETNI_CHATKU ->
                    vypisKonkretniChatku();
                case PRIDANI_NAVSTEVNIKU ->
                    pridaniNavstevniku();
                case ODEBRANI_NAVSTEVNIKU ->
                    odebraniNavstevniku();
                case CELKOVA_OBSAZENOST ->
                    celkovaObsazenost();
                case VYPIS_PRAZDNE_CHATKY ->
                    vypisPrazdneChatky();
                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }
                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (volba != Volba.KONEC_PROGRAMU);
    }

    private static boolean pridaniNavstevniku() {
        int indexChatky = zadejCisloChatky();
        if (!printExistujeChatka(indexChatky)) {
            return false;
        }

        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        if (pocetNavstevniku <= 0) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return false;
        }
        if ((chatky[indexChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return false;
        }
        chatky[indexChatky] = pocetNavstevniku + chatky[indexChatky];
        return true;
    }

    private static boolean odebraniNavstevniku() {
        int indexChatky = zadejCisloChatky();
        if (!printExistujeChatka(indexChatky)) {
            return false;
        }

        chatky[indexChatky] = 0;
        return true;
    }

    private static boolean printExistujeChatka(int indexChatky) {
        if (!indexChatkyJeValidni(indexChatky)) {
            System.err.println("Tato chatka neexistuje");
            return false;
        }
        return true;
    }

    private static boolean vypisKonkretniChatku() {
        int indexChatky = zadejCisloChatky();
        if (!printExistujeChatka(indexChatky)) {
            return false;
        }
        printChatka(indexChatky);
        return true;
    }

    private static int zadejCisloChatky() {
        System.out.print("Zadej cislo chatky: ");
        int indexChatky = scanner.nextInt() - 1;
        return indexChatky;
    }

    private static boolean indexChatkyJeValidni(int indexChatky) {
        return (indexChatky > 0) && (indexChatky < chatky.length);
    }

    private static void vypisChatek() {
        for (int i = 0; i < chatky.length; i++) {
            printChatka(i);
        }
    }

    private static void printChatka(int i) {
        int cisloChatky = i + 1;
        int chatka = chatky[i];
        System.out.format("Chatka [%d] = %d\n",
                cisloChatky, chatka);
    }

    private static void celkovaObsazenost() {
        vypisChatek();
    }

    private static void vypisPrazdneChatky() {
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                printChatka(i);
            }
        }
    }
}
