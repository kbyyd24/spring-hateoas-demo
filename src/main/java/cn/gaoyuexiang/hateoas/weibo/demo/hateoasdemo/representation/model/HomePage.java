package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@Getter
public class HomePage extends RepresentationModel<HomePage> {

  private List<WeiboListItem> weiboListItems;
}
