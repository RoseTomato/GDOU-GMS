package love.drose.gms.controllers;

import love.drose.gms.models.FirstCategory;
import love.drose.gms.models.SecondCategory;
import love.drose.gms.services.FirstCategoryService;
import love.drose.gms.services.SecondCategoryService;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.Page;
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
 * 分类处理器
 * Created by lovedrose on 2015/12/11.
 */
@Controller
@RequestMapping("/categoryHandler")
public class CategoryHandler extends BaseHandler {

    @Autowired
    private FirstCategoryService firstCategoryService;
    @Autowired
    private SecondCategoryService secondCategoryService;

    /**
     * 新增一级分类
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addFirstCategory", method = RequestMethod.POST)
    public Object addFirstCategory(String name) {
        logger.debug("<== [name:" + name + "]");
        try {
            if (name != null && !"".equals(name)) {
                FirstCategory firstCategory = new FirstCategory();
                firstCategory.setName(name);
                firstCategoryService.save(firstCategory);
            }
            result = DWZJsonUtil.closeCurrentAndRefresh("showFirstCategories");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    /**
     * 查看一级分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/listFirstCategory")
    public ModelAndView listFirstCategory(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = firstCategoryService.getPageData(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_FIRST_CATEGORY);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 转至添加二级分类页面
     * @param id - 一级分类id
     * @return
     */
    @RequestMapping("/toAddSecondCategory")
    public ModelAndView toAddSecondCategory(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("firstCategoryId", id);
            modelAndView.setViewName(TO_ADD_SECOND_CATEGORY);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 添加二级分类
     * @param name
     * @param firstCategoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addSecondCategory", method = RequestMethod.POST)
    public Object addSecondCategory(String name, Integer firstCategoryId) {
        logger.debug("<== [name:" + name + "]");
        try {
            if (name != null && !"".equals(name)) {
                SecondCategory secondCategory = new SecondCategory();
                secondCategory.setName(name);
                secondCategory.setParentId(firstCategoryId);
                secondCategoryService.save(secondCategory);
            }
            result = DWZJsonUtil.closeCurrentAndRefresh("showSecondCategories");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping(value = "/listSecondCategory")
    public ModelAndView listSecondCategory(Integer parentId) {
        logger.debug("<== [parentId:" + parentId + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<SecondCategory> secondCategories = secondCategoryService.findAllByProperty("parentId",parentId);
            modelAndView.addObject("secondCategories", secondCategories);
            modelAndView.setViewName(TO_LIST_SECOND_CATEGORY);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteSecondCategory")
    public Object deleteSecondCategory(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            secondCategoryService.deleteById(id);
            result = DWZJsonUtil.successAndRefresh("showSecondCategories");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/fetchFirstCategories")
    public Object fetchFirstCategories() {
        logger.debug("<== ");
        Map<String, Object> map = new HashMap<String, Object>();
        List<FirstCategory> firstCategories = null;
        try {
            firstCategories = firstCategoryService.findAll();
            map.put(RESULT, OK);
            map.put(DATA, firstCategories);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping("/fetchSecondCategories")
    public Object fetchSecondCategories() {
        logger.debug("<== ");
        Map<String, Object> map = new HashMap<String, Object>();
        List<SecondCategory> secondCategories = null;
        try {
            secondCategories = secondCategoryService.findAll();
            map.put(RESULT, OK);
            map.put(DATA, secondCategories);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }
}
