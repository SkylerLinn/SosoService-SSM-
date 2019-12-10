import com.pojo.MobileCard;
import com.service.MobileCardService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

public class testMobileCardService extends BaseTest {
    @Autowired
    private MobileCardService mobileCardService;

    @Test
//    @Bean
    public void testSaveMobileCard() throws Exception {
        MobileCard currCard=new MobileCard("testService","test","test",111,1);
        boolean insert = mobileCardService.saveMobileCard(currCard);
        System.out.println(insert);
    }
    @Test
    public void testDeleteMobileCard()throws Exception{
        boolean deleteCard = mobileCardService.deleteMobileCard("test1");

        System.out.println(deleteCard);

    }

    @Test
    public void testUpdateMobileCard()throws Exception{
        //这里需要构造MobileCard的对象传入
        MobileCard updateMC = new MobileCard("test","111","test",111,1);
        updateMC.getAccordPackage();
        boolean updateMobileCard = mobileCardService.updateMobileCard(updateMC);
        System.out.println(updateMobileCard);

    }

    @Test
    public void testGetMobileCard() throws Exception{
        MobileCard testCard= mobileCardService.getMobileCard("test");
        testCard.getAccordPackage();
        testCard.showMeg();

    }

    @Test
    public void testQueryAllMobileCard()throws Exception{
        List<MobileCard> allCard= mobileCardService.queryAllMobileCard();
        return;
    }
    @Test
    public void testIsExistMobileCard()throws Exception{
        System.out.println(mobileCardService.isExistMobileCard("test","1111"));
    }
}
