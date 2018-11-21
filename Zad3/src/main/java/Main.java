import com.dmn.Projekt.domain.Alcohol;
import com.dmn.Projekt.service.AlcoholService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    try {
        AlcoholService test = new AlcoholService();
        test.clearAlcohols();
        test.addAlcohol(new Alcohol("Hennsey VS", "Hennessy", 2010, "whisky", 48.27f));
        test.addAlcohol(new Alcohol("Dellirium", "Huyghe", 2017, "piwo", 4.27f));
        List<Alcohol> alcoholList = new ArrayList<>();
        alcoholList.add(new Alcohol("Guiness", "Huyghe", 2017, "piwo", 4.14f));
        alcoholList.add(new Alcohol("Boar", "Wilderness", 2015, "burbon", 21.27f));
        alcoholList.add(new Alcohol("American IPA", "PK", 2017, "piwo", 3.97f));

        test.addAllAlcohols(alcoholList);
        System.out.println("Wszystkie alkohole w bazie:");
        printList(test.getAllAlcohols());
        System.out.println("______");
        System.out.println("Wszystkie piwa w bazie:");
        printList(test.getAllAlcoholsByType("piwo"));
        System.out.println("______");
        System.out.println("Posortowane alkohole z zawartością alkoholu 3% - 5%, posortowane względem mocy:");
        List<Alcohol> testOfVolt = new ArrayList<>();
        testOfVolt.addAll(test.getAlcoholsByVolt(3.00f, 5.00f, AlcoholService.ORDER_ASC));
        for (Alcohol a: testOfVolt
             ) {
            System.out.println(a.getName() + " "+a.getVolt());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
static void printList(List<Alcohol> list){
    for (Alcohol a: list
    ) {
        System.out.println(a.getName() + " "+a.getType()+ " "+a.getVolt());
    }
}
}
