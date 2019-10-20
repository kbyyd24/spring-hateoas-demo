package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.repository;

import cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.model.Weibo;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class WeiboRepository {
  private final Map<String, Weibo> weibos = new HashMap<>();

  public void save(Weibo weibo) {
    weibos.put(weibo.getId(), weibo);
  }

  public Optional<Weibo> findBy(String id) {
    return Optional.ofNullable(weibos.get(id));
  }

  public void delete(String id) {
    weibos.remove(id);
  }

  public Collection<Weibo> findAll() {
    return weibos.values();
  }
}
