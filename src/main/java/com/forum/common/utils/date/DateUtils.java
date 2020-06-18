package com.forum.common.utils.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author: zhaohuan
 */
public class DateUtils {

    //获取当前日期 格式：2020-05-06
    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    //获取当前时间 格式：15:10:10.548151
    public static LocalTime getCurrentTime(){
        return LocalTime.now();
    }

    //获取当前日期+时间 格式：2020-05-06T15:10:10.548181
    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }

    //格式化 时间格式
    public static DateTimeFormatter getFormat(String format){
        return DateTimeFormatter.ofPattern(format);
    }

    // 获取系统当前时间戳: 0获取秒  其余为毫秒
    public static long getCurrentTimestamp(int id){
        if(id == 0){
            return Instant.now().getEpochSecond();
        }
        return Instant.now().toEpochMilli();
    }

    // 获取传入时间的时间戳
    /*public static long getGivenTimestamp(LocalDateTime localDateTime){
        return -1L;
    }*/

    // 比较传入时间先后 firstTime在secondTime之前 返回true
    public static boolean compareDate(Object firstTime, Object secondTime){
           if(firstTime instanceof LocalDateTime && secondTime instanceof LocalDateTime ){
                return ((LocalDateTime) firstTime).isBefore((LocalDateTime)secondTime);
           }else if(firstTime instanceof LocalDate && secondTime instanceof LocalDate ){
                return ((LocalDate) firstTime).isBefore((LocalDate)secondTime);
           }else if(firstTime instanceof LocalTime && secondTime instanceof LocalTime){
               return ((LocalTime) firstTime).isBefore((LocalTime)secondTime);
           }
           throw new DateTimeException("param exception");
    }

    /**比较两个时间的差值
     *LocalDateTime之间的差值 类似 PT1235H10M30S，表示1235小时10分钟30秒
     * LocalDate之间的差值 类似 P1M21D，表示1个月21天
     */
    public static String getTimeDifference(Object firstTime,Object secondTime){
        if(firstTime instanceof LocalDateTime && secondTime instanceof LocalDateTime){
            return Duration.between((LocalDateTime)firstTime, (LocalDateTime)secondTime).toString();
        }else if(firstTime instanceof LocalDate && secondTime instanceof LocalDate){
            return Period.between((LocalDate)firstTime, (LocalDate)secondTime).toString();
        }
        throw new DateTimeException("param exception");
    }

}
