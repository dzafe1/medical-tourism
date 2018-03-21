package com.tracking.panel.controllers;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmployee;
import com.tracking.panel.repository.HospitalEmployeeRepository;
import com.tracking.panel.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HospitalEmployeeController {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalEmployeeRepository hospitalEmployeeRepository;

    @GetMapping(value = "/edit-hospital-employees/{id}")
    public String editHospitalEmployees(@PathVariable("id") Long id, Model model){
        Hospital hospital=hospitalRepository.findOneById(id);
        if(hospital!=null) {
            List<HospitalEmployee> employees = hospitalEmployeeRepository.findAllByHospital(hospital);
            model.addAttribute("employees", employees);
            model.addAttribute("hospitalId", id);
            return "edit-employees";
        }else
            return "redirect:/404";
    }
    @PostMapping(value = "/edit-hospital-employee")
    public String editHospitalEmployee(@Valid HospitalEmployee employe,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes, @RequestParam("employeeId") Long employeeId,
                                       @RequestParam("hospitalId") Long hospitalId,
                                       @RequestParam("fName") String fName, @RequestParam("lName") String lName,
                                       @RequestParam("email") String email, @RequestParam("employeeTitle") String employeeTitle,
                                       @RequestParam("employeeDob") String employeeDob, @RequestParam(value = "employeePictures",required = false) MultipartFile employeePictures){
        if (bindingResult.hasErrors()) {
            return "edit-employees";
        }else {
            HospitalEmployee employee=hospitalEmployeeRepository.findOneById(employeeId);
            if(employee==null){
                return "redirect:/404";
            }
            System.out.println(employee);
            //System.exit(0);
            return "redirect:/edit-hospital-employees/"+hospitalId;
        }




    }
}
