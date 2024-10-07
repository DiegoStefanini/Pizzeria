public class Cameriere implements Runnable{
    private BufferPizze LeggereTavolo;
    private BufferPizze MandaPizzaiolo;
    private BufferPizze LeggerePizzaiolo;
    private BufferPizze MandaTavoli;
    public Cameriere(BufferPizze a, BufferPizze b, BufferPizze c, BufferPizze d){
        LeggereTavolo = a;
        MandaPizzaiolo = b;
        LeggerePizzaiolo = c;
        MandaTavoli = d;
    }
    public void run(){
        while(true){
            System.out.println("ei sono sveglio ");

            if(LeggereTavolo.isOrder()){
                Ordine order = LeggereTavolo.getOrder();
                try {
                    Thread.sleep(900); //simulo la consegna della pizza
                } catch (InterruptedException e) {}
                MandaPizzaiolo.pushOrder(order);
                System.out.println("Ordine del tavolo " + order.getnTavolo() + " consegnato in pizzeria");
            }
            if(LeggerePizzaiolo.isOrder()){
                Ordine order = LeggerePizzaiolo.getOrder();
                try {
                    Thread.sleep(900); //simulo la consegna della pizza
                } catch (InterruptedException e) {}
                MandaTavoli.pushOrder(order);
            }
        }
    }

}
