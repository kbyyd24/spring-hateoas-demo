package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("weibos")
@Slf4j
public class WeiboDetailController {

  @PostMapping
  @ResponseStatus(CREATED)
  public void post(@RequestBody PostWeiboCommand command) {
    log.info("Received post weibo command, content is \"{}\"", command.getContent());
  }
}
