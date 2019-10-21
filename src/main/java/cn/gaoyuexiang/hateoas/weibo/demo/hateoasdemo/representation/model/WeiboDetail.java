package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WeiboDetail {
  private String id;
  private UserInfo owner;
  private String content;
  private List<UserInfo> likedBy;
}
