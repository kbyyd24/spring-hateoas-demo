package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.HomePageController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller.WeiboDetailController;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.HomePage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class HomePageRepresentationService {
  public HomePage assemble(HomePage homePage, String userId) {
    homePage.getWeiboListItems()
        .forEach(item -> item.add(linkTo(methodOn(WeiboDetailController.class).viewDetail(item.getId(), null)).withSelfRel()));
    homePage.add(linkTo(methodOn(HomePageController.class).getHomePage(userId)).withSelfRel());
    homePage.addIf(!StringUtils.isEmpty(userId), () -> linkTo(methodOn(WeiboDetailController.class).post(null, null)).withRel("postWeibo"));
    return homePage;
  }
}
