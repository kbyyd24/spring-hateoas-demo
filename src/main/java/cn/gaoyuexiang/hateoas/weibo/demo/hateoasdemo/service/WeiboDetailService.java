package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.EditWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command.PostWeiboCommand;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.NotFoundException;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.User;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.Weibo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository.UserRepository;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository.WeiboRepository;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.UserInfo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model.WeiboDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class WeiboDetailService {

  private final WeiboRepository weiboRepository;
  private final UserRepository userRepository;

  public WeiboDetailService(WeiboRepository weiboRepository, UserRepository userRepository) {
    this.weiboRepository = weiboRepository;
    this.userRepository = userRepository;
  }

  public WeiboDetail viewDetail(String id) {
    Weibo weibo = weiboRepository.findBy(id).orElseThrow(NotFoundException::new);
    User user = userRepository.findBy(weibo.getUserId());
    UserInfo owner = new UserInfo(user.getId(), user.getName(), user.getAvatar());
    List<UserInfo> likedBy = weibo.getLikedBy().stream()
        .map(userRepository::findBy)
        .map(u -> new UserInfo(u.getId(), u.getName(), u.getAvatar()))
        .collect(toList());
    return new WeiboDetail(id, owner, weibo.getContent(), likedBy);
  }

  public void post(PostWeiboCommand command, String userId) {
    String id = UUID.randomUUID().toString();
    Weibo weibo = new Weibo(id, userId, command.getContent());
    weiboRepository.save(weibo);
  }

  public void edit(String id, EditWeiboCommand command) {
    Weibo weibo = weiboRepository.findBy(id).orElseThrow(NotFoundException::new);
    weibo.edit(command.getContent());
    weiboRepository.save(weibo);
  }

  public void delete(String id) {
    weiboRepository.delete(id);
  }
}
