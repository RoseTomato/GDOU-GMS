package love.drose.gms.controllers;

import love.drose.gms.models.*;
import love.drose.gms.services.PocketService;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.FinanceUtil;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lovedrose on 2015/11/27.
 */
@Controller
@RequestMapping("/fieldHandler")
public class FieldHandler extends BaseHandler {

//    Logger logger = LogManager.getLogger(FieldHandler.class);

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

    /**
     * iOS端租借场地
     * @param fieldId － 场地id
     * @param userId － 用户id
     * @param duration － 租借时长
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/useField", method = RequestMethod.POST)
    public Object useField(Integer fieldId, Integer userId, Double duration) {
        logger.debug("<== [fieldId:" + fieldId + ", userId:"+userId+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field field = fieldService.findById(fieldId);
            User user = userService.findById(userId);
            Pocket pocket = pocketService.findByUserId(user.getId());
            // 获取价格
            Double price = field.getFee();
            if (field.getUseType() == "按时") {
                price = price * duration;
            }
            // 判断用户是否有购买力
            if (pocket.getMoney() >= price) {
                String result = useFieldService.useField(field, userId, duration);
                if (result.equals(OK)) {
                    // 扣钱
                    pocket.setMoney(pocket.getMoney() - price);
                    pocketService.update(pocket);

                    // 添加营业额
                    FinanceUtil.setCurDayTurnover(FinanceUtil.getCurDayTurnover() + price);

                    // 场地使用人数增加
                    field.setCurrentNumber(field.getCurrentNumber() + 1);
                    fieldService.update(field);
                    // 运动达人加分
                    sportStarService.increaseScore(user);

                    map.put(RESULT, result);
                } else {
                    map.put(RESULT, FAILURE);
                }
            } else {
                map.put(RESULT, FAILURE);
            }
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    /**
     * 获取租用的场地
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/fetchUseFields")
    public Object fetchUseFields(Integer userId) {
        logger.debug("<== [userId:" + userId + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Field> fields = new ArrayList<Field>();
        try {
            // 获取用户租用的场地
            List<UseField> useFields = useFieldService.findUserUseField(userId);
            if (useFields != null) {
                for (UseField useField : useFields) {
                    fields.add(fieldService.findById(useField.getFieldId()));
                }
            }

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

    @ResponseBody
    @RequestMapping("/fetchUseField")
    public Object fetchUseField(Integer userId, Integer fieldId, int useType) {
        logger.debug("<== [userId:" + userId + ", fieldId:" + fieldId + ", useType:" + useType + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取用户租用的场地
            UseField useField = new UseField();
            useField.setState("有效");
            useField.setUserId(userId);
            useField.setFieldId(fieldId);
            useField = useFieldService.findOne(useField);

            // 算出结束时间
            if (useType == 0) { // 按时
                // 算出加多少分钟
                int min = (int) (useField.getDuration() * 60);
                Calendar end = Calendar.getInstance();
                end.setTime(useField.getStartTime());
                end.add(Calendar.MINUTE, min);

                // 算出剩余时间
                int leftMin = (int) ((end.getTimeInMillis() - new Date().getTime()) / 60000);

                map.put("endTime", end);
                map.put("leftMin", leftMin);
            } else if (useType == 1){ // 按次
                Date endTime = new Date();
                useField.setStartTime(useField.getCreateTime());
                endTime.setTime(useField.getStartTime().getTime() + (long)(24 * 3600000) );
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strEndDate = sdf.format(endTime);
                Date endDate = sdf.parse(strEndDate);

                // 算出剩余时间
                int leftMin = (int) ((endDate.getTime() - new Date().getTime()) / 60000);

                map.put("endTime", endDate);
                map.put("leftMin", leftMin);
            }

            map.put(RESULT, OK);
            map.put(DATA, useField);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    /**
     * 续租场地
     * @param useFieldId
     * @param duration
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/continueUseField", method = RequestMethod.POST)
    public Object continueUseField(Integer useFieldId, Double duration) {
        logger.debug("<== [useFieldId:" + useFieldId + ", duration:" + duration);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取用户租用的场地
            UseField useField = useFieldService.findById(useFieldId);
            // 获取场地信息
            Field field = fieldService.findById(useField.getFieldId());
            // 判断用户购买力
            Boolean canPurchase = pocketService.canPurchaseField(useField.getUserId(), field, duration);
            if (canPurchase) {
                useField.setDuration(useField.getDuration() + duration);


                useFieldService.update(useField);

                //扣钱
                pocketService.cutField(useField.getUserId(), field, duration);
                int min = (int) (useField.getDuration() * 60);
                Calendar end = Calendar.getInstance();
                end.setTime(useField.getStartTime());
                end.add(Calendar.MINUTE, min);

                // 算出剩余时间
                int leftMin = (int) ((end.getTimeInMillis() - new Date().getTime()) / 60000);

                map.put(RESULT, OK);
                map.put("leftMin", leftMin);
            } else {
                map.put(RESULT, FAILURE);
            }

        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    /**
     * 预约场地
     * @param userId
     * @param fieldId
     * @param startTime
     * @param duration
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "appointField", method = RequestMethod.POST)
    public Object appointField(Integer userId, Integer fieldId, String startTime, Double duration) {
        logger.debug("<== [userId:" + userId + ", fieldId:"+fieldId+", startTime:"+startTime+", duration:"+duration+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field field = fieldService.findById(fieldId);
            Boolean result = useFieldService.appointField(userId, field, startTime, duration);

            if (result) {
                // 达人加分
                sportStarService.increaseScore(userService.findById(userId));
                map.put(RESULT, OK);
            } else {
                map.put(RESULT, FAILURE);
            }
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            logger.error("<== [error:"+e.getMessage()+"]");
            e.printStackTrace();
        }

        logger.debug("==>");
        return map;
    }

    /**
     * 获取用户预约的场地
     * @param userId － 用户ID
     * @return
     */
    @ResponseBody
    @RequestMapping("fetchUserAppointField")
    public Object fetchUserAppointField(Integer userId) {
        logger.debug("<== [userId:"+userId+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Field> fields = useFieldService.fecthUserAppointField(userId, fieldService);
            map.put(DATA, fields);
            map.put(RESULT, OK);
        } catch (Exception e) {
            logger.error("==> [error:"+e.getMessage()+"]");
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    public Object addNotice(String description, String dateStr) {
        logger.debug("<== [description:" + description + ", dateStr:" + dateStr + "]");
        try {
            Notice notice = new Notice();
            notice.setDateStr(dateStr);
            notice.setDescription(description);
            notice.setState("有效");

            noticeService.save(notice);

            result = DWZJsonUtil.closeCurrentAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/listNotices")
    public ModelAndView listNotices(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = noticeService.getPageData(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_NOTICE);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 获取场地公告
     * @return
     */
    @ResponseBody
    @RequestMapping("fetchNotice")
    public Object fetchNotice(String num) {
        logger.debug("<== [num:"+num+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Notice> notices = noticeService.findAll();
            map.put(DATA, notices.get(notices.size() - 1));
            map.put(RESULT, OK);
        } catch (Exception e) {
            logger.error("==> [error:"+e.getMessage()+"]");
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/updateField", method =  RequestMethod.POST)
    public Object updateField(Integer id, String name, String description, Double fee, Integer galleryful, Integer currentNumber, String useType, Integer category_id) {
        logger.debug("<== [id:" + id +", name:"+name+", description:"+description+", fee:"+fee+", total:"+galleryful+", currentNumber:"+currentNumber+", useType:"+useType+", category_id:"+category_id+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field field = fieldService.findById(id);
            field.setName(name);
            field.setDescription(description);
            field.setFee(fee);
            field.setGalleryful(galleryful);
            field.setCurrentNumber(currentNumber);
            field.setUseType(useType);
            field.setCategoryId(category_id);

            fieldService.update(field);
            map.put(RESULT, OK);
            DWZJsonUtil.closeCurrentAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/freezeField", method =  RequestMethod.POST)
    public Object updateField(Integer id) {
        logger.debug("<== [id:"+ id +"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field field = fieldService.findById(id);
            field.setState("已停用");

            fieldService.update(field);
            map.put(RESULT, OK);
            DWZJsonUtil.successAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/recoverField", method =  RequestMethod.POST)
    public Object recoverField(Integer id) {
        logger.debug("<== [id:"+ id +"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field field = fieldService.findById(id);
            field.setState("可使用");

            fieldService.update(field);
            map.put(RESULT, OK);
            DWZJsonUtil.successAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }
}
