package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        // if you have a static method within a class, use the class name to call the method
        model.addAttribute("title", "All Events");
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    // lives at /events/create -- okay to host at same location
    // as above since these two methods handle different types of requests
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:";     // redirect to the root path for this controller
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event selectedEvent = EventData.getById(eventId);
        model.addAttribute("selectedEvent", selectedEvent);
        model.addAttribute("title", "Edit Event " + selectedEvent.getName() + " (id=" + selectedEvent.getId() + ")");
        return "events/edit";
    }

    @PostMapping("edit/{eventId}")
    public String processEditForm(int eventId, String name, String description) {
        Event selectedEvent = EventData.getById(eventId);
        selectedEvent.setName(name);
        selectedEvent.setDescription(description);
        return "redirect:";
    }
}
