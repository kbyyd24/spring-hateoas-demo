package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.controller;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.HomePage;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomePageController {
  private final HomePageService homePageService;

  @Autowired
  public HomePageController(HomePageService homePageService) {
    this.homePageService = homePageService;
  }

  @GetMapping
  public HomePage getHomePage() {
    return homePageService.getHomePage();
  }
}
