package crawler;


import crawler.service.CrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
    ThreadPool for managing Thread periodically
*/
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
//            scheduler.scheduleAtFixedRate(new MainThread(), 1, ConfigNew.getBatchInterval(), TimeUnit.SECONDS); //scheduleWithFixedDelay
//            scheduler.scheduleWithFixedDelay(new MainThread(), 1, ConfigNew.getBatchInterval(), TimeUnit.HOURS);
            scheduler.scheduleWithFixedDelay(new MainThread(), 1, 60, TimeUnit.SECONDS);
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

/*
    Thread for managing BaseThread and DetailThread
*/
class MainThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(MainThread.class);

    public MainThread(){}

    public void run() {
        try {
            BaseThread baseThread = new BaseThread();
            DetailThread detailThread = new DetailThread();

            baseThread.start();
            detailThread.start();

            logger.debug("Free  : "+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB ");
            logger.debug("Max   : "+ Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB ");
            logger.debug("Total : "+ Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB ");
            logger.debug("Use   : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");

            try{
                baseThread.join();
                detailThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }



//				Thread.sleep(5000L);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

/*
    Thread for crawling using selenium
*/
class BaseThread extends Thread{
    private static Logger logger = LoggerFactory.getLogger(BaseThread.class);
    private CrawlerService crawlerService = new CrawlerService();

    public BaseThread(){}

    public void run() {
        try {
            crawlerService.baseCrawler();

//            logger.debug("Free  : "+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Max   : "+ Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Total : "+ Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Use   : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");
//			Thread.sleep(5000L);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

/*
    Thread for crawling using JSoup
*/
class DetailThread extends Thread{
    private static Logger logger = LoggerFactory.getLogger(DetailThread.class);
    private CrawlerService crawlerService = new CrawlerService();

    public DetailThread(){}

    public void run() {
        try {
            crawlerService.detailCrawler();

//            logger.debug("Free  : "+ Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Max   : "+ Runtime.getRuntime().maxMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Total : "+ Runtime.getRuntime().totalMemory() / (1024 * 1024) + " MB ");
//            logger.debug("Use   : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " MB ");
//			Thread.sleep(5000L);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}