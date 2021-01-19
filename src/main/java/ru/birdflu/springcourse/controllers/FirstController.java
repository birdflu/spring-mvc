package ru.birdflu.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

  @GetMapping("/hello")
  //  public String helloPage(HttpServletRequest request) {
  //    String name = request.getParameter("name");
  //    String surname = request.getParameter("surname");
  public String helloPage(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "surname", required = false) String surname,
                          Model model) {
    //System.out.println("Hello, " + name + " " + surname);
    model.addAttribute("message", "Hello, " + name + " " + surname);
    return "first/hello";
  }

  @GetMapping("/calculator")
  public String calculatorPage(@RequestParam(value = "a") int a,
                               @RequestParam(value = "b") int b,
                               @RequestParam(value = "action") String action,
                               Model model) {
    double result = 0;
    boolean Na = false;
    if (action.equals("multiplication")) result = a * b;
    else if (action.equals("addition")) result = a + b;
    else if (action.equals("subtraction")) result = a - b;
    else if ((b != 0) && (action.equals("division"))) result = a / (double) b;
    else Na = true;

    if (Na) model.addAttribute("calculate", "result = Na");
    else model.addAttribute("calculate", "result = " + result);
    return "first/calculator";
  }

  @GetMapping("/goodbye")
  public String goodByePage() {
    return "first/goodbye";
  }
}
