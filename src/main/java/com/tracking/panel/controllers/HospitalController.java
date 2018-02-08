package com.tracking.panel.controllers;

import com.tracking.panel.domain.Hospital;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class HospitalController{
    @GetMapping(value = "/add-hospital")
    public String addHospital(Hospital hospital){
        return "add-hospital";
    }
    @PostMapping(value = "/add-hospital")
    public String addHospital(@Valid Hospital hospital, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("fullName")String fullName, @RequestParam("city")String city,
                              @RequestParam("postalCode")String postalCode, @RequestParam("address")String address,
                              @RequestParam("about")String about, @RequestParam("hospitalPictures")MultipartFile hospitalPictures,
                              @RequestParam("employeeFname[]")List<String> employeeFname, @RequestParam("employeeLname[]")List<String> employeeLname,
                              @RequestParam("employeeRole[]")List<String> employeeRole, @RequestParam("employeePictures[]")List<MultipartFile> employeePictures){
        for(String number : employeeRole) {
            System.out.println(number);
        }
        return "add-hospital";
    }
    @RequestMapping(value = "/overview-hospitals")
    public String overviewHospital(){
        return "hospitals";
    }
}
