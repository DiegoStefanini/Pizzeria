public class BufferPizze {
    private String[] menu = {"Margherita", "Diavola", "Marinara", "Salamino"};
    private Ordine[] order;
    private int i = 0;
    private int maxOrders = 20;

    public BufferPizze() {
        this.order = new Ordine[maxOrders];
    }

    public synchronized void pushOrder(Ordine ordine) {
        // Controlla che il buffer non sia pieno
        if (i < maxOrders) {
            this.order[i] = ordine;
            i++;
            notifyAll(); // Notifica che un nuovo ordine è stato inserito
            try{
                Thread.sleep(250);
            } catch (InterruptedException e){}
        }
    }

    public synchronized boolean isOrder() {
        return i > 0;  // Restituisce true se ci sono ordini nel buffer
    }

    public synchronized Ordine isMyOrdine() {
        if (i > 0) {
            return order[i - 1];  // Restituisce l'ultimo ordine nel buffer
        } else {
            return null;  // Nessun ordine disponibile
        }
    }

    public synchronized Ordine getOrder() {
        if (i > 0) {
            Ordine ordineDaRimuovere = order[i - 1];
            order[i - 1] = null;
            i--;
            return ordineDaRimuovere;
        } else {
            return null;  // Nessun ordine disponibile
        }
    }
}
