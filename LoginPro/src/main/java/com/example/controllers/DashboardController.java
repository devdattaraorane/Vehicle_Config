package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Manufacturer;
import com.example.models.Segment;
import com.example.services.ManufacturerService;
import com.example.services.SegmentManufacturerService;
import com.example.services.SegmentService;

@Controller
public class DashboardController {

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private SegmentManufacturerService smService;

    /**
     * Load dashboard page with segments
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model,
            @AuthenticationPrincipal OAuth2User user) {

        String email = user.getAttribute("email");

        model.addAttribute("username", email);
        model.addAttribute("segments", segmentService.getAllSegments());

        return "dashboard";
    }


    /**
     * Load manufacturers for selected segment
     */
    @GetMapping("/manufacturers")
    public String manufacturersBySegment(
            @RequestParam("segmentId") int segmentId,
            Model model,
            Authentication auth) {

        String email = auth.getName();

        List<Manufacturer> manufacturers =
                smService.getManufacturersBySegment(segmentId);

        model.addAttribute("username", email);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("segments", segmentService.getAllSegments());
        model.addAttribute("selectedSegmentId", segmentId);

        return "dashboard"; // reuse same page
    }
}
