package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.BadRequestException;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboReactionService;

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
    if (StringUtils.isEmpty(userId)) {
      throw new BadRequestException();
    }
    weiboReactionService.like(weiboId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("cancel-like")
  public ResponseEntity<Void> cancelLike(@PathVariable("weiboId") String weiboId, @RequestHeader(USER_ID) String userId) {
    if (StringUtils.isEmpty(userId)) {
      throw new BadRequestException();
    }
    weiboReactionService.cancelLike(weiboId, userId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
