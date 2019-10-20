package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.HomePage;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.User;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.WeiboListItem;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
public class HomePageService {

  private static final String WEIBO_CONTENT = "#洞见# 《真正的敏捷工作流 —— GitHub flow》" +
      "作为“敏捷”的固有属性，持续集成并不仅限于特定的模式，不同的项目可能遵循不同的实践，形式多样，效果可能也参差不齐。" +
      "为了解决这些问题，一些 Workflow 的通用模式被提出，而本文的主角，就是其中的天之骄子 —— GitHub flow。";

  public HomePage getHomePage() {
    User poster = new User("userId", "ThoughtWorks", "/weibo/xxx");
    return new HomePage(singletonList(new WeiboListItem("weiboId", poster, WEIBO_CONTENT)));
  }
}
