public class Tavoli implements Runnable {
    private int nTavolo;
    private int persone = 5;
    private String[] menu = {"Margherita", "Diavola", "Marinara", "Salamino"};
    private String[] pizze;
    BufferPizze buffer;
    BufferPizze LeggeDaCameriere;

    public Tavoli(BufferPizze a, BufferPizze b, int nTavolo) {
        buffer = a;
        LeggeDaCameriere = b;
        this.nTavolo = nTavolo;  // per identificare il tavolo nell'ordine
    }

    public void run() {
        while (true) {
            pizze = new String[persone];
            for (int i = 0; i < persone; i++) {
                pizze[i] = menu[(int) (Math.random() * 4)];
                // dico la pizza che voglio
            }
            Ordine ordine = new Ordine(pizze, nTavolo);
            buffer.pushOrder(ordine);
            System.out.println("Tavolo " + nTavolo + " ha inviato il suo ordine");

            synchronized (LeggeDaCameriere) {
                while (true) {
                    if (LeggeDaCameriere.isOrder()) {
                        Ordine ordineCameriere = LeggeDaCameriere.isMyOrdine();
                        // Se è l'ordine di questo tavolo, esci dal ciclo
                        if (ordineCameriere.getnTavolo() == nTavolo) {
                            System.out.println("Tavolo " + nTavolo + " ha ricevuto lw sua pizze");
                            break; // Esce dal ciclo
                        } else {
                            LeggeDaCameriere.notify(); // Risveglia un altro thread
                            try {
                                // Aspetta ancora finché non arriva il suo ordine
                                LeggeDaCameriere.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(5000);  // Simula il tempo di attesa per un nuovo ordine
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
