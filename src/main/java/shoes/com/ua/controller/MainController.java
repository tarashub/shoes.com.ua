package shoes.com.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shoes.com.ua.entity.User;
import shoes.com.ua.services.MailSenderService;
import shoes.com.ua.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailSenderService senderService;


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
    public String login(HttpServletRequest request) {

        Iterable<User> col = userService.findAll();

        long count = userService.count();
        System.out.println("count " + count);
        request.setAttribute("userCount", count);
        //model.addAttribute("userCount", count);

        return "login";
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

    @PostMapping("/registration")
    public String saveProduct(User user){

        userService.save(user);

        return "index";
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
