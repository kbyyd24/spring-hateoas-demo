package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.ConflictException;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.NotAllowedException;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Weibo {
  private String id;
  private String userId;
  private String content;
  private Set<String> likedBy = new HashSet<>();

  public Weibo(String id, String userId, String content) {
    this.id = id;
    this.userId = userId;
    this.content = content;
  }

  public void edit(String userId, String content) {
    if (!this.userId.equals(userId)) {
      throw new NotAllowedException();
    }
    this.content = content;
  }

  public void likedBy(String userId) {
    boolean addSuccess = likedBy.add(userId);
    if (!addSuccess) {
      throw new ConflictException();
    }
  }

  public void cancelLikedBy(String userId) {
    boolean removeSuccess = likedBy.remove(userId);
    if (!removeSuccess) {
      throw new ConflictException();
    }
  }
}
