package com.hoodee.community.Controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Package: com.hoodee.community.Controlller
 * Description：
 * Author: wude
 * Date:  2019.12.09 23:32
 * Modified By:
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
