package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Weibo {
  private String id;
  private String userId;
  private String content;

  public void edit(String content) {
    this.content = content;
  }
}
