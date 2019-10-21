package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.HomePageController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.WeiboDetailController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.WeiboReactionController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WeiboDetailRepresentationService {

  public RepresentationModel<WeiboDetail> assemble(String weiboId) {
    RepresentationModel<WeiboDetail> model = new RepresentationModel<>();
    model.add(linkTo(methodOn(WeiboDetailController.class).viewDetail(weiboId)).withSelfRel());
    model.add(linkTo(methodOn(WeiboDetailController.class).edit(weiboId, null)).withRel("edit"));
    model.add(linkTo(methodOn(WeiboDetailController.class).delete(weiboId)).withRel("delete"));
    return model;
  }

  public WeiboDetail assemble(WeiboDetail model) {
    model.add(linkTo(methodOn(WeiboDetailController.class).viewDetail(model.getId())).withSelfRel());
    model.add(linkTo(methodOn(WeiboDetailController.class).edit(model.getId(), null)).withRel("edit"));
    model.add(linkTo(methodOn(WeiboDetailController.class).delete(model.getId())).withRel("delete"));
    model.add(linkTo(methodOn(WeiboReactionController.class).like(model.getId(), null)).withRel("like"));
    model.add(linkTo(methodOn(WeiboReactionController.class).cancelLike(model.getId(), null)).withRel("cancelLike"));
    return model;
  }

  public RepresentationModel<WeiboDetail> assembleDeleted() {
    RepresentationModel<WeiboDetail> model = new RepresentationModel<>();
    model.add(linkTo(methodOn(HomePageController.class).getHomePage()).withSelfRel());
    return model;
  }
}
