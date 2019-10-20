package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation;

import lombok.Value;

@Value
public class WeiboDetail {
  private String id;
  private User owner;
  private String content;
}
