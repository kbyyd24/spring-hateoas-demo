package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboReactionService;
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
  public void like(@PathVariable("weiboId") String weiboId, @RequestHeader(USER_ID) String userId) {
    weiboReactionService.like(weiboId, userId);
  }

  @PostMapping("cancel-like")
  public void cancelLike(@PathVariable("weiboId") String weiboId, @RequestHeader(USER_ID) String userId) {
    weiboReactionService.cancelLike(weiboId, userId);
  }
}
