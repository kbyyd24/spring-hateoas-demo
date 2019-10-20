package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EditWeiboCommand {
  private String content;

  @JsonCreator
  public EditWeiboCommand(@JsonProperty("content") String content) {
    this.content = content;
  }
}
