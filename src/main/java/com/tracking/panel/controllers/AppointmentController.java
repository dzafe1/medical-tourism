package com.tracking.panel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppointmentController {
    @RequestMapping(value = "/overview-appointments")
    public String overviewAppointments(){
        return "appointments";
    }
}
