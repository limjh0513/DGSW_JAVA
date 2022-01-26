package kr.hs.dgsw.java.noticeboard.controller;

import kr.hs.dgsw.java.noticeboard.request.BoardRequest;
import kr.hs.dgsw.java.noticeboard.request.UpdateRequest;
import kr.hs.dgsw.java.noticeboard.response.BoardDto;
import kr.hs.dgsw.java.noticeboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("/")
    public String mainList(Model model) {
        List<BoardDto> dtoList = service.getAllList();
        model.addAttribute("boardList", dtoList);

        return "list";
    }

    @GetMapping("/write")
    public String writePage(){
        return "write";
    }

    @GetMapping("/post/{id}")
    public String detailBoard(@PathVariable int id, Model model){
        BoardDto dto = service.detailBoard(id);
        model.addAttribute("board", dto);

        return "detail";
    }

    @PostMapping("/post/edit/{id}")
    public String update(@PathVariable int id, UpdateRequest request){
        service.updateBoard(id, request);

        return "redirect:/post/" + id;
    }

    @GetMapping("/post/edit/page/{id}")
    public String updatePage(@PathVariable int id ,Model model){
        BoardDto dto = service.detailBoard(id);

        model.addAttribute("id", id);
        model.addAttribute("name", dto.getName());
        model.addAttribute("content", dto.getContent());
        model.addAttribute("title", dto.getTitle());

        return "edit";
    }

    @PostMapping("/post/write")
    public String write(BoardRequest request){
        service.insertBoard(request);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        service.deleteBoard(id);

        return "redirect:/";
    }
}
