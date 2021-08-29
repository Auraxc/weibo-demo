package web23.web18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.model.MapperAuth;
import web23.web18.model.User;
import web23.web18.service.ServiceProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class ControllerProfile {
    private MapperAuth mapperAuth;
    ServiceProfile serviceProfile;

    ControllerProfile(ServiceProfile serviceProfile, MapperAuth mapperAuth) {
        this.mapperAuth = mapperAuth;
        this.serviceProfile = serviceProfile;
    }

    @GetMapping("/profile")
    public ModelAndView profileView(HttpServletRequest request) {
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("name", user.username);
        modelAndView.addObject("avator", user.avator);
        return modelAndView;
    }

    @PostMapping("/avator/add")
    public ModelAndView avatorAdd(MultipartFile file, HttpServletRequest request) {
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        String filename = file.getOriginalFilename();
        Path path = Path.of("avator", filename);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.serviceProfile.add(filename, user.id);
        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/avator")
    public void avatorFile(HttpServletResponse response, String file) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of("avator", file));
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        //关闭响应输出流
        out.close();
    }
}
