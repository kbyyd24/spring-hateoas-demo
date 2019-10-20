package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.User;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.representation.WeiboDetail;
import org.springframework.stereotype.Service;

@Service
public class WeiboDetailService {
  public WeiboDetail viewDetail(String id) {
    return new WeiboDetail(id, new User("userId", "蔡徐坤", "/weibo/xxx"), "解约 NBA");
  }
}
