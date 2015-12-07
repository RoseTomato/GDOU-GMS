package love.drose.gms.controllers;

import love.drose.gms.models.Field;
import love.drose.gms.services.FieldService;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lovedrose on 2015/11/27.
 */
@Controller
@RequestMapping("/fieldHandler")
public class FieldHandler extends BaseHandler {

    Logger logger = LogManager.getLogger(FieldHandler.class);

    @Autowired
    private FieldService fieldService;

    @ResponseBody
    @RequestMapping(value = "/addField", method = RequestMethod.POST)
    public Object addField(Field field) {
        logger.debug("<== [field:" + field + "]");
        try {
            // 设置默认图片
            field.setImage("defaultImage");
            // 设置默认状态
            field.setState("可使用");
            // 保存
            fieldService.save(field);

            result = DWZJsonUtil.closeCurrentAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/listField")
    public ModelAndView listField(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = fieldService.getPageData(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_FIELD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/detailField")
    public ModelAndView detailField(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Field field = fieldService.findById(id);
            modelAndView.addObject("field", field);
            modelAndView.setViewName(TO_DETAILD_FIELD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }
}
