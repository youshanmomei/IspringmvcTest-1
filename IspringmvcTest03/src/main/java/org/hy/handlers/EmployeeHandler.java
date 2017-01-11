package org.hy.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by andy on 2017/1/11.
 */
@Controller
public class EmployeeHandler {

    @RequestMapping("/emps")
    public String list() {

        return "list";
    }
}
