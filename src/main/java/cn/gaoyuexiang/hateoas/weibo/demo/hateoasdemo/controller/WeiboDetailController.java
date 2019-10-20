package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.EditWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.WeiboDetail;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("weibos")
@Slf4j
public class WeiboDetailController {

  private final WeiboDetailService weiboDetailService;

  public WeiboDetailController(WeiboDetailService weiboDetailService) {
    this.weiboDetailService = weiboDetailService;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public void post(@RequestBody PostWeiboCommand command) {
    log.info("Received post weibo command, content is [{}]", command.getContent());
  }

  @GetMapping("{weiboId}")
  public WeiboDetail viewDetail(@PathVariable("weiboId") String id) {
    return weiboDetailService.viewDetail(id);
  }

  @PostMapping("{weiboId}")
  public void edit(@PathVariable("weiboId") String id, @RequestBody EditWeiboCommand command) {
    log.info("Received edit weibo command, id is [{}], content is [{}]", id, command.getContent());
  }

  @DeleteMapping("{weiboId}")
  public void delete(@PathVariable("weiboId") String id) {
    log.info("Received delete weibo command, id is [{}]", id);
  }
}
