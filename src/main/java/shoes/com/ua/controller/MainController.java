package shoes.com.ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shoes.com.ua.entity.User;
import shoes.com.ua.services.MailSenderService;
import shoes.com.ua.services.UserServise;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserServise userServise;
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
      public String sales (Model model, Principal principal){
      model.addAttribute("principal", principal);
          return "sales";
      }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/register")
    public String toRegistration() {
        return "registration";
    }

    @PostMapping("registration")
    public String register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                           @RequestParam("username") String username, @RequestParam("password") String password,
                           @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("avatar") MultipartFile multipartFile
                          ) {

        String path=System.getProperty("user.home") + File.separator+"usersImages\\";

        try {
            multipartFile.transferTo(new File(path+multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAvatar("\\userAvatar\\" +multipartFile.getOriginalFilename());
        userServise.save(user);
        senderService.send(user);

        return "index";
    }


    @GetMapping("/users")
        public String users (Model model) {
        model.addAttribute("users", userServise.findAll());

            return "users";
        }
        @GetMapping("/user-{id}")
            public String user (@PathVariable("id") int idUser,Model model) {
            model.addAttribute("user",userServise.findOne(idUser) );
                return "simpleUser";
            }


}
