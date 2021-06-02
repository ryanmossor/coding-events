package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        /* List<String> events = new ArrayList<>();
        events.add("Code With Pride");
        events.add("Strange Loop");
        events.add("Apple WWDC");
        events.add("SpringOne Platform");
        model.addAttribute("events", events); */

        HashMap<String, String> events = new HashMap<>();
        events.put("WWDC", "an information technology conference held annually by Apple Inc.");
        events.put("Google I/O", "an annual developer conference held by Google in Mountain View, California");
        events.put("DockerCon", "a free, one day virtual event for development teams who are building the next " +
                "generation of modern applications");
        model.addAttribute("events", events);
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

    // lives at /events/create -- okay to host at same location
    // as above since these two methods handle different types of requests
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName) {
//        events.add(eventName);
        return "redirect:";     // redirect to the root path for this controller
    }

}
