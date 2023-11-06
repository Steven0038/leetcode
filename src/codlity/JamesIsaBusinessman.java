package codlity;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int solution(String s) {
        // 將字串拆分成行
        String[] meetings = s.split("\n");

        // 轉換每個會議的開始和結束時間
        MeetingTime[] meetingTimes = new MeetingTime[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            String[] parts = meetings[i].split(" ");
            String[] timeRange = parts[1].split("-");
            LocalTime start = LocalTime.parse(timeRange[0]);
            LocalTime end = LocalTime.parse(timeRange[1]);
            DayOfWeek meetingDay = DayOfWeek.valueOf(parts[0]);
            meetingTimes[i] = new MeetingTime(meetingDay, start, end);
        }

        // 尋找最長的睡眠時段
        Duration maxSleepDuration = Duration.ZERO;
        DayOfWeek currentDay = DayOfWeek.MONDAY;
        LocalTime currentTime = LocalTime.of(0, 0);

        for (MeetingTime meetingTime : meetingTimes) {
            Duration sleepDuration = Duration.between(currentTime, meetingTime.start);
            if (currentDay == meetingTime.day && sleepDuration.compareTo(maxSleepDuration) > 0) {
                maxSleepDuration = sleepDuration;
            }

            currentDay = meetingTime.day;
            currentTime = meetingTime.end;
        }

        // 檢查最後一個睡眠時段（周日的時段）
        Duration sleepDuration = Duration.between(currentTime, LocalTime.of(23, 59));
        if (currentDay == DayOfWeek.SUNDAY && sleepDuration.compareTo(maxSleepDuration) > 0) {
            maxSleepDuration = sleepDuration;
        }

        // 返回以分鐘為單位的最長睡眠時段
        return (int) maxSleepDuration.toMinutes();
    }

    // 定義會議時間的類別
    static class MeetingTime {
        DayOfWeek day;
        LocalTime start;
        LocalTime end;

        public MeetingTime(DayOfWeek day, LocalTime start, LocalTime end) {
            this.day = day;
            this.start = start;
            this.end = end;
        }
    }
}


class Solution2 {
    public static int solution(String s) {
        // 將字串拆分成行
        String[] meetings = s.split("\n");

        // 建立每天的時間表
        Map<String, Integer> timetable = new HashMap<>();
        timetable.put("Mon", 0);
        timetable.put("Tue", 1);
        timetable.put("Wed", 2);
        timetable.put("Thu", 3);
        timetable.put("Fri", 4);
        timetable.put("Sat", 5);
        timetable.put("Sun", 6);

        // 轉換每個會議的開始和結束時間為分鐘數
        int[] startTimes = new int[meetings.length];
        int[] endTimes = new int[meetings.length];
        for (int i = 0; i < meetings.length; i++) {
            String[] parts = meetings[i].split(" ");
            String[] timeRange = parts[1].split("-");
            startTimes[i] = parseTime(timeRange[0]);
            endTimes[i] = parseTime(timeRange[1]);
        }

        // 尋找最長的睡眠時段
        int maxSleepDuration = 0;
        for (int i = 0; i < startTimes.length; i++) {
            int sleepDuration = 0;
            for (int j = 0; j < startTimes.length; j++) {
                if (i != j && (startTimes[j] <= startTimes[i] && startTimes[i] < endTimes[j]
                        || startTimes[j] < endTimes[i] && endTimes[i] <= endTimes[j]
                        || startTimes[i] <= startTimes[j] && endTimes[j] <= endTimes[i])) {
                    sleepDuration = 0; // 若有會議時間重疊，則睡眠時段為0
                    break;
                } else {
                    int duration = (endTimes[i] - startTimes[i] + 1440) % 1440;
                    sleepDuration = Math.max(sleepDuration, duration);
                }
            }
            maxSleepDuration = Math.max(maxSleepDuration, sleepDuration);
        }

        return maxSleepDuration;
    }

    // 將時間轉換為分鐘數
    private static int parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}


