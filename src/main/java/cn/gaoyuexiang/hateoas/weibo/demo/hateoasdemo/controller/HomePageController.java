package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.HomePage;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.service.HomePageRepresentationService;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomePageController {
  private static final String USER_ID = "X-USER-ID";
  private final HomePageService homePageService;
  private final HomePageRepresentationService homePageRepresentationService;

  @Autowired
  public HomePageController(HomePageService homePageService, HomePageRepresentationService homePageRepresentationService) {
    this.homePageService = homePageService;
    this.homePageRepresentationService = homePageRepresentationService;
  }

  @GetMapping
  public HomePage getHomePage(@RequestHeader(value = USER_ID, required = false) String userId) {
    HomePage homePage = homePageService.getHomePage();
    return homePageRepresentationService.assemble(homePage, userId);
  }
}
