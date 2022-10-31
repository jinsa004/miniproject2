package site.metacoding.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmDto {
    private String receiver;
    private String sender;
    private String alarmMessage;
}
