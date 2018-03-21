package com.tracking.panel.controllers;

import com.tracking.panel.domain.User;
import com.tracking.panel.repository.HospitalRepository;
import com.tracking.panel.repository.UserRepository;
import com.tracking.panel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private HospitalRepository hospitalRepository;

    //private String userFnameTest=userService.getUser(principal.getName()).getfName();


    @GetMapping(value = "/")
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap modelMap){
        if(error!=null){
             modelMap.addAttribute("badCredentials","value");
        }
        return "index";
    }

    @GetMapping (value = "/add-admin")
    public String addAdmin(User user){
        return "add-admin";
    }

    @PostMapping(value = "/add-admin")
    public String addAdmin(@Valid User user, BindingResult bindingResult,@RequestParam("fName")String fName,
                           @RequestParam("lName")String lName, @RequestParam("email")String email,
                           @RequestParam("password")String password, @RequestParam("repeatPassword")String repeatPassword,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            return "add-admin";
        }else {
            String imgPath="images/avatar5.png";
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            userRepository.save(new User(fName, lName, email, bCryptPasswordEncoder.encode(password), imgPath, true, "ADMIN"));
            redirectAttributes.addFlashAttribute("adminInserted", "Admin successfully inserted!");
            return "redirect:/add-admin";
        }
    }
    @RequestMapping(value = "/dashboard")
    public String dashboard(Principal principal,ModelMap modelMap){
        String userFname=userService.getUser(principal.getName()).getfName();
        modelMap.addAttribute("usersCount",userRepository.getAllUsers());
        modelMap.addAttribute("activeUser",userFname);
        System.out.println(userService.getUser(principal.getName()));
        Long hospitalsCount=hospitalRepository.getHospitalsCount();
        System.out.println("Broj je: "+hospitalsCount);
        return "dashboard";
    }
    @RequestMapping("/admin-login")
    public String home() {
        return "admin-login";
    }
    @RequestMapping("/overview-admins")
    public String overviewAdmins(ModelMap modelMap) {
        List<User> userList=userRepository.getAllAdmins();
        modelMap.addAttribute("users",userList);
        return "admins";
    }
    @RequestMapping("/overview-users")
    public String overviewUsers(ModelMap modelMap) {
        List<User> userList=userRepository.getAllNonAdmins();
        modelMap.addAttribute("users",userList);
        Long count=userRepository.getAllNonAdminsCount();
        modelMap.addAttribute("count",count);
        return "users";
    }
    @GetMapping("/add-user")
    public String addUser(User user) {
        return "add-user";
    }
    @PostMapping("/add-user")
    public String addUser(@Valid User user, BindingResult bindingResult,@RequestParam("fName")String fName,
                          @RequestParam("lName")String lName, @RequestParam("email")String email,
                          @RequestParam("password")String password, @RequestParam("repeatPassword")String repeatPassword,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-user";
        }else {
            String imgPath="images/avatar5.png";
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            userRepository.save(new User(fName, lName, email, bCryptPasswordEncoder.encode(password), imgPath, true, "USER"));
            redirectAttributes.addFlashAttribute("userInserted", "User successfully inserted!");
            return "redirect:/add-user";
        }
    }
    @PostMapping("/edit-user/{id}")
    public String editUser(@Valid User user, BindingResult bindingResult,
                           @RequestParam("fName")String fName,
                           @RequestParam("lName")String lName, @RequestParam("email")String email,
                           @PathVariable(value="id") Long id,RedirectAttributes redirectAttributes){
        User userDB=userRepository.findOneById(id);
        if (user == null) {
            redirectAttributes.addFlashAttribute("userNotFound", "User not found!");
            return "redirect:/overview-users";
        }
        Boolean updated=false;
        if (!userDB.getfName().equals(fName) && !fName.equals("")){
            userDB.setfName(fName);
            updated=true;
        }
        if (!userDB.getlName().equals(lName) && !lName.equals("")){
            userDB.setlName(lName);
            updated=true;
        }
        if (!userDB.getEmail().equals(email) && !email.equals("")){
            userDB.setEmail(email);
            updated=true;
        }
        if(updated) {
            userDB = userRepository.save(userDB);
            redirectAttributes.addFlashAttribute("userUpdated", "User updated!");
        }
        return "redirect:/overview-users";
    }
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable(value="id") Long id,RedirectAttributes redirectAttributes){
        userRepository.delete(id);
        redirectAttributes.addFlashAttribute("userDeleted", "User deleted!");
        return "redirect:/overview-users";
    }
    @GetMapping("/access-denied")
    public String unauthorizedPage(){
        return "403";
    }
    @GetMapping("/404")
    public String notFoundPage(){
        return "404";
    }
}
