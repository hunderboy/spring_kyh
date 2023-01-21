package co.kr.steadysprinting.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") // Get 메소드 url
    @ResponseBody // http 의 body
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // 반환값이 Text 인 경우,템플릿의 html 코드가 아닌 있는 그래도 Text 배출
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // command + shift + enter
        hello.setName(name);
        return hello; // @ResponseBody 를 사용하고, 반환값이 객체인 경우, JSON으로 변환됨 = {"name":"spring!!"}
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }



}
