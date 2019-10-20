package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation;

import lombok.Value;

@Value
public class WeiboListItem {
  private String id;
  private User owner;
  private String content;
}
