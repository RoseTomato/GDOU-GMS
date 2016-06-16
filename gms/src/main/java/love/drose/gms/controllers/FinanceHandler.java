package love.drose.gms.controllers;

import love.drose.gms.models.*;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.DateUtil;
import love.drose.gms.utils.FinanceUtil;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by lovedrose on 6/15/16.
 */
@Controller
@RequestMapping("/financeHandler")
public class FinanceHandler extends BaseHandler {

    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public Object recharge(String username, String rechargeMoney) {
        logger.debug("<== [username:" + username + ", rechargeMoney:" + rechargeMoney + "]");

        try {
            // 获取用户
            List<User> users = userService.findAllByProperty("username", username);
            User user = users.get(0);

            Recharge recharge = new Recharge();
            recharge.setUsername(username);
            recharge.setUserId(user.getId());
            recharge.setRechargeMoney(Double.parseDouble(rechargeMoney));
            recharge.setCreateTime(new Date());

            // 更新用户钱包
            Pocket pocket = pocketService.findByUserId(user.getId());
            pocket.setMoney(pocket.getMoney() + Double.parseDouble(rechargeMoney));
            pocketService.update(pocket);

            // 添加营业额
            FinanceUtil.setCurDayTurnover(FinanceUtil.getCurDayTurnover() + Double.parseDouble(rechargeMoney));

            rechargeService.save(recharge);

            result = DWZJsonUtil.successAndCloseCurrent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/listRechargedRecord")
    public ModelAndView listRechargedRecord(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = rechargeService.getPageData(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_RECHARGED_RECORD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/deleteRecord")
    public Object deleteRecord(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            Recharge recharge = rechargeService.findById(id);
            rechargeService.deleteByField("id", recharge.getId());
            result = DWZJsonUtil.successAndRefresh("showRechargedRecords");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping(value = "/paySalary", method = RequestMethod.POST)
    @ResponseBody
    public Object paySalary(String staffNo, String staffName, String realSalary, String shouldSalary) {
        logger.debug("<== [username:" + staffNo + ", staffName:" + staffName + ", realSalary" + realSalary + ", shouldSalary:" + shouldSalary + "]");

        try {
            // 新增记录
            Salary salary = new Salary();
            salary.setStaffNo(staffNo);
            salary.setStaffName(staffName);
            salary.setRealSalary(Double.parseDouble(realSalary));
            salary.setShouldSalary(Double.parseDouble(shouldSalary));
            salary.setCreateTime(new Date());

            salaryService.save(salary);

            result = DWZJsonUtil.successAndRefresh("showSalaryRecords");
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/listSalaryRecord")
    public ModelAndView listSalaryRecord(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = salaryService.getPageData(pageNum, pageSize);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_SALARY_RECORD);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/deleteSalaryRecord")
    public Object deleteSalaryRecord(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            Salary salary = salaryService.findById(id);
            salaryService.deleteByField("id", salary.getId());
            result = DWZJsonUtil.successAndRefresh("showSalaryRecords");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/toShowCurrentDayTurnover")
    public ModelAndView toShowCurrentDayTurnover() {
        logger.debug("<==");
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("curDayTurnover", FinanceUtil.getCurDayTurnover());
            modelAndView.setViewName(TO_SHOW_CURRENT_DAY_TURNOVER);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

}
