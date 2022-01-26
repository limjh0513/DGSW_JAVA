package kr.hs.dgsw.java.noticeboard.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRequest {
    private String title;
    private String content;
    private String name;
}
