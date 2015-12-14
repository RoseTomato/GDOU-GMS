package love.drose.gms.controllers;

import love.drose.gms.models.Field;
import love.drose.gms.models.SecondCategory;
import love.drose.gms.services.FieldService;
import love.drose.gms.services.SecondCategoryService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovedrose on 2015/11/27.
 */
@Controller
@RequestMapping("/fieldHandler")
public class FieldHandler extends BaseHandler {

    Logger logger = LogManager.getLogger(FieldHandler.class);

    @Autowired
    private FieldService fieldService;

    @Autowired
    private SecondCategoryService secondCategoryService;

    @RequestMapping("/toAddField")
    public ModelAndView toAddField() {
        logger.debug("<==");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取所有二级分类
            List<SecondCategory> secondCategories = secondCategoryService.findAll();
            modelAndView.addObject("secondCategories", secondCategories);
            modelAndView.setViewName(TO_ADD_FIELD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addField", method = RequestMethod.POST)
    public Object addField(Field field) {
        logger.debug("<== [field:" + field + "]");
        try {
            // 设置默认图片
            field.setImage("defaultImage");
            // 设置默认状态
            field.setState("可使用");
            // 默认当前使用人数0
            field.setCurrentNumber(0);
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
            Field field = fieldService.findById(id);
            SecondCategory secondCategory = secondCategoryService.findById(field.getCategoryId());
            List<SecondCategory> secondCategories = secondCategoryService.findAll();

            modelAndView.addObject("secondCategories", secondCategories);
            modelAndView.addObject("field", field);
            modelAndView.addObject("secondCategory", secondCategory);
            modelAndView.setViewName(TO_DETAILD_FIELD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * iOS客户端获取场地分页数据
     * @param pageNum - 当前页码
     * @param pageSize - 页面大小
     * @return
     */
    @ResponseBody
    @RequestMapping("/fetchFieldPageData")
    public Object fetchFieldPageData(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean hasNext = false;
        List<Field> fields = null;
        try {
            Page page = fieldService.getPageData(pageNum, pageSize);
            fields = page.getList();
            hasNext = page.hasNextPage();

            map.put(RESULT, OK);
            map.put(DATA, fields);
            map.put("hasNext", hasNext);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping("/fetchFields")
    public Object fetchFields(Integer categoryId) {
        logger.debug("<== [categoryId:" + categoryId + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Field> fields = null;
        try {
            fields = fieldService.findAllByProperty("categoryId", categoryId);
            map.put(RESULT, OK);
            map.put(DATA, fields);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }
}
