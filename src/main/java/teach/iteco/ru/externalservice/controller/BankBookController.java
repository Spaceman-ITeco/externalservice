package teach.iteco.ru.externalservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teach.iteco.ru.externalservice.model.BankBookDto;
import teach.iteco.ru.externalservice.service.BankBookService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/rest/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;

    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping({"/{id}", "/"})
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bankBookService.findById(id));
    }

    @GetMapping({"/by-user-id/{userId}","/by-user-id/"})
    public ResponseEntity<List<BankBookDto>> getBankBookByUserId(@PathVariable Integer userId, @RequestHeader Map<String, String> headers) {
        log.info("Call with headers: {}", headers);
        return ResponseEntity.ok(bankBookService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bankBookService.create(bankBookDto));
    }

    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.update(bankBookDto);
    }

    @DeleteMapping({"/{id}", "/"})
    public void deleteBankBook(@PathVariable Integer id) {
        bankBookService.delete(id);
    }

    @DeleteMapping({"/by-user-id/{id}", "/by-user-id/"})
    public void deleteBankBookByUserId(@PathVariable Integer id) {
        bankBookService.deleteByUserId(id);
    }


}
