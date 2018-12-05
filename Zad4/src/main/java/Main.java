
import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.service.DistributionManager;
import com.example.shdemo.service.DistributionManagerI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class Main {


    static DistributionManagerI distributionManager;

    public static void main(String[] args) {

        System.out.println(distributionManager.getAvailableAlcohols());
    }

}

