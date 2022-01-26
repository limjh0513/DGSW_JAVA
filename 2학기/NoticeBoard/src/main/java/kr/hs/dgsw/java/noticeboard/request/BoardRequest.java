package kr.hs.dgsw.java.noticeboard.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardRequest {
    private String title;
    private String content;
    private String name;
}
