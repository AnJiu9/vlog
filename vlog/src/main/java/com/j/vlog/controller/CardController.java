package com.j.vlog.controller;

import com.j.vlog.common.ResponseResult;
import com.j.vlog.common.ResultCode;
import com.j.vlog.model.Card;
import com.j.vlog.utils.DataUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CardController
 * @Description 卡片控制器
 * @Author orange
 * @Date 2020-12-03 14:43
 **/

@RestController
@RequestMapping(value = "api")

public class CardController {

    @GetMapping("cards")
    public ResponseResult getCard() {
        List<Card> cards = DataUtil.initCards();
        ResultCode success = ResultCode.SUCCESS;
        return ResponseResult.success(cards);
    }
}
