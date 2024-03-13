package com.chan.ssb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TeamController {

    // Team: id, name, city, championships
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/team")
    public List<Team> retrieveAllTeams() {
        return Arrays.asList(
                new Team(counter.incrementAndGet(), "Lakers", "Los Angeles", 17),
                new Team(counter.incrementAndGet(), "Celtics", "Boston", 17),
                new Team(counter.incrementAndGet(), "Warriors", "Golden State", 6)
        );
    }
}
