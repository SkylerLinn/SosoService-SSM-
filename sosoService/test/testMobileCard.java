import com.dao.MobileCardDao;
import com.pojo.MobileCard;
import org.junit.Test;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class testMobileCard extends BaseTest{
    @Autowired
    private MobileCardDao mobileCardDao;

    @Test
    public void testSaveMobileCard() throws Exception {
        MobileCard currCard=new MobileCard("test","test","test",111,1);
        boolean insert = mobileCardDao.saveMobileCard(currCard);
        System.out.println(insert);
        //经过调试发现save成功，接下来调试其他内容
    }
    @Test
    public void testDeleteMobileCard()throws Exception{
        boolean deleteCard = mobileCardDao.deleteMobileCard("test");

        System.out.println(deleteCard);

    }

    @Test
    public void testUpdateMobileCard()throws Exception{
        //这里需要构造MobileCard的对象传入
        MobileCard updateMC = new MobileCard("test","111","test",111,1);
        updateMC.getAccordPackage();
        boolean updateMobileCard = mobileCardDao.updateMobileCard(updateMC);
        System.out.println(updateMobileCard);

    }

    @Test
    public void testGetMobileCard() throws Exception{
        MobileCard testCard= mobileCardDao.getMobileCard("test");
        testCard.getAccordPackage();
        testCard.showMeg();

    }

    @Test
    public void testQueryAllMobileCard()throws Exception{
        List<MobileCard> allCard= mobileCardDao.queryAllMobileCard();
        return;
    }

}
