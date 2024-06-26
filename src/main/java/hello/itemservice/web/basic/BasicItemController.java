package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

   // RequiredArgsConstructor가 있으면 자동적으로 생성자를 만들어준다 생략 가능
/*  @Autowired
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }*/


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }


    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // 같은 url 인데 메서드로 기능 구분 해줌 - 자주 이용하는 방식
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){


        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }


   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,
                       Model model){

        // ModelAttribute는 model 객체를 만들어주고 담은 Model에 view에 넣어준다(@ModelAttribute("item")).
        itemRepository.save(item);
       // model.addAttribute("item", item); @ModelAttribute("item") 생략 가능

        return "basic/item";
    }

  //  @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        // ("item") 생략 하실 클래스 명을 가져와 Item -> item으로 바꿔서 model에 담겨준다.
        itemRepository.save(item);
        // model.addAttribute("item", item); @ModelAttribute("item") 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item){

        // @ModelAttribute 생략 가능
        itemRepository.save(item);
        // model.addAttribute("item", item); @ModelAttribute("item") 생략 가능

        return "basic/item";
    }

   // @PostMapping("/add")
    public String addItemV5(Item item){
        // @ModelAttribute 생략 가능
        itemRepository.save(item);
        // model.addAttribute("item", item); @ModelAttribute("item") 생략 가능

        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){

        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }



    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("ItemA", 10000, 10));
        itemRepository.save(new Item("ItemB", 20000, 20));
    }

}
