package kr.hs.dgsw.java.noticeboard.service;

import kr.hs.dgsw.java.noticeboard.entity.BoardEntity;
import kr.hs.dgsw.java.noticeboard.repository.NoticeRepository;
import kr.hs.dgsw.java.noticeboard.request.BoardRequest;
import kr.hs.dgsw.java.noticeboard.request.UpdateRequest;
import kr.hs.dgsw.java.noticeboard.response.BoardDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    final NoticeRepository repository;

    public List<BoardDto> getAllList(){
        List<BoardEntity> entities = repository.findAll();

        ArrayList<BoardDto> responseArray = new ArrayList<>();

        entities.forEach(it -> {

            String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(it.getTime());

            BoardDto dto = new BoardDto(it.getId(), it.getTitle(), it.getContent(), it.getName(), date);
            responseArray.add(dto);
        });

        return responseArray;
    }

    public void insertBoard(BoardRequest request){
        BoardEntity entity = new BoardEntity(request.getTitle(), request.getContent(), request.getName());
        repository.save(entity);
    }

    public void deleteBoard(int id){
        repository.deleteById(id);
    }

    public void updateBoard(int id, UpdateRequest request){
        BoardEntity entity = repository.findById(id).get();

        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        entity.setName(request.getName());

        repository.save(entity);
    }

    public BoardDto detailBoard(int id){
        BoardEntity entity = repository.findById(id).get();
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(entity.getTime());
        BoardDto dto = new BoardDto(entity.getId(), entity.getTitle(), entity.getContent(), entity.getName(), date);

        return dto;
    }
}
