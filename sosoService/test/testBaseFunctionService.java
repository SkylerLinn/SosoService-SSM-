import com.service.BasicFunctionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class testBaseFunctionService extends BaseTest {
    @Autowired
    private BasicFunctionService basicFunctionService;

    @Test
    public void testUseSoso() throws Exception{
        System.out.println(basicFunctionService.useSoso("test"));
    }

    @Test
    public void testChargeMoney()throws Exception{

        System.out.println(basicFunctionService.chargeMoney("test",1));//返回错误信息
        System.out.println(basicFunctionService.chargeMoney("test",100));
    }
    @Test
    public void testShowInformation(){
        System.out.println(basicFunctionService.showInformation("test"));
    }

    @Test
    public void testRandomScene(){
        System.out.println(basicFunctionService.randomScene().printInfo());
    }
    @Test
    public void testSearchMonthList(){
        System.out.println(basicFunctionService.searchMonthList("test"));
    }
    @Test
    public void testSearchRemaining(){
        System.out.println(basicFunctionService.searchRemaining("test"));

    }

    @Test
    public void testPrintConsumeList(){
        System.out.println(basicFunctionService.printConsumeList("test"));


    }
    @Test
    public void testChangePack(){
        System.out.println(basicFunctionService.changePack("test",1));
        System.out.println(basicFunctionService.changePack("test",1));
        System.out.println(basicFunctionService.changePack("test",2));



    }
    @Test
    public void testDeleteUser(){
        System.out.println(basicFunctionService.deleteUser("test"));



    }
}
