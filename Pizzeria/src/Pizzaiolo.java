public class Pizzaiolo implements Runnable{
    private BufferPizze LeggeCameriere;
    private BufferPizze ScriveCameriere;
    public Pizzaiolo(BufferPizze a, BufferPizze b){
        LeggeCameriere = a;
        ScriveCameriere = b;
    }
    public void run(){
        //produce le pizze
        while (true) {
            // non fatto di processare da 1 a 8, ma processa un tavolo alla volta
            Ordine a = LeggeCameriere.getOrder();
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
            }
            ScriveCameriere.pushOrder(a);
        }
    }
}
