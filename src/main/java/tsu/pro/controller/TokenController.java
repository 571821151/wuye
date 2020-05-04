package tsu.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsu.pro.bean.Info;
import tsu.pro.bean.LivingArea;
import tsu.pro.service.LivingAreaService;
import tsu.pro.utils.qiniuyunUtils;


@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @RequestMapping(value = "/qiniu", method = RequestMethod.GET)
    public String getHouseList() {
        return qiniuyunUtils.getQiniuToken();
    }


}
