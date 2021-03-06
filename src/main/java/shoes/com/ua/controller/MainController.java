package shoes.com.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shoes.com.ua.entity.Products;
import shoes.com.ua.entity.User;
import shoes.com.ua.services.MailSenderService;
import shoes.com.ua.services.ProductsService;
import shoes.com.ua.services.SecurityService;
import shoes.com.ua.services.UserService;
import shoes.com.ua.validator.UserValidator;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService senderService;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserValidator userValidator;

    @Autowired
    ProductsService productsService;


    @GetMapping({"/", "/home"})
    public String index(ModelMap model, HttpServletResponse response) {
        System.out.println(response.getStatus());
        return "index";
    }


    @GetMapping("/admin")
    public String adminPage(ModelMap model, Principal principal) {
        model.addAttribute("principal", principal);
        return "admin";
    }

    @GetMapping("/myAccount")
    public String myAccount(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "myAccount";

    }

    @GetMapping("/sales")
    public String sales(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "sales";
    }


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "index";
    }


    @PostMapping("/registration")
    public String signUp(User user, BindingResult bindingResult){

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPassword());
//        senderService.send(user);
        return "index";
    }

    @PostMapping("/createProduct")
    public String createProduct(Products products){

        productsService.save(products);
        return "new";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users";
    }

    @GetMapping("/user-{id}")
    public String user(@PathVariable("id") int idUser, Model model) {
        model.addAttribute("user", userService.findOne(idUser));
        return "simpleUser";
    }


}


//    @GetMapping("/register")
//    public String toRegistration() {
//        return "registration";
//    }

//    @PostMapping("registration")
//    public String register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
//                           @RequestParam("username") String username, @RequestParam("password") String password,
//                           @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,
//                           @RequestParam("avatar") MultipartFile multipartFile
//    ) {
//
//        String path = System.getProperty("user.home") + File.separator + "usersImages\\";
//
//        try {
//            multipartFile.transferTo(new File(path + multipartFile.getOriginalFilename()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setPhoneNumber(phoneNumber);
//        user.setAvatar("\\userAvatar\\" + multipartFile.getOriginalFilename());
//        userService.save(user);
//        senderService.send(user);
//
//        return "index";
//    }
