package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.EditWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service.WeiboDetailRepresentationService;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("weibos")
@Slf4j
public class WeiboDetailController {

  private static final String USER_ID = "X-USER-ID";
  private final WeiboDetailService weiboDetailService;
  private final WeiboDetailRepresentationService weiboDetailRepresentationService;

  public WeiboDetailController(WeiboDetailService weiboDetailService, WeiboDetailRepresentationService weiboDetailRepresentationService) {
    this.weiboDetailService = weiboDetailService;
    this.weiboDetailRepresentationService = weiboDetailRepresentationService;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public RepresentationModel<WeiboDetail> post(@RequestBody PostWeiboCommand command, @RequestHeader(USER_ID) String userId) {
    String weiboId = weiboDetailService.post(command, userId);
    return weiboDetailRepresentationService.assemble(weiboId);
  }

  @GetMapping("{weiboId}")
  public WeiboDetail viewDetail(@PathVariable("weiboId") String id) {
    WeiboDetail weiboDetail = weiboDetailService.viewDetail(id);
    return weiboDetailRepresentationService.assemble(weiboDetail);
  }

  @PostMapping("{weiboId}")
  public RepresentationModel<WeiboDetail> edit(@PathVariable("weiboId") String id, @RequestBody EditWeiboCommand command) {
    weiboDetailService.edit(id, command);
    return weiboDetailRepresentationService.assemble(id);
  }

  @DeleteMapping("{weiboId}")
  public RepresentationModel<WeiboDetail> delete(@PathVariable("weiboId") String id) {
    weiboDetailService.delete(id);
    return weiboDetailRepresentationService.assembleDeleted();
  }
}
