import com.dmn.Projekt.service.AlcoholService;

public class Main {public static void main(String[] args) {
    try {
        AlcoholService test = new AlcoholService();
        // test.addAlcohol(new Alcohol("Dellirium", "Huyghe", 2017, "piwo", 4.27f));
        System.out.println(test.getAllAlcohols().get(0).getName());
        System.out.println(test.getAllAlcoholsByType("piwo").get(1).getName());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
