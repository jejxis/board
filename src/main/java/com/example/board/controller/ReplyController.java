package com.example.board.controller;

import com.example.board.dto.ReplyDTO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//모든 메서드의 리턴타입 기본으로 JSON
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;//자동 주입을 위해 final

    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)//{}로 묶은 변수는 @PathVariable로 처리함. 브라우저에서는 /replies/board/100과 같이 100이라는 데이터를 변수로 처리하는 것이 가능함.
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno){
        log.info("bno: "+bno);
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){//@RequestBody가 JSON으로 들어오는 데이터를 해당(여기서는 ReplyDTO)타입 객체로 매핑해준다.
        log.info(replyDTO);
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("RNO: "+rno);
        replyService.remove(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
