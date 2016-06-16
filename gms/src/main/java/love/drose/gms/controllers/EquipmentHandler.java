package love.drose.gms.controllers;

import love.drose.gms.models.*;
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
 * Created by lovedrose on 1/16/16.
 */
@Controller
@RequestMapping("/equipmentHandler")
public class EquipmentHandler extends BaseHandler {

    /**
     * 转发到添加器材页面
     * @return
     */
    @RequestMapping("/toAddEquipment")
    public ModelAndView toAddEquipment() {
        logger.debug("<==");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取所有二级分类
            List<SecondCategory> secondCategories = secondCategoryService.findAll();
            modelAndView.addObject("secondCategories", secondCategories);
            modelAndView.setViewName(TO_ADD_EQUIPMENT);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 添加器材
     * @param equipment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public Object addEquipment(Equipment equipment) {
        logger.debug("<== [equipment:" + equipment + "]");
        try {
            equipmentService.addEquipment(equipment);
            result = DWZJsonUtil.closeCurrentAndRefresh("showFields");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("system is busy now.");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/fetchEquipmentPageData")
    public Object fetchEquipmentPageData(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
//            Page page = equipmentService.getPageData(pageNum, pageSize);
            Page page = equipmentService.findPageDataWithTotalIsNotNull(pageNum, pageSize);
            List<Equipment> data = page.getList();
            Boolean hasNext = page.hasNextPage();

            map.put(RESULT, OK);
            map.put(DATA, data);
            map.put("hasNext", hasNext);

        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/useEquipments", method = RequestMethod.POST)
    public Object useEquipments(Integer userId, Integer equipmentId, Double duration, Integer number) {
        logger.debug("<== [useId: " + userId + ",equipmentId:" + equipmentId + ", duration:" + duration  +", number:" + number + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Equipment equipment = equipmentService.findById(equipmentId);
            if (pocketService.canPurchaseEquipment(userId, equipment, duration)) {
                useEquipmentService.useEquipments(messageService, equipmentService,userId, equipmentId, duration, number);
                // 扣钱
                pocketService.cutEquipment(userId, equipment, duration , number);

                map.put(RESULT, OK);
            } else {
                map.put(RESULT, FAILURE);
            }

        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }

        logger.debug("==>");
        return map;
    }

    /**
     * 获取单个用户所有租借的器材
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("fetchUseEquipments")
    public Object fetchUseEquipments(Integer userId) {
        logger.debug("<== [userId:" + userId + "]");
        Map<String, Object> map = null;
        try {
            map = new HashMap<String, Object>();
            List<Equipment> equipments = new ArrayList<Equipment>();
            List<UseEquipment> useEquipments = useEquipmentService.findUserUseEquipments(userId);
            if (useEquipments != null) {
                for (UseEquipment useEquipment : useEquipments) {
                    Equipment equipment = equipmentService.findById(useEquipment.getEquipmentId());
                    equipments.add(equipment);
                }
                map.put(RESULT, OK);
                map.put(DATA, equipments);
            }
        } catch (Exception e) {
            logger.debug("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping("/fetchUseEquipment")
    public Object fetchUseEquipment(Integer userId, Integer equipmentId, int useType) {
        logger.debug("<== [userId:" + userId + ", equipmentId:" + equipmentId + ", useType:" + useType + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取用户租用的场地
            UseEquipment useEquipment = new UseEquipment();
            useEquipment.setState("有效");
            useEquipment.setAppoint(0);
            useEquipment.setUserId(userId);
            useEquipment.setEquipmentId(equipmentId);
            useEquipment = useEquipmentService.findOne(useEquipment);

            // 算出结束时间
            if (useType == 0) { // 按时
                // 算出加多少分钟
                int min = (int) (useEquipment.getDuration() * 60);
                Calendar end = Calendar.getInstance();
                end.setTime(useEquipment.getStartTime());
                end.add(Calendar.MINUTE, min);

                // 算出剩余时间
                int leftMin = (int) ((end.getTimeInMillis() - new Date().getTime()) / 60000);

                map.put("endTime", end);
                map.put("leftMin", leftMin);
            } else if (useType == 1){ // 按次
                Date endTime = new Date();
                useEquipment.setStartTime(useEquipment.getCreateTime());
                endTime.setTime(useEquipment.getStartTime().getTime() + (long)(24 * 3600000) );
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String strEndDate = sdf.format(endTime);
                Date endDate = sdf.parse(strEndDate);

                // 算出剩余时间
                int leftMin = (int) ((endDate.getTime() - new Date().getTime()) / 60000);

                map.put("endTime", endDate);
                map.put("leftMin", leftMin);
            }

            map.put(RESULT, OK);
            map.put(DATA, useEquipment);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/continueUseEquipment")
    public Object continueUseEquipment(Integer useEquipmentId, Double duration) {
        logger.debug("<== [useEquipmentId:" + useEquipmentId + ", duration:" + duration);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取用户租用的场地
            UseEquipment useEquipment = useEquipmentService.findById(useEquipmentId);

            useEquipment.setDuration(useEquipment.getDuration() + duration);

            int min = (int) (useEquipment.getDuration() * 60);
            useEquipmentService.update(useEquipment);
            Calendar end = Calendar.getInstance();
            end.setTime(useEquipment.getStartTime());
            end.add(Calendar.MINUTE, min);

            // 算出剩余时间
            int leftMin = (int) ((end.getTimeInMillis() - new Date().getTime()) / 60000);

            map.put(RESULT, OK);
            map.put("leftMin", leftMin);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping("/fetchEquipmentsWithCategoryId")
    public Object fetchEquipmentsWithCategoryId(Integer categoryId) {
        logger.debug("<== [categoryId:" + categoryId +"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(DATA, equipmentService.fetchEquipmentsWithCategoryId(categoryId));
            map.put(RESULT, OK);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @RequestMapping("/listEquipment")
    public ModelAndView listEquipment(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = equipmentService.findPageDataWithTotalIsNotNull(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_EQUIPMENT);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/detailEquipment")
    public ModelAndView detailEquipment(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            Equipment equipment = equipmentService.findById(id);
            SecondCategory secondCategory = secondCategoryService.findById(equipment.getCategoryId());
            List<SecondCategory> secondCategories = secondCategoryService.findAll();

            modelAndView.addObject("secondCategories", secondCategories);
            modelAndView.addObject("equipment", equipment);
            modelAndView.addObject("secondCategory", secondCategory);
            modelAndView.setViewName(TO_DETAILD_EQUIPMENT);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/updateEquipment", method =  RequestMethod.POST)
    public Object updateEquipment(Integer id, String name, String description, Double fee, Integer total, Integer currentNumber, String useType, Integer category_id) {
        logger.debug("<== [id:" + id +", name:"+name+", description:"+description+", fee:"+fee+", total:"+total+", currentNumber:"+currentNumber+", useType:"+useType+", category_id:"+category_id+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
           Equipment equipment = equipmentService.findById(id);
            equipment.setName(name);
            equipment.setDescription(description);
            equipment.setFee(fee);
            equipment.setTotal(total);
            equipment.setCurrentNumber(currentNumber);
            equipment.setUseType(useType);
            equipment.setCategoryId(category_id);

            equipmentService.update(equipment);
            map.put(RESULT, OK);
            DWZJsonUtil.closeCurrentAndRefresh("showEquipment");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/freezeEquipment", method =  RequestMethod.POST)
    public Object freezeEquipment(Integer id) {
        logger.debug("<== [id:"+ id +"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Equipment equipment = equipmentService.findById(id);
            equipment.setState("已停用");

            equipmentService.update(equipment);
            map.put(RESULT, OK);
            DWZJsonUtil.successAndRefresh("showEquipment");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/recoverEquipment", method =  RequestMethod.POST)
    public Object recoverEquipment(Integer id) {
        logger.debug("<== [id:"+ id +"]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Equipment equipment = equipmentService.findById(id);
            equipment.setState("可使用");

            equipmentService.update(equipment);
            map.put(RESULT, OK);
            DWZJsonUtil.successAndRefresh("showEquipment");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }
}


