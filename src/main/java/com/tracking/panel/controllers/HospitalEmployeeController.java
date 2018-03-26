package com.tracking.panel.controllers;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmployee;
import com.tracking.panel.domain.HospitalsImages;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /*
    @TODO postoji bug neki sa updated, ispitati sa sout komandom
     */
    @PostMapping(value = "/edit-hospital-employee")
    public String editHospitalEmployee(@Valid HospitalEmployee employeeForm,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes, @RequestParam("employeeId") Long employeeId,
                                       @RequestParam("hospitalId") Long hospitalId,
                                       @RequestParam("fName") String fName, @RequestParam("lName") String lName,
                                       @RequestParam("email") String email, @RequestParam("employeeTitle") String employeeTitle,
                                       @RequestParam("employeeDob") String employeeDob, @RequestParam(value = "employeePictures",required = false) MultipartFile employeePicture){
        if (bindingResult.hasErrors()) {
            return "edit-employees";
        }else {
            HospitalEmployee employee=hospitalEmployeeRepository.findOneById(employeeId);
            if(employee==null){
                return "redirect:/404";
            }
            Boolean updated=false;
            if (!employee.getfName().equals(fName) && !fName.isEmpty()){
                employee.setfName(fName);
                updated=true;
            }
            if (!employee.getlName().equals(lName) && !lName.isEmpty()){
                employee.setlName(lName);
                updated=true;
            }
            if (!employee.getEmail().equals(email) && !email.isEmpty()){
                employee.setEmail(email);
                updated=true;
            }
            if (!employee.getTitle().equals(employeeTitle) && !employeeTitle.isEmpty()){
                employee.setTitle(employeeTitle);
                updated=true;
            }

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            try {
                date = formatter.parse(employeeDob);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!employee.getDob().equals(date) && !employeeDob.isEmpty()){
                employee.setDob(date);
                updated=true;
            }
            if(employeePicture!=null){
                File file=new File("C:/Users/haris/IdeaProjects/panel/src/main/resources/static/images/hospitals employees/" + employeePicture.getOriginalFilename());
                if(!file.exists()) {
                    try {
                        byte[] bytes = employeePicture.getBytes();
                        Path path = Paths.get("C:/Users/haris/IdeaProjects/panel/src/main/resources/static/images/hospitals employees/" + employeePicture.getOriginalFilename());
                        Files.write(path, bytes);
                        employee.setImgPath(path.toString());
                        updated=true;
                        System.out.println("izvrsen");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(updated){
                redirectAttributes.addFlashAttribute("employeeUpdated", "Employee"+employee.getfName()+" updated!");
                hospitalEmployeeRepository.save(employee);
            }
            //System.out.println(employee);
            //System.exit(0);
            return "redirect:/edit-hospital-employees/"+hospitalId;
        }




    }
}
