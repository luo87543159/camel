package com.camel.system.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface SysService {

    @GetMapping(value = "/abc")
    String abc();
}
