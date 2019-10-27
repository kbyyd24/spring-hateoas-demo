package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class UserRepository {

  private static final List<User> USERS = asList(
      new User("kunkun", "蔡徐坤", "/weibo/ccc"),
      new User("jay", "周杰伦", "/weibo/zzz"),
      new User("ming", "姚明", "/weibo/yyy")
  );

  public User findBy(String id) {
    return USERS.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
  }
}
