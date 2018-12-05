
import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.service.DistributionManager;
import com.example.shdemo.service.DistributionManagerI;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class Main {


    static DistributionManagerI distributionManager;

    public static void main(String[] args) {
        System.out.println(distributionManager.getAvailableAlcohols());
    }

}

