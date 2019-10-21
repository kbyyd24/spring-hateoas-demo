package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weibos/{weiboId}")
public class WeiboReactionController {
  private static final String USER_ID = "X-USER-ID";
  private final WeiboReactionService weiboReactionService;

  public WeiboReactionController(WeiboReactionService weiboReactionService) {
    this.weiboReactionService = weiboReactionService;
  }

  @PostMapping("like")
  public ResponseEntity<Void> like(@PathVariable("weiboId") String weiboId, @RequestHeader(USER_ID) String userId) {
    weiboReactionService.like(weiboId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("cancel-like")
  public ResponseEntity<Void> cancelLike(@PathVariable("weiboId") String weiboId, @RequestHeader(USER_ID) String userId) {
    weiboReactionService.cancelLike(weiboId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
