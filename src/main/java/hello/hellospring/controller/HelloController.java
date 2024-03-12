package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class HelloController {
    //hello.html-localhost:8080/hello
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    //MVC-localhost:8080/hello-mvc?name=namekey
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name="name", required = false) String namevalue , Model model) {
        model.addAttribute("namekey", namevalue);
        return "hello-template";
    }
    //API-문자
    @GetMapping("hello-string")
    @ResponseBody //http 프로토콜의 body부에 직접 넣어주겠다는 것
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    //API-데이터 **주 사용
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
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
