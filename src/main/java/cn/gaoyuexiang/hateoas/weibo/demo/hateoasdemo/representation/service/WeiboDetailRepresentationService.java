package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.HomePageController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.WeiboDetailController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.WeiboReactionController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.UserInfo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WeiboDetailRepresentationService {

  public RepresentationModel<WeiboDetail> assemble(String weiboId) {
    RepresentationModel<WeiboDetail> model = new RepresentationModel<>();
    model.add(linkTo(methodOn(WeiboDetailController.class).viewDetail(weiboId, null)).withSelfRel());
    model.add(linkTo(methodOn(WeiboDetailController.class).edit(weiboId, null)).withRel("edit"));
    model.add(linkTo(methodOn(WeiboDetailController.class).delete(weiboId)).withRel("delete"));
    return model;
  }

  public WeiboDetail assemble(WeiboDetail model, String userId) {
    model.add(linkTo(methodOn(WeiboDetailController.class).viewDetail(model.getId(), null)).withSelfRel());

    model.addAllIf(
        Objects.equals(userId, model.getOwner().getId()),
        () ->
            asList(linkTo(methodOn(WeiboDetailController.class).edit(model.getId(), null)).withRel("edit"),
                linkTo(methodOn(WeiboDetailController.class).delete(model.getId())).withRel("delete")));

    boolean hasLiked = model.getLikedBy().stream().map(UserInfo::getId).anyMatch(id -> id.equals(userId));
    if (hasLiked) {
      model.add(linkTo(methodOn(WeiboReactionController.class).cancelLike(model.getId(), null)).withRel("cancelLike"));
    } else {
      model.add(linkTo(methodOn(WeiboReactionController.class).like(model.getId(), null)).withRel("like"));
    }
    return model;
  }

  public RepresentationModel<WeiboDetail> assembleDeleted() {
    RepresentationModel<WeiboDetail> model = new RepresentationModel<>();
    model.add(linkTo(methodOn(HomePageController.class).getHomePage(null)).withSelfRel());
    return model;
  }
}
