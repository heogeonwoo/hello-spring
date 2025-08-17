package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠 방식: HTML 파일을 그대로 반환
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // mvc 방식: view를 템플릿 엔진으로 렌더링한 가공된 HTML 파일을 반환
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody 문자 반환을 사용한 API 방식: 리턴값이 문자인 경우 데이터를 그대로 반환
    @GetMapping("hello-string")
    @ResponseBody // http body에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
    }

    // @ResponseBody 객체 반환을 시용한 API 방식: 리턴값이 객체인 경우 json 방식으로 데이터를 가공하여 반환
    @GetMapping("hello-api")
    @ResponseBody // http body에 문자 내용을 직접 반환, HttpMessageConverter 사용
    public Hello helloApi(@RequestParam("name") String name) {
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