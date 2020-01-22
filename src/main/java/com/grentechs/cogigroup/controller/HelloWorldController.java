package com.grentechs.cogigroup.controller;

import com.grentechs.cogigroup.entities.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private final ResourceBundleMessageSource messageSource;

    @Autowired
    public HelloWorldController(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "hello World";
    }

    @GetMapping(path = "/helloworld-bean")
    public UserDetails getUserDetails() {
        return new UserDetails("Ousmane", "NGOM", "PARIS");
    }

    @GetMapping(path = "/hello-int")
    public String getMessagesInI18NFormat(@RequestHeader(value = "Accept-Language", required = false) String locale) {
        return messageSource.getMessage("label.hello", null, "Default", new Locale(locale));
    }

    @GetMapping(path = "/hello-france")
    public String GetMessagesInI18NFormat2() {
        return messageSource.getMessage("label.hello", null, "Default", LocaleContextHolder.getLocale());
    }
}
