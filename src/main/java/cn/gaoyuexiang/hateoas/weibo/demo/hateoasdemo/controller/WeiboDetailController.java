package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.EditWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.BadRequestException;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service.WeiboDetailRepresentationService;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.WeiboDetailService;

@RestController
@RequestMapping("weibos")
public class WeiboDetailController {

  private static final String USER_ID = "X-USER-ID";
  private final WeiboDetailService weiboDetailService;
  private final WeiboDetailRepresentationService weiboDetailRepresentationService;

  public WeiboDetailController(WeiboDetailService weiboDetailService,
      WeiboDetailRepresentationService weiboDetailRepresentationService) {
    this.weiboDetailService = weiboDetailService;
    this.weiboDetailRepresentationService = weiboDetailRepresentationService;
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public RepresentationModel<WeiboDetail> post(@RequestBody PostWeiboCommand command,
      @RequestHeader(USER_ID) String userId) {
    if (StringUtils.isEmpty(userId)) {
      throw new BadRequestException();
    }
    String weiboId = weiboDetailService.post(command, userId);
    return weiboDetailRepresentationService.assemble(weiboId);
  }

  @GetMapping("{weiboId}")
  public WeiboDetail viewDetail(@PathVariable("weiboId") String id,
      @RequestHeader(value = USER_ID, required = false) String userId) {
    WeiboDetail weiboDetail = weiboDetailService.viewDetail(id);
    return weiboDetailRepresentationService.assemble(weiboDetail, userId);
  }

  @PostMapping("{weiboId}")
  public RepresentationModel<WeiboDetail> edit(@PathVariable("weiboId") String id,
      @RequestBody EditWeiboCommand command, @RequestHeader(USER_ID) String userId) {
    weiboDetailService.edit(id, userId, command);
    return weiboDetailRepresentationService.assemble(id);
  }

  @DeleteMapping("{weiboId}")
  public RepresentationModel<WeiboDetail> delete(@PathVariable("weiboId") String id, @RequestHeader(USER_ID) String userId) {
    weiboDetailService.delete(id, userId);
    return weiboDetailRepresentationService.assembleDeleted();
  }
}
