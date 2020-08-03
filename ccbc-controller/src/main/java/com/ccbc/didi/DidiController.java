package com.ccbc.didi;

import com.ccbc.Car;
import com.ccbc.pojo.User;
import com.ccbc.pojo.req.LoanApplyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DidiController {


    @Autowired
    private LoanApplyService loanApplyService;




    @RequestMapping("/ecpD/loanApply")
    public String loanApply(@RequestBody LoanApplyReq loanApplyReq){
        loanApplyService.checkValidity(loanApplyReq);
        return "success";
    }

    @RequestMapping("/hi")
    public String execute(String name){

        return "success";
    }

    @RequestMapping("/hello")
    public String hello(User user){

        return "success";
    }

    @RequestMapping("/car")
    public String car(@Valid Car car){

        return "success";
    }

}
