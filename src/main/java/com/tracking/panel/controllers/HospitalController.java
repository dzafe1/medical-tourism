package com.tracking.panel.controllers;

import com.tracking.panel.domain.Hospital;
import com.tracking.panel.domain.HospitalEmploye;
import com.tracking.panel.domain.HospitalsImages;
import com.tracking.panel.repository.HospitalEmployeeRepository;
import com.tracking.panel.repository.HospitalRepository;
import com.tracking.panel.repository.HospitalsImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class HospitalController{
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalEmployeeRepository hospitalEmployeeRepository;
    @Autowired
    private HospitalsImagesRepository hospitalsImagesRepository;
    @GetMapping(value = "/add-hospital")
    public String addHospital(Model model) {
        Hospital hospital=new Hospital();
        HospitalsImages hospitalsImages=new HospitalsImages();
        HospitalEmploye hospitalEmploye=new HospitalEmploye();
        model.addAttribute(hospital);
        model.addAttribute(hospitalsImages);
        model.addAttribute(hospitalEmploye);
        return "add-hospital";
    }
    @PostMapping(value = "/add-hospital")
    public String addHospital(@Valid Hospital hospital,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @RequestParam("fullName")String fullName, @RequestParam("city")String city,
                              @RequestParam("postalCode")String postalCode, @RequestParam("address")String address,
                              @RequestParam("aboutHospital")String about, @RequestParam("hospitalPictures")MultipartFile[] hospitalPictures,
                              @RequestParam("employeeFname[]")List<String> employeeFname, @RequestParam("employeeLname[]")List<String> employeeLname,
                              @RequestParam("employeeEmail[]")List<String> employeeEmail, @RequestParam("employeeDob[]")List<String> employeeDob,
                              @RequestParam("employeeTitle[]")List<String> employeeTitle, @RequestParam("employeePictures[]")List<MultipartFile> employeePictures){
        if (bindingResult.hasErrors()) {
            return "add-hospital";
        }else {
            Hospital newHospital=hospitalRepository.save(new Hospital(fullName,city,postalCode,address,about));
            for(MultipartFile file : hospitalPictures) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get("C:/Users/haris/IdeaProjects/panel/src/main/resources/static/images/hospitals/" + file.getOriginalFilename());
                    Files.write(path, bytes);
                    hospitalsImagesRepository.save(new HospitalsImages(path.toString(),newHospital));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(employeeFname.size() == employeeLname.size()
                    && employeeLname.size()==employeeEmail.size()
                    && employeeEmail.size()==employeeDob.size()
                    && employeeTitle.size()==employeePictures.size()) {
                for (int i = 0; i < employeeFname.size(); i++) {
                    String employeePicturePath = "";
                    try {
                        byte[] bytes = employeePictures.get(i).getBytes();
                        Path path = Paths.get("C:/Users/haris/IdeaProjects/panel/src/main/resources/static/images/hospitals employees/" + employeePictures.get(i).getOriginalFilename());
                        Files.write(path, bytes);
                        employeePicturePath = path.toString();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    try {
                        date = formatter.parse(employeeDob.get(i));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    hospitalEmployeeRepository.save(new HospitalEmploye(employeeFname.get(i), employeeLname.get(i), employeeEmail.get(i), employeePicturePath, employeeTitle.get(i), date, true, newHospital));
                }
            }
            redirectAttributes.addFlashAttribute("hospitalInserted", "Hospital successfully inserted!");
            return "redirect:/add-hospital";
        }
    }
    @GetMapping(value = "/overview-hospitals")
    public String overviewHospital(ModelMap modelMap){
        List<Hospital> hospitals=hospitalRepository.getAllHospitals();
        modelMap.addAttribute("hospitals",hospitals);
        return "hospitals";
    }
    @GetMapping(value = "/get-hospital-data/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map getHospitalsData(@PathVariable Long id){
        return Collections.singletonMap("hospital", hospitalRepository.findOneById(id));
    }
    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String test(){
        List<HospitalEmploye> employes=hospitalRepository.findOneById(new Long(1)).getHospitalsEmployee();
        return employes.get(1).getEmail();
    }
    @GetMapping(value = "/delete-hospital/{id}")
    public String deleteHospital(Hospital hospital,RedirectAttributes redirectAttributes,@PathVariable Long id){
        hospitalRepository.delete(id);
        redirectAttributes.addFlashAttribute("hospitalDeleted", "Hospital deleted!");
        return "redirect:/overview-hospitals";
    }
}

