package com.tartyp.otchet.RestCont;

import com.tartyp.otchet.repo.FeignClientRepo;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RequestMapping({"/api"})
public class FeignClientRestTest {
    private final FeignClientRepo feignClientRepo;

    @RequestMapping({"/api1"})
    public String getData() throws IOException, URISyntaxException {
        Response response = feignClientRepo.getDictionary("Auth", new URI("https://arta.tartyp.kz/Synergy/rest/api/dictionaries/otchet_po_ispolnitel_skoi_distsipline_prazdniki?getColumns=false"));
        try {
            JSONObject dictionaryData = null;
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(response.body().asInputStream()))) {
                String resp = buffer.lines().collect(Collectors.joining("\n"));
                dictionaryData = new JSONObject(resp);
                Iterator keys = dictionaryData.getJSONObject("items").keys();
                while (keys.hasNext()) {
                    JSONObject holidaysDict = dictionaryData.getJSONObject("items").getJSONObject((String) keys.next());
                    try {
                        System.out.println(LocalDate.of(holidaysDict.getJSONObject("holiday_year").getInt("value"), holidaysDict.getJSONObject("holiday_month").getInt("value"), holidaysDict.getJSONObject("holiday_day").getInt("value")));
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                }
                //dictionaryData.getJSONObject("items").keys();

                return resp;
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to process response body.", ex);
        }
    }
}
