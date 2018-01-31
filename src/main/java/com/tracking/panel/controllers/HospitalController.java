package com.tracking.panel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HospitalController {
    @RequestMapping(value = "/add-hospital")
    public String addHospital(){
        return "add-hospital";
    }
    @RequestMapping(value = "/overview-hospitals")
    public String overviewHospital(){
        return "hospitals";
    }
}
