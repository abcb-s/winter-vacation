package com.dgsw.winter.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends CustomException {

    public ArticleNotFoundException(){
        super("해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }


}
