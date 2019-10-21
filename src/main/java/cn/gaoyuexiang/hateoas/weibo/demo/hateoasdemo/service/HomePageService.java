package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.User;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.Weibo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository.UserRepository;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository.WeiboRepository;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.HomePage;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.UserInfo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboListItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class HomePageService {

  private final WeiboRepository weiboRepository;
  private final UserRepository userRepository;

  public HomePageService(WeiboRepository weiboRepository, UserRepository userRepository) {
    this.weiboRepository = weiboRepository;
    this.userRepository = userRepository;
  }

  public HomePage getHomePage() {
    Collection<Weibo> weibos = weiboRepository.findAll();
    Map<String, User> users = weibos.stream()
        .map(Weibo::getUserId)
        .map(userRepository::findBy)
        .collect(toMap(User::getId, identity()));

    List<WeiboListItem> weiboListItems = weibos.stream()
        .map(weibo -> {
          User user = users.get(weibo.getUserId());
          UserInfo owner = new UserInfo(user.getId(), user.getName(), user.getAvatar());
          return new WeiboListItem(weibo.getId(), owner, weibo.getContent(), weibo.getLikedBy().size());
        })
        .collect(toList());

    return new HomePage(weiboListItems);
  }
}
