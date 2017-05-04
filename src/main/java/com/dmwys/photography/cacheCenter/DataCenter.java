package com.dmwys.photography.cacheCenter;

import com.dmwys.photography.services.PhotoService;
import org.nutz.mvc.Mvcs;

public class DataCenter {
	private static PhotoService photoService  = Mvcs.getIoc().get(PhotoService.class, "photoService");
	private static int DAY = 24 * 60 * 60;
 /*   public static String getColumnName(Long column_id) {
        final String key = "columnInfo:column_id:";
        String result = RedisUtil.getItem(key + column_id);
        if(StringUtils.isBlank(result)){
            TVStationColumn tvStationColumn = kytvAdminService.find(column_id, TVStationColumn.class);
            if(tvStationColumn != null){
                result = tvStationColumn.getCd_name();
                RedisUtil.setValue(key + column_id, result);
                return result;
            }
        }
        return result;
    }*/
}
