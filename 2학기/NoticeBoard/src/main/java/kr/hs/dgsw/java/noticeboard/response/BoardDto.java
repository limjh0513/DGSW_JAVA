package kr.hs.dgsw.java.noticeboard.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {
    private int id;
    private String title;
    private String content;
    private String name;
    private String time;

    public BoardDto() {

    }
}
