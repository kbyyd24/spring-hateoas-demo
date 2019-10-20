package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation;

import lombok.Value;

@Value
public class User {
  private String id;
  private String name;
  private String avatar;
}
