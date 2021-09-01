package jpabook.jpashop.controller;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model) {
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book item =(Book) itemService.findOne(itemId);
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setStockQuantity(item.getStockQuantity());
        form.setPrice(item.getPrice());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
        Book book = new Book(); //객체는 새로운 객체지만 id를 가지고 있기때문에 준영속컨테이너가 관리하는 것.
        //준영속 : 영속성 컨테이너가 더 이상 관리하지 않는 엔티티(JPA가 관리를 안해서 업데이트를 안한다.)
        //어떻게 하지? 머지를 사용하거나 준영속 엔티티도 감지하게 만든다(레퍼지토리에 update메소드생성).
        //머지는 쓰면 안되는게 필드가 없으면 null로 채워질 수 있다.
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
        itemService.updateItem(itemId,form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }


}
