package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class UserRepository {

  private static final List<User> USERS = asList(
      new User("kunkun", "蔡徐坤", "/weibo/xxx"),
      new User("lbj", "詹姆斯", "/weibo/zzz")
  );

  public User findBy(String id) {
    return USERS.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
  }
}
