package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.service;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception.NotFoundException;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.Weibo;
import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository.WeiboRepository;
import org.springframework.stereotype.Service;

@Service
public class WeiboReactionService {
  private final WeiboRepository weiboRepository;

  public WeiboReactionService(WeiboRepository weiboRepository) {
    this.weiboRepository = weiboRepository;
  }

  public void like(String weiboId, String userId) {
    Weibo weibo = weiboRepository.findBy(weiboId).orElseThrow(NotFoundException::new);
    weibo.likedBy(userId);
  }

  public void cancelLike(String weiboId, String userId) {
    Weibo weibo = weiboRepository.findBy(weiboId).orElseThrow(NotFoundException::new);
    weibo.cancelLikedBy(userId);
  }
}
