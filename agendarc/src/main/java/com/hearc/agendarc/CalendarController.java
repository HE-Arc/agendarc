package com.hearc.agendarc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalendarController{

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String test() {
        return "calendar";
    }
    
}