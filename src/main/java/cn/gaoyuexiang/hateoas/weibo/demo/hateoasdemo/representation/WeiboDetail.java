package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation;

import lombok.Value;

import java.util.List;

@Value
public class WeiboDetail {
  private String id;
  private UserInfo owner;
  private String content;
  private List<UserInfo> likedBy;
}
