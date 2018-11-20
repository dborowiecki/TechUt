import com.dmn.Projekt.domain.Alcohol;
import com.dmn.Projekt.service.AlcoholService;

import java.util.ArrayList;
import java.util.List;

public class Main {public static void main(String[] args) {
    try {
        AlcoholService test = new AlcoholService();
        test.addAlcohol(new Alcohol("Hennsey VS", "Hennessy", 2010, "whisky", 18.27f));
        test.addAlcohol(new Alcohol("Dellirium", "Huyghe", 2017, "piwo", 4.27f));
        List<Alcohol> alcoholList = new ArrayList<>();
        alcoholList.add(new Alcohol("Guiness", "Huyghe", 2017, "piwo", 4.27f));
        alcoholList.add(new Alcohol("Boar", "Wilderness", 2015, "piwo", 8.27f));
        alcoholList.add(new Alcohol("American IPA", "PK", 2017, "piwo", 3.27f));

        test.addAllAlcohols(alcoholList);
        System.out.println(test.getAllAlcohols().get(0).getName());
        System.out.println(test.getAllAlcoholsByType("piwo").get(1).getName());
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
