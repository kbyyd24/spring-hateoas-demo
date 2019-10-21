package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.EditWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("weibos")
@Slf4j
public class WeiboDetailController {

  private static final String USER_ID = "X-USER-ID";
  private final WeiboDetailService weiboDetailService;

  public WeiboDetailController(WeiboDetailService weiboDetailService) {
    this.weiboDetailService = weiboDetailService;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public void post(@RequestBody PostWeiboCommand command, @RequestHeader(USER_ID) String userId) {
    weiboDetailService.post(command, userId);
  }

  @GetMapping("{weiboId}")
  public WeiboDetail viewDetail(@PathVariable("weiboId") String id) {
    return weiboDetailService.viewDetail(id);
  }

  @PostMapping("{weiboId}")
  public void edit(@PathVariable("weiboId") String id, @RequestBody EditWeiboCommand command) {
    weiboDetailService.edit(id, command);
  }

  @DeleteMapping("{weiboId}")
  public void delete(@PathVariable("weiboId") String id) {
    weiboDetailService.delete(id);
  }
}
