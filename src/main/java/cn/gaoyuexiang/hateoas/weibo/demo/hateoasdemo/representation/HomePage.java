package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation;

import lombok.Value;

import java.util.List;

@Value
public class HomePage {

  private List<WeiboListItem> weiboListItems;
}
