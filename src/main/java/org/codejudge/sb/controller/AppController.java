package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.entity.Sentiment;
import org.codejudge.sb.model.EvalRequest;
import org.codejudge.sb.serivce.api.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/")
    @ApiOperation("This is the hello world api")
    public String hello() {
        return "Hello World!!";
    }

    @PostMapping("/initiate")
    @ResponseBody
    public Sentiment initiateProcess(@RequestBody EvalRequest request) {
        log.info("Got request to initiate the selenium process for request: {}", request);
        EvalRequest.validate(request);
        return appService.initiate(request);
    }

    @GetMapping("/sentiments")
    @ResponseBody
    public Sentiment getSentiments(@RequestParam Integer id) {
        log.info("Got request to get the sentiments for selenium process via id: {}", id);
        return appService.getSentiments(id);
    }

}
