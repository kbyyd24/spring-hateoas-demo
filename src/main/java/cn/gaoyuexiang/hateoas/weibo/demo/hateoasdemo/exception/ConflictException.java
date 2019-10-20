package cn.gaoyuexiang.hateoas.weibo.demo.hateoasdemo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class ConflictException extends RuntimeException {
}
