package com.controller;

import com.pojo.MobileCard;
import com.service.BasicFunctionService;
import com.service.MobileCardService;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Parameter;

@Controller
@MapperScan({"com.dao.MobileCardDao"})
//@SessionAttributes("user")
@SessionAttributes({"user","userInfo","title","changePackInfo","chargeInfo","useSosoInfo"})
@RequestMapping("/soso")
public class SosoController {
    @Autowired
    public MobileCardService mobileCardService;
    @Autowired
    public BasicFunctionService basicFunctionService;

    @RequestMapping(value = "/Register")
    public String Register(@RequestParam("cardNum")String cardNum, @RequestParam("choosePackageIndex")String choosePackageIndex, @RequestParam("userName")String userName, @RequestParam("passWord")String passWord,@RequestParam("money")String money, Model model,HttpServletRequest request, HttpServletResponse response)throws IOException {
//        HttpServletRequest request, HttpServletResponse response
        {
            try{
                int index = Integer.parseInt(choosePackageIndex);
                int currMoney =Integer.parseInt(money);
                MobileCard mobileCard = new MobileCard(cardNum,passWord,userName,currMoney,index);
                mobileCardService.saveMobileCard(mobileCard);
                return "redirect:/SuccessToOperate.jsp";

//                response.sendRedirect("index.jsp");
            }catch(Exception e){
                e.printStackTrace();
//                session.setAttribute("msg","保存失败！");
                return "redirect:/FailToOperate.jsp";
            }

        }
    }
    @RequestMapping("/login")
    public String logIn(@RequestParam("cardNum")String cardNum,@RequestParam("passWord")String passWord,Model model){
        try{
            MobileCard mobileCard=mobileCardService.getMobileCard(cardNum);
            model.addAttribute("user",mobileCard);
            return "redirect:/user.jsp";
        }
        catch(Exception e){
            e.printStackTrace();
            return "redirect:/FailToOperate.jsp";
        }
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(String cardNum,Model model){
            if (mobileCardService.deleteMobileCard(cardNum))
                return "redirect:/SuccessToOperate.jsp";
            else
                return "redirect:/FailToOperate.jsp";

    }
    @RequestMapping("/getInfo")
    public String getInfo(@SessionAttribute("user")MobileCard mobileCard,Model model) {
       model.addAttribute("userInfo",basicFunctionService.showInformation(mobileCard.getCardNumber()));
       model.addAttribute("title","用户资费说明");
       return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/getMonthList")
    public String getMonthList(@SessionAttribute("user")MobileCard mobileCard,Model model) {
        model.addAttribute("userInfo",basicFunctionService.searchMonthList(mobileCard.getCardNumber()));
        model.addAttribute("title","用户本月账单");
        return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/changePack")
    public String changePack(@SessionAttribute("user")MobileCard mobileCard,@RequestParam("packageIndex")String newPackIndex, Model model) {
        try{
            model.addAttribute("changePackInfo",basicFunctionService.changePack(mobileCard.getCardNumber(),Integer.parseInt(newPackIndex)));

        }catch (Exception e){
            model.addAttribute("changePackInfo","输入非法，请重新输入！");
        }

        return "redirect:/changePackPage.jsp";
    }
    @RequestMapping("/chargeMoney")
    public String chargeMoney(@SessionAttribute("user")MobileCard mobileCard,@RequestParam("money")String money, Model model) {

        try{
            int chargeMoney = Integer.parseInt(money);
            model.addAttribute("chargeInfo",basicFunctionService.chargeMoney(mobileCard.getCardNumber(),chargeMoney));
        }catch (Exception e){
            model.addAttribute("chargeInfo","输入非法，请重新输入！");
        }

        return "redirect:/chargePage.jsp";
    }
    @RequestMapping("/useSoso")
    public String useSoso(@SessionAttribute("user")MobileCard mobileCard,Model model) {

        try{
            model.addAttribute("useSosoInfo",basicFunctionService.useSoso(mobileCard.getCardNumber()));
            return "redirect:/useSoso.jsp";
        }catch (Exception e){
            model.addAttribute("useSosoInfo","使用soso失败");
            return "redirect:/useSoso.jsp";
        }


    }
    @RequestMapping("/getRemainInfo")
    public String getRemainInfo(@SessionAttribute("user")MobileCard mobileCard,Model model) {
        model.addAttribute("userInfo",basicFunctionService.searchRemaining(mobileCard.getCardNumber()));
        model.addAttribute("title","查询余量");
        return "redirect:/userInfoPage.jsp";
    }
    @RequestMapping("/printDetailedList")
    public String printDetailedList(@SessionAttribute("user")MobileCard mobileCard,Model model) {
        model.addAttribute("userInfo",basicFunctionService.printConsumeList(mobileCard.getCardNumber()));
        model.addAttribute("title","打印消费详单");
        return "redirect:/userInfoPage.jsp";
    }
}

