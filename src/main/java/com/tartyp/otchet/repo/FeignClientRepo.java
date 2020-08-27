package com.tartyp.otchet.repo;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

@FeignClient(value = "dictionary", url = "https://arta.tartyp.kz/Synergy")
public interface FeignClientRepo {

    @GetMapping("")
    Response getDictionary(@RequestHeader("Authorization") String Token, URI baseUrl);
}
