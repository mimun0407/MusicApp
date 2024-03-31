package org.example.musicapp.controller;

import jakarta.servlet.http.HttpSession;
import org.example.musicapp.model.Song;
import org.example.musicapp.model.User;
import org.example.musicapp.service.ISongService;
import org.example.musicapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    ISongService songService;
    public static String UPLOAD_DIRECTORY = "/home/dang/Test111111111111111111/MusicApp/src/main/resources/static/";
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_DIRECTORY+fileName));
        return fileName;
    }

    @GetMapping("/registerF")
    public ModelAndView registerF(){
        ModelAndView modelAndView=new ModelAndView("register");
        modelAndView.addObject("newUser",new User());
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView register(User user,@RequestParam(value = "image") MultipartFile image) throws IOException {
        ModelAndView modelAndView=new ModelAndView("register");
        user.setAvatar(uploadImage(image));
        List<User>list =userService.findUserName(user.getUserName());
        int length=list.size();
        if (length>0){
            modelAndView.addObject("newUser",new User());
            modelAndView.addObject("msg","da co tai khoan nay");
            return modelAndView;
        }
        userService.createUser(user);
        modelAndView.addObject("msg","success");
        modelAndView.addObject("newUser",new User());
        return modelAndView;
    }
    @GetMapping("/loginF")
    public ModelAndView loginF(){
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@PageableDefault(value = 6)Pageable pageable, @RequestParam(value = "username")String username, @RequestParam(value = "password")String password, HttpSession session){

        User user=userService.logIn(username,password);
        session.setAttribute("sessionUser",user);
        User user1= (User) session.getAttribute("sessionUser");
        ModelAndView modelAndView;
        if (user==null){
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("msg","login fail");
        }
        else {
            modelAndView = new ModelAndView("home");
            modelAndView.addObject("User",user1);
            modelAndView.addObject("allSong",songService.viewAllSong(pageable));
        }
        return modelAndView;
    }

}
