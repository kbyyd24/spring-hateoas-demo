package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
  private String id;
  private String name;
  private String avatar;
}
