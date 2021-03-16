package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model){
        model.addAttribute("users",userService.index());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }
    @PostMapping("/admin/add")
    public String add(@ModelAttribute User user, @RequestParam(value = "role") Long[] roles){
        userService.save(user,roles);
        return "redirect:/admin";

    }
    @PostMapping("/admin/delete")
    public String delete(@ModelAttribute("id") Long id){
        if (userService.getUserById(id) == null){
            return "redirect:/admin";
        } else {
            userService.delete(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String updateUser(@ModelAttribute("id") Long id,Model model){
        if (userService.getUserById(id) == null){
            return "redirect:/admin";
        } else {
            User user = userService.getUserById(id);
            model.addAttribute("user",user);
            model.addAttribute("roles",roleService.getRoles());
        }
        return "update";
    }


    @PostMapping("/admin/update")
    public String update(@ModelAttribute User user,
                         @RequestParam(value = "role",required = false) Long[] rolesID){

        userService.update(user,rolesID);
        return "redirect:/admin";
    }


}
