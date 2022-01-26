package kr.hs.dgsw.java.noticeboard.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "user_name")
    private String name;

    @CreationTimestamp
    @Column(name = "write_time")
    private Timestamp time;

    public BoardEntity(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
    }
}