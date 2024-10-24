package cz.fei.upce.cv05.evidence.chatek;

/**
 *
 * @author st75721
 */
public enum Volba {
    KONEC_PROGRAMU(0),
    VYPIS_CHATEK(1),
    VYPIS_KONKRETNI_CHATKU(2),
    PRIDANI_NAVSTEVNIKU(3),
    ODEBRANI_NAVSTEVNIKU(4),
    CELKOVA_OBSAZENOST(5),
    VYPIS_PRAZDNE_CHATKY(6);

    private final int value;

    Volba(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Volba fromValue(int value) {
        for (Volba volba : Volba.values()) {
            if (volba.value == value) {
                return volba;
            }
        }
        return Volba.KONEC_PROGRAMU;
    }
}
