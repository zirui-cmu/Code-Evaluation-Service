package edu.cmu.cs.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.cs.cloud.model.HeartbeatResponse;

@RestController
public class HeartbeatController {
    @RequestMapping(value={"/", "/heartbeat"})
    @ResponseBody
    public HeartbeatResponse heartbeat() {
        return new HeartbeatResponse();
    }
}
