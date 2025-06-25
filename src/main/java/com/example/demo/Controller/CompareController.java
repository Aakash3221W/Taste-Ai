package com.example.demo.Controller;

import com.example.demo.model.CompareRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Service.OpenRouterService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/compare")
public class CompareController {

    @Autowired
    private OpenRouterService openRouterService;

    @Value("${countries}")
    private String countriesProperty;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("compareRequest", new CompareRequest());
        List<String> countries = Arrays.asList(countriesProperty.split(","));
        System.out.println("Countries: " + countries);
        model.addAttribute("countries", countries);
        return "index";
    }

    @PostMapping
    public String compareDish(@ModelAttribute CompareRequest request, Model model) {
        String result = openRouterService.getComparison(request);
        model.addAttribute("result", result);
        model.addAttribute("compareRequest", request);
        List<String> countries = Arrays.asList(countriesProperty.split(","));
        model.addAttribute("countries", countries);
        return "index";
    }
}
