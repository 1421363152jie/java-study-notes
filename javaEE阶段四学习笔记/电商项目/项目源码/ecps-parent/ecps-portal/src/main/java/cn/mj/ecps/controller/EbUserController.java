package cn.mj.ecps.controller;

import cn.mj.ecps.dao.EbAreaDao;
import cn.mj.ecps.model.EbShipAddr;
import cn.mj.ecps.model.TsPtlUser;
import cn.mj.ecps.service.EbAreaService;
import cn.mj.ecps.service.EbShipAddrService;
import cn.mj.ecps.service.TsPtlUserService;
import cn.mj.ecps.utils.EbMJUtis;
import cn.mj.ecps.utils.MD5;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.compass.core.json.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class EbUserController {

    @Autowired
    private TsPtlUserService userService;

    @Autowired
    private EbShipAddrService addrService;

    @Autowired
    private EbAreaService areaService;

    @RequestMapping("/toLogin.do")
    public String toLogin() {
        return "passport/login";
    }

    @RequestMapping("/login.do")
    public String login(String username, String password, String captcha, HttpSession session, Model model) {
        String valiCode = (String) session.getAttribute("piccode");
        if (!StringUtils.equalsIgnoreCase(valiCode, captcha)) {
            model.addAttribute("tip", "code_error");
            return "passport/login";
        }
        //使用MD5给密码加密
        password = MD5.GetMD5Code(password);
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        TsPtlUser user = userService.selectUserByUnameAndPword(map);
        if (user == null) {
            model.addAttribute("tip", "UandP_error");
            return "passport/login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/item/toIndex.do";
        }
    }

    /**
     *使用ajax登陆
     */
      @RequestMapping("/loginAjax.do")
    public void loginAjax(String username, String password, String captcha, HttpSession session, PrintWriter pw){
        String valiCode= (String) session.getAttribute("piccode");
        if(!StringUtils.equalsIgnoreCase(valiCode,captcha)){
            pw.write("code_error");
        }
        //使用MD5给密码加密
        password=MD5.GetMD5Code(password);
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",username);
        map.put("password",password);
        TsPtlUser user = userService.selectUserByUnameAndPword(map);
        if(user==null){
            pw.write("UandP_error");
        }else{
            session.setAttribute("user",user);
            pw.write("success");
        }

    }


    /**
     * 获得session中的登陆用户数据
     */

    @RequestMapping("/getUser.do")
    public void getUser(HttpSession session,HttpServletResponse response){
       TsPtlUser user= (TsPtlUser) session.getAttribute("user");
        JSONObject jo=new JSONObject();
        jo.accumulate("user",user);
        String result = jo.toString();
        EbMJUtis.printAjax(response,result);
    }

    /**
     * 收货地址的查询
     */
    @RequestMapping("/login/listAddr.do")
     public String listAddr(HttpSession session,Model model){
        TsPtlUser user= (TsPtlUser) session.getAttribute("user");
        List<EbShipAddr> addrList =addrService.selectAddrByUserId(user.getPtlUserId());
        List<EbAreaDao> areaList = areaService.selectAddrByPid(0l);
        model.addAttribute("areaList",areaList);
        model.addAttribute("addrList",addrList);
        return "/person/deliverAddress";
     }

    /**
     * 根据父节点的id查询下面的地址
     */
    @RequestMapping("/login/getAddr.do")
     public void getAddr(Long areaId,HttpServletResponse response){
        List<EbAreaDao> areaList = areaService.selectAddrByPid(areaId);
         JSONObject jo=new JSONObject();
         jo.accumulate("areaList",areaList);
         String result = jo.toString();
        EbMJUtis.printAjax(response,result);
    }


    /**
     * 修改地址信息的加载
     */
    @RequestMapping("/login/modify.do")
    public void modify(Long shipAddrId,HttpServletResponse response){
        EbShipAddr addr = addrService.selectAddrById(shipAddrId);
       JSONObject jo=new JSONObject();
       jo.accumulate("addr",addr);
        String result = jo.toString();
        EbMJUtis.printAjax(response,result);
    }

    /**
     * 修改或添加地址
     */
    @RequestMapping("/login/savaOrUpdateByAddr.do")
   public String savaOrUpdateByAddr(EbShipAddr shipAddr,HttpSession session){
        TsPtlUser user= (TsPtlUser) session.getAttribute("user");
        shipAddr.setPtlUserId(user.getPtlUserId());
        if(shipAddr.getDefaultAddr()==null){
            shipAddr.setDefaultAddr((short)0);
        }
        addrService.savaOrUpdateAddr(user.getPtlUserId(),shipAddr);
        return "redirect:listAddr.do";
   }

    /**
     * 删除地址
     */
    @RequestMapping("/login/deleteAddr.do")
   public String deleteAddr(Long shipAddrId){
        addrService.deleteAddrById(shipAddrId);
        return "redirect:listAddr.do";
   }

    /**
     * 设置默认收货地址
     * @return
     */
    @RequestMapping("/login/setDefaultAddr.do")
    public String setDefaultAddr(Long shipAddrId,HttpSession session){
        TsPtlUser user= (TsPtlUser) session.getAttribute("user");
        addrService.updateDefaultAddrByShipAddrId(user.getPtlUserId(),shipAddrId);
        return "redirect:listAddr.do";
    }



    @RequestMapping("/login/toPersonIndex.do")
    public String toPersonIndex(){
       return "person/index";
    }


    @RequestMapping("/getImage.do")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("#######################生成数字和字母的验证码#######################");
        BufferedImage img = new BufferedImage(68, 22,

                BufferedImage.TYPE_INT_RGB);

        // 得到该图片的绘图对象

        Graphics g = img.getGraphics();

        Random r = new Random();

        Color c = new Color(200, 150, 255);

        g.setColor(c);

        // 填充整个图片的颜色

        g.fillRect(0, 0, 68, 22);

        // 向图片中输出数字和字母

        StringBuffer sb = new StringBuffer();

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        int index, len = ch.length;

        for (int i = 0; i < 4; i++) {

            index = r.nextInt(len);

            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt

                    (255)));

            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
            // 输出的  字体和大小

            g.drawString("" + ch[index], (i * 15) + 3, 18);
            //写什么数字，在图片 的什么位置画

            sb.append(ch[index]);

        }

        request.getSession().setAttribute("piccode", sb.toString());

        ImageIO.write(img, "JPG", response.getOutputStream());
    }




}
