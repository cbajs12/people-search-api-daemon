package crawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigNew;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ThreadManager{
    public static ThreadManager instance;
    public static ScheduledExecutorService scheduler;
    public static int taskStatus = 0;
    private static Logger logger = LoggerFactory.getLogger(ThreadManager.class);

    public static synchronized ThreadManager getInstance() {
        if (instance == null) {
            logger.debug(" ::::::: Daemon START :::::: ");
            instance = new ThreadManager();
        }
        return instance;
    }

    public static synchronized ScheduledExecutorService getServiceInstance(){
        if (scheduler == null) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
        }
        return scheduler;
    }

    public static void start(){
        if(scheduler != null ) {
            stop();
        }

        getServiceInstance();

        synchronized (getServiceInstance()) {
            scheduler.scheduleAtFixedRate(new MainThread(), 1, ConfigNew.getBatchInterval(), TimeUnit.SECONDS);
        }

        taskStatus = 1;
    }

    public static void stop(){
        getServiceInstance();
        scheduler.shutdown();
        while (!scheduler.isTerminated()) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        taskStatus  = 4;
        scheduler = null;
    }

    public static boolean threadCheck(){
        if(scheduler == null){
            return false;
        }
        return true;
    }


    public static void taskWait(){
        try {

            synchronized (getServiceInstance()) {
                scheduler.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskStatus = 2;
    }

    public static void taskNotify(){

        try {
            synchronized (getServiceInstance()) {
                scheduler.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        taskStatus  = 3;
    }

//    public static void main(String [] args){
//        ThreadManager.start();
//    }
}

//class WorkThread  extends Thread{
//private static Logger logger = LoggerFactory.getLogger(ThreadWork.class);
//    private DataVO dataVO = new DataVO();
//    private CrawlerService crawelerService = new CrawlerService();
//
//    public WorkThread(){ }
//
//    public WorkThread(DataVO vo){dataVO = vo; }
//
//    public void run() {
//        try {
//            /**
//             * DB 저장 Class
//             */
//
//            crawelerService.startService(dataVO);
//
//            logger.debug("Free  : "+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Max   : "+ Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Total : "+ Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Use   : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");
////			Thread.sleep(5000L);
//        } catch (Exception e) {
//            Thread.currentThread().interrupt();
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}


class MainThread  implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(MainThread.class);

    public MainThread(){ }

    public void run() {
        try {
//            List<DataVO> list = CrawlerDao.getStatusDataList();
//            if(list != null || list.size() == 0){
//                if(CrawlerService.threadFlag ){
//                    logger.debug(" ::::::: Daemon Main Process Call !! ::::::["+CrawlerService.threadFlag+"]");
//                    for(DataVO vo : list){
//                        logger.debug("::test data ::" + vo.getCrawler_seq());
//                        Thread thread = new ThreadWork(vo);
//                        thread.run();	//flag check
//                        logger.debug("status::"+thread.getState());
//                        Thread.sleep(5000);
//                    }
//                } else {
//                    logger.debug(" ::::::: Daemon Process Pass !! ::::::["+CrawlerService.threadFlag+"]");
//                }
//            }
            logger.debug("Free  : "+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB ");
            logger.debug("Max   : "+ Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB ");
            logger.debug("Total : "+ Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB ");
            logger.debug("Use   : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");

//				Thread.sleep(5000L);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
