package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.entity.Sentiment;
import org.codejudge.sb.entity.SentimentV2;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.EvalRequest;
import org.codejudge.sb.serivce.api.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Sentiment> initiateProcess(@RequestBody EvalRequest request) throws CustomException {
        log.info("Got request to initiate the selenium process for request: {}", request);
        return new ResponseEntity<>(appService.initiate(request), HttpStatus.CREATED);
    }

    @GetMapping("/sentiments")
    @ResponseBody
    public ResponseEntity<Sentiment> getSentiments(@RequestParam Integer id) throws CustomException{
        log.info("Got request to get the sentiments for selenium process via id: {}", id);
        return new ResponseEntity<>(appService.getSentiments(id), HttpStatus.OK);
    }

    @PostMapping("/scrape-sentiments")
    @ResponseBody
    public ResponseEntity<SentimentV2> initiateProcessV2(@RequestParam String url) throws CustomException {
        log.info("Got request to initiate the selenium process for url: {}", url);
        return new ResponseEntity<>(appService.initiateV2(url), HttpStatus.CREATED);
    }

}
