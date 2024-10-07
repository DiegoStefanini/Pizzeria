//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BufferPizze TavoloScrive = new BufferPizze();
        BufferPizze CameriereScrivePizzaiolo = new BufferPizze();
        BufferPizze PizzaioloScriveCameriere = new BufferPizze();
        BufferPizze CameriereScriveTavoli = new BufferPizze();

        for(int i=0;i<20;i++){
            if(i<3){
                Cameriere cameriere = new Cameriere(TavoloScrive, CameriereScrivePizzaiolo, PizzaioloScriveCameriere, CameriereScriveTavoli);
                Thread camer = new Thread(cameriere);
                camer.start();
            }
            Tavoli tavoli = new Tavoli(TavoloScrive, CameriereScriveTavoli, i);
            Thread th = new Thread(tavoli);
            th.start();
        }
        Pizzaiolo pizzaiolo = new Pizzaiolo(CameriereScrivePizzaiolo,PizzaioloScriveCameriere );
        Thread pt = new Thread(pizzaiolo);
        pt.start();
    }
}