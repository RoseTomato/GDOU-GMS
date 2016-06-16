package love.drose.gms.services.impl;

import love.drose.gms.mappers.ActivityMapper;
import love.drose.gms.models.Activity;
import love.drose.gms.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lovedrose on 4/8/16.
 */
@Service("activityService")
public class ActivityServiceImpl extends BaseService<Activity> implements ActivityService {
}
