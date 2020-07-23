package org.fcm.alg.example.chapter2;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Random;

/**
 * 某跨国公司总裁分身乏术，正为一大堆会议时间表焦头烂额，希望秘书能够做出合理的安排，能在有限的时间内召开更多的会议
 */
public class MeetingArrangement {
    private static Meeting[] init(int count) {
        Meeting[] meetings = new Meeting[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Meeting meeting = new Meeting();
            meeting.setStart(random.nextInt(23));
            meeting.setEnd(random.nextInt(23 - meeting.getStart()) + meeting.getStart() + 1);
            meetings[i] = meeting;
        }

        return meetings;
    }

    public static void arrangeMeeting(Meeting[] meetings) {
        Meeting pickedMeeting = meetings[0];
        System.out.println(pickedMeeting.toString());
        for (int i = 0; i < meetings.length; i++) {
            if (pickedMeeting.getEnd() <= meetings[i].getStart()) {
                arrangeMeeting(Arrays.copyOfRange(meetings, i, meetings.length));
                break;
            }
        }
    }

    public static void main(String[] args) {
        Meeting[] meetings = init(10);
        Arrays.sort(meetings);
        System.out.println("Meetings = " + Arrays.deepToString(meetings));
        arrangeMeeting(meetings);

    }
}

@Data
@ToString
class Meeting implements Comparable<Meeting> {
    private int start;
    private int end;


    @Override
    public int compareTo(Meeting other) {
        if (this.end == other.end) {
            return this.start - other.end;
        } else
            return this.end - other.end;
    }

}
