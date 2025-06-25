package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootRedirectController {
    @GetMapping("/")
    public RedirectView redirectToCompare() {
        return new RedirectView("/api/compare/");
    }
} 