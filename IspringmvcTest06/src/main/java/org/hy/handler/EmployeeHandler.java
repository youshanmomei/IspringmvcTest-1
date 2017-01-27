package org.hy.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by andy on 2017/1/27.
 */
@Controller
public class EmployeeHandler {

    @RequestMapping("/testSuccess")
    public String testSuccess(){
        return "success";
    }
}
