import com.pojo.ConsumeInfo;
import com.service.ConsumeInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class testConsumInfoService extends BaseTest {
    @Autowired
    private ConsumeInfoService consumeInfoService;

    @Test
    public void testSaveConsumeInfo() throws Exception {
        ConsumeInfo currCard=new ConsumeInfo("test","test",111);
        boolean insert = consumeInfoService.saveConsumeInfo(currCard);
        System.out.println(insert);
        //经过调试发现save成功，接下来调试其他内容
    }
    @Test
    public void testDeleteConsumeInfo()throws Exception{
        boolean deleteCard = consumeInfoService.deleteConsumeInfo("test");

        System.out.println(deleteCard);

    }

    @Test
    public void testUpdateConsumeInfo()throws Exception{
        //这里需要构造ConsumeInfo的对象传入
        ConsumeInfo updateCI = new ConsumeInfo("test","test",111);
        boolean updateConsumeInfo = consumeInfoService.updateConsumeInfo(updateCI);
        System.out.println(updateConsumeInfo);

    }

    @Test
    public void testGetConsumeInfo() throws Exception{
        List<ConsumeInfo> testInfos= consumeInfoService.getConsumeInfo("test");


    }

    @Test
    public void testQueryAllConsumeInfo()throws Exception{
        List<ConsumeInfo> allInfos= consumeInfoService.queryAllConsumeInfo();
        return;
    }
}
