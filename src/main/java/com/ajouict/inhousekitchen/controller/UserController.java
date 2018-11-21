package com.ajouict.inhousekitchen.controller;

import com.ajouict.inhousekitchen.domain.User;
import com.ajouict.inhousekitchen.service.UserService;
import com.ajouict.inhousekitchen.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger log=LoggerFactory.getLogger(UserController.class);
    private final StorageService storageService;/*파일 업로드하는 API*/

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/loginForm")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/regForm")
    public String regForm(){
        return "/user/regForm";
    }

    //원본 회원가입 메서드(프로필 사진 업로드 기능 없는 것)
    /*@PostMapping("/register")
    public String registerMember(User user){
        userService.registerMember(user);
        return "redirect:/";
    }*/

    /*
    작성자 : 강정호
    날짜 : 2018-10-02
    내용: 프로필 사진 업로드 기능 추가한 회원가입 기능*/
    @PostMapping("/register")
    public String registerMember(User user, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        storageService.store(file);//storageService를 통해서 파일을 업로드 하는 메서드
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        user.setProfilePhoto(file.getOriginalFilename());//User의 profilePhoto에 파일이름을 저장.
        userService.registerMember(user);
        return "redirect:/";
    }

    /*@PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }*/

    @GetMapping("/checkId")
    @ResponseBody
    public String checkId(String id){
        log.info("Check User id : "+id);
        String data=userService.checkId(id);
        return data;
    }

    @PostMapping("/login")
    public String login(String userId, String password, String latitude, String longitude, HttpSession session){
        log.info("[/users/login] User latitude : "+latitude);
        log.info("[/users/login] User longitude : "+longitude);
        log.info("[/users/login] User Id: "+userId);
        log.info("[/users/login] User password : "+password);

        User user=userService.findById(userId);
        if(user==null){
            log.info("Login fail - no member exist");
            return "redirect:/";
        }

        if(!user.matchPassword(password)){
            log.info("Login fail - not correct password");
            return "redirect:/";
        }

        log.info("Login Success");
        user.setLatitude(Double.parseDouble(latitude));
        user.setLongitude(Double.parseDouble(longitude));
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY,user);

        return "redirect:/";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("/getSession")
    @ResponseBody
    public User getSessionedUser(HttpSession session){
        return (User)HttpSessionUtils.getUserFromSession(session);
    }






}
