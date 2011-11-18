package com.aqua.pingtai.compass.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.gps.CompassGps;

public class QuartzUpdateCompassIndex {
	
	private static final Log log = LogFactory.getLog(QuartzUpdateCompassIndex.class);
	
	private CompassGps compassGps;
	
	/**
	 * 重建索引.
	 * 如果compass实体中定义的索引文件已存在，索引过程中会建立临时索引,
	 * 索引完成后再进行覆盖.
	 */
	public void startCompassUpdateIndex(){
		//System.out.println("Compass开始更新索引...");
		log.info("quartz Compass开始更新索引...");
        long beginTime = System.currentTimeMillis();
        compassGps.index();
        long costTime = System.currentTimeMillis() - beginTime;
        //System.out.println("Compass完成更新索引...");
        //System.out.println("Compass更新索引的时间是:" + costTime + " milliseconds");
        log.info("quartz Compass完成更新索引...");
        log.info("quartz Compass更新索引的时间是:" + costTime + " milliseconds");
	}

	public CompassGps getCompassGps() {
		return compassGps;
	}

	public void setCompassGps(CompassGps compassGps) {
		this.compassGps = compassGps;
	}
	
	
}
