public class Ordine {
    private int nTavolo;
    private String[] ordine;
    public Ordine(String[] ordine, int nTavolo) {
        this.nTavolo = nTavolo;
        this.ordine = ordine;
    }
    public int getnTavolo() {
        return nTavolo;
    }
    public String[] getOrdine() {
        return ordine;
    }
}

