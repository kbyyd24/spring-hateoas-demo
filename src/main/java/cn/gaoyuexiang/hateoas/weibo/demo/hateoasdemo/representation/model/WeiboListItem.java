package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Value
public class WeiboListItem extends RepresentationModel<WeiboListItem> {
  private String id;
  private UserInfo owner;
  private String content;
  private int likedCount;
}
