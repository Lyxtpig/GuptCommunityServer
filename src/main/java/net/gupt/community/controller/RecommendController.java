package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.PageInfoBean;
import net.gupt.community.entity.Recommend;
import net.gupt.community.entity.Result;
import net.gupt.community.service.RecommendService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>反馈建议控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-29 19:47
 **/
@AuthToken
@RestController
@RequestMapping(value = "/recommend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecommendController {

    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @ResponseBody
    @RequestMapping(value = "/postRecommend", method = RequestMethod.POST)
    public Result postRecommend(@RequestBody Recommend recommend) {
        int sqlResult = recommendService.postRecommend(recommend);
        if (sqlResult == 0) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/getRecommends", method = RequestMethod.GET)
    public Result getRecommends(@RequestParam(value = "pageNum") Integer pageNum,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<Recommend> recommendPageInfo = recommendService.getRecommends(pageNum, pageSize);
        if (recommendPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(recommendPageInfo));
    }

}